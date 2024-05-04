package edu.odu.cs.cs350;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;
import java.io.InputStream;

/**
 * Model loader.
 */
public class ModelLoader {

    /**
     * Load model from jar.
     * 
     * @return serialization helper
     * @throws Exception exception
     */
    public static Classifier loadModelFromJar() throws Exception {
        try (InputStream modelStream = ModelLoader.class.getResourceAsStream("/model/trainedMachine.model")) {
            if (modelStream == null) {
                throw new Exception("Model file not found in JAR.");
            }
            return (Classifier) SerializationHelper.read(modelStream);
        }
    }
}
