package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import weka.core.pmml.jaxbbindings.True;

public class TestToken {

    @Test
    public void testConstructor() {
        String string = "token";
        Token theToken = new Token(string);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetTokenString() {
        String string = " ";
        Token theToken = new Token(string);
        theToken.setTokenString("token");
        assertEquals("token", theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsName() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsName(true);
        assertEquals(string, theToken.getTokenString());
        assertTrue(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsInDictionary() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsInDictionary(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertTrue(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsLocation() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsLocation(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertTrue(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsCommonFirst() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsCommonFirst(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertTrue(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsCommonLast() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsCommonLast(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertTrue(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsHonorific() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsHonorific(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertTrue(theToken.isHonorific());
        assertFalse(theToken.isPunctuation());
    }

    @Test
    public void testSetIsPunctuation() {
        String string = "token";
        Token theToken = new Token(string);
        theToken.setIsPunctuation(true);
        assertEquals(string, theToken.getTokenString());
        assertFalse(theToken.isName());
        assertFalse(theToken.isInDictionary());
        assertFalse(theToken.isLocation());
        assertFalse(theToken.isCommonFirst());
        assertFalse(theToken.isCommonLast());
        assertFalse(theToken.isHonorific());
        assertTrue(theToken.isPunctuation());
    }

    @Test
    public void testLexicalFeatures() {
        // Test input strings representing representing various lexical features
        String testInput = "10";
        String punctuationMarkInput = "!";
        String singleCapLetterInput = "A";
        String aCapitalizedWordInput = "Word";
        String allCapsInput = "HELLO";
        String newLineInput = "\n";
        String nullInput = "";
        String otherInput = "h.e.l.l.o";

        // creating a new Token object with test input with various lexical Features
        Token token = new Token(testInput);
        Token puncToken = new Token(punctuationMarkInput);
        Token singleCapToken = new Token(singleCapLetterInput);
        Token capWordToken = new Token(aCapitalizedWordInput);
        Token allCapToken = new Token(allCapsInput);
        Token newLineToken = new Token(newLineInput);
        Token nullToken = new Token(nullInput);
        Token otherToken = new Token(otherInput);

        // check if lexical feature associated with the token is equal to certain
        // lexical feature
        assertEquals(LexicalFeature.NUMBER, token.getLexicalFeature());
        assertEquals(LexicalFeature.PUNCTUATION, puncToken.getLexicalFeature());
        assertEquals(LexicalFeature.SINGLECAPLETTER, singleCapToken.getLexicalFeature());
        assertEquals(LexicalFeature.CAPITALIZEDWORD, capWordToken.getLexicalFeature());
        assertEquals(LexicalFeature.ALLCAPS, allCapToken.getLexicalFeature());
        assertEquals(LexicalFeature.NEWLINE, newLineToken.getLexicalFeature());
        assertEquals(LexicalFeature.NULLFEATURE, nullToken.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, otherToken.getLexicalFeature());

    }

    @Test
    public void testDetectLexicalFeatureNumber() {
        String testRepOfNumber = "10";
        String testLeadingZeroNumber = "007";
        String negativeInteger = "-456";
        String floatingPointNumber = "3.14";

        Token numbToken = new Token(testRepOfNumber);
        Token leadingZToken = new Token(testLeadingZeroNumber);
        Token negativeToken = new Token(negativeInteger);
        Token floatingToken = new Token(floatingPointNumber);

        numbToken.detectLexicalFeature();
        leadingZToken.detectLexicalFeature();
        negativeToken.detectLexicalFeature();
        floatingToken.detectLexicalFeature();

        assertEquals(LexicalFeature.NUMBER, numbToken.getLexicalFeature());
        assertEquals(LexicalFeature.NUMBER, leadingZToken.getLexicalFeature());
        assertEquals(LexicalFeature.NUMBER, negativeToken.getLexicalFeature());
        assertEquals(LexicalFeature.NUMBER, floatingToken.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeatureCapWord() {
        String capitalizedWordInput = "Hello";
        String textSingleCapitalizedLetter = "World";

        Token capitalizedWordToken = new Token(capitalizedWordInput);
        Token singleCapToken = new Token(textSingleCapitalizedLetter);
        capitalizedWordToken.detectLexicalFeature();
        singleCapToken.detectLexicalFeature();

        assertEquals(LexicalFeature.CAPITALIZEDWORD, capitalizedWordToken.getLexicalFeature());
        assertEquals(LexicalFeature.CAPITALIZEDWORD, singleCapToken.getLexicalFeature());

    }

    @Test
    public void testDetectLexicalFeatureAllCaps() {
        String allCapsWord = "TEST";
        String allCapsWordStringInput = "BUILD";

        Token allCapsWordToken = new Token(allCapsWord);
        Token allCapitalizedWordToken = new Token(allCapsWordStringInput);
        allCapsWordToken.detectLexicalFeature();
        allCapitalizedWordToken.detectLexicalFeature();

        assertEquals(LexicalFeature.ALLCAPS, allCapsWordToken.getLexicalFeature());
        assertEquals(LexicalFeature.ALLCAPS, allCapitalizedWordToken.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeatureSingleCap() {
        String singleLetterInput = "L";
        String secondSingleLetterInput = "M";

        Token tokenizeSingleLetterInput = new Token(singleLetterInput);
        Token tokenizeSecondSingleLetterInput = new Token(secondSingleLetterInput);

        tokenizeSingleLetterInput.detectLexicalFeature();
        tokenizeSecondSingleLetterInput.detectLexicalFeature();

        assertEquals(LexicalFeature.SINGLECAPLETTER, tokenizeSingleLetterInput.getLexicalFeature());
        assertEquals(LexicalFeature.SINGLECAPLETTER, tokenizeSecondSingleLetterInput.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeaturePunctuation() {
        String testComma = ",";
        String testPeriod = ".";
        String testExclamationMark = "!";

        Token commaToken = new Token(testComma);
        Token periodToken = new Token(testPeriod);
        Token exclamationMarkToken = new Token(testExclamationMark);

        commaToken.detectLexicalFeature();
        periodToken.detectLexicalFeature();
        exclamationMarkToken.detectLexicalFeature();

        assertEquals(LexicalFeature.PUNCTUATION, commaToken.getLexicalFeature());
        assertEquals(LexicalFeature.PUNCTUATION, periodToken.getLexicalFeature());
        assertEquals(LexicalFeature.PUNCTUATION, exclamationMarkToken.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeatureNewLine() {
        String testNewLine = "\n";

        Token tokenizeNewLine = new Token(testNewLine);

        tokenizeNewLine.detectLexicalFeature();

        assertEquals(LexicalFeature.NEWLINE, tokenizeNewLine.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeatureNullFeature() {
        String testNullFeature = "";

        Token tokenizeNullFeature = new Token(testNullFeature);

        tokenizeNullFeature.detectLexicalFeature();

        assertEquals(LexicalFeature.NULLFEATURE, tokenizeNullFeature.getLexicalFeature());
    }

    @Test
    public void testDetectLexicalFeatureOther() {
        String firstTestOther = "a";
        String secondTestOther = "words";
        String thirdTestOther = "TEST123";
        String fourthTestOther = "don't";
        String fifthTestOther = "odu.edu";
        String sixthTestOther = "tEst";

        Token tokenizeFirstOther = new Token(firstTestOther);
        Token tokenizeSecondOther = new Token(secondTestOther);
        Token tokenizeThirdOther = new Token(thirdTestOther);
        Token tokenizeFourthOther = new Token(fourthTestOther);
        Token tokenizeFifthOther = new Token(fifthTestOther);
        Token tokenizeSixthOther = new Token(sixthTestOther);

        tokenizeFirstOther.detectLexicalFeature();
        tokenizeSecondOther.detectLexicalFeature();
        tokenizeThirdOther.detectLexicalFeature();
        tokenizeFourthOther.detectLexicalFeature();
        tokenizeFifthOther.detectLexicalFeature();
        tokenizeSixthOther.detectLexicalFeature();

        assertEquals(LexicalFeature.OTHER, tokenizeFirstOther.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, tokenizeSecondOther.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, tokenizeThirdOther.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, tokenizeFourthOther.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, tokenizeFifthOther.getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, tokenizeSixthOther.getLexicalFeature());
    }

    @Test
    public void testGetFeatureOfSpeech() {
        Token token = new Token("randomString");

        assertEquals(FeatureOfSpeech.OTHER, token.getFeatureOfSpeech());
    }

    @Test
    public void testSetFeatureOfSpeech() {
        Token token = new Token("and");

        assertEquals(FeatureOfSpeech.CONJUNCTION, token.getFeatureOfSpeech());
    }

    @Test
    public void testDetectFeatureOfSpeech() {
        String articleA = "a";
        String articleAn = "an";
        String articleThe = "the";
        String conjunction = "and";
        String periodPunctuation = ".";
        String commaPunctuation = ",";
        String hyphenPunctuation = "-";

        // Create tokens
        Token tokenizeArticleA = new Token(articleA);
        Token tokenizeArticleAn = new Token(articleAn);
        Token tokenizeArticleThe = new Token(articleThe);
        Token tokenizeConjunction = new Token(conjunction);
        Token tokenizePeriodPunctuation = new Token(periodPunctuation);
        Token tokenizeCommaPunctuation = new Token(commaPunctuation);
        Token tokenizeHyphenPunctuation = new Token(hyphenPunctuation);

        assertEquals(FeatureOfSpeech.ARTICLES, tokenizeArticleA.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.ARTICLES, tokenizeArticleAn.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.ARTICLES, tokenizeArticleThe.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.CONJUNCTION, tokenizeConjunction.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.PERIOD, tokenizePeriodPunctuation.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.COMMA, tokenizeCommaPunctuation.getFeatureOfSpeech());
        assertEquals(FeatureOfSpeech.HYPHEN, tokenizeHyphenPunctuation.getFeatureOfSpeech());

    }

    @Test
    public void testDetectPersonalName() {
        // Test input containing a personal name
        String testInputWithPersonalNames = "Ralph";
        String testOtherPersonalNameInput = "Matthew";
        String testAnotherPersonalNameInput = "T";
        String testLastPersonalNameInput = "MAH";

        // Create a token with the test input
        Token tokenizePersonalNameInput = new Token(testInputWithPersonalNames);
        Token tokenizeToken = new Token(testOtherPersonalNameInput);
        Token tokenizeAnotherToken = new Token(testAnotherPersonalNameInput);
        Token tokenizeLastToken = new Token(testLastPersonalNameInput);
        tokenizePersonalNameInput.detectLexicalFeature();
        tokenizeToken.detectLexicalFeature();
        tokenizeAnotherToken.detectLexicalFeature();
        tokenizeLastToken.detectLexicalFeature();

        // check if detected lexical feature suggests its likely a personal name
        boolean result = tokenizePersonalNameInput.isLikelyPersonalName(tokenizePersonalNameInput.getLexicalFeature(),
                tokenizePersonalNameInput.getFeatureOfSpeech());
        boolean result2 = tokenizeToken.isLikelyPersonalName(tokenizeToken.getLexicalFeature(),
                tokenizeToken.getFeatureOfSpeech());
        boolean result3 = tokenizeAnotherToken.isLikelyPersonalName(tokenizeAnotherToken.getLexicalFeature(),
                tokenizeAnotherToken.getFeatureOfSpeech());
        boolean result4 = tokenizeLastToken.isLikelyPersonalName(tokenizeLastToken.getLexicalFeature(),
                tokenizeLastToken.getFeatureOfSpeech());

        // detect personal name based on lexical features: Capitalized Word, ALL-CAPS,
        // OR SingleCap
        String personalNameDetectResult = tokenizePersonalNameInput.detectPersonalName();
        String detectOtherPersonalName = tokenizeToken.detectPersonalName();
        String detectAnotherPersonalName = tokenizeAnotherToken.detectPersonalName();
        String detectLastPersonalName = tokenizeLastToken.detectPersonalName();

        // assert that detected lexical feature suggests a likely personal name
        assertEquals(true, result);
        assertEquals(true, result2);
        assertEquals(true, result3);
        assertEquals(true, result4);

        // assert that the personal name is correctly wrapped in <PER> tags
        assertEquals("<PER>Ralph</PER>", personalNameDetectResult);
        assertEquals("<PER>Matthew</PER>", detectOtherPersonalName);
        assertEquals("<PER>T</PER>", detectAnotherPersonalName);
        assertEquals("<PER>MAH</PER>", detectLastPersonalName);

    }

    @Test
    public void testDetectCommonFirstName() {
        String token = "John";
        Token tokenizePersonalNameInput = new Token(token);
        Token testNonCommonFirstName = new Token("johnny123");
        tokenizePersonalNameInput.detectCommonFirstName();
        testNonCommonFirstName.detectCommonFirstName();

        assertTrue(tokenizePersonalNameInput.isCommonFirst());
        assertFalse(testNonCommonFirstName.isCommonFirst());
    }

    @Test
    public void testDetectCommonLastName() {
        String token = "Washington";
        Token tokenizer = new Token(token);
        tokenizer.detectCommonLastName();

        assertTrue(tokenizer.isCommonLast());

    }

    @Test
    public void testGetisEnglishWord() {
        Token test_token = new Token("hero");
        assertEquals(true, test_token.getIsEnglishWord());
    }

    @Test
    public void testSetisEnglishWord() {
        Token test_token = new Token("hero");
        test_token.setIsEnglishWord(true);
        assertTrue(test_token.getIsEnglishWord());
    }

    @Test
    public void testDetectEnglishWord() {
        Token test_token = new Token("hero");
        Token test_token2 = new Token("marshussy");
        assertTrue(test_token.getIsEnglishWord());
        assertFalse(test_token2.getIsEnglishWord());
    }

    @Test
    public void testDetectCitiesAndStates() {
        Token test_token = new Token("Virginia");
        Token test_token2 = new Token("Hampton");
        Token test_token3 = new Token("words");
        assertTrue(test_token.isLocation());
        assertTrue(test_token2.isLocation());
        assertFalse(test_token3.isLocation());
    }

    @Test
    public void testDetectCountriesAndTerritories() {
        Token test_token = new Token("Canada");
        Token test_token2 = new Token("Puerto Rico");
        Token test_token3 = new Token("words");
        assertTrue(test_token.isLocation());
        assertTrue(test_token2.isLocation());
        assertFalse(test_token3.isLocation());
    }

    @Test
    public void testDetectPlaces() {
        Token test_token = new Token("Aajker Creek");
        Token test_token2 = new Token("words");
        assertTrue(test_token.isLocation());
        assertFalse(test_token2.isLocation());
    }

    @Test
    public void testIsKnownAuthor() {

        String authorName = "Dr. John Smith";
        String secondAuthorName = "Mr. Jack London";

        Token token = new Token(authorName);
        Token secondAuthor = new Token(secondAuthorName);
        token.detectKnownAuthor();
        secondAuthor.detectKnownAuthor();

        assertTrue(token.isKnownAuthor());
        assertTrue(secondAuthor.isKnownAuthor());

    }

    @Test
    public void testKillWord() {
        Token test_token = new Token("John");
        Token test_token2 = new Token("Grumman");
        Token test_token3 = new Token("Student");
        assertFalse(test_token.isKillWord());
        assertTrue(test_token2.isKillWord());
        assertTrue(test_token3.isKillWord());
    }

}
