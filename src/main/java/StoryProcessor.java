import database.store.WordsStore;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StoryProcessor {
    public static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StoryHandler.class);
    private final WordsStore wordsStore = new WordsStore();

    public String processStory(final String story){
        final List<String> words = new ArrayList<>(List.of(splitStoryIntoWords(story)));
        dropUnwantedWords(words);
        return removeIncompleteSentences(words);
    }

    private void dropUnwantedWords(final List<String> words){
        // drop adjectives and adverbs
        for(int i = 0; i < words.size(); i++){
            dropWordForAdjAndAdv(words, i);
        }
    }

    private void dropWordForAdjAndAdv(final List<String> words, final int index){
        if(wordsStore.isAdjective(words.get(index))){
            LOGGER.info("Dropping word because it's adjective: " + words.get(index));
            words.set(index, "");
        }
        else if(wordsStore.isAdverb(words.get(index))){
            LOGGER.info("Dropping word because it's adverb: " + words.get(index));
            words.set(index, "");
        }
    }

    public String removeIncompleteSentences(final List<String> words){
        final String storyString = String.join(" ", words);
        final List<String> sentences = new java.util.ArrayList<>(List.of(storyString.split("\\.")));
        for(int i = 0; i < sentences.size(); i++){
            final String sentence = sentences.get(i).trim();
            if(hasNVerbOrPVerb(sentence)){
                LOGGER.debug("Adding sentence: " + sentence);
            }
            else{
                LOGGER.debug("Dropping sentence: " + sentence);
                sentences.set(i, "");
            }
        }
        return String.join(".", sentences);
    }

    private String[] splitStoryIntoWords(final String story){
        return story.replace(".", " . ")
                .replace(",", " , ")
                .replace("!", " ! ")
                .replace("?", " ? ")
                .split(" ");
    }

    public void addDesired(final List<String> words, final int index){
        final String word = words.get(index);
        // word is noun, verb, article, preposition, conjunction
        if(wordsStore.isNoun(word)){
            LOGGER.info("Adding word because it's noun: " + word);
        }
        else if(wordsStore.isVerb(word)){
            LOGGER.info("Adding word because it's verb: " + word);
        }
        else if(wordsStore.isArticle(word)){
            LOGGER.info("Adding word because it's article: " + word);
        }
        else if(wordsStore.isPreposition(word)){
            LOGGER.info("Adding word because it's preposition: " + word);
        }
        else if(wordsStore.isConjunction(word)){
            LOGGER.info("Adding word because it's conjunction: " + word);
        }
        // word is punctuation
        else if(wordsStore.isPunctuation(word)){
            LOGGER.info("Adding word because it's punctuation: " + word);
        }
        // first letter of the word is capital
        else if(word.length() > 1 && Character.isUpperCase(word.charAt(0))){
            LOGGER.info("Adding word because it's capitalized: " + word);
        }

        // drop the word otherwise
        else {
            words.set(index, "");
        }
    }

    private boolean hasNVerbOrPVerb(final String sentence){
        final String[] words = sentence.split(" ");
        boolean hasNoun = false;
        boolean hasVerb = false;
        boolean hasPronoun = false;
        for(final String word : words){
            if(wordsStore.isNoun(word)){
                hasNoun = true;
            }
            // same word can be noun and verb
            if(wordsStore.isVerb(word)){
                hasVerb = true;
            }
            else if(wordsStore.isPronoun(word)){
                hasPronoun = true;
            }
        }
        return (hasNoun && hasVerb) || (hasVerb && hasPronoun);
    }
}
