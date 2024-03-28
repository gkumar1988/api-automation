package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertiesLoader {

    public Properties loadProperties(String propertyFile) {
        Properties properties = new Properties();
        if (propertyFile == null) {
            throw new IllegalArgumentException("Missing Environment File");
        }

        String fileName = propertyFile + ".properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Property file not loaded");
        }

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }
}
