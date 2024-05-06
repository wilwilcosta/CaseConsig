package br.com.CaseWilsonIdentificacao.CaseIdentificacao.http;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.InfosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("simulacaoms")
public interface SimulacaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/simulacao")
    ResponseEntity createSimulacao(@RequestBody InfosCliente infosCliente);
}
