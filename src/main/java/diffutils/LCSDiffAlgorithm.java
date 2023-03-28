package diffutils;

/**
 * The LCSDiffAlgorithm class implements the DiffAlgorithm interface
 * to calculate the length of the Longest Common Subsequence (LCS) between two strings.
 * This algorithm is case-insensitive.
 */
public class LCSDiffAlgorithm implements DiffAlgorithm<String> {

    /**
     * Computes the length of the Longest Common Subsequence (LCS) between two strings.
     * The algorithm is case-insensitive.
     *
     * @param source The first string to compare.
     * @param target The second string to compare.
     * @return The length of the LCS between the two input strings.
     */
    @Override
    public int computeDiff(final String source, final String target) {
        char[] sourceArr = source.toLowerCase().toCharArray();
        char[] targetArr = target.toLowerCase().toCharArray();

        int targetLen = targetArr.length;

        int[] prev = new int[targetLen + 1];
        int[] curr = new int[targetLen + 1];

        for (char sourceCh : sourceArr) {
            for (int j = 0; j < targetLen; j++) {
                if (sourceCh == targetArr[j]) {
                    curr[j + 1] = prev[j] + 1;
                } else {
                    curr[j + 1] = Math.max(prev[j + 1], curr[j]);
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[targetLen];
    }
}