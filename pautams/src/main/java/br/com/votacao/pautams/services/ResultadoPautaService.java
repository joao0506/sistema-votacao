package br.com.votacao.pautams.services;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.ResultadoPauta;
import br.com.votacao.pautams.repositories.PautaRepository;
import br.com.votacao.pautams.repositories.ResultadoPautaRepository;
import br.com.votacao.pautams.utils.UUIDGenerator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoPautaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultadoPautaService.class);

    @Autowired
    private ResultadoPautaRepository resultadoPautaRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private PautaService pautaService;

    public void inserirResultadoPauta(JSONObject resultado){
        Pauta pauta = pautaService.listarPautaPorId(resultado.getString("idPauta"));
        ResultadoPauta resultadoPauta = new ResultadoPauta(UUIDGenerator.generateUUID(), pauta, resultado.getString("resultado"));

        this.salvarResultado(resultadoPauta);
        this.atualizarPautaComResultado(pauta, resultadoPauta);
    }

    private void atualizarPautaComResultado(Pauta pauta, ResultadoPauta resultadoPauta){
        pauta.setResultado(resultadoPauta);
        LOGGER.info("Atualizando pauta "+pauta.getId()+" com o resultado "+resultadoPauta.getResultado());
        pautaService.salvarPauta(pauta);
    }

    public void salvarResultado(ResultadoPauta resultadoPauta) {
        resultadoPautaRepository.save(resultadoPauta);
    }
}
