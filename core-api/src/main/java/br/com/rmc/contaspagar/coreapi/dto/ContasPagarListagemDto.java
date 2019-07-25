package br.com.rmc.contaspagar.coreapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContasPagarListagemDto {

    private Long id;
    private String nome;
    private BigDecimal valorOriginal;
    private BigDecimal valorCorrigido;
    private Long quantidadeDiasAtraso;
    private Date dataPagamento;


}
