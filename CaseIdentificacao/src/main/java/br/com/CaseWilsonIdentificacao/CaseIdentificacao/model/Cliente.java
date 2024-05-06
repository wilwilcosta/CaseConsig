package br.com.CaseWilsonIdentificacao.CaseIdentificacao.model;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "/^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$/")
    private String cpf;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotNull
    @NotBlank
    private boolean correntista;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Segmento segmento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    public ClienteDTO ConvertToDTO(){

        var dto = new ClienteDTO(){};
        dto.setId(id);
        dto.setCpf(cpf);
        dto.setNome(nome);
        dto.setCorrentista(correntista);
        dto.setSegmento(segmento);
        dto.setConvenio(convenio);

        return dto;
    }
}
