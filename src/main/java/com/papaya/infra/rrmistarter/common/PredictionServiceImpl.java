package com.papaya.infra.rrmistarter.common;

import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
@Component
public class PredictionServiceImpl implements PredictionService {
    @Override
    public String predict() {
        return "prediction";
    }
}
