package br.com.rmc.contaspagar.coreapi.service;


import br.com.rmc.contaspagar.coreapi.dto.ContasPagarDto;
import br.com.rmc.contaspagar.coreapi.dto.ContasPagarListagemDto;
import br.com.rmc.contaspagar.coreapi.entity.ContasPagar;
import br.com.rmc.contaspagar.coreapi.exception.BusinessException;
import br.com.rmc.contaspagar.coreapi.repository.ContasPagarRepository;
import br.com.rmc.contaspagar.coreapi.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
            ContasPagarListagemDto dto = convertContasPagarListagemDto(conta);
            lista.add(dto);
        });

        return lista;
    }

    public ContasPagarListagemDto buscarContasPagar(Long id) {
        ContasPagarListagemDto dto = null;

        if (id != null) {
            Optional<ContasPagar> conta = this.contasPagarRepository.findById(id);
            if (conta.isPresent()) {
                dto = convertContasPagarListagemDto(conta.get());
            }
        }

        return dto;
    }

    public ContasPagarDto criarContaPagar(ContasPagarDto dto) {
        ContasPagar conta = new ContasPagar();
        this.carregarContaEntity(dto, conta);

        this.calcularMultaJuros(conta);

        ContasPagar persistido = this.contasPagarRepository.save(conta);
        if (persistido != null) {
            dto.setId(persistido.getId());
        }

        return dto;
    }

    public ContasPagarDto alterarContaPagar(Long id, ContasPagarDto dto) {
        if (id == null) {
            throw new BusinessException("Id é obrigatório!");
        }

        Optional<ContasPagar> contaOpt = this.contasPagarRepository.findById(id);

        if (!contaOpt.isPresent()) {
            throw new BusinessException("Conta não encontrada!");
        }

        dto.setId(id);
        ContasPagar conta = contaOpt.get();
        this.carregarContaEntity(dto, conta);
        this.calcularMultaJuros(conta);

        this.contasPagarRepository.save(conta);
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
        BigDecimal juros = (jurosDia.multiply(new BigDecimal(diasAtraso))).setScale(2, RoundingMode.HALF_UP);

        BigDecimal jurosTotais = (valorOriginal.multiply(juros).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal multaTotais = (valorOriginal.multiply(multa).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);

        return (valorOriginal.add(multaTotais).add(jurosTotais)).setScale(2, RoundingMode.HALF_UP);
    }






    private ContasPagarListagemDto convertContasPagarListagemDto(ContasPagar conta) {
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
        return dto;
    }

    private void carregarContaEntity(ContasPagarDto dto, ContasPagar conta) {
        conta.setNome(dto.getNome());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setValorOriginal(dto.getValorOriginal());
    }

}
