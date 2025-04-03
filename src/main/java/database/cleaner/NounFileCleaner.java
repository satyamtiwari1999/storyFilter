package database.cleaner;
import database.interfaces.ICleaner;
import database.store.WordsStore;
import org.slf4j.Logger;

public class NounFileCleaner implements ICleaner {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NounFileCleaner.class);
    private final WordsStore wordsStore;

    public NounFileCleaner(){
        wordsStore = new WordsStore();
    }

    @Override
    public void extractWordsFromLine(final String line) {
        LOGGER.debug("Extracting noun from line: " + line);
        final int indexOfNumberOfNouns = 3;
        final String[] lineSplit = line.toLowerCase().split(" ");
        final int numberOfNouns = Integer.parseInt(lineSplit[indexOfNumberOfNouns], 16);
        LOGGER.debug("Number of nouns: " + numberOfNouns);

        int startNounIndex = 4;
        for(int i = 0; i < numberOfNouns; i++){
            if(startNounIndex < lineSplit.length){
                LOGGER.debug("Adding noun: " + lineSplit[startNounIndex]);
                wordsStore.addNoun(lineSplit[startNounIndex]);
                startNounIndex += 2;
            }
        }
    }

}
