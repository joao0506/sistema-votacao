package br.com.votacao.sessaoms.repository;

import br.com.votacao.sessaoms.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, String> {
}
