package ru.otus.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.jsp.model.Employee;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {

    @Test
    public void testTemplate() throws IOException, TemplateException {
        Configuration cfg = new Configuration(new Version("2.3.28"));

        cfg.setClassForTemplateLoading(FreemarkerTest.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("/ftl/index.ftl");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("user", "Ivanov Vitalii");

        String templateOutput;
        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            templateOutput = out.getBuffer().toString();
            out.flush();
        }

        Assert.assertEquals("Hello, Ivanov Vitalii!", templateOutput);
    }
}
