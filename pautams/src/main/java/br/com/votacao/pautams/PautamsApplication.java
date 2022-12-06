package br.com.votacao.pautams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PautamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PautamsApplication.class, args);
	}

}
