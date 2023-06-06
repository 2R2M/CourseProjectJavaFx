package com.example.mycourseproject.model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


public class AiModel {
    private String apiKey = "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";
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AiModel(){}
    public void sendOpenAIRequest(String prompt) throws Exception {
        String apiUrl = "https://api.openai.com/v1/engines/text-davinci-003/completions";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String query = "Continue the sequence: "+ prompt + ". Your answer should be in the format: next 5 days [Date: Amount]. Do not write anything else.";
        // Создайте JSON-объект с вашими параметрами запроса
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("prompt", query);
        jsonParams.put("max_tokens", 100);

        // Установите JSON-параметры в качестве тела запроса
        StringEntity requestBody = new StringEntity(jsonParams.toString());
        httpPost.setEntity(requestBody);

        // Отправьте запрос и получите ответ
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String responseString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
        System.out.println(responseString);
        JSONObject jsonResponse = new JSONObject(responseString);
        JSONArray choicesArray = jsonResponse.getJSONArray("choices");
        String textResp = choicesArray.getJSONObject(0).getString("text");
        httpClient.close();
        text = textResp;
        //return responseString;
        //System.out.println(responseString);
        // Закройте соединение и верните ответ в виде строки
    }

    public String DataPreparation(List<String> dates, List<Double> amounts){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dates.size(); i++) {
            String date = dates.get(i);
            Double amount = amounts.get(i);
            String formatted = String.format("[%s : %.2f]", date, amount);
            sb.append(formatted);

            if (i < dates.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();

    }

    public void ReverseDataPreparation(String input, List<String> dates, List<Double> amounts) {
        String[] elements = input.split(", ");

        for (String element : elements) {
            String[] parts = element.split(" : ");
            String date = parts[0].substring(1);
            Double amount = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1));

            dates.add(date);
            amounts.add(amount);
        }
    }

}
