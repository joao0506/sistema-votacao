package br.com.votacao.pautams.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PautaDTO {
    @NotEmpty(message = "Descrição da pauta não pode estar vazia!")
    @Size(min = 2, max = 80, message = "Descrição da pauta deve conter de 2 a 80 caracteres!")
    private String descricao;
}
