package br.com.votacao.sessaoms.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResultadoProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarResultadoVotacao(String resultado){
        this.kafkaTemplate.send("resultado.pauta", resultado);
    }

}
