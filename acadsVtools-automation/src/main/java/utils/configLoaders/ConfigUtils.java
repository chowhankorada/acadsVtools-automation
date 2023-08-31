package utils.configLoaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    public static ConfigUtils CONFIG = new ConfigUtils();
    private final Properties config = new Properties();

    public ConfigUtils() {
        try {
            String propFileName = "Config.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                config.load(inputStream);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getProperty(String key) {
        return config.getProperty(key);
    }
}


