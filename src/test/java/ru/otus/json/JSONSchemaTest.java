package ru.otus.json;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class JSONSchemaTest {

    @Test(expected = ValidationException.class)
    public void testException() {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/json/schema.json")));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/json/product_invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    @Test
    public void test() {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/json/schema.json")));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(JSONSchemaTest.class.getResourceAsStream("/json/product_valid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}
