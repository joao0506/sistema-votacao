package br.com.votacao.sessaoms.mocks;

import br.com.votacao.sessaoms.domain.Voto;
import br.com.votacao.sessaoms.utils.UUIDGenerator;

public class VotoMock {

    public Voto getVoto(){
        return new Voto(UUIDGenerator.generateUUID(), new SessaoMock().getSessao(), new AssociadoMock().getAssociado(), "1");
    }

}
