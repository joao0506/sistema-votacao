package br.com.votacao.pautams.services;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.dto.PautaDTO;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.utils.UUIDGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    String urlSessaoMS = "http://localhost:8081/sessao-ms";

    public Pauta salvarPauta(Pauta pauta){
        return pautaRepository.save(pauta);
    }

    public Pauta fromDTO(PautaDTO pautaDTO) {
        String idPauta = UUIDGenerator.generateUUID();
        return new Pauta(idPauta, pautaDTO.getDescricao().trim());
    }

    public Page<Pauta> listarTodasAsPautas(String pagina, String linhasPorPagina) {
        Pageable pageRequest = PageRequest.of(Integer.valueOf(pagina), Integer.valueOf(linhasPorPagina));
        return pautaRepository.findAll(pageRequest);
    }


    public void criarSessao(String pauta, Integer duracaoSessao){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = criarJsonObjectSessao(pauta, duracaoSessao);
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);


        String result = restTemplate.postForObject(urlSessaoMS+"/sessao/criarSessao", entity, String.class);
        System.out.println(result);
    }

    public JSONObject criarJsonObjectSessao(String pauta, Integer duracaoSessao) {
        JSONObject json = new JSONObject();
        json.put("idPauta", pauta);
        json.put("duracaoSessao", duracaoSessao);
        return json;
    }
}
