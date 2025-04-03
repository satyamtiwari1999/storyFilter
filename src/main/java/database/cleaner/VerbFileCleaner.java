package database.cleaner;

import database.interfaces.ICleaner;
import database.store.WordsStore;
import org.slf4j.Logger;

import java.util.List;

public class VerbFileCleaner implements ICleaner {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VerbFileCleaner.class);
    private final WordsStore wordsStore;

    public VerbFileCleaner(){
        wordsStore = new WordsStore();
    }

    @Override
    public void extractWordsFromLine(final String line) {
        extractFromVerbDotTxtFile(line);
    }

    private void extractFromDataDotVerbFile(final String line) {
        LOGGER.debug("Extracting verb from line: " + line);
        final int indexOfNumberOfVerbs = 3;
        final String[] lineSplit = line.toLowerCase().split(" ");
        final int numberOfVerbs = Integer.parseInt(lineSplit[indexOfNumberOfVerbs], 16);
        LOGGER.debug("Number of verbs: " + numberOfVerbs);

        int startVerbIndex = 4;
        for(int i = 0; i < numberOfVerbs; i++){
            if(startVerbIndex < lineSplit.length){
                LOGGER.debug("Adding verb: " + lineSplit[startVerbIndex]);
                wordsStore.addVerb(lineSplit[startVerbIndex]);
                startVerbIndex += 2;
            }
        }
    }

    private void extractFromVerbDotTxtFile(final String line){
        LOGGER.debug("Extracting verb from line: " + line);
        final String[] lineSplit = line.toLowerCase().split(" ");
        for(final String word : lineSplit){
            if(!word.isEmpty()){
                LOGGER.debug("Adding verb: " + word);
                wordsStore.addVerb(word);
            }
        }
    }

    private void saveVerbsToFile(final List<String> verbs) {
        LOGGER.debug("Saving verbs to file");
        try(final java.io.FileWriter fileWriter = new java.io.FileWriter("E:/Projects/2025/Words/StoryFilter/filestore/verbs.txt", true);
            final java.io.BufferedWriter bufferedWriter = new java.io.BufferedWriter(fileWriter);
        ){
            bufferedWriter.write(String.join(" " , verbs));
            bufferedWriter.newLine();
        } catch (final java.io.IOException e) {
            LOGGER.error("Error saving verbs to file: " + e.getMessage());
        }
    }
}
