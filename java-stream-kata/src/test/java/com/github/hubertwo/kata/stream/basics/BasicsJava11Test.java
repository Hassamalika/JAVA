package com.github.hubertwo.kata.stream.basics;

import com.github.hubertwo.kata.stream.Fruit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * By doing this tasks you will learn how to use:
 * <p>
 * - {@link Predicate#not}
 * - {@link String#lines}
 * </p>
 *
 * @author https://github.com/HubertWo
 */
@SuppressWarnings("SimplifyStreamApiCallChains")
@DisplayName("Stream basics - Java 11")
class BasicsJava11Test {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final Fruit BANANA = new Fruit("Banana", 105);
    private static final Fruit PAPAYA = new Fruit("Papaya", 109);
    private static final Fruit KIWI = new Fruit("Kiwi", 46);
    private static final Fruit MANGO = new Fruit("Mango", 107);
    private static final Fruit PEACH = new Fruit("Peach", 48);

    private static final Set<Fruit> FRUITS = Set.of(PAPAYA, BANANA, KIWI, MANGO, PEACH);

    @Test
    @DisplayName("Task: Get all fruits except BANANA")
    void task1() {
        final List<Fruit> everythingExceptBanana = FRUITS.stream()
                // TODO: put your answer here
                .filter(Predicate.not(f -> f.equals(BANANA)))
                .collect(toUnmodifiableList());

        assertThat(everythingExceptBanana)
                .doesNotContain(BANANA)
                .containsExactlyInAnyOrder(PAPAYA, KIWI, MANGO, PEACH);
    }

    @Test
    @DisplayName("Task: Load fruit list from file")
    void task2() throws IOException, URISyntaxException {
        // Loads the String from resources file
        URI resource = ClassLoader.getSystemResource("java11/fruitList.txt").toURI();
        final String givenFruitList = Files.readString(Paths.get(resource));

        // TODO: convert givenFruitList to list using Streams
        final List<Fruit> actualFruitList = givenFruitList
                .lines()
                .map(l -> l.split(","))
                .map(ll -> new Fruit(ll[0], Integer.parseInt(ll[1])))
                .collect(Collectors.toList());


        assertThat(actualFruitList)
                .containsExactlyInAnyOrderElementsOf(FRUITS);
    }

}
