package br.com.votacao.pautams.service;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.ResultadoPauta;
import br.com.votacao.pautams.mocks.PautaMock;
import br.com.votacao.pautams.mocks.ResultadoPautaMock;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.repositories.ResultadoPautaRepository;
import br.com.votacao.pautams.services.PautaService;
import br.com.votacao.pautams.services.ResultadoPautaService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoPautaServiceTest {

    @InjectMocks
    ResultadoPautaService resultadoPautaService;

    @Mock
    ResultadoPautaRepository resultadoPautaRepository;

    @Mock
    PautaService pautaService;

    private ResultadoPauta resultadoPauta;
    private Pauta pauta;

    @Before
    public void setUp(){
        resultadoPauta = new ResultadoPautaMock().getResultadoPauta();
        pauta = new PautaMock().getPauta();
    }

    @Test
    public void deveInserirResultadoDaPauta() throws JSONException {
        when(pautaService.listarPautaPorId(anyString())).thenReturn(pauta);

        resultadoPautaService.inserirResultadoPauta(new ResultadoPautaMock().montarResultadPautaJson());

        verify(resultadoPautaRepository, times(1)).save(any());

    }

}
