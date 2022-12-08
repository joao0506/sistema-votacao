package br.com.votacao.sessaoms.service;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.domain.Voto;
import br.com.votacao.sessaoms.domain.dto.VotacaoDTO;
import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
import br.com.votacao.sessaoms.mocks.AssociadoMock;
import br.com.votacao.sessaoms.mocks.SessaoMock;
import br.com.votacao.sessaoms.mocks.VotoMock;
import br.com.votacao.sessaoms.repository.VotoRepository;
import br.com.votacao.sessaoms.services.AssociadoService;
import br.com.votacao.sessaoms.services.SessaoService;
import br.com.votacao.sessaoms.services.VotoService;
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
public class VotoServiceTest {

    @InjectMocks
    VotoService votoService;

    @Mock
    VotoRepository votoRepository;

    @Mock
    SessaoService sessaoService;

    @Mock
    AssociadoService associadoService;

    Associado associado;

    @Before
    public void setUp(){
        associado = new AssociadoMock().getAssociado();

        when(sessaoService.buscarSessaoPorId(anyString())).thenReturn(new SessaoMock().getSessao());
        when(associadoService.removerPontuacaoCpf(anyString())).thenReturn(new AssociadoMock().getAssociado().getCpf());
        when(associadoService.buscarAssociadoPorCPF(anyString())).thenReturn(associado);
        when(votoRepository.save(any())).thenReturn(new VotoMock().getVoto());

    }

    @Test
    public void deveComputarVoto() throws Exception {
        VotacaoDTO votacaoDTO = getVotacaoDTO();

        Voto voto = votoService.computarVoto(votacaoDTO);

        Assert.assertNotNull(voto);
    }

    @Test
    public void deveTestarInserirAssociadoQuandoNaoEncontrado() throws Exception {
        VotacaoDTO votacaoDTO = getVotacaoDTO();

        when(associadoService.buscarAssociadoPorCPF(anyString())).thenReturn(null);
        when(associadoService.fromDTO(any())).thenReturn(associado);

        Voto voto = votoService.computarVoto(votacaoDTO);

        Assert.assertNotNull(voto);
    }

    @Test(expected = ValidacoesVotoException.class)
    public void deveLancarExcecaoQuandoAssociadoJaVotou() throws Exception {
        VotacaoDTO votacaoDTO = getVotacaoDTO();

        when(votoRepository.findBySessaoAndAssociado(any(), any())).thenReturn(Optional.of(new VotoMock().getVoto()));

        votoService.computarVoto(votacaoDTO);
    }

    public VotacaoDTO getVotacaoDTO(){
        VotacaoDTO votacaoDTO = new VotacaoDTO();
        votacaoDTO.setIdSessao("12345");
        votacaoDTO.setCpfAssociado("12345678900");
        votacaoDTO.setVoto("0");
        return votacaoDTO;
    }

}
