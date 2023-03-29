package com.example.test.commandlinerunner;

import com.example.test.config.AppProps;
import com.example.test.diffutils.MatchAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MatchingProcessor implements CommandLineRunner {

    private final MatchAlgorithm<String> matchAlgorithm;
    private final AppProps props;


    /**
     * The main method serves as the entry point for the program.
     * It reads two arrays of strings from the input file, calculates the matching pairs using
     * the longest common subsequence algorithm and greedy matching, and writes the result
     * to the output file.
     *
     * @param args Command-line arguments, not used in this program.
     */
    @Override
    public void run(String... args) throws Exception {
        try(BufferedReader reader = new BufferedReader(new FileReader(props.files().input()))) {
            String[] source = readArrayFromFile(reader);
            String[] target = readArrayFromFile(reader);

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
    private String[] readArrayFromFile(BufferedReader reader) throws IOException {
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
    private void writePairToFile(Map<String, String> pairs) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(props.files().output()))) {
            for (Map.Entry<String, String> pair : pairs.entrySet()) {
                writer.write(pair.getKey() + ":" + pair.getValue());
                writer.newLine();
            }
        }
    }
}
