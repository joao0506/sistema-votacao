package br.com.votacao.pautams.repositories;

import br.com.votacao.pautams.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, String> {
}
