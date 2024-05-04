package edu.odu.cs.cs350;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testWordLists {
    @Test
    public void testSetIsCommonFirstNamesFileLoading() {
        // String fileName = "/Dictionary.commonFirstNames.txt";
        Iterable resourceUrl = WordLists.commonFirstNames();
        assertNotNull(resourceUrl, "Failed to load resource file");
        assertEquals(resourceUrl.iterator().next(), "mary");
    }

    @Test
    public void testSetIsCommonLastNamesFileLoading() {
        // String fileName = "/Dictionary.commonFirstNames.txt";
        Iterable resourceUrl = WordLists.commonLastNames();
        assertNotNull(resourceUrl, "Failed to load resource file");
        assertEquals(resourceUrl.iterator().next(), "smith");
    }

    @Test
    public void testDetectHonorific() {
        Token token = new Token("Dr.");
        token.detectHonorific();
        assertEquals(true, token.isHonorific());

        token = new Token("Mr.");
        token.detectHonorific();
        assertEquals(true, token.isHonorific());

        token = new Token("Mrs.");
        token.detectHonorific();
        assertEquals(true, token.isHonorific());

    }

    @Test
    public void testDetectPrefix() {
        Token token = new Token("la");
        token.detectPrefix();
        assertEquals(true, token.isPrefix());

        token = new Token("el");
        token.detectPrefix();
        assertEquals(true, token.isPrefix());

        token = new Token("de");
        token.detectPrefix();
        assertEquals(true, token.isPrefix());

    }

    @Test
    public void testDetectSuffix() {
        Token token = new Token("Jr.");
        token.detectSuffix();
        assertEquals(true, token.isSuffix());

        token = new Token("Sr.");
        token.detectSuffix();
        assertEquals(true, token.isSuffix());

        token = new Token("II");
        token.detectSuffix();
        assertEquals(true, token.isSuffix());

    }

}
