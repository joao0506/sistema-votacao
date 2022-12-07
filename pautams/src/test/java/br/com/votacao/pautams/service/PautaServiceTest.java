package br.com.votacao.pautams.service;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.mocks.PautaMock;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.services.PautaService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(pautaService, "URL_CRIAR_SESSAO", "http://localhost:8080/");
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

}
