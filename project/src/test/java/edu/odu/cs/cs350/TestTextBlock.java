package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weka.core.pmml.Array;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.ByteArrayOutputStream;

public class TestTextBlock {

    @Test
    public void testTextBlockConstructor() {
        String inputTest = "<NER>Ralph, Izzy, Peter</NER>";
        TextBlock inputTxt = new TextBlock(inputTest);
        inputTxt.createTokens(inputTest);

        assertEquals("<NER> <PER>Ralph</PER>,<PER>Izzy</PER>,<PER>Peter</PER></NER>", inputTxt.toString());

    }

    @Test
    public void testCreateTokens() {
        // create a block of words
        String blockOfWords = "This is a block of words";
        TextBlock secondBlockOfText = new TextBlock(blockOfWords);

        // create tokens for the block of words
        secondBlockOfText.createTokens(blockOfWords);
        String subStringOfBlock = "This";

        // check if the first token is equal to the first word in the block of words
        assertEquals(subStringOfBlock, secondBlockOfText.getTokensList().get(0).getTokenString());

    }

    @Test
    public void testSetTokens() {
        String originalText = "This is to test set tokens function";
        TextBlock aBlockOfText = new TextBlock(originalText);

        List<Token> originalTokens = aBlockOfText.createTokens(originalText);
        aBlockOfText.setTokensList(originalTokens);

        List<Token> newTokens = aBlockOfText.createTokens("new tokens for test");
        aBlockOfText.setTokensList(newTokens);
        assertEquals("new", aBlockOfText.getTokensList().get(0).getTokenString());
    }

    @Test
    public void testToString() {
        String text = "<NER>my name is John!</NER>";
        TextBlock block = new TextBlock(text);
        assertThat(block.toString(), is("<NER> my name is <PER>John</PER>!</NER>"));

        Token John = new Token("John");
        John.setIsName(true);
        John.setIsPunctuation(false);
        Token Jim = new Token("Jim");
        Jim.setIsName(true);
        Jim.setIsPunctuation(false);
        Token Doe = new Token("Doe");
        Doe.setIsName(true);
        Doe.setIsPunctuation(false);
        Token period = new Token(".");
        period.setIsPunctuation(true);
    }

    @Test
    public void testGenerateShingles() {
        List<Token> tokenList = new ArrayList<>();
        List<List<Token>> shingles = new ArrayList<>();
        String text = "<NER>by John Doe, n Lawrence Livermore Laboratory</NER>";
        TextBlock block = new TextBlock(text);
        tokenList = block.getTokensList();
        shingles = block.generateShingles(tokenList, 3, 3);
        for (List<Token> shingle : shingles) {
            for (Token token : shingle) {
                System.out.print(token.toString() + " ");
            }
            System.out.println();
        }

        //In debug console, demonstrates PER tags can be applied at various locations
        System.out.println();
        for (List<Token> shingle : shingles) {
            for (Token token : shingle) {
                System.out.print(token.detectPersonalName() + " ");
            }
            System.out.println();
        }
        

        List<Token> expected = new ArrayList<>();
        expected.add(new Token("null"));
        expected.add(new Token("null"));
        expected.add(new Token("null"));
        expected.add(new Token("by"));
        expected.add(new Token("John"));
        expected.add(new Token("Doe"));
        expected.add(new Token(","));

        assertEquals(shingles.get(0).toString(), expected.toString());

    }

    @Test
    public void testCreateTokensWithLexicalFeatures() {
        String firstTestInput = "This is a test, with some punctuation.";
        String secondTestInput = "Here is a test with an email, someone@odu.edu.";
        String thirdTestInput = "What if we tested something random, nAmes, d4tes, it'll, please work.";
        String fourthTestInput = "\ntest";
        String fifthTestInput = "Where are some numbers and such, A NUMBER 50.";

        TextBlock firstBlock = new TextBlock(firstTestInput);
        TextBlock secondBlock = new TextBlock(secondTestInput);
        TextBlock thirdBlock = new TextBlock(thirdTestInput);
        TextBlock fourthBlock = new TextBlock(fourthTestInput);
        TextBlock fifthBlock = new TextBlock(fifthTestInput);

        // Tokenize the test input
        List<Token> firstTokens = firstBlock.createTokens(firstTestInput);
        List<Token> secondTokens = secondBlock.createTokens(secondTestInput);
        List<Token> thirdTokens = thirdBlock.createTokens(thirdTestInput);
        List<Token> fourthTokens = fourthBlock.createTokens(fourthTestInput);
        List<Token> fifthTokens = fifthBlock.createTokens(fifthTestInput);

        // Assert that number of tokens matches the expected number
        assertEquals(9, firstTokens.size());
        assertEquals(14, secondTokens.size());
        assertEquals(18, thirdTokens.size());
        assertEquals(2, fourthTokens.size());
        assertEquals(11, fifthTokens.size());

        assertEquals(LexicalFeature.CAPITALIZEDWORD, firstTokens.get(0).getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, firstTokens.get(1).getLexicalFeature());
        assertEquals(LexicalFeature.PUNCTUATION, firstTokens.get(4).getLexicalFeature());
        assertEquals(LexicalFeature.PUNCTUATION, firstTokens.get(8).getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, secondTokens.get(9).getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, thirdTokens.get(7).getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, thirdTokens.get(9).getLexicalFeature());
        assertEquals(LexicalFeature.OTHER, thirdTokens.get(11).getLexicalFeature());
        assertEquals(LexicalFeature.NEWLINE, fourthTokens.get(0).getLexicalFeature());
        assertEquals(LexicalFeature.SINGLECAPLETTER, fifthTokens.get(7).getLexicalFeature());
        assertEquals(LexicalFeature.ALLCAPS, fifthTokens.get(8).getLexicalFeature());
        assertEquals(LexicalFeature.NUMBER, fifthTokens.get(9).getLexicalFeature());

    }

    @Test
    public void testDetectPersonalNamesInTextBlock() {
        // test input containing personal names
        String testInput = "<NER> Ralph worked on user story number 7 for sprint 2.</NER>";

        // Create a textblock with test input
        TextBlock block = new TextBlock(testInput);
        List<Token> tokens = block.createTokens(testInput);

        // Detect personal names in the text block
        List<String> detectedPersonalNames = new ArrayList<>();
        for (Token token : tokens) {
            String personalName = token.detectPersonalName();
            detectedPersonalNames.add(personalName);

        }

        // Expected output
        String expectedOutput = "<PER>Ralph</PER> worked on user story number 7 for sprint 2 .";

        // actual string
        StringBuilder detectedOutputBuilder = new StringBuilder();
        for (String personalName : detectedPersonalNames) {
            detectedOutputBuilder.append(personalName).append(" ");

        }
        String detectedOutput = detectedOutputBuilder.toString().trim();

        assertEquals(expectedOutput, detectedOutput);
    }

}
