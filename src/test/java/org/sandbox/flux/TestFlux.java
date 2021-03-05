package org.sandbox.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by vyacheslav.mischeryakov
 * Created 05.03.2021
 */
public class TestFlux {

    @Test
    public void test() {
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);

        assertEquals(Arrays.asList(1, 2, 3, 4), elements);
    }

}
