package br.com.votacao.sessaoms.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Associado {
    @Id
    private String id;

    @Column(unique = true)
    private String cpf;
}
