package br.com.votacao.sessaoms.resources;

import br.com.votacao.sessaoms.domain.Sessao;
import br.com.votacao.sessaoms.domain.dto.SessaoDTO;
import br.com.votacao.sessaoms.exceptions.ValidacoesVotoException;
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

    @PostMapping("/criar-sessao")
    public ResponseEntity<?> criarSessao(@RequestBody SessaoDTO sessaoDTO) {
        try {
            Sessao sessao = sessaoService.fromDTO(sessaoDTO);

            sessaoService.salvarSessao(sessao);
            return ResponseEntity.ok(sessao.getId());
        } catch (ValidacoesVotoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
