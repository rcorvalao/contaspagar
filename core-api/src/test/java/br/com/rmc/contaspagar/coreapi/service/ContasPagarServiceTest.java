package br.com.rmc.contaspagar.coreapi.service;


import br.com.rmc.contaspagar.coreapi.entity.ContasPagar;
import br.com.rmc.contaspagar.coreapi.repository.ContasPagarRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ContasPagarServiceTest {

    @InjectMocks
    private ContasPagarService contasPagarService;

    @Mock
    private ContasPagarRepository contasPagarRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validaBeanComSucesso() throws Exception {

        assertNotNull(new ContasPagarService(contasPagarRepository));

    }

    @Test
    public void deveCalcularMultaJurosParaPagamentoVencimentosIguais() {
        ContasPagar conta = new ContasPagar();
        conta.setDataVencimento(new Date());
        conta.setDataPagamento(new Date());

        contasPagarService.calcularMultaJuros(conta);

        Assert.assertEquals(BigDecimal.ZERO, conta.getJurosDia());
        Assert.assertEquals(BigDecimal.ZERO, conta.getMulta());
    }

    @Test
    public void deveCalcularMultaJurosParaPagamentoMenorVencimentos() {
        ContasPagar conta = new ContasPagar();
        conta.setDataVencimento(new Date());
        conta.setDataPagamento(new Date());

        contasPagarService.calcularMultaJuros(conta);

        Assert.assertEquals(BigDecimal.ZERO, conta.getJurosDia());
        Assert.assertEquals(BigDecimal.ZERO, conta.getMulta());
    }

    @Test
    public void deveCalcularMultaJurosParaPagamentoMaiorVencimentosEntre1e3() {
        ContasPagar conta = new ContasPagar();
        Calendar data1 = Calendar.getInstance();
        conta.setDataVencimento(data1.getTime());
        Calendar data2 = Calendar.getInstance();
        data2.add(Calendar.DAY_OF_MONTH, 2);
        conta.setDataPagamento(data2.getTime());

        contasPagarService.calcularMultaJuros(conta);

        Assert.assertEquals(new BigDecimal(0.1), conta.getJurosDia());
        Assert.assertEquals(new BigDecimal(2), conta.getMulta());
    }

    @Test
    public void deveCalcularMultaJurosParaPagamentoMaiorVencimentosEntre4e5() {
        ContasPagar conta = new ContasPagar();
        Calendar data1 = Calendar.getInstance();
        conta.setDataVencimento(data1.getTime());
        Calendar data2 = Calendar.getInstance();
        data2.add(Calendar.DAY_OF_MONTH, 4);
        conta.setDataPagamento(data2.getTime());

        contasPagarService.calcularMultaJuros(conta);

        Assert.assertEquals(new BigDecimal(0.2), conta.getJurosDia());
        Assert.assertEquals(new BigDecimal(3), conta.getMulta());
    }

    @Test
    public void deveCalcularMultaJurosParaPagamentoMaiorVencimentosMaiorQue5() {
        ContasPagar conta = new ContasPagar();
        Calendar data1 = Calendar.getInstance();
        conta.setDataVencimento(data1.getTime());
        Calendar data2 = Calendar.getInstance();
        data2.add(Calendar.DAY_OF_MONTH, 7);
        conta.setDataPagamento(data2.getTime());

        contasPagarService.calcularMultaJuros(conta);

        Assert.assertEquals(new BigDecimal(0.3), conta.getJurosDia());
        Assert.assertEquals(new BigDecimal(5), conta.getMulta());
    }

    @Test
    public void deveCalcularValorCorrigido() {
        BigDecimal valorOriginal = new BigDecimal(1000);
        BigDecimal multa = new BigDecimal(2);
        BigDecimal jurosDia = new BigDecimal(0.1);
        Long diasAtraso = 2L;


        BigDecimal value = contasPagarService.calcularValorCorrigido(valorOriginal, multa, jurosDia, diasAtraso);

        Assert.assertEquals(new BigDecimal(1022).setScale(2, RoundingMode.HALF_UP), value);
    }

}
