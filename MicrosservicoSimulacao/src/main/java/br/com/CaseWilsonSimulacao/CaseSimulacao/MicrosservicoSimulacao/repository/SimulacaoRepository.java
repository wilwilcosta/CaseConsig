package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.repository;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
}
