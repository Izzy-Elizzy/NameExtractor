package edu.odu.cs.cs350;

import java.util.Arrays;


/**
 * The Tagger class provides methods to detect the part of speech or lexical feature
 * of a given word or token.
 */
public class FeatureTag {
    /**
     * Constructs a new Tagger object.
     */
    public FeatureTag() {
    }

    /**
     * Checks if a given word is an article (a, an, the).
     *
     * @param word the word to be checked
     * @return true if the word is an article, false otherwise
     */
    public boolean IsArticle(String word) {
        String[] articles = {"a", "an", "the"};
        return Arrays.asList(articles).contains(word.toLowerCase());
    }

    /**
     * Checks if a given word is a conjunction (and).
     *
     * @param word the word to be checked
     * @return true if the word is a conjunction, false otherwise
     */
    public boolean IsConjunction(String word) {
        String[] conjunctions = {"and"};
        return Arrays.asList(conjunctions).contains(word.toLowerCase());
    }

    /**
     * Checks if a given word is a period.
     *
     * @param word the word to be checked
     * @return true if the word is a period, false otherwise
     */
    public boolean IsPeriod(String word) {
        return "." == word;
    }

    /**
     * Checks if a given word is a comma.
     *
     * @param word the word to be checked
     * @return true if the word is a comma, false otherwise
     */
    public boolean IsComma(String word) {
        return "," == word;
    }

    /**
     * Checks if a given word contains a hyphen.
     *
     * @param word the word to be checked
     * @return true if the word contains a hyphen, false otherwise
     */
    public boolean IsHyphen(String word) {
        return "-" == word;
    }
}