import diffutils.DiffAlgorithm;
import diffutils.GreedyMatchAlgorithm;
import diffutils.LCSDiffAlgorithm;
import diffutils.MatchAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    private static final String FILE_INPUT = "input.txt";
    private static final String FILE_OUTPUT = "output.txt";
    private static final String ZERO = "?";

    /**
     * The main method serves as the entry point for the program.
     * It reads two arrays of strings from the input file, calculates the matching pairs using
     * the longest common subsequence algorithm and greedy matching, and writes the result
     * to the output file.
     *
     * @param args Command-line arguments, not used in this program.
     */
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_INPUT))) {
            String[] source = readArrayFromFile(reader);
            String[] target = readArrayFromFile(reader);

            DiffAlgorithm<String> diff = new LCSDiffAlgorithm();
            MatchAlgorithm<String> matchAlgorithm = new GreedyMatchAlgorithm<>(diff, ZERO);
            Map<String, String> match = matchAlgorithm.match(source, target);

            writePairToFile(match);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an array of strings from a file using the given BufferedReader.
     *
     * @param reader The BufferedReader to read from the file.
     * @return An array of strings read from the file.
     * @throws IOException If there is a problem reading from the file.
     */
    private static String[] readArrayFromFile(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = reader.readLine();
        }
        return result;
    }

    /**
     * Writes a map of string pairs to the output file.
     *
     * @param pairs The map of string pairs to write to the output file.
     * @throws IOException If there is a problem writing to the file.
     */
    private static void writePairToFile(Map<String, String> pairs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_OUTPUT))) {
            for (Map.Entry<String, String> pair : pairs.entrySet()) {
                writer.write(pair.getKey() + ":" + pair.getValue());
                writer.newLine();
            }
        }
    }

}
