package diffutils;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreedyMatchAlgorithmIT {

    private final DiffAlgorithm<String> diff = new LCSDiffAlgorithm();
    private final GreedyMatchAlgorithm<String> greedyMatchAlgorithm = new GreedyMatchAlgorithm<>(diff, "?");

    @Test
    void testEmptyArrays() {
        String[] source = {};
        String[] target = {};
        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);
        assertTrue(actual.isEmpty(), "Result should be an empty map when both arrays are empty");
    }

    @Test
    void testIdenticalArrays() {
        String[] source = {"apple", "banana", "cherry"};
        String[] target = {"apple", "banana", "cherry"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("apple", "apple");
        expected.put("banana", "banana");
        expected.put("cherry", "cherry");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(3, actual.size(), "Result should have 3 entries for identical arrays");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for identical arrays");
    }

    @Test
    void testOverlappingElements() {
        String[] source = {"apple", "banana", "cherry"};
        String[] target = {"banana", "cherry", "apple"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("apple", "apple");
        expected.put("banana", "banana");
        expected.put("cherry", "cherry");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(3, actual.size(), "Result should have 3 entries for overlapping arrays");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for overlapping arrays");
    }

    @Test
    void testDifferentLengthsSourceIsLonger() {
        String[] source = {"apple", "banana", "cherry"};
        String[] target = {"apple", "cherry"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("apple", "apple");
        expected.put("banana", "?");
        expected.put("cherry", "cherry");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(3, actual.size(), "Result should have 3 entries for arrays of different lengths");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for arrays of different lengths");
    }

    @Test
    void testDifferentLengthsTargetIsLonger() {
        String[] source = {"apple", "cherry"};
        String[] target = {"apple", "banana", "cherry"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("apple", "apple");
        expected.put("cherry", "cherry");
        expected.put("banana", "?");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(3, actual.size(), "Result should have 3 entries for arrays of different lengths");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for arrays of different lengths");
    }

    @Test
    void testExample1() {
        String[] source = {"гвоздь", "шуруп", "краска синяя", "ведро для воды"};
        String[] target = {"краска", "корыто для воды", "шуруп 3х1.5"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("гвоздь", "?");
        expected.put("шуруп", "шуруп 3х1.5");
        expected.put("краска синяя", "краска");
        expected.put("ведро для воды", "корыто для воды");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(4, actual.size(), "Result should have 4 entries for arrays of different lengths");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for Example1");
    }

    @Test
    void testExample2() {
        String[] source = {"Бетон с присадкой"};
        String[] target = {"Цемент"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Бетон с присадкой", "Цемент");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(1, actual.size(), "Result should have 4 entries for arrays of different lengths");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for Example2");
    }

    @Test
    void testExample3() {
        String[] source = {"Бетон с присадкой"};
        String[] target = {"присадка для бетона", "доставка"};

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Бетон с присадкой", "присадка для бетона");
        expected.put("доставка", "?");

        Map<String, String> actual = greedyMatchAlgorithm.match(source, target);

        assertEquals(2, actual.size(), "Result should have 4 entries for arrays of different lengths");
        assertEquals(expected, actual, "All keys should be equal to their corresponding values for Example3");
    }

}