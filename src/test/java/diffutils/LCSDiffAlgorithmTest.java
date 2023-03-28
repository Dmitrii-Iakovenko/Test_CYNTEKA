package diffutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LCSDiffAlgorithmTest {
    private final DiffAlgorithm<String> diff = new LCSDiffAlgorithm();

    @Test
    void testEmptyStrings() {
        int lcs = diff.computeDiff("", "");
        assertEquals(0, lcs, "LCS of empty strings should be 0");
    }

    @Test
    void testIdenticalStrings() {
        int lcs = diff.computeDiff("abcdefg", "abcdefg");
        assertEquals(7, lcs, "LCS of identical strings should be equal to their length");
    }

    @Test
    void testNoCommonSubsequence() {
        int lcs = diff.computeDiff("abc", "def");
        assertEquals(0, lcs, "LCS of strings with no common subsequence should be 0");
    }

    @Test
    void testDifferentStrings() {
        int lcs = diff.computeDiff("abcdef", "azcde");
        assertEquals(4, lcs, "LCS of 'abcdef' and 'azcde' should be 4 ('acde')");
    }

    @Test
    void testCaseInsensitivity() {
        int lcs = diff.computeDiff("AbCdEf", "aBcDeF");
        assertEquals(6, lcs, "LCS should be case-insensitive and return the length of the strings");
    }

}