package edu.odu.cs.cs350;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class TestDocument {

    @Test
    public void testDefaultConstructor() {
        Document file = new Document();
        assertEquals("", file.getDataFromFile());
        assertEquals(new ArrayList<>(), file.getBlocks());
    }

    @Test
    public void testParameterizedConstructor() {
        String block = "<NER> this is for testing</NER>";
        Document testInput = new Document(block);

        assertEquals("<NER> this is for testing</NER>", testInput.getDataFromFile());

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        testInput.printDocument();

        assertEquals(block, outputStreamCaptor.toString().trim());

    }

    // public void testReadInput() throws Exception {
    // // Prepare input data
    // String inputData = "Test input data";
    // InputStream inputStream = new
    // ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));
    // System.setIn(inputStream); // Redirect System.in to our input stream test

    // Document identifyBlock = new Document();
    // identifyBlock.readInput(); // Call the method to read input

    // // Assert that dataFromFile is equal to inputData
    // assertEquals(inputData, identifyBlock.getDataFromFile());

    // // Clean up: Restore System.in
    // System.setIn(System.in);
    // }

    @Test
    public void testCreateBlocks() {
        Document identifyBlock = new Document();

        // Sample input data containing NER blocks
        String inputData = "This is a <NER>sample</NER> text <NER>with</NER> NER blocks.";

        // Expected extracted NER blocks
        List<TextBlock> expectedBlocks = new ArrayList<>();
        expectedBlocks.add(new TextBlock("<NER>sample</NER>"));
        expectedBlocks.add(new TextBlock("<NER>with</NER>"));

        // Call extractNerBlocks method
        List<TextBlock> actualBlocks = identifyBlock.createBlocks(inputData);

        // Compare the actual blocks with the expected blocks
        assertEquals(expectedBlocks.size(), actualBlocks.size());
        for (int i = 0; i < expectedBlocks.size(); i++) {
            assertEquals(expectedBlocks.get(i).toString(), actualBlocks.get(i).toString());
        }
    }

    @Test
    public void testPrintDocument() {
        // Create a document with some sample data
        String data = "<NER>Hello, world!</NER><NER>This is a test.</NER>";
        Document doc = new Document(data);

        // Create a list to hold the expected output
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("<NER> <PER>Hello</PER>,world!</NER>");
        expectedOutput.add("<NER> <PER>This</PER> is a test.</NER>");

        // Create a list to hold the actual output
        List<String> actualOutput = new ArrayList<>();

        // Set up a output stream to capture the output of the printDocument() method
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Call the printDocument() method and capture the output
        doc.printDocument();
        String actualOutputString = out.toString();
        String[] actualOutputLines = actualOutputString.split(System.lineSeparator());
        for (String line : actualOutputLines) {
            actualOutput.add(line.trim());
        }

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }
}
