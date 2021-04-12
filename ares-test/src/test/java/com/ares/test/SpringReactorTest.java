/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.ares.test;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: Young
 * @date: 2021/03/01
 * @see: com.ares.test.leetcode SpringReactorTest.java
 **/
public class SpringReactorTest {

    @Test
    public void createAFlux_just() {
        Flux<String> flux = Flux.just("1", "2", "3");
        flux.subscribe(f -> System.out.println("Here`s some number:" + f)
                , error -> System.out.println(error)
                , () -> System.out.println("run"));
        StepVerifier.create(flux)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .verifyComplete();

    }

    @Test
    public void testMono() {
        Mono<String> mono = Mono.create(monoSink -> System.out.println(monoSink.currentContext()));
        mono.subscribe(a -> System.out.println(a));
    }


    @Test
    public void test() {
        String a = "a", b = "b", c = "c";
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println(a);
            return Arrays.asList("A", "B", "C");
        });
        future.thenApplyAsync(o -> {
            List<String> list = (List<String>) o;
            System.out.println(list.size());
            return list.get(0);
        });
        future.thenAcceptAsync(o -> {
            System.out.println(o);
        });

    }
}
