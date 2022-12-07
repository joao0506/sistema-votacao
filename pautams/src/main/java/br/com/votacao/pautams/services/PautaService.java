package br.com.votacao.pautams.services;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.utils.UUIDGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PautaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);

    @Autowired
    private PautaRepository pautaRepository;

    @Value("${api.sessao.criar-sessao}")
    private String URL_CRIAR_SESSAO;

    private RestTemplate restTemplate = new RestTemplate();

    public Pauta salvarPauta(Pauta pauta){
        LOGGER.info("Inserindo pauta "+pauta.getDescricao());
        return pautaRepository.save(pauta);
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        String idPauta = UUIDGenerator.generateUUID();
        return new Pauta(idPauta, pautaDTO.getDescricao().trim(), null);
    }

    public Page<Pauta> listarTodasAsPautas(String pagina, String linhasPorPagina) {
        Pageable pageRequest = PageRequest.of(Integer.valueOf(pagina), Integer.valueOf(linhasPorPagina));
        return pautaRepository.findAll(pageRequest);
    }

    public Pauta listarPautaPorId(String id){
        return pautaRepository.findById(id).get();
    }

    public ResponseEntity criarSessao(String pauta, Integer duracaoSessao){
        JSONObject json = criarJsonObjectSessao(pauta, duracaoSessao);
        HttpEntity<String> entity = new HttpEntity<String>(json.toString(), criarHeadersRequisicao());

        LOGGER.info("Criando sessão para pauta "+pauta+" em "+URL_CRIAR_SESSAO);
        String response = restTemplate.postForObject(URL_CRIAR_SESSAO, entity, String.class);
        return ResponseEntity.ok("Id Sessão: "+response);
    }

    private HttpHeaders criarHeadersRequisicao(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private JSONObject criarJsonObjectSessao(String pauta, Integer duracaoSessao) {
        JSONObject json = new JSONObject();
        json.put("idPauta", pauta);
        json.put("duracaoSessao", duracaoSessao);
        return json;
    }
}
