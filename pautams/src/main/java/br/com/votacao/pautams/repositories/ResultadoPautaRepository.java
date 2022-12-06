package br.com.votacao.pautams.repositories;

import br.com.votacao.pautams.domain.ResultadoPauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoPautaRepository extends JpaRepository<ResultadoPauta, String> {
}
