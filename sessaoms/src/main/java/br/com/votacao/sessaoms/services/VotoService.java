package br.com.votacao.sessaoms.services;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.Voto;
import br.com.votacao.sessaoms.domain.dto.VotacaoDTO;
import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
import br.com.votacao.sessaoms.repository.VotoRepository;
import br.com.votacao.sessaoms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    public VotoRepository votoRepository;

    @Autowired
    public SessaoService sessaoService;

    @Autowired
    public AssociadoService associadoService;

    public Voto salvarVoto(Voto voto){
        return votoRepository.save(voto);
    }

    public Voto computarVoto(VotacaoDTO votacaoDTO) throws Exception {
        Sessao sessao = obterSessaoDoVoto(votacaoDTO.getIdSessao());
        sessaoService.validarSessao(sessao);
        Associado associado = obterAssociadoDoVoto(votacaoDTO.getCpfAssociado());
        associadoPossuiVotoNaSessao(associado, sessao);

        return salvarVoto(mapVoto(sessao, associado, votacaoDTO.getVoto()));
    }

    private void associadoPossuiVotoNaSessao(Associado associado, Sessao sessao) throws Exception {
        Optional<Voto> voto = votoRepository.findBySessaoAndAssociado(sessao, associado);
        if (voto.isPresent())
            throw new ValidacoesVotoException("Associado já possui voto nesta sessão!");
    }

    private Sessao obterSessaoDoVoto(String idSessao) throws Exception {
        return sessaoService.buscarSessaoPorId(idSessao);
    }

    private Associado obterAssociadoDoVoto(String cpfAssociado){
        Associado associado = associadoService.buscarAssociadoPorCPF(cpfAssociado);
        if (associado == null){
            associado = associadoService.fromDTO(cpfAssociado);
            associadoService.salvarAssociado(associado);
        }
        return associado;
    }

    private Voto mapVoto(Sessao sessao, Associado associado, String voto){
        return new Voto(UUIDGenerator.generateUUID(), sessao, associado, voto);
    }

}
