package com.github.agify.prediction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
class PredictionService {

    private final RestTemplate restTemplate;
    @Value("${agify.url}")
    private String url;
    @Value("${agify.response.error}")
    private String error ;

    public Prediction query(String name) {
        Prediction prediction = null;
        try {
            prediction = restTemplate.getForObject(String.format(url, name), Prediction.class);
        } catch (HttpClientErrorException e) {
            JSONObject jsonObject = null;
            String message;
            try {
                jsonObject = new JSONObject(e.getResponseBodyAsString());
                message = jsonObject.get(error).toString();
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getStatusCode(), message);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }

        log.info(Objects.requireNonNull(prediction).toString());
        return prediction;
    }
}
