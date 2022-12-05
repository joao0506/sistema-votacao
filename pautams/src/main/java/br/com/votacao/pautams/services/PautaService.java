package br.com.votacao.pautams.services;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta salvarPauta(Pauta pauta){
        return pautaRepository.save(pauta);
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        String idPauta = UUIDGenerator.generateUUID();
        return new Pauta(idPauta, pautaDTO.getDescricao().trim());
    }
}
