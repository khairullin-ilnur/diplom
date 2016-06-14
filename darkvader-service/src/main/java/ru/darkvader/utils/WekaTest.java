package ru.darkvader.utils;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * Created by ilnur on 10/06/16.
 */
public class WekaTest {
    public static void main(String[] args) throws Exception {
//        // load CSV
//        CSVLoader loader = new CSVLoader();
//        loader.setSource(new File("/Users/ilnur/IdeaProjects/DarkVader/Опрос 1 copy 2.csv"));
//        Instances data = loader.getDataSet();
//
//        // save ARFF
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(data);
//        saver.setFile(new File("/Users/ilnur/IdeaProjects/DarkVader/Опрос 1 copy 2.arff"));
//        saver.setDestination(new File("/Users/ilnur/IdeaProjects/DarkVader/Опрос 1 copy 2.arff"));
//        saver.writeBatch();

        BufferedReader br = null;
        int numFolds = 20;
        br = new BufferedReader(new FileReader("/Users/ilnur/IdeaProjects/DarkVader/Опрос 1 copy.arff"));

        Instances trainData = new Instances(br);
        trainData.setClassIndex(trainData.numAttributes() - 1);
        br.close();
        System.out.println("\nDataset:\n");
        System.out.println(trainData);
        RandomForest rf = new RandomForest();
        rf.setNumTrees(100);

        rf.buildClassifier(trainData);
        Evaluation evaluation = new Evaluation(trainData);
        evaluation.crossValidateModel(rf, trainData, numFolds, new Random(1));

        System.out.println(evaluation.toSummaryString("\nResults\n======\n", true));
        System.out.println(evaluation.toClassDetailsString());
        System.out.println("Results For Class -1- ");
        System.out.println("Precision=  " + evaluation.precision(0));
        System.out.println("Recall=  " + evaluation.recall(0));
        System.out.println("F-measure=  " + evaluation.fMeasure(0));
        System.out.println("Results For Class -2- ");
        System.out.println("Precision=  " + evaluation.precision(1));
        System.out.println("Recall=  " + evaluation.recall(1));
        System.out.println("F-measure=  " + evaluation.fMeasure(1));
        System.out.println("All precision= " + evaluation.weightedPrecision());
    }
}
