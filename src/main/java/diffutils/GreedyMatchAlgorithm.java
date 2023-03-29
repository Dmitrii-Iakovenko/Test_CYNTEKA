package diffutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

/**
 * GreedyMatchAlgorithm is an implementation of MatchAlgorithm that uses
 * a greedy algorithm to find the best matching pairs between two arrays
 * based on the provided DiffAlgorithm.
 *
 * @param <T> The type of elements in the arrays to be matched.
 */
public class GreedyMatchAlgorithm<T> implements MatchAlgorithm<T>{

    private final DiffAlgorithm<T> diff;
    private final T ZERO;

    /**
     * Constructs a GreedyMatchAlgorithm with a given DiffAlgorithm and a zero value.
     *
     * @param diff The DiffAlgorithm used to compute the similarity between elements.
     * @param zero The value to use for unmatched elements in the resulting map.
     */
    public GreedyMatchAlgorithm(DiffAlgorithm<T> diff, T zero) {
        this.diff = diff;
        ZERO = zero;
    }

    private record Pair<T>(T source, Integer sourceIndex, T target, Integer targetIndex, int similarity) {}

    /**
     * Matches elements from two input arrays using a greedy algorithm.
     *
     * @param source The first input array.
     * @param target The second input array.
     * @return A LinkedHashMap that maps elements from the first array to elements from the second array,
     *         preserving the order of elements in the first array. If an element from the first array
     *         doesn't have a match, it will be followed by elements from the second array in their original order.
     *         If a match is not found, the extra element from the first or second array becomes the key
     *         with a ZERO value.
     */
    @Override
    public Map<T, T> match(T[] source, T[] target) {
        // Create a list of all possible pairs of elements from the two arrays
        // with their indices and similarity, calculated using the DiffAlgorithm,
        // then sort the list in descending order of similarity.
        List<Pair<T>> pairs = new ArrayList<>(source.length * target.length);
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < target.length; j++) {
                int similarity = diff.computeDiff(source[i], target[j]);
                Pair<T> pair = new Pair<>(source[i], i, target[j], j, similarity);
                pairs.add(pair);
            }
        }
        pairs.sort(comparingInt(Pair<T>::similarity).reversed());

        // Create a list of non-overlapping pairs with the highest similarity,
        // ensuring that each element from the source and target arrays is used only once.
        List<Pair<T>> matching = new ArrayList<>(Math.max(source.length, target.length));
        Set<T> usedSource = new HashSet<>();
        Set<T> usedTarget = new HashSet<>();
        for (Pair<T> pair: pairs) {
            if (!usedSource.contains(pair.source()) && !usedTarget.contains(pair.target())) {
                matching.add(pair);
                usedSource.add(pair.source());
                usedTarget.add(pair.target());
            }
        }

        // If a match is not found, the extra element from the first or second array
        // becomes the key with a ZERO value.
        for (int i = 0; i < source.length; i++) {
            if (!usedSource.contains(source[i])) {
                matching.add(new Pair<T>(source[i], i, ZERO, -1, 0));
            }
        }
        for (int j = 0; j < target.length; j++) {
            if (!usedTarget.contains(target[j])) {
                matching.add(new Pair<T>(target[j], j, ZERO, -1, 0));
            }
        }

        return matching.stream()
                .sorted(comparing(Pair<T>::sourceIndex)
                        .thenComparing(Pair<T>::targetIndex))
                .collect(toMap(Pair::source, Pair::target, (key1, key2) -> key1, LinkedHashMap::new));
    }
}
