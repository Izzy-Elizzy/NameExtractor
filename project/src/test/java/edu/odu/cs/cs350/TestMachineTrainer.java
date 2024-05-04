package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import weka.classifiers.functions.SMO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestMachineTrainer {

    // Checks if the parameter tuning method correctly configures and returns an SMO object
    @Test
    public void testTuneParameters() {
        MachineTrainer trainer = new MachineTrainer();
        
        try {
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("attribute1"));
            attributes.add(new Attribute("class", List.of("A", "B")));
    
            Instances data = new Instances("TestDataset", attributes, 0);
            data.setClassIndex(data.numAttributes() - 1);
    
            // Add enough instances for cross-validation
            for (int i = 0; i < 10; i++) {  // Ensure at least 10 instances
                DenseInstance instance = new DenseInstance(2);
                instance.setValue(attributes.get(0), i);
                instance.setValue(attributes.get(1), i % 2 == 0 ? "A" : "B");
                data.add(instance);
            }
    
            if (data.numInstances() >= 5) {  // Now using 5-fold cross-validation should be safe
                SMO optimizedSmo = trainer.tuneParameters(data);
                assertNotNull(optimizedSmo, "SMO should not be null after tuning");
            } else {
                fail("Insufficient data for training");
            }
        } catch (Exception e) {
            fail("Unexpected exception during test: " + e.getMessage());
        }
    }
    
    /* 
    // Tests the saveModel functionality to ensure the model is saved to disk
    @Test
    public void testSaveModel() {
        MachineTrainer trainer = new MachineTrainer();
        try {
            SMO mockModel = new SMO(); // Create a mock SMO model
            String tempModelFilePath = "tempModel.model"; // Temporary file path for testing

            trainer.saveModel(mockModel, tempModelFilePath); // Attempt to save the model

            File modelFile = new File(tempModelFilePath);
            assertTrue(modelFile.exists(), "Model file should exist after saving");

            // Clean up after test
            if (modelFile.exists()) {
                assertTrue(modelFile.delete(), "Cleanup failed to delete the model file");
            }
        } catch (Exception e) {
            fail("Exception occurred during saving the model: " + e.getMessage());
        }
    }
    */
}
