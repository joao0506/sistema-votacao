package br.com.votacao.sessaoms.service;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.mocks.AssociadoMock;
import br.com.votacao.sessaoms.repository.AssociadoRepository;
import br.com.votacao.sessaoms.services.AssociadoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceTest {

    @InjectMocks
    AssociadoService associadoService;

    @Mock
    AssociadoRepository associadoRepository;

    Associado associado;

    @Before
    public void setUp(){
        associado = new AssociadoMock().getAssociado();
    }

    @Test
    public void deveInserirAssociado(){
        associadoService.salvarAssociado(associado);

        verify(associadoRepository, times(1)).save(associado);
    }

    @Test
    public void deveBuscarAssociadoPorCPF(){
        when(associadoRepository.findBycpf(associado.getCpf())).thenReturn(Optional.of(associado));

        Associado associadoFromRepository = associadoService.buscarAssociadoPorCPF(associado.getCpf());

        Assert.assertNotNull(associadoFromRepository);
    }

    @Test
    public void deveRetornarNullAoBuscarAssociadoPorCPF(){
        Associado associadoFromRepository = associadoService.buscarAssociadoPorCPF(associado.getCpf());

        Assert.assertNull(associadoFromRepository);
    }

    @Test
    public void deveCriarAssociadoFromDTO(){
        Associado associado = associadoService.fromDTO("123.456.789-00");
        Assert.assertNotNull(associado);
    }

    @Test
    public void deveRemoverPontuacaoDoCPF(){
        String cpfSemPontuacao = associadoService.removerPontuacaoCpf("123.456.789-00");

        Assert.assertEquals("12345678900", cpfSemPontuacao);
    }
}
