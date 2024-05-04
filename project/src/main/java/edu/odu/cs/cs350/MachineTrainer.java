package edu.odu.cs.cs350;

import weka.classifiers.functions.SMO;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

/**
 * Trains a machine learning model using the Weka library and saves the trained model.
 */
public class MachineTrainer {

    /**
     * Trains the SMO model using the specified data file and saves the trained model to the given path.
     *
     * @param filePath The path to the training data file.
     * @param modelPath The path where the trained model will be saved.
     * @throws Exception if there is an error during the training or saving process.
     */
    public void trainAndSaveModel(String filePath, String modelPath) throws Exception {
        Document document = new Document(readFileContent(filePath));
        List<TextBlock> blocks = document.getBlocks();

        FeatureVector featureVector = new FeatureVector();
        Instances data = null;

        for (TextBlock block : blocks) {
            List<Token> tokens = block.getTokensList();
            if (data == null) {
                data = featureVector.createVectors(tokens);
                data.setClassIndex(data.numAttributes() - 1);
            }
            Instances newData = featureVector.createVectors(tokens);
            for (int i = 0; i < newData.numInstances(); i++) {
                data.add(newData.instance(i));
            }
        }

        SMO bestSmo = tuneParameters(data);
        saveModel(bestSmo, modelPath);
    }

    /**
     * Tunes the SMO classifier by testing different configurations and selecting the one with the highest accuracy.
     *
     * @param data The dataset to be used for tuning the model.
     * @return The best tuned SMO model.
     * @throws Exception if there is an error during the model evaluation.
     */
    public SMO tuneParameters(Instances data) throws Exception {
        double bestC = 1.0;
        double bestResult = 0.0;

        for (double c = 1.0; c <= 10.0; c += 1.0) {
            SMO smo = new SMO();
            smo.setC(c);
            smo.setOptions(Utils.splitOptions("-L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1"));

            Evaluation evaluation = new Evaluation(data);
            evaluation.crossValidateModel(smo, data, 5, new Random(1));
            double score = evaluation.pctCorrect();

            if (score > bestResult) {
                bestResult = score;
                bestC = c;
            }
        }

        SMO smo = new SMO();
        smo.setC(bestC);
        smo.setOptions(Utils.splitOptions("-L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1"));
        smo.buildClassifier(data);
        return smo;
    }

    /**
     * Reads the entire content of a file into a string.
     *
     * @param filePath The path to the file to be read.
     * @return The content of the file.
     * @throws FileNotFoundException if the file cannot be found.
     */
    String readFileContent(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        scanner.useDelimiter("\\A");
        String content = scanner.next();
        scanner.close();
        return content;
    }

    /**
     * Saves the trained model to the specified file path.
     *
     * @param smo The SMO model to be saved.
     * @param filePath The path where the model will be saved.
     * @throws Exception if there is an error during the saving process.
     */
    void saveModel(SMO smo, String filePath) throws Exception {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure the directory exists
        SerializationHelper.write(filePath, smo);
    }

    /**
     * The main method that runs the training process.
     *
     * @param argument Command-line arguments.
     */
    public static void main(String[] argument) {
        try {
            String trainingDataPath = "src/main/data/trainingData.txt";
            String modelPath = "src/main/resources/model/trainedMachine.model";
            new MachineTrainer().trainAndSaveModel(trainingDataPath, modelPath);
            System.out.println("Model trained and saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to train or save the model.");
        }
    }
}
