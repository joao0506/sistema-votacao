package br.com.votacao.sessaoms.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Sessao {
    @Id
    private String id;
    private LocalDateTime dataAberturaSessao;
    private LocalDateTime dataFechamentoSessao;
    @Column(unique = true)
    private String idPauta;

    private Boolean isSessaoEncerrada;

    @OneToMany(mappedBy = "sessao")
    private List<Voto> votos;
}
