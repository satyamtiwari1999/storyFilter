package database.cleaner;

import database.interfaces.ICleaner;
import database.store.WordsStore;
import org.slf4j.Logger;

public class AdjectiveFileCleaner implements ICleaner {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdjectiveFileCleaner.class);
    private final WordsStore wordsStore;

    public AdjectiveFileCleaner(){
        wordsStore = new WordsStore();
    }
    @Override
    public void extractWordsFromLine(final String line) {
        LOGGER.debug("Extracting adjective from line: " + line);
        final int indexOfNumberOfAdjectives = 3;
        final String[] lineSplit = line.toLowerCase().split(" ");
        final int numberOfNouns = Integer.parseInt(lineSplit[indexOfNumberOfAdjectives], 16);
        LOGGER.debug("Number of adjectives: " + numberOfNouns);

        int startNounIndex = 4;
        for(int i = 0; i < numberOfNouns; i++){
            if(startNounIndex < lineSplit.length){
                LOGGER.debug("Adding adjective: " + lineSplit[startNounIndex]);
                wordsStore.addAdjective(lineSplit[startNounIndex]);
                startNounIndex += 2;
            }
        }
    }
}
