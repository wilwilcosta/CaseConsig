package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.controller;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Convenio;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.InfosCliente;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Segmento;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest

class SimulacaoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String cpf = "111.111.111-11";
    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllSimulacoes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/simulacao")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createSimulacao() throws Exception {
        InfosCliente infosCliente = new InfosCliente();
        infosCliente.setCpf(cpf);
        infosCliente.setConvenio(Convenio.EP);
        infosCliente.setCorrentista(true);
        infosCliente.setSegmento(Segmento.Person);
        infosCliente.setValor_solicitado(1000.00);
        infosCliente.setNumero_parcelas(10);

        mockMvc.perform(MockMvcRequestBuilders.post("/simulacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(infosCliente))
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }
}