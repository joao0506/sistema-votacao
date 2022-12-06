package br.com.votacao.sessaoms.resources;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.SessaoDTO;
import br.com.votacao.sessaoms.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/sessao")
public class SessaoResource {

    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/criarSessao")
    public ResponseEntity<?> criarSessao(@RequestBody SessaoDTO sessaoDTO){
        Sessao sessao = sessaoService.fromDTO(sessaoDTO);
        sessaoService.salvarSessao(sessao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(sessao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
