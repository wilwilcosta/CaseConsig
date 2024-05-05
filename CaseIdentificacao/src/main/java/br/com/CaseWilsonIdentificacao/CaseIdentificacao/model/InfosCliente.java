package br.com.CaseWilsonIdentificacao.CaseIdentificacao.model;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
public class InfosCliente {
    public InfosCliente(ClienteDTO cliente, double valorSolicitado, int numeroParcelas) {
        this.cpf = cliente.getCpf();
        this.convenio = cliente.getConvenio();
        this.segmento = cliente.getSegmento();
        this.correntista = cliente.isCorrentista();
        this.valor_solicitado = valorSolicitado;
        this.numero_parcelas = numeroParcelas;
    }

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")
    @JsonAlias("cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull
    @JsonAlias("convenio")
    private Convenio convenio;

    @Enumerated(EnumType.STRING)
    @Nullable
    @JsonAlias("segmento")
    private Segmento segmento;

    @NotNull
    @JsonAlias("correntista")
    private boolean correntista;

    @NotNull
    @DecimalMin("0.00")
    @JsonAlias("valor_solicitado")
    private double valor_solicitado;

    @NotNull
    @Min(0)
    @JsonAlias("numero_parcelas")
    private int numero_parcelas;

}
