package br.com.votacao.sessaoms.resources;

import br.com.votacao.sessaoms.domain.Voto;
import br.com.votacao.sessaoms.domain.dto.VotacaoDTO;
import br.com.votacao.sessaoms.services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/votar")
public class VotacaoResource {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<?> votar(@RequestBody VotacaoDTO votacaoDTO) throws Exception {
        Voto voto = votoService.computarVoto(votacaoDTO);

        return ResponseEntity.ok("Voto computado com sucesso!");
    }

}
