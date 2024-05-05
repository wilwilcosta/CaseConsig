package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "custodia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Custodia {

    public Custodia(InfosCustodia infosCustodia) {
        this.data_contrato = infosCustodia.getData_contrato();
        this.id_simulacao = infosCustodia.getId_simulacao();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date data_contrato;

    @NotNull
    private Long id_simulacao;

}
