package br.com.votacao.pautams.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ResultadoPauta {

    @Id
    private String id;

    @OneToOne
    @JsonIgnore
    private Pauta pauta;

    private String resultado;
}
