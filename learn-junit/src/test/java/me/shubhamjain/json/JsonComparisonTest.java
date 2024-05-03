package me.shubhamjain.json;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonComparisonTest {

    @Test
    void given_when_then() throws IOException, JSONException, URISyntaxException {
        // Arrange
        List<Path> jsons = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/requests-versions/"))) {
            jsons = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        List<String> data = jsons.stream().map(path -> {
            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toUnmodifiableList());
        // Act

        // Assert
        for(String d : data) {
            for(String p : data) {
                JSONAssert.assertEquals(d, p, true);
            }
        }

    }
}
