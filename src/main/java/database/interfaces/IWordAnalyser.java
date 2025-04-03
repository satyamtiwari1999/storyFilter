package database.interfaces;

public interface IWordAnalyser {
    boolean isNoun(final String word);
    boolean isVerb(final String word);
    boolean isAdjective(final String word);
    boolean isConjunction(final String word);
    boolean isPreposition(final String word);
    boolean isPronoun(final String word);
    boolean isPunctuation(final String word);
    boolean isAdverb(final String word);

}
