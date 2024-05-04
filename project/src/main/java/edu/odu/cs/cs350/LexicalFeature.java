package edu.odu.cs.cs350;

/**
 * Represents a lexical feature in a word or token.
 */
public enum LexicalFeature {
    /**
     * Indicates that the word or token is a number.
     */
    NUMBER,

    /**
     * Indicates that the word or token is punctuation.
     */
    PUNCTUATION,

    /**
     * Indicates that the word or token is a single capital letter.
     */
    SINGLECAPLETTER,

    /**
     * Indicates that the word or token is capitalized.
     */
    CAPITALIZEDWORD,

    /**
     * Indicates that the word or token is in all caps.
     */
    ALLCAPS,

    /**
     * Indicates that the word or token is a newline character.
     */
    NEWLINE,

    /**
     * Indicates that the lexical feature is null or undefined.
     */
    NULLFEATURE,

    /**
     * Indicates any other lexical feature not covered by the predefined categories.
     */
    OTHER
}