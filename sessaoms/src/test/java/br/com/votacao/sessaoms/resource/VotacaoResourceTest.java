package br.com.votacao.sessaoms.resource;

import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
import br.com.votacao.sessaoms.resources.VotacaoResource;
import br.com.votacao.sessaoms.services.VotoService;
import br.com.votacao.sessaoms.utils.RealizarRequisicao;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(VotacaoResource.class)
@ContextConfiguration(classes = {VotacaoResource.class})
@AutoConfigureMockMvc
public class VotacaoResourceTest {

    @Autowired
    VotacaoResource votacaoResource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VotoService votoService;

    private RealizarRequisicao realizarRequisicao;

    private static String path = "/votar/";

    @Before
    public void setUp(){
        realizarRequisicao = new RealizarRequisicao();
        realizarRequisicao.setMockMvc(mockMvc);

    }

    @Test
    public void deveComputarVoto() throws Exception {
        MockHttpServletResponse response = realizarRequisicao.doMockPost(path, getVotacaoRequest(), new LinkedMultiValueMap<>());

        Assert.assertNotNull(response);
        Assert.assertEquals("Voto computado com sucesso!", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void deveLancarExceptionAoInserirVoto() throws Exception {
        when(votoService.computarVoto(any())).thenThrow(new ValidacoesVotoException("Erro ao computar voto!"));

        MockHttpServletResponse response = realizarRequisicao.doMockPost(path, getVotacaoRequest(), new LinkedMultiValueMap<>());
        Assert.assertNotNull(response);
        Assert.assertEquals("Erro ao computar voto!", response.getContentAsString(StandardCharsets.UTF_8));
    }

    private JSONObject getVotacaoRequest() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idSessao", "12345");
        json.put("cpfAssociado", "12345678900");
        json.put("voto", "1");

        return json;
    }

}
