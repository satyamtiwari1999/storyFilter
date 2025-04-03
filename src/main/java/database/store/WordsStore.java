package database.store;

import database.store.nodes.AdjectiveNode;
import database.store.nodes.AdverbNode;
import database.store.nodes.NounNode;
import database.store.nodes.VerbNode;
import org.slf4j.Logger;

public class WordsStore implements database.interfaces.IWordProcessor, database.interfaces.IWordAnalyser {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WordsStore.class);
    private static final NounNode nounStore = new NounNode();
    private static final VerbNode verbStore = new VerbNode();
    private static final AdjectiveNode adjectiveStore = new AdjectiveNode();
    private static final AdverbNode adverbStore = new AdverbNode();

    @Override
    public void addAdverb(final String adverb) {
        AdverbNode currentAdverbNode = adverbStore;
        for (int i = 0; i < adverb.length(); i++) {
            currentAdverbNode = (AdverbNode) currentAdverbNode.addChild(adverb.charAt(i));
        }
        currentAdverbNode.setIsAdverb(true);
    }

    @Override
    public boolean isAdverb(final String word) {
        AdverbNode currentAdverbNode = adverbStore;
        for (int i = 0; i < word.length(); i++) {
            LOGGER.debug("current node character " + currentAdverbNode.toString());
            if (currentAdverbNode.getChildren().containsKey(word.charAt(i))) {
                currentAdverbNode = (AdverbNode) currentAdverbNode.getChildren().get(word.charAt(i));
            } else {
                LOGGER.debug("Could not find character " + word.charAt(i));
                return false;
            }
        }
        LOGGER.debug("current node character " + currentAdverbNode.toString());
        LOGGER.debug("Word " + word + (currentAdverbNode.getIsAdverb() ? " is" : " is not") + " an adverb");
        return currentAdverbNode.getIsAdverb();
    }
    @Override
    public void addNoun(final String noun) {
        NounNode currentNounNode = nounStore;
        for(int i = 0; i < noun.length(); i++){
            currentNounNode = (NounNode) currentNounNode.addChild(noun.charAt(i));
        }
        currentNounNode.setIsNoun(true);
    }

    @Override
    public void addVerb(final String verb) {
        VerbNode currentVerbNode = verbStore;
        for(int i = 0; i < verb.length(); i++){
            currentVerbNode = (VerbNode) currentVerbNode.addChild(verb.charAt(i));
        }
        currentVerbNode.setIsVerb(true);
    }

    @Override
    public void addAdjective(final String adjective) {
        AdjectiveNode currentAdjectiveNode = adjectiveStore;
        for(int i = 0; i < adjective.length(); i++){
            currentAdjectiveNode = (AdjectiveNode) currentAdjectiveNode.addChild(adjective.charAt(i));
        }
        currentAdjectiveNode.setIsAdjective(true);
    }

    @Override
    public boolean isNoun(final String word) {
        NounNode currentNounNode = nounStore;
        for(int i = 0; i < word.length(); i++) {
            LOGGER.debug("current node character " + currentNounNode.toString());
            if(currentNounNode.getChildren().containsKey(word.charAt(i))){
                currentNounNode = (NounNode) currentNounNode.getChildren().get(word.charAt(i));
            } else {
                LOGGER.debug("Could not find character " + word.charAt(i));
                return false;
            }
        }
        LOGGER.debug("current node character " + currentNounNode.toString());
        LOGGER.debug("Word " + word + (currentNounNode.getIsNoun() ? " is" : " is not") + " a noun");
        return currentNounNode.getIsNoun();
    }

    @Override
    public boolean isVerb(final String word) {
        VerbNode currentVerbNode = verbStore;
        for(int i = 0; i < word.length(); i++){
            LOGGER.debug("current node character " + currentVerbNode.toString());
            if(currentVerbNode.getChildren().containsKey(word.charAt(i))){
                currentVerbNode = (VerbNode) currentVerbNode.getChildren().get(word.charAt(i));
            } else {
                LOGGER.debug("Could not find character " + word.charAt(i));
                return false;
            }
        }
        LOGGER.debug("current node character " + currentVerbNode.toString());
        LOGGER.debug("Word " + word + (currentVerbNode.getIsVerb() ? " is" : " is not") + " a verb");
        return currentVerbNode.getIsVerb();
    }

    @Override
    public boolean isAdjective(String word) {
        AdjectiveNode currentAdjectiveNode = adjectiveStore;
        for(int i = 0; i < word.length(); i++){
            LOGGER.debug("current node character " + currentAdjectiveNode.toString());
            if(currentAdjectiveNode.getChildren().containsKey(word.charAt(i))){
                currentAdjectiveNode = (AdjectiveNode) currentAdjectiveNode.getChildren().get(word.charAt(i));
            } else {
                LOGGER.debug("Could not find character " + word.charAt(i));
                return false;
            }
        }
        LOGGER.debug("current node character " + currentAdjectiveNode.toString());
        LOGGER.debug("Word " + word + (currentAdjectiveNode.getIsAdjective() ? " is" : " is not") + " an adjective");
        return currentAdjectiveNode.getIsAdjective();
    }

    public boolean isPunctuation(final String word) {
        return word.matches("[.,!?]");
    }

    public boolean isConjunction(final String word) {
        return word.toLowerCase().matches("and|or|but");
    }

    public boolean isPreposition(final String word) {
        return word.toLowerCase().matches("in|on|at|since|for|ago|before|to|past|by|until|till|of|off|from|about|with|without|under|over|above|below|beside|between|among|through|into|onto|upon|out|down|up|across|around|after|during|against|along|behind|beneath|besides|inside|near|outside|underneath|within|without");
    }

    @Override
    public boolean isPronoun(final String word) {
        return word.toLowerCase().matches("i|me|my|mine|myself|you|your|yours|yourself|he|him|his|himself|she|her|hers|herself|it|its|itself|we|us|our|ours|ourselves|they|them|their|theirs|themselves");
    }

    public boolean isArticle(final String word) {
        return word.toLowerCase().matches("a|an|the");
    }
}
