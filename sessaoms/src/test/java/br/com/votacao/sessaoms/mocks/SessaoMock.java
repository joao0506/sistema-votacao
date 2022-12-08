package br.com.votacao.sessaoms.mocks;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.utils.UUIDGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessaoMock {

    public Sessao getSessao(){
        return new Sessao(UUIDGenerator.generateUUID(), LocalDateTime.of(2022, 12,07, 20, 00,00),
                LocalDateTime.of(2022, 12,07, 20, 05,00), "123", false, new ArrayList<>());
    }

}
