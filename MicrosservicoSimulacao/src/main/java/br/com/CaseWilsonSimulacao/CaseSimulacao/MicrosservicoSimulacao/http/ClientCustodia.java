package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.InfosCustodia;

@FeignClient("custodiams")
public interface ClientCustodia {
    @RequestMapping(method = RequestMethod.POST, value = "/custodia")
    ResponseEntity createCustodia(@RequestBody InfosCustodia infosCustodia);
}
