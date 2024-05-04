package edu.odu.cs.cs350;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This class represents a token object.
 * It contains boolean values for various token attributes.
 * It also contains a string representation of the token.
 */
public class Token {
    /**
     * The token string representation.
     * This is the raw string value of the token.
     */
    private String tokenString = null;

    /**
     * Flag indicating whether the token represents a name.
     */
    private boolean nameFlag;

    /**
     * Flag indicating whether the token is found in the dictionary.
     */
    private boolean inDictionary;

    /**
     * Flag indicating whether the token represents a location.
     */
    private boolean locationFlag;

    /**
     * Flag indicating whether the token is a common first name.
     */
    private boolean commonFirst;

    /**
     * Flag indicating whether the token is a common last name.
     */
    private boolean commonLast;

    /**
     * Flag indicating whether the token has an honorific (e.g., "Mr.", "Dr.").
     */
    private boolean honorificFlag;

    /**
     * Flag indicating whether the token has an prefix (e.g., "la, el, de").
     */
    private boolean prefixFlag;

    /**
     * Flag indicating whether the token has an suffix (e.g., "Jr, Sr, II").
     */
    private boolean suffixFlag;

    /**
     * Flag indicating whether the token is a "kill word" that should be ignored.
     */
    private boolean killWordFlag;

    /**
     * Flag indicating whether the token contains punctuation.
     */
    private boolean punctuationFlag;

    /**
     * The lexical feature of the token.
     */
    private LexicalFeature lexicalFeature;

    /**
     * The part of speech feature of the token.
     */
    private FeatureOfSpeech speechFeature;

    /**
     * Flag indicating whether the assinged string is possibly part of the english
     * language.
     * 
     */
    private boolean isEnglishWord;

    /**
     * Flag indicating whether the assinged string is possibly an known author.
     * 
     * 
     */
    private boolean knownAuthorFlag;

    /**
     * Constructor for Token.
     * 
     * @param assignedString string to assign to token
     */
    public Token(String assignedString) {
        this.tokenString = assignedString;
        this.inDictionary = false;
        this.locationFlag = false;
        this.commonFirst = false;
        this.commonLast = false;
        this.honorificFlag = false;
        this.prefixFlag = false;
        this.suffixFlag = false;
        this.killWordFlag = false;
        this.punctuationFlag = false;
        this.isEnglishWord = false;
        this.knownAuthorFlag = false;
        detectFeatureOfSpeech();
        detectLexicalFeature();
        detectCommonFirstName();
        detectEnglishWord();
        detectCommonLastName();
        detectCitiesAndStates();
        detectCountriesAndTerritories();
        detectPlaces();
        detectKillWord();
        detectHonorific();

    }

    /**
     * @return the string of the token
     */
    public String getTokenString() {
        return this.tokenString;
    }

    /**
     * @return true if is a name, false if else
     */
    public boolean isName() {
        return this.nameFlag;
    }

    /**
     * @return true of dictionary, false if not
     */
    public boolean isInDictionary() {
        return this.inDictionary;
    }

    /**
     * @return true if is a location, false if not
     */
    public boolean isLocation() {
        return this.locationFlag;
    }

    /**
     * @return true if a common first name, false if not
     */
    public boolean isCommonFirst() {
        return this.commonFirst;
    }

    /**
     * @return true if common last name, false if not
     */
    public boolean isCommonLast() {
        return this.commonLast;
    }

    /**
     * @return true if a honorific, false if not
     */
    public boolean isHonorific() {
        return this.honorificFlag;
    }

    /**
     * @return true if a Prefix, false if not
     */
    public boolean isPrefix() {
        return this.prefixFlag;
    }

    /**
     * @return true if a suffix, false if not
     */
    public boolean isSuffix() {
        return this.suffixFlag;
    }

    /**
     * @return true if a kill word, false if not
     */
    public boolean isKillWord() {
        return this.killWordFlag;
    }

    /**
     * @return true if a puncuation, false if not
     */
    public boolean isPunctuation() {
        return this.punctuationFlag;
    }

    /**
     * @return true if a known author, false if not
     */
    public boolean isKnownAuthor() {
        return this.knownAuthorFlag;
    }

    /**
     * Sets the TokenString of a Token.
     * 
     * @param string string representation of Token.
     */
    public void setTokenString(String string) {
        this.tokenString = string;
    }

    /**
     * Sets the Bool isName of a Token.
     * 
     * @param name boolean isName.
     */
    public void setIsName(boolean name) {
        this.nameFlag = name;
    }

    /**
     * Sets the Bool inDictionary of Token.
     * 
     * @param dictionary is this token in the dictionary.
     */
    public void setIsInDictionary(boolean dictionary) {
        this.inDictionary = dictionary;
    }

    /**
     * Sets the Bool isLocation of Token.
     * 
     * @param location is this token a location.
     */
    public void setIsLocation(boolean location) {
        this.locationFlag = location;
    }

    /**
     * Sets the Bool commonFirst of a Token.
     * 
     * @param first is this a common first name.
     */
    public void setIsCommonFirst(boolean first) {
        this.commonFirst = first;
    }

    /**
     * Sets the Bool commonLast of a Token.
     * 
     * @param last is this a common last name.
     */
    public void setIsCommonLast(boolean last) {
        this.commonLast = last;
    }

    /**
     * Sets the Bool honorific of a Token.
     * 
     * @param honorific is this a honorific.
     */
    public void setIsHonorific(boolean honorific) {
        this.honorificFlag = honorific;
    }

    /**
     * Sets the Bool prefix of a Token.
     * 
     * @param prefix is this a prefix.
     */
    public void setIsPrefix(boolean prefix) {
        this.prefixFlag = prefix;
    }

    /**
     * Sets the Bool suffix of a Token.
     * 
     * @param suffix is this a suffix.
     */
    public void setIsSuffix(boolean suffix) {
        this.suffixFlag = suffix;
    }

    /**
     * Sets the Bool killWord of a Token.
     * 
     * @param killWord is this a kill word.
     */
    public void setIsKillWord(boolean killWord) {
        this.killWordFlag = killWord;
    }

    /**
     * Sets the Bool isPunctuation of a Token.
     * 
     * @param punctuation is this punctuation.
     */
    public void setIsPunctuation(boolean punctuation) {
        this.punctuationFlag = punctuation;
    }

    /**
     * @return string representation of a Token.
     */
    @Override
    public String toString() {
        return this.tokenString;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Gets the lexical feature associated with this token.
     * 
     * @return the lexical feature of this token.
     */
    public LexicalFeature getLexicalFeature() {
        return lexicalFeature;
    }

    /**
     * Setter for lexical features.
     * 
     * @param lexicalFeature lexical feature.
     */
    public void setLexicalFeature(LexicalFeature lexicalFeature) {
        this.lexicalFeature = lexicalFeature;
    }

    /**
     * Assigns a Lexical Feature Type to the Token.
     */
    public void detectLexicalFeature() {
        if (tokenString.matches("-?\\d+(\\.\\d+)?")) {
            lexicalFeature = LexicalFeature.NUMBER;
        } else if (tokenString.length() == 1 && Character.isUpperCase(tokenString.charAt(0))) {
            lexicalFeature = LexicalFeature.SINGLECAPLETTER;
        } else if (tokenString.matches("[A-Z][a-z]*")) {
            lexicalFeature = LexicalFeature.CAPITALIZEDWORD;
        } else if (tokenString.matches("[A-Z]+")) {
            lexicalFeature = LexicalFeature.ALLCAPS;
        } else if (Pattern.matches(".*[.,?!]$", tokenString)) {
            lexicalFeature = LexicalFeature.PUNCTUATION;
        } else if (tokenString.equals("\n")) {
            lexicalFeature = LexicalFeature.NEWLINE;
        } else if (tokenString.isEmpty()) {
            lexicalFeature = LexicalFeature.NULLFEATURE;
        } else {
            lexicalFeature = LexicalFeature.OTHER;
        }

    }

    /**
     * Sets the Feature Of Speech Attribute.
     * 
     * @param feature feature
     */
    public void setFeatureOfSpeech(FeatureOfSpeech feature) {
        speechFeature = feature;
    }

    /**
     * Gets the Feature of Speech Attribute.
     * 
     * @return FeatureOfSpeech speechFeature
     */
    public FeatureOfSpeech getFeatureOfSpeech() {
        return speechFeature;
    }

    /**
     * Assigns the Token a Feature of Speech Type.
     */
    public void detectFeatureOfSpeech() {

        FeatureTag tag = new FeatureTag();

        if (tag.IsArticle(tokenString)) {
            setFeatureOfSpeech(FeatureOfSpeech.ARTICLES);
        } else if (tag.IsConjunction(tokenString)) {
            setFeatureOfSpeech(FeatureOfSpeech.CONJUNCTION);
        } else if (tag.IsPeriod(tokenString)) {
            setFeatureOfSpeech(FeatureOfSpeech.PERIOD);
        } else if (tag.IsComma(tokenString)) {
            setFeatureOfSpeech(FeatureOfSpeech.COMMA);
        } else if (tag.IsHyphen(tokenString)) {
            setFeatureOfSpeech(FeatureOfSpeech.HYPHEN);
        } else {
            setFeatureOfSpeech(FeatureOfSpeech.OTHER);
        }
    }

    /**
     * Detects if the token's lexical features suggest it represents a personal
     * name.
     * and wraps the token string in PER tags if it is likely a personal name.
     * 
     * @return the token string wrapped in PER tags if it's likely a personal name.
     */
    public String detectPersonalName() {
        // Check lexical feature of the token
        LexicalFeature lexicalFeature = this.getLexicalFeature();
        FeatureOfSpeech featureOfSpeech = this.getFeatureOfSpeech();
        // Check if the lexical feature suggests it is likely a personal name
        if (isLikelyPersonalName(lexicalFeature, featureOfSpeech)) {
            return "<PER>" + this.getTokenString() + "</PER>" + "";
        } else {
            return this.getTokenString(); // else return the token without per tags
        }

    }

    /**
     * Determine if the provided lexical feature, feature of speech is likely to.
     * represent a personal.
     * name.
     * 
     * @param lexicalFeature  lexical feature to be evaluated.
     * @param featureOfSpeech speech feature to be evaluated.
     * @return True if the lexical feature is either Capitalized word, All caps, or
     *         Single capitalized letter and the feature of speech is not an
     *         article, conjunction, period, comma or hyphen.
     */
    public boolean isLikelyPersonalName(LexicalFeature lexicalFeature, FeatureOfSpeech featureOfSpeech) {
        return lexicalFeature == LexicalFeature.CAPITALIZEDWORD
                || lexicalFeature == LexicalFeature.ALLCAPS
                || lexicalFeature == LexicalFeature.SINGLECAPLETTER &&
                        (featureOfSpeech != FeatureOfSpeech.ARTICLES &&
                                featureOfSpeech != FeatureOfSpeech.CONJUNCTION &&
                                featureOfSpeech != FeatureOfSpeech.PERIOD &&
                                featureOfSpeech != FeatureOfSpeech.COMMA &&
                                featureOfSpeech != FeatureOfSpeech.HYPHEN);

    }

    /**
     * Detects Common First Name.
     */
    public void detectCommonFirstName() {
        Iterable<String> commonFirstNames = WordLists.commonFirstNames();
        for (String name : commonFirstNames) {
            if (tokenString.equalsIgnoreCase(name)) {
                commonFirst = true;
                return;
            }
        }
        commonFirst = false;
    }

    /**
     * Detects Common Last Name.
     */
    public void detectCommonLastName() {
        Iterable<String> commonLastNames = WordLists.commonLastNames();
        for (String lastNames : commonLastNames) {
            if (tokenString.equalsIgnoreCase(lastNames)) {
                commonLast = true;
                return;
            }
        }
        commonLast = false;
    }

    /**
     * Gets English Param. 
     * @return true if english word
     */
    public boolean getIsEnglishWord() {
        return isEnglishWord;
    }

    /**
     * Sets Englishword Param.
     * @param isEqualTo compare to isEnglishWord
     */
    public void setIsEnglishWord(boolean isEqualTo) {
        isEnglishWord = isEqualTo;
    }

    /**
     * Detects English Words.
     */
    public void detectEnglishWord() {
        Iterable<String> englishDictionary = WordLists.englishDictionary();
        for (String dictionaryWord : englishDictionary) {
            if (dictionaryWord.equalsIgnoreCase(tokenString)) {
                setIsEnglishWord(true);
                return;
            }
        }
        setIsEnglishWord(false);
        return;
    }

    /**
     * Checks library for cities and states.
     *
     */
    public void detectCitiesAndStates() {
        Iterable<String> citiesAndStates = WordLists.citiesAndStatesUS();
        for (String cityOrState : citiesAndStates) {
            if (cityOrState.equalsIgnoreCase(tokenString)) {
                setIsLocation(true);
                return;
            }
        }
        setIsLocation(false);
        return;
    }

    /**
     * Checks library for countries and territories.
     * 
     */
    public void detectCountriesAndTerritories() {
        Iterable<String> countriesAndTerritories = WordLists.countriesAndTerritories();
        for (String countryOrTerritory : countriesAndTerritories) {
            if (countryOrTerritory.equalsIgnoreCase(tokenString)) {
                setIsLocation(true);
                return;
            }
        }
        setIsLocation(false);

    }

    /**
     * Checks library for geographical places.
     *
     */
    public void detectPlaces() {
        Iterable<String> places = WordLists.places();
        for (String place : places) {
            if (place.equalsIgnoreCase(tokenString)) {
                setIsLocation(true);
                return;
            }
        }
        setIsLocation(false);
        return;
    }

    /**
     * Checks for Honorifics.
     */
    public void detectHonorific() {
        Iterable<String> Honorific = WordLists.honorifics();
        for (String honorific : Honorific) {
            if (honorific.equalsIgnoreCase(tokenString)) {
                setIsHonorific(true);
                return;
            }
        }
        setIsHonorific(false);
        return;
    }

    /**
     * Detects Prefix.
     */
    public void detectPrefix() {
        Iterable<String> lastNamePrefixes = WordLists.lastNamePrefixes();
        for (String Prefix : lastNamePrefixes) {
            if (tokenString.equalsIgnoreCase(Prefix)) {
                prefixFlag = true;
                return;
            }
        }
        prefixFlag = false;
        return;
    }

    /**
     * Detects Suffix.
     */
    public void detectSuffix() {
        Iterable<String> lastNameSuffix = WordLists.lastNameSuffixes();
        for (String Suffix : lastNameSuffix) {
            if (tokenString.equalsIgnoreCase(Suffix)) {
                suffixFlag = true;
                return;
            }
        }
        suffixFlag = false;
        return;
    }

    /**
     * Compares tokenString with a list of known kill words.
     */
    public void detectKillWord() {
        Iterable<String> notPeople = WordLists.nonPersonalIdentifierCues();
        for (String notPerson : notPeople) {
            if (notPerson.equalsIgnoreCase(tokenString)) {
                setIsKillWord(true);
                return;
            }
        }
        setIsKillWord(false);
        return;
    }

    /**
     * Checks for a possible author. Returns bool
     */
    public void detectKnownAuthor() {

        String[] tokens = tokenString.split("\\s+");
        boolean hasHonorific = false;
        boolean hasFirstName = false;
        boolean hasLastName = false;

        // check if any token is an honorific
        for (String token : tokens) {
            for (String honorific : WordLists.honorifics()) {
                if (token.equalsIgnoreCase(honorific)) {
                    hasHonorific = true;
                    break;
                }
            }
        }

        // check if any token is a common first name
        for (String token : tokens) {
            for (String firstName : WordLists.firstNames()) {
                if (token.equalsIgnoreCase(firstName)) {
                    hasFirstName = true;
                    break;
                }
            }
        }

        // Check if any token is a common last name
        for (String token : tokens) {
            for (String lastName : WordLists.lastNames()) {
                if (token.equalsIgnoreCase(lastName)) {
                    hasLastName = true;
                    break;
                }
            }
        }

        // set knownAuthorFlag if token contains an honorific, common first name, and
        // common last name.
        knownAuthorFlag = hasHonorific && hasFirstName && hasLastName;

    }

}