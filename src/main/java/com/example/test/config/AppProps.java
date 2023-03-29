package com.example.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the application.
 */
@ConfigurationProperties(prefix = "app")
public record AppProps(
        Files files,
        MatchAlgorithm matchAlgorithm) {

    public record Files(String input, String output) {}

    public record MatchAlgorithm(String placeholder) {}
}
