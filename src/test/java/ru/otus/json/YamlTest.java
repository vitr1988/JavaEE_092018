package ru.otus.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.json.model.User;

public class YamlTest {

    @Test
    public void testYaml(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            User user = mapper.readValue(YamlTest.class.getResourceAsStream("/user.yaml"), User.class);
            Assert.assertEquals("Test User", user.getName());
            Assert.assertEquals(30, user.getAge());
            Assert.assertNotNull(user.getRoles());
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }
}
