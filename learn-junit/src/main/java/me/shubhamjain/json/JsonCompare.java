package me.shubhamjain.json;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonCompare {
    public static String format(JsonValue json) {
        StringWriter stringWriter = new StringWriter();
        prettyPrint(json, stringWriter);
        return stringWriter.toString();
    }

    public static void prettyPrint(JsonValue json, Writer writer) {
        Map<String, Object> config =
                Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        try (JsonWriter jsonWriter = writerFactory.createWriter(writer)) {
            jsonWriter.write(json);
        }
    }

    public static void main(String[] args) {
        List<Path> jsons = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("/Users/shubhamjain/Yapily/ext/sj/learn-modern-testing/learn-junit/src/main/resources/requests-versions"))) {
            jsons = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        for (String d : data) {
            for (String p : data) {
                JsonValue source = Json.createReader(new StringReader(d)).readValue();
                JsonValue target = Json.createReader(new StringReader(p)).readValue();
                JsonPatch diff = Json.createDiff(source.asJsonObject(), target.asJsonObject());

                System.out.println(format(diff.toJsonArray()));

            }
        }
    }
}
