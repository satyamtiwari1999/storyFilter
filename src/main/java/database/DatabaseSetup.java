package database;

import database.cleaner.AdjectiveFileCleaner;
import database.cleaner.AdverbFileCleaner;
import database.cleaner.NounFileCleaner;
import database.cleaner.VerbFileCleaner;
import database.interfaces.IDatabaseSetup;
import org.slf4j.Logger;

import java.io.*;
import java.util.function.Consumer;

public class DatabaseSetup implements IDatabaseSetup {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DatabaseSetup.class);
    private final String nounFileName;
    private final String verbFileName;
    private final String adjectiveFileName;
    private final String adverbFileName;

    public DatabaseSetup(final String nounFileName, final String verbFileName, final String adjectiveFileName, final String adverbFileName) {
        this.nounFileName = nounFileName;
        this.verbFileName = verbFileName;
        this.adjectiveFileName = adjectiveFileName;
        this.adverbFileName = adverbFileName;
    }

    @Override
    public void readFileAndStoreWords() {
        LOGGER.info("Reading files and processing words");
        readNounFile(nounFileName);
        readVerbFile(verbFileName);
        readAdjectiveFile(adjectiveFileName);
        readAdverbFile(adverbFileName);
    }

    private void readAdverbFile(final String fileName) {
        LOGGER.debug("Reading adverb file: " + fileName);
        readFile(fileName, new AdverbFileCleaner()::extractWordsFromLine);
        LOGGER.info("Finished reading adverb file: " + fileName);
    }

    private void readVerbFile(final String fileName) {
        LOGGER.debug("Reading verb file: " + fileName);
        readFile(fileName, new VerbFileCleaner()::extractWordsFromLine);
        LOGGER.info("Finished reading verb file: " + fileName);
    }

    private void readNounFile(final String fileName) {
        LOGGER.debug("Reading noun file: " + fileName);
        readFile(fileName, new NounFileCleaner()::extractWordsFromLine);
        LOGGER.info("Finished reading noun file: " + fileName);
    }

    private void readAdjectiveFile(final String fileName) {
        LOGGER.debug("Reading adjective file: " + fileName);
        readFile(fileName, new AdjectiveFileCleaner()::extractWordsFromLine);
        LOGGER.info("Finished reading adjective file: " + fileName);
    }

    private void readFile(final String fileName, final Consumer<String> consumer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.lines().forEach(consumer);
        } catch (IOException e) {
            LOGGER.error("Error reading file: " + fileName);
        }
    }
}
