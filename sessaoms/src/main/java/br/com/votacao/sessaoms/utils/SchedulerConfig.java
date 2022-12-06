package br.com.votacao.sessaoms.utils;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private SessaoService sessaoService;

    @Scheduled(fixedDelay = 60000)
    public void verificarSessoesParaEncerrar() throws InterruptedException {
        System.out.println(LocalDateTime.now() + " Buscando pautas para finalizar...");
        LocalDateTime dataAtual = LocalDateTime.now();
        List<Sessao> sessoes = sessaoService.buscarSessoesEncerradas(dataAtual);

        sessaoService.encerrarSessoes(sessoes);

    }

}