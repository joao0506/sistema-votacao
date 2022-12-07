package br.com.votacao.pautams.resource;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.mocks.PautaMock;
import br.com.votacao.pautams.resources.PautaResource;
import br.com.votacao.pautams.services.PautaService;
import br.com.votacao.pautams.utils.RealizarRequisicao;
import org.json.JSONArray;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PautaResource.class)
@ContextConfiguration(classes = {PautaResource.class})
@AutoConfigureMockMvc
public class PautaResourceTest {

    @Autowired
    PautaResource pautaResource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PautaService pautaService;

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    private static String path = "/pauta/";

    private RealizarRequisicao realizarRequisicao;

    private static Pauta pauta;

    private static List<Pauta> pautas;

    private JSONObject jsonBody;

    @Before
    public void setUp() throws JSONException {
        realizarRequisicao = new RealizarRequisicao();
        realizarRequisicao.setMockMvc(mockMvc);

        PautaMock pautaMock = new PautaMock();
        pauta = pautaMock.getPauta();
        pautas = pautaMock.getPautas();
        jsonBody = pautaMock.getPautaRequest();
    }

    @Test
    public void deveTestarInsercaoPauta() throws Exception {
        when(pautaService.fromDTO(any())).thenReturn(pauta);

        MockHttpServletResponse response = realizarRequisicao.doMockPost(path, jsonBody, params);

        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void deveTestarAberturaDaSessao() throws Exception {
        params.add("pauta", jsonBody.getString("descricao"));
        params.add("duracaoSessao", "5");

        MockHttpServletResponse response = realizarRequisicao.doMockPost(path+"/abrir-sessao", new JSONObject() ,params);

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void deveListarTodasAsPautas() throws Exception {
        when(pautaService.listarTodasAsPautas(any(), any())).thenReturn(new PageImpl<>(pautas));

        MockHttpServletResponse response = realizarRequisicao.doMockGet(path);

        JSONArray pautas = realizarRequisicao.getJSONArrayFromResponse(response);

        Assert.assertNotNull(response);
        Assert.assertEquals(5, pautas.length());
    }

    @Test
    public void deveListarPautaPorId() throws Exception {
        when(pautaService.listarPautaPorId(any())).thenReturn(pauta);

        MockHttpServletResponse response = realizarRequisicao.doMockGet(path+"/123");

        JSONObject pauta = realizarRequisicao.getJSONObjectFromResponse(response);

        Assert.assertNotNull(response);
        Assert.assertNotNull(pauta);
        Assert.assertEquals("Pauta Teste", pauta.getString("descricao"));
    }

}
