package br.com.CaseWilsonIdentificacao.CaseIdentificacao.repository;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(String cpf);
}
