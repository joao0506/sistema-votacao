package br.com.votacao.sessaoms.services;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.repository.AssociadoRepository;
import br.com.votacao.sessaoms.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado salvarAssociado(Associado associado){
        return associadoRepository.save(associado);
    }

    public Associado buscarAssociadoPorCPF(String cpf){
        Optional<Associado> associado = associadoRepository.findBycpf(cpf);
        return associado.orElse(null);
    }

    public Associado fromDTO(String cpf){
        cpf =  removerPontuacaoCpf(cpf);
        return new Associado(UUIDGenerator.generateUUID(), cpf.trim(), new ArrayList<>());
    }

    public String removerPontuacaoCpf(String cpf) {
        while (cpf.contains(".")) cpf = cpf.replace(".", "");
        while (cpf.contains("-")) cpf = cpf.replace("-", "");
        return cpf;
    }

}
