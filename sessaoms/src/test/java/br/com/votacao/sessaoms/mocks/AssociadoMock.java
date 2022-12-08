package br.com.votacao.sessaoms.mocks;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.utils.UUIDGenerator;

import java.util.ArrayList;

public class AssociadoMock {

    public Associado getAssociado(){
        return new Associado(UUIDGenerator.generateUUID(), "12345678900", new ArrayList<>());
    }

}
