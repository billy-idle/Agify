package com.github.agify.prediction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/agify")
@RequiredArgsConstructor
class PredictionController {

    private final PredictionService predictionService;

    @GetMapping()
    Prediction getPrediction(@RequestParam String name) {
        return predictionService.query(name);
    }
}
