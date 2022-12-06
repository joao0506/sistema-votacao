package br.com.votacao.sessaoms.repository;

import br.com.votacao.sessaoms.domain.Associado;
import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, String> {
    Optional<Voto> findBySessaoAndAssociado(Sessao sessao, Associado associado);
}
