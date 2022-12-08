package br.com.votacao.sessaoms.resource;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
import br.com.votacao.sessaoms.mocks.SessaoMock;
import br.com.votacao.sessaoms.resources.SessaoResource;
import br.com.votacao.sessaoms.services.SessaoService;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessaoResource.class)
@ContextConfiguration(classes = {SessaoResource.class})
@AutoConfigureMockMvc
public class SessaoResourceTest {

    @Autowired
    SessaoResource sessaoResource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SessaoService sessaoService;

    private RealizarRequisicao realizarRequisicao;

    private static String path = "/sessao/";

    private Sessao sessao;

    @Before
    public void setUp(){
        realizarRequisicao = new RealizarRequisicao();
        realizarRequisicao.setMockMvc(mockMvc);

        sessao = new SessaoMock().getSessao();
    }

    @Test
    public void deveCriarSessao() throws Exception {
        JSONObject request = getJsonSessaoRequest();

        when(sessaoService.fromDTO(any())).thenReturn(sessao);

        MockHttpServletResponse response = realizarRequisicao.doMockPost(path+"/criar-sessao", request, new LinkedMultiValueMap<>());
        System.out.println(response.getContentAsString());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getContentAsString());
    }

    @Test
    public void deveLancarExceptionAoInserirSessao() throws Exception {
        JSONObject request = getJsonSessaoRequest();

        when(sessaoService.fromDTO(any())).thenReturn(sessao);
        when(sessaoService.salvarSessao(sessao)).thenThrow(new ValidacoesVotoException("Erro ao inserir sessão!"));

        MockHttpServletResponse response = realizarRequisicao.doMockPost(path+"/criar-sessao", request, new LinkedMultiValueMap<>());
        Assert.assertNotNull(response);
        Assert.assertEquals("Erro ao inserir sessão!", response.getContentAsString(StandardCharsets.UTF_8));
    }

    private JSONObject getJsonSessaoRequest() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idPauta", "12345");
        json.put("duracaoSessao", 5);
        return json;
    }

}
