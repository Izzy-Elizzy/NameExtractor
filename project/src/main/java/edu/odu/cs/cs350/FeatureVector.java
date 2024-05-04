package edu.odu.cs.cs350;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import java.util.List;
import java.util.ArrayList;

/**
 * This class creates feature vectors based on token attributes/other 
 * features using Weka's Instances class. 
 */
public class FeatureVector {

    /**
     * Default constructor.
     */
    public FeatureVector(){

    }

    /**
     * Method to create feature vectors based on Token attributes.
     * @param tokenList tokenList
     * @return data
     */
    public Instances createVectors(List<Token> tokenList) {
        ArrayList<Attribute> attributes = createAttributes();
    
        // Create instances object with the defined attributes
        Instances data = new Instances("FeatureVectors", attributes, tokenList.size());
    
        // Find index of 'isName' attribute
        int classIndex = attributes.indexOf(new Attribute("isName"));
        data.setClassIndex(classIndex);
        
        for (Token token : tokenList) {
            double[] values = createAttributeValues(token);
            data.add(new DenseInstance(1.0, values));
        }
    
        return data;
    }

    /**
     * Creates the attributes for the feature vectors.
     * @return List of attributes.
     */
    private ArrayList<Attribute> createAttributes() {
        ArrayList<Attribute> attributes = new ArrayList<>();

        // Boolean attributes from Token class
        attributes.add(new Attribute("isName"));
        attributes.add(new Attribute("isInDictionary"));
        attributes.add(new Attribute("isLocation"));
        attributes.add(new Attribute("isCommonFirst"));
        attributes.add(new Attribute("isCommonLast"));
        attributes.add(new Attribute("isHonorific"));
        attributes.add(new Attribute("isPrefix"));
        attributes.add(new Attribute("isSuffix"));
        attributes.add(new Attribute("isEnglishWord"));
        attributes.add(new Attribute("isKillWord"));
        attributes.add(new Attribute("isPunctuation"));

        // Lexical feature attribute
        ArrayList<String> lexicalFeatureValues = new ArrayList<>();
        lexicalFeatureValues.add("NUMBER");
        lexicalFeatureValues.add("SINGLECAPLETTER");
        lexicalFeatureValues.add("CAPITALIZEDWORD");
        lexicalFeatureValues.add("ALLCAPS");
        lexicalFeatureValues.add("PUNCTUATION");
        lexicalFeatureValues.add("NEWLINE");
        lexicalFeatureValues.add("NULLFEATURE");
        lexicalFeatureValues.add("OTHER");
        attributes.add(new Attribute("lexicalFeature", lexicalFeatureValues));

        // Part of speech feature attribute
        ArrayList<String> speechFeatureValue = new ArrayList<>();
        speechFeatureValue.add("ARTICLES");
        speechFeatureValue.add("CONJUNCTION");
        speechFeatureValue.add("PERIOD");
        speechFeatureValue.add("COMMA");
        speechFeatureValue.add("HYPHEN");
        speechFeatureValue.add("OTHER");
        attributes.add(new Attribute("speechFeature", speechFeatureValue));

        return attributes;
    }

    /**
     * Creates the attribute values for a given token.
     * @param token The token for which attribute values are to be created.
     * @return An array of attribute values.
     */
    private double[] createAttributeValues(Token token) {
        // Create an array of attribute values size depending on how many attributes we have
        double[] values = new double[13];

        values[0] = token.isName() ? 1.0 : 0.0;
        values[1] = token.isInDictionary() ? 1.0 : 0.0;
        values[2] = token.isLocation() ? 1.0 : 0.0;
        values[3] = token.isCommonFirst() ? 1.0 : 0.0;
        values[4] = token.isCommonLast() ? 1.0 : 0.0;
        values[5] = token.isHonorific() ? 1.0 : 0.0;
        values[6] = token.isPrefix() ? 1.0 : 0.0;
        values[7] = token.isSuffix() ? 1.0 : 0.0;
        values[8] = token.getIsEnglishWord() ? 1.0 : 0.0;
        values[9] = token.isKillWord() ? 1.0 : 0.0;
        values[10] = token.isPunctuation() ? 1.0 : 0.0;
        values[11] = token.getLexicalFeature().ordinal();
        values[12] = token.getFeatureOfSpeech().ordinal();

        return values;
    }
}
