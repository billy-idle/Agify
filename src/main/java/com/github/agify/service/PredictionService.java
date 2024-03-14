package com.github.agify.service;

import com.github.agify.domain.Prediction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PredictionService {

    private final RestTemplate restTemplate;
    @Value("${agify.url}")
    private String url;

    public Prediction query(String name) {
        Prediction prediction = restTemplate.getForObject(String.format(url, name), Prediction.class);
        log.info(Objects.requireNonNull(prediction).toString());
        return prediction;
    }
}
