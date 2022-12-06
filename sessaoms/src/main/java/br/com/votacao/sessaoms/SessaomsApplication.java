package br.com.votacao.sessaoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SessaomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessaomsApplication.class, args);
	}

}
