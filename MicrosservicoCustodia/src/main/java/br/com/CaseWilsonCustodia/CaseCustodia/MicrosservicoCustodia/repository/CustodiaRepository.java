package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.repository;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.Custodia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustodiaRepository extends JpaRepository<Custodia, Long> {
}
