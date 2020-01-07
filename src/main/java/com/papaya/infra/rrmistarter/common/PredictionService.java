package com.papaya.infra.rrmistarter.common;

import com.papaya.infra.common.Adapter;

/**
 * @author Evgeny Borisov
 */
public interface PredictionService extends Adapter {
    String predict();
}
