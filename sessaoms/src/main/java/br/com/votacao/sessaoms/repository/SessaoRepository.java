package br.com.votacao.sessaoms.repository;

import br.com.votacao.sessaoms.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, String> {

    @Query(value = "SELECT * FROM Sessao s WHERE s.data_fechamento_sessao < ?1 AND s.is_sessao_encerrada IS FALSE", nativeQuery = true)
    List<Sessao> findAllByDataFechamentoSessao(LocalDateTime dataAtual);

    Sessao findByIdPauta(String idPauta);
}
