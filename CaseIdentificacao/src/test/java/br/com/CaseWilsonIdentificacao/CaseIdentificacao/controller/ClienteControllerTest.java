package br.com.CaseWilsonIdentificacao.CaseIdentificacao.controller;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClienteControllerTest {
    @InjectMocks
    ClienteController controller;

    @Mock
    private ClienteService service;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String cpf = "111.111.111-11";
    private ClienteDTO clienteDTO;
    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void Should_getall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
    }


    @Test
    void Should_return_existing_cliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+ cpf).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_return_bad_request_cliente_cpf_not_valid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+ "123.123.123_11").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_no_content() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+ "123.123.123-11").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void simulate() {
        var response = controller.getByCpf(cpf);
        assertTrue(response.getStatusCode() == HttpStatusCode.valueOf(201));
    }
}