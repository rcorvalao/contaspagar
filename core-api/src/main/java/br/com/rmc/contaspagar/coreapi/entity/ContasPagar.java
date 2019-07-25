package br.com.rmc.contaspagar.coreapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "contas_pagar", schema = "rmcsys")
@SequenceGenerator(name = "cntpagId", sequenceName = "rmcsys.cntpag_SEQ", allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ContasPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cntpagId")
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "NOME", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "VALOR_ORIGINAL", nullable = false)
    private BigDecimal valorOriginal;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private Date dataVencimento;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private Date dataPagamento;

    @NotNull
    @Column(name = "MULTA", nullable = false)
    private BigDecimal multa;

    @NotNull
    @Column(name = "JUROS_DIA", nullable = false)
    private BigDecimal jurosDia;

}
