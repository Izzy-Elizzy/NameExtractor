package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import weka.core.Instances;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestTagger {


    @Test
    public void testIsArticle() {
        //Create Tokens - This Will Be Taken From a TextBlock
        Token testToken0 = new Token("a");
        Token testToken1 = new Token("an");
        Token testToken2 = new Token("the");


        FeatureTag tagger = new FeatureTag();
        assertTrue(tagger.IsArticle(testToken0.getTokenString()));
        assertTrue(tagger.IsArticle(testToken1.getTokenString()));
        assertTrue(tagger.IsArticle(testToken2.getTokenString()));
    }

    @Test
    public void testIsConjunction() {
        //Create Tokens - This Will Be Taken From a TextBlock
        Token testToken0 = new Token("and");


        FeatureTag tagger = new FeatureTag();
        assertTrue(tagger.IsConjunction(testToken0.getTokenString()));
    }

    @Test
    public void testIsPeriod() {
        //Create Tokens - This Will Be Taken From a TextBlock
        Token testToken0 = new Token(".");


        FeatureTag tagger = new FeatureTag();
        assertTrue(tagger.IsPeriod(testToken0.getTokenString()));
    }

    @Test
    public void testIsComma() {
        //Create Tokens - This Will Be Taken From a TextBlock
        Token testToken0 = new Token(",");


        FeatureTag tagger = new FeatureTag();
        assertTrue(tagger.IsComma(testToken0.getTokenString()));
    }

    @Test
    public void testIsHyphen() {
        //Create Tokens - This Will Be Taken From a TextBlock
        Token testToken0 = new Token("-");


        FeatureTag tagger = new FeatureTag();
        assertTrue(tagger.IsHyphen(testToken0.getTokenString()));
    }
    
    // @Test
    // public void testDetectFeatureOffSpeech() {
    //     //Create Tokens - This Will Be Taken From a TextBlock
    //     Token testToken0 = new Token("a");
    //     Token testToken1 = new Token("an");
    //     Token testToken2 = new Token("the");
    //     Token testToken3 = new Token("and");
    //     Token testToken4 = new Token(".");
    //     Token testToken5 = new Token(",");
    //     Token testToken6 = new Token("-");
    // }

    
}
