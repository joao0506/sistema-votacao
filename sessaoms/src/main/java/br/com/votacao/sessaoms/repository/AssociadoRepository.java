package br.com.votacao.sessaoms.repository;

import br.com.votacao.sessaoms.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, String> {
    Optional<Associado> findBycpf(String cpf);
}
