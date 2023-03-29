package com.example.test.diffutils;

import java.util.Map;

/**
 * MatchAlgorithm is an interface for algorithms that find the best matching pairs
 * between two arrays of elements.
 *
 * @param <T> The type of elements in the arrays to be matched.
 */
public interface MatchAlgorithm<T> {

    /**
     * Matches elements from two input arrays and returns a map that associates
     * elements from the first array to elements from the second array.
     *
     * @param source The first input array.
     * @param target The second input array.
     * @return A map that maps elements from the first array to elements from the second array.
     */
    Map<T, T> match(T[] source, T[] target);

}