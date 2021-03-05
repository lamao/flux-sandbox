package org.sandbox.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * Created by vyacheslav.mischeryakov
 * Created 05.03.2021
 */
public class TestFlux {

    @Test
    public void test() {

        int batchSize = 10;
        int batchCount = 40;

        final LongAccumulator sum = new LongAccumulator(Long::sum, 0);
        List<Integer> elements = new ArrayList<>();

        Flux.range(1, batchCount)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .map(index -> calculateResult(index, batchSize))
                .log()
                .sequential()
                .doOnNext(sum::accumulate)
                .doOnNext(elements::add)
                .log()
                .blockLast();

        System.out.println(sum.longValue());
        System.out.println(elements);
        System.out.println("Completed");
    }

    private int calculateResult(int index, int batchSize) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return index * batchSize;
    }

}
