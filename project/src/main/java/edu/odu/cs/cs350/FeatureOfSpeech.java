package edu.odu.cs.cs350;

/**
 * Represents a part of speech feature for a word or token.
 */
public enum FeatureOfSpeech {
    /**
     * Indicates that the word or token is an article (e.g., "a", "an", "the").
     */
    ARTICLES,
    /**
     * Indicates that the word or token is a conjunction (e.g., "and", "or", "but").
     */
    CONJUNCTION,
    /**
     * Indicates that the word or token is a period.
     */
    PERIOD,
    /**
     * Indicates that the word or token is a comma.
     */
    COMMA,
    /**
     * Indicates that the word or token contains a hyphen.
     */
    HYPHEN,
    /**
     * Indicates any other part of speech feature not covered by the predefined categories.
     */
    OTHER
}