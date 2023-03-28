package diffutils;

/**
 * The DiffAlgorithm interface defines a method for calculating
 * a similarity or difference metric between two objects of type T.
 *
 * @param <T> The type of objects this DiffAlgorithm can compare.
 */
public interface DiffAlgorithm<T> {

    /**
     * Computes a similarity or difference metric between two objects of type T.
     *
     * @param source The first object to compare.
     * @param target The second object to compare.
     * @return An integer representing the similarity or difference between the two input objects.
     */
    int computeDiff(T source, T target);

}
