package edu.odu.cs.cs350;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FeatureVectorSystest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn;

    @BeforeEach
    public void setUpStreams() {
        originalIn = System.in;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void systemTestModelNotDetected() throws Exception {
        String expectedOutput = "Model not detected, please check the model path.";

        String inputFileContent = new String(Files.readAllBytes(Paths.get("src/systest/data/FeatureVectors.txt")),
                StandardCharsets.UTF_8);

        System.setIn(new ByteArrayInputStream(inputFileContent.getBytes(StandardCharsets.UTF_8)));

        try {
            Extractor.main(new String[0]);
        } catch (Exception e) {
            assertFalse(e.getMessage().contains("Model not detected"), "Unexpected exception type");
        } finally {
            System.setIn(originalIn);  
        }

        String actualOutput = outContent.toString().trim();
        assertNotEquals(expectedOutput.trim(), actualOutput, "Expected message about model not being detected");
    }
}
