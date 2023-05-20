package com.example.mycourseproject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class AiModel {
    private String apiKey = "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";
    private String prompt = "";
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    AiModel(){}
    public String sendOpenAIRequest(String prompt) throws Exception {
        String apiUrl = "https://api.openai.com/v1/engines/text-ada-001/completions";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        // Создайте JSON-объект с вашими параметрами запроса
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("prompt", prompt);
        jsonParams.put("max_tokens", 100);

        // Установите JSON-параметры в качестве тела запроса
        StringEntity requestBody = new StringEntity(jsonParams.toString());
        httpPost.setEntity(requestBody);

        // Отправьте запрос и получите ответ
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String responseString = EntityUtils.toString(responseEntity);

        // Закройте соединение и верните ответ в виде строки
        httpClient.close();
        return responseString;
    }
}
