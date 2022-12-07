package br.com.votacao.pautams.service;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.mocks.PautaMock;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.services.PautaService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

    @InjectMocks
    PautaService pautaService;

    @Mock
    PautaRepository pautaRepository;

    @Mock
    RestTemplate restTemplate;

    private static Pauta pauta;

    private static List<Pauta> pautas;
    public static final String URL_CRIAR_SESSAO = "http://localhost:8081/sessao-ms/sessao/criar-sessao";

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(pautaService, "URL_CRIAR_SESSAO", URL_CRIAR_SESSAO);

        PautaMock pautaMock = new PautaMock();
        pauta = pautaMock.getPauta();
        pautas = pautaMock.getPautas();
    }

    @Test
    public void deveTestarInsercaoPauta(){
        pautaService.salvarPauta(pauta);

        verify(pautaRepository, times(1)).save(any());
    }

    @Test
    public void deveTestarFromDTO(){
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setDescricao("Teste Pauta");

        Pauta pauta = pautaService.fromDTO(pautaDTO);

        Assert.assertNotNull(pauta);
        Assert.assertEquals("Teste Pauta", pauta.getDescricao());
    }

    @Test
    public void deveListarTodasAsPautas(){
        when(pautaRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(pautas));

        Page<Pauta> pautas = pautaService.listarTodasAsPautas("0", "5");

        verify(pautaRepository, times(1)).findAll(PageRequest.of(0, 5));
        Assert.assertNotNull(pautas);
    }

    @Test
    public void deveListarPautaPorId(){
        when(pautaRepository.findById(anyString())).thenReturn(Optional.of(pauta));

        Pauta pauta = pautaService.listarPautaPorId("123");
        verify(pautaRepository, times(1)).findById(anyString());
        Assert.assertNotNull(pauta);
    }

    @Test
    public void deveCriarSessaoParaPauta() throws JSONException {
        when(restTemplate.postForObject(URL_CRIAR_SESSAO, getHttpEntityCriarSessao(), String.class)).thenReturn("123");

        pautaService.criarSessao("123", 5);
    }

    private HttpEntity<String> getHttpEntityCriarSessao() throws JSONException {
        JSONObject body = new JSONObject();
        body.put("idPauta", "123");
        body.put("duracaoSessao", 5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body.toString(), headers);
        return entity;
    }

}
