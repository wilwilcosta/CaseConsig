package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "simulacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Simulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    private String cpf;

    @NotNull
    private Date data_contratacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @NotNull
    @Valid()
    private double valor_solicitado;

    @NotNull
    private double taxa_aplicada;

    @NotNull
    private int quantidade_parcelas;

    @NotNull
    private double valor_total;

    @NotNull
    private double valor_parcela;
}
