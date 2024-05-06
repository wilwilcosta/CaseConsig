package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.service;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTO;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Convenio;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.InfosCliente;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Segmento;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Simulacao;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.repository.SimulacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SimulacaoServiceTest {

    private String cpf = "111.111.111-11";

    @MockBean
    SimulacaoRepository repository;

    @Autowired
    SimulacaoService simulacaoService;

    ObjectMapper om = new ObjectMapper();

    @Test
    public void getAll() throws JsonProcessingException {
        Pageable pageable = PageRequest.of(0, 20);
        var result = simulacaoService.getAll(pageable);
        Assert.assertTrue(result.hasContent());
    }

    @Test
    public void should_create_simulacao_ok_varejo() {
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Varejo);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(10);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(201), resultado.getStatusCode());
    }

    @Test
    public void should_create_simulacao_nok_parcelas_a_mais_varejo(){
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Varejo);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(25);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(400), resultado.getStatusCode());
    }@Test
    public void should_create_simulacao_ok_uniclass() {
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Uniclass);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(10);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(201), resultado.getStatusCode());
    }

    @Test
    public void should_create_simulacao_nok_parcelas_a_mais_uniclass(){
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Uniclass);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(37);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(400), resultado.getStatusCode());
    }
    @Test
    public void should_create_simulacao_ok_person() {
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Person);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(10);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(201), resultado.getStatusCode());
    }

    @Test
    public void should_create_simulacao_nok_parcelas_a_mais_person(){
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Person);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(49);

        var resultado = simulacaoService.createSimulacao(infosCliente);
        Assert.assertEquals(HttpStatusCode.valueOf(400), resultado.getStatusCode());
    }
}