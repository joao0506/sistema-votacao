package br.com.votacao.pautams.resources;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/abrir-sessao")
    public ResponseEntity<?> inserirSessaoDaPauta(@RequestParam(value = "pauta") String pauta,
                                                  @RequestParam(value = "duracaoSessao", defaultValue = "1") String duracaoSessao){
        return pautaService.criarSessao(pauta, Integer.valueOf(duracaoSessao));
    }

    @GetMapping
    public ResponseEntity<Page<Pauta>> listarTodasAsPautas(@RequestParam(value = "pagina", defaultValue = "0") String pagina,
                                                           @RequestParam(value = "linhasPorPagina", defaultValue = "5") String linhasPorPagina){
        Page<Pauta> pautas = pautaService.listarTodasAsPautas(pagina, linhasPorPagina);
        return ResponseEntity.ok(pautas);
    }

}
