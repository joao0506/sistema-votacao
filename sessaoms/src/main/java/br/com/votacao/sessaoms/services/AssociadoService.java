package br.com.votacao.sessaoms.services;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.repository.AssociadoRepository;
import br.com.votacao.sessaoms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado salvarAssociado(Associado associado){
        return associadoRepository.save(associado);
    }

    public Associado fromDTO(String cpf){
        return new Associado(UUIDGenerator.generateUUID(), cpf.trim(), new ArrayList<>());
    }

}
