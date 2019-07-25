package br.com.rmc.contaspagar.coreapi.service;


import br.com.rmc.contaspagar.coreapi.dto.ContasPagarDto;
import br.com.rmc.contaspagar.coreapi.dto.ContasPagarListagemDto;
import br.com.rmc.contaspagar.coreapi.entity.ContasPagar;
import br.com.rmc.contaspagar.coreapi.repository.ContasPagarRepository;
import br.com.rmc.contaspagar.coreapi.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ContasPagarService {

    private ContasPagarRepository contasPagarRepository;

    public ContasPagarService(ContasPagarRepository contasPagarRepo) {
        this.contasPagarRepository = contasPagarRepo;
    }

    public List<ContasPagarListagemDto> listarContasPagar() {
        List<ContasPagarListagemDto> lista = new LinkedList<>();

        this.contasPagarRepository.findAll().forEach(conta -> {
            ContasPagarListagemDto dto = new ContasPagarListagemDto();

            dto.setId(conta.getId());
            dto.setNome(conta.getNome());
            dto.setDataPagamento(conta.getDataPagamento());
            dto.setValorOriginal(conta.getValorOriginal());
            Long diasAtraso = DateUtils.diferencaEmDias(conta.getDataVencimento(), conta.getDataPagamento());
            dto.setValorCorrigido(
                this.calcularValorCorrigido(conta.getValorOriginal(), conta.getMulta(), conta.getJurosDia(), diasAtraso)
            );
            dto.setQuantidadeDiasAtraso(diasAtraso);

            lista.add(dto);
        });

        return lista;
    }

    public ContasPagarDto criarContaPagar(ContasPagarDto dto) {
        ContasPagar conta = new ContasPagar();
        conta.setNome(dto.getNome());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setValorOriginal(dto.getValorOriginal());

        this.calcularMultaJuros(conta);

        ContasPagar persistido = this.contasPagarRepository.save(conta);
        if (persistido != null) {
            dto.setId(persistido.getId());
        }

        return dto;
    }

    public void calcularMultaJuros(ContasPagar conta) {
        Long diferenca = DateUtils.diferencaEmDias(conta.getDataVencimento(), conta.getDataPagamento());

        conta.setMulta(BigDecimal.ZERO);
        conta.setJurosDia(BigDecimal.ZERO);

        if (diferenca > 0 && diferenca <= 3) {
            conta.setMulta(new BigDecimal(2));
            conta.setJurosDia(new BigDecimal(0.1));
        } else if (diferenca > 3 && diferenca <= 5) {
            conta.setMulta(new BigDecimal(3));
            conta.setJurosDia(new BigDecimal(0.2));
        } else if (diferenca > 5) {
            conta.setMulta(new BigDecimal(5));
            conta.setJurosDia(new BigDecimal(0.3));
        }
    }

    public BigDecimal calcularValorCorrigido(BigDecimal valorOriginal
            , BigDecimal multa, BigDecimal jurosDia, Long diasAtraso) {
        BigDecimal juros = jurosDia.multiply(new BigDecimal(diasAtraso));

        BigDecimal jurosTotais = valorOriginal.multiply(juros).divide(new BigDecimal(100));
        BigDecimal multaTotais = valorOriginal.multiply(multa).divide(new BigDecimal(100));

        return valorOriginal.add(multaTotais).add(jurosTotais);
    }

}
