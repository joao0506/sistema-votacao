package br.com.votacao.sessaoms.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotacaoDTO {

    private String idSessao;
    private String cpfAssociado;
    private String voto;

}
