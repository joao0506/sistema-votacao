package br.com.votacao.sessaoms.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
}
