package com.example.tsk.Service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

class PatternMatchingAlgorithmTest {

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testPatternMatching(String pattern, String input, int expectedPosition, int expectedTypos) {
        var result = PatternMatchingAlgorithm.findBestMatch(pattern, input);
        assertEquals(expectedPosition, result.position());
        assertEquals(expectedTypos, result.typos());
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("BCD", "ABCD", 1, 0),
                Arguments.of("BWD", "ABCD", 1, 1),
                Arguments.of("CFG", "ABCDEFG", 4, 1),
                Arguments.of("ABC", "ABCABC", 0, 0),
                Arguments.of("TDD", "ABCDEFG", 1, 2),
                Arguments.of("XYZ", "AXYZA", 1, 0),
                Arguments.of("MN", "ABCMNOP", 3, 0),
                Arguments.of("123", "912345", 1, 0),
                Arguments.of("AAA", "ABAACAADAA", 0, 1),
                Arguments.of("FOO", "BAR", -1, -1),
                Arguments.of("TEST", "TESTTEST", 0, 0),
                Arguments.of("ZZZ", "ZZZZZZ", 0, 0),
                Arguments.of("123", "456789", -1, -1),
                Arguments.of("NULL", "NULL", 0, 0)
        );
    }
}