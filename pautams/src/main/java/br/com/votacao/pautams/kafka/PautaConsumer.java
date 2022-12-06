package br.com.votacao.pautams.kafka;

import br.com.votacao.pautams.domain.ResultadoPauta;
import br.com.votacao.pautams.services.ResultadoPautaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PautaConsumer {

    @Autowired
    private ResultadoPautaService resultadoPautaService;

    @KafkaListener(topics = "resultado.pauta", groupId = "pauta")
    public void resultadoVotacaoPauta(String message){
        JSONObject resultado = new JSONObject(message);
        resultadoPautaService.inserirResultadoPauta(resultado);
    }

}
