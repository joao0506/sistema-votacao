package br.com.votacao.pautams.resources;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pauta")
public class PautaResource {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<?> inserirPauta(@Valid @RequestBody PautaDTO pautaDTO){
        Pauta pauta = pautaService.fromDTO(pautaDTO);
        pautaService.salvarPauta(pauta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
