package br.com.votacao.sessaoms.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;

public class RealizarRequisicao {

    private MockMvc mockMvc;

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public MockHttpServletResponse doMockGet(String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }

    public MockHttpServletResponse doMockPost(String path, JSONObject body, MultiValueMap<String, String> params) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .post(path)
                        .params(params)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }

    public JSONArray getJSONArrayFromResponse(MockHttpServletResponse response) throws Exception {
        JSONObject jsonResponse = new JSONObject(response.getContentAsString(StandardCharsets.UTF_8));
        return new JSONArray(jsonResponse.get("content").toString());
    }

    public JSONObject getJSONObjectFromResponse(MockHttpServletResponse response) throws Exception {
        return new JSONObject(response.getContentAsString(StandardCharsets.UTF_8));
    }

}
