package br.com.votacao.pautams.kafka;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PautaConsumer {

    @KafkaListener(topics = "resultado.pauta", groupId = "pauta")
    public void resultadoVotacaoPauta(String message){
        JSONObject resultado = new JSONObject(message.toString());
        System.out.println(resultado);
    }

}
