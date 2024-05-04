package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import weka.core.Instances;
import java.util.ArrayList;
import java.util.List;

public class TestFeatureVectors {

    // Helper method to create a test token with predefined attributes
    private Token createTestToken(boolean isName, boolean isInDictionary, boolean isLocation, boolean isCommonFirst,
                                  boolean isCommonLast, boolean isHonorific, boolean isPrefix, boolean isSuffix,
                                  boolean isEnglishWord, boolean isKillWord, boolean isPunctuation,
                                  LexicalFeature lexicalFeature, FeatureOfSpeech featureOfSpeech) {
        Token token = new Token("testToken");
        token.setIsName(isName);
        token.setIsInDictionary(isInDictionary);
        token.setIsLocation(isLocation);
        token.setIsCommonFirst(isCommonFirst);
        token.setIsCommonLast(isCommonLast);
        token.setIsHonorific(isHonorific);
        token.setIsPrefix(isPrefix);
        token.setIsSuffix(isSuffix);
        token.setIsEnglishWord(isEnglishWord);
        token.setIsKillWord(isKillWord);
        token.setIsPunctuation(isPunctuation);
        token.setLexicalFeature(lexicalFeature);
        token.setFeatureOfSpeech(featureOfSpeech);
        return token;
    }

    // Test to ensure that the createVectors method returns valid instances
    @Test
    public void testCreateVectors() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(createTestToken(true, true, false, false, false, false, false, false, true, false, false,
                                   LexicalFeature.CAPITALIZEDWORD, FeatureOfSpeech.ARTICLES));
        FeatureVector featureVector = new FeatureVector();
        Instances instances = featureVector.createVectors(tokens);

        assertNotNull(instances, "Instances should not be null");
        assertEquals(1, instances.numInstances(), "There should be one instance created");
        assertEquals(13, instances.numAttributes(), "There should be 13 attributes per instance");

        // Check first instance values
        double[] expectedValues = {1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
                                   LexicalFeature.CAPITALIZEDWORD.ordinal(), FeatureOfSpeech.ARTICLES.ordinal()};
        double[] instanceValues = instances.firstInstance().toDoubleArray();
        assertArrayEquals(expectedValues, instanceValues, "Attribute values do not match expected values");
    }

    // Test handling of an empty token list
    @Test
    public void testEmptyTokenList() {
        FeatureVector featureVector = new FeatureVector();
        Instances instances = featureVector.createVectors(new ArrayList<>());

        assertNotNull(instances, "Instances should not be null even for an empty token list");
        assertEquals(0, instances.numInstances(), "No instances should be created for an empty token list");
    }

    // Test the integrity of attribute definitions
    @Test
    public void testAttributes() {
        FeatureVector featureVector = new FeatureVector();
        Instances instances = featureVector.createVectors(new ArrayList<>()); // Empty list to just get the structure

        String[] expectedNames = {"isName", "isInDictionary", "isLocation", "isCommonFirst", "isCommonLast",
                                  "isHonorific", "isPrefix", "isSuffix", "isEnglishWord", "isKillWord",
                                  "isPunctuation", "lexicalFeature", "speechFeature"};
        List<String> attributeNames = new ArrayList<>();
        for (int i = 0; i < instances.numAttributes(); i++) {
            attributeNames.add(instances.attribute(i).name());
        }

        assertArrayEquals(expectedNames, attributeNames.toArray(new String[0]), "Attribute names do not match expected names");
    }

        // Create a new FeatureVector instance
        @Test
        public void testConstructor() {
    
            FeatureVector featureVector = new FeatureVector();
    
            assertNotNull(featureVector);
            
        }
}
