package br.com.votacao.sessaoms.repository;

import br.com.votacao.sessaoms.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, String> {
}