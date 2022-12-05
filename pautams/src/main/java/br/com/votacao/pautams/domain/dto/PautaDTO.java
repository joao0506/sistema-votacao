package br.com.votacao.pautams.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PautaDTO {
    @NotEmpty(message = "Descrição da pauta não pode estar vazia!")
    private String descricao;
}
