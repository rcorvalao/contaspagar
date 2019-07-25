package br.com.rmc.contaspagar.coreapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContasPagarDto {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private BigDecimal valorOriginal;
    @NotNull
    private Date dataVencimento;
    @NotNull
    private Date dataPagamento;

}
