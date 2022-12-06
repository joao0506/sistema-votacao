package br.com.votacao.sessaoms.services;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.SessaoDTO;
import br.com.votacao.sessaoms.repository.SessaoRepository;
import br.com.votacao.sessaoms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;

    public Sessao salvarSessao(Sessao sessao){
        return sessaoRepository.save(sessao);
    }

    public Sessao fromDTO(SessaoDTO sessaoDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataEncerramentoDaSessao = dataAtual.plusMinutes(sessaoDTO.getDuracaoSessao());
        return new Sessao(UUIDGenerator.generateUUID(), dataAtual.minusSeconds(dataAtual.getSecond()), dataEncerramentoDaSessao.minusSeconds(dataEncerramentoDaSessao.getSecond()), sessaoDTO.getIdPauta(), false);
    }
}
