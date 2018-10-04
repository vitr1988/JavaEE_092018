package ru.otus.json;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class JSONPathTest {

    @Test
    public void test(){
        try {
            try (InputStream resource = JSONPathTest.class.getResourceAsStream("/json/store.json")) {
                String json =
                        new BufferedReader(new InputStreamReader(resource,
                                StandardCharsets.UTF_8)).lines().collect(Collectors.joining());
                List<String> authors = JsonPath.read(json, "$.store.book[*].author");
                Assert.assertEquals(4, authors.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
