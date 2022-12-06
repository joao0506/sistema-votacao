package br.com.votacao.sessaoms.utils;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.services.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    private SessaoService sessaoService;

    @Scheduled(fixedDelay = 60000)
    public void verificarSessoesParaEncerrar() throws InterruptedException {
        LocalDateTime dataAtual = LocalDateTime.now().minusSeconds(LocalDateTime.now().getSecond());
        LOGGER.info(dataAtual + " Buscando sessões para finalizar...");
        List<Sessao> sessoes = sessaoService.buscarSessoesEncerradas(dataAtual);

        if (sessoes.size() > 0) {
            LOGGER.info("Encerrando "+sessoes.size()+" sessão/sessões!");
            sessaoService.encerrarSessoes(sessoes);
        } else LOGGER.info("Não existem sessões para encerrar!");
    }

}