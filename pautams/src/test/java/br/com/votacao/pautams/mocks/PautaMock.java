package br.com.votacao.pautams.mocks;

import br.com.votacao.pautams.domain.Pauta;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class PautaMock {

    public Pauta getPauta(){
        return new Pauta("7054d9ce-9fa4-4dbb-ab29-82138e9acbde", "Pauta Teste", null);
    }

    public List<Pauta> getPautas(){
        return Arrays.asList(
                new Pauta("7054d9ce-9fa4-4dbb-ab29-82138e9acbde", "Pauta Teste 1", null),
                new Pauta("a8a344db-1828-45f5-b0c1-2138e15b6cd5", "Pauta Teste 2", null),
                new Pauta("ea53f302-ff84-4f8b-be65-cc22321f2549", "Pauta Teste 3", null),
                new Pauta("fd3566d8-b924-4ec6-9843-c67aa0014d3b", "Pauta Teste 4", null),
                new Pauta("8443b94c-65c8-4105-a638-d7ed7fd79cd2", "Pauta Teste 5", null)
        );
    }

    public JSONObject getPautaRequest() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("descricao", "Pauta Teste");
        return jsonObject;
    }



}
