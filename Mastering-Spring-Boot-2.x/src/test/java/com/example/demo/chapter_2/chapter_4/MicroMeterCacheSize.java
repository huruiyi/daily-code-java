package com.example.demo.chapter_2.chapter_4;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;
import org.springframework.metrics.instrument.Measurement;
import org.springframework.metrics.instrument.Meter;
import org.springframework.metrics.instrument.simple.SimpleMeterRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class MicroMeterCacheSize {
    private SimpleMeterRegistry metricRegistry = new SimpleMeterRegistry();

    private final LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10000000).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) {
            return key.toUpperCase();
        }
    });

    @Test
    public void shouldUseGauge() throws ExecutionException {
        //given
        metricRegistry.gauge("cache.size", cache, value -> cache.size());

        //when
        cache.get("k1");
        cache.get("k2");
        cache.get("k3");

        //then
        Optional<Meter> gauge = metricRegistry.findMeter(Meter.Type.Gauge, "cache.size");
        List<Measurement> measurements = new ArrayList<>();
        gauge.get().measure().iterator().forEachRemaining(measurements::add);

        System.out.println((measurements.get(0).getValue()));
    }
}