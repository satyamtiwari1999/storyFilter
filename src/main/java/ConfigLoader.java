import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConfigLoader.class);

    private final Properties properties = new Properties();

    public ConfigLoader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            LOGGER.info("Error loading config.properties");
        }
    }

    private String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public String getNounFilePath() {
        return getProperty(Consts.NOUN_FILE_PATH);
    }
    public String getVerbFilePath() {
        return getProperty(Consts.VERB_FILE_PATH);
    }
    public String getAdjectiveFilePath() {
        return getProperty(Consts.ADJECTIVE_FILE_PATH);
    }
    public String getAdverbFilePath() {
        return getProperty(Consts.ADVERB_FILE_PATH);
    }
}
