package br.com.votacao.pautams.mocks;

import br.com.votacao.pautams.domain.Pauta;
import br.com.votacao.pautams.domain.ResultadoPauta;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoPautaMock {

    public ResultadoPauta getResultadoPauta(){
        return new ResultadoPauta("12345", this.getPauta(), "Sim");
    }

    private Pauta getPauta(){
        return new PautaMock().getPauta();
    }

    public JSONObject montarResultadPautaJson() throws JSONException {
        JSONObject resultado = new JSONObject();
        resultado.put("idPauta", "123");
        resultado.put("resultado", "Sim");
        return resultado;
    }
}
