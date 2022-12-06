package br.com.votacao.sessaoms.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Voto {

    @Id
    private String id;

    @ManyToOne
    private Sessao sessao;

    @ManyToOne
    private Associado associado;

    private String voto;
}
