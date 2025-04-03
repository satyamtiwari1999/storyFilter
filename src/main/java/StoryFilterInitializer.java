import database.DatabaseSetup;
import database.store.WordsStore;
import org.slf4j.Logger;

public class StoryFilterInitializer {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StoryFilterInitializer.class);

    public static void main(final String[] args) {
        init();
        processStory();
    }

    private static void processStory(){
        final StoryHandler storyHandler = new StoryHandler();
        storyHandler.readStory();
    }

    private static void init(){
        final String[] fileNames = loadConfig();
        setupDatabase(fileNames[0], fileNames[1], fileNames[2], fileNames[3]);
    }

    private static void setupDatabase(final String nounFilePath, final String verbFilePath, final String adjectiveFilePath, final String adverbFilePath){
        final DatabaseSetup databaseSetup = new DatabaseSetup(nounFilePath, verbFilePath, adjectiveFilePath, adverbFilePath);
        databaseSetup.readFileAndStoreWords();
    }

    private static String[] loadConfig() {
        LOGGER.info("Loading configuration");
        final ConfigLoader configLoader = new ConfigLoader();
        final String nounFilePath = configLoader.getNounFilePath();
        final String verbFilePath = configLoader.getVerbFilePath();
        final String adjectiveFilePath = configLoader.getAdjectiveFilePath();
        final String adverbFilePath = configLoader.getAdverbFilePath();
        return new String[]{nounFilePath, verbFilePath, adjectiveFilePath, adverbFilePath};
    }

    private static void wordsChecking(){
        LOGGER.debug("Checking words");
        final WordsStore wordsStore = new WordsStore();
        wordsStore.isVerb("run");
    }
}
