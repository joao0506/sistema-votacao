package br.com.votacao.sessaoms.services;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.Voto;
import br.com.votacao.sessaoms.domain.dto.SessaoDTO;
import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
import br.com.votacao.sessaoms.repository.SessaoRepository;
import br.com.votacao.sessaoms.repository.VotoRepository;
import br.com.votacao.sessaoms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private VotoRepository votoRepository;

    public Sessao salvarSessao(Sessao sessao){
        return sessaoRepository.save(sessao);
    }

    public Sessao buscarSessaoPorId(String id) throws Exception {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        return sessao.orElseThrow(() -> new Exception("Sessão não encontrada!"));
    }

    public Sessao fromDTO(SessaoDTO sessaoDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataEncerramentoDaSessao = dataAtual.plusMinutes(sessaoDTO.getDuracaoSessao());
        return new Sessao(UUIDGenerator.generateUUID(), dataAtual.minusSeconds(dataAtual.getSecond()), dataEncerramentoDaSessao.minusSeconds(dataEncerramentoDaSessao.getSecond()), sessaoDTO.getIdPauta(), false, new ArrayList<>());
    }

    public void validarSessao(Sessao sessao) {
        if (sessao.getIsSessaoEncerrada() || LocalDateTime.now().isAfter(sessao.getDataFechamentoSessao()))
            throw new ValidacoesVotoException("Sessão já encerrada!");
    }

    public List<Sessao> buscarSessoesEncerradas(LocalDateTime dataAtual) {
        return sessaoRepository.findAllByDataFechamentoSessao(dataAtual);
    }

    public void encerrarSessoes(List<Sessao> sessoes) {
        sessoes.forEach(sessao -> {

            computarResultado(sessao);
        });
    }

    private void computarResultado(Sessao sessao) {
        Integer votosSim = votoRepository.findAllVotosBySessaoAndVoto(sessao, "1").size();
        Integer votosNao = votoRepository.findAllVotosBySessaoAndVoto(sessao, "0").size();

        if (votosSim > votosNao)
            System.out.println("Resultado da pauta"+sessao.getIdPauta()+" Sim");
        else if (votosSim < votosNao)
            System.out.println("Resultado da pauta"+sessao.getIdPauta()+" Não");
        else
            System.out.println("Sem resultado definido");

    }

}
