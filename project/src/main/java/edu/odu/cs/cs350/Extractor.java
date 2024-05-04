package edu.odu.cs.cs350;

import java.io.InputStreamReader;
import java.util.Scanner;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * Main class for calling other classes and functions.
 */
public class Extractor {

    /**
     * Main function.
     * 
     * @param args command-line arguments
     * @throws Exception if an error occurs during processing
     */
    public static void main(String[] args) throws Exception {
        // Load the trained model
        Classifier model = ModelLoader.loadModelFromJar();

        Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));
        scanner.useDelimiter("\\A"); 
        String contentOfFile = scanner.next();
        scanner.close();

        Document document = new Document(contentOfFile);

        // Process each TextBlock in the document
        for (TextBlock block : document.getBlocks()) {
            FeatureVector featureVector = new FeatureVector();
            Instances instances = featureVector.createVectors(block.getTokensList());
            
            instances.setClassIndex(instances.numAttributes() - 1);

            // Use the model to predict and tag personal names
            for (int i = 0; i < instances.numInstances(); i++) {
                double classLabel = model.classifyInstance(instances.instance(i));
                block.getTokensList().get(i).setIsName(classLabel == 1.0);
            }

            System.out.println(block.toString());
        }
    }
}
