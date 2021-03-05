package org.sandbox.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by vyacheslav.mischeryakov
 * Created 05.03.2021
 */
public class TestFlux {

    @Test
    public void test() {

        int batchSize = 10;
        int batchCount = 3;

        final LongAccumulator sum = new LongAccumulator(Long::sum, 0);
        List<Integer> elements = new ArrayList<>();

        Flux.range(1, batchCount)
                .log()
                .map(index -> calculateResult(index, batchSize))
                .log()
                .doOnNext(sum::accumulate)
                .log()
                .subscribe(elements::add);

        System.out.println(sum.longValue());
        System.out.println(elements);
    }

    private int calculateResult(int index, int batchSize) {
        return index * batchSize;
    }

}
