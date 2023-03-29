package com.example.test.config;

import com.example.test.diffutils.DiffAlgorithm;
import com.example.test.diffutils.GreedyMatchAlgorithm;
import com.example.test.diffutils.LCSDiffAlgorithm;
import com.example.test.diffutils.MatchAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration class.
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final AppProps props;

    /**
     * Creates a bean for a string match algorithm.
     *
     * @return an instance of MatchAlgorithm<String>
     */
    @Bean
    public MatchAlgorithm<String> stringMatch() {
        DiffAlgorithm<String> diffAlgorithm = new LCSDiffAlgorithm();
        return new GreedyMatchAlgorithm<>(diffAlgorithm, props.matchAlgorithm().placeholder());
    }

}
