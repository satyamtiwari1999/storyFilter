package database.cleaner;

import database.interfaces.ICleaner;
import database.store.WordsStore;
import org.slf4j.Logger;

public class AdverbFileCleaner implements ICleaner {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdverbFileCleaner.class);
    private final WordsStore wordsStore;

    public AdverbFileCleaner() {
        wordsStore = new WordsStore();
    }

    @Override
    public void extractWordsFromLine(final String line) {
        LOGGER.debug("Extracting adverb from line: " + line);
        final int indexOfNumberOfAdverbs = 3;
        final String[] lineSplit = line.toLowerCase().split(" ");
        final int numberOfAdverbs = Integer.parseInt(lineSplit[indexOfNumberOfAdverbs], 16);
        LOGGER.debug("Number of adverbs: " + numberOfAdverbs);

        int startAdverbIndex = 4;
        for (int i = 0; i < numberOfAdverbs; i++) {
            if (startAdverbIndex < lineSplit.length) {
                LOGGER.debug("Adding adverb: " + lineSplit[startAdverbIndex]);
                wordsStore.addAdverb(lineSplit[startAdverbIndex]);
                startAdverbIndex += 2;
            }
        }
    }
}