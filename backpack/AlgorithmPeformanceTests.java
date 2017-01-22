package backpack;

import backpack.impl.*;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.nanoTime;
import static java.lang.System.setOut;

/**
 * Created by piotrek on 09.12.16.
 */
public class AlgorithmPeformanceTests {

    private static final int SIZE_MAX = 10;
    private static final int VALUE_MAX = 150;
    private static final int VALUE_MIN = 100;
    private static final int BACKPACK_SIZE = 1000;
    private static final int COUNT_OF_MEASURES_TO_AVERAGE = 100;

    public static void main(String[] args) {
        analizeQuality();
    }

    private static void analizeTime() {
        try {
            FileWriter file = new FileWriter("wynikiCzas.txt");
            BufferedWriter stream = new BufferedWriter(file);
            List<Integer> instanceSizes = IntStream.rangeClosed(100, 300).filter(e -> e % 20 == 0).boxed().collect(Collectors.toList());
            System.out.println(instanceSizes);
            for (Integer instanceSize : instanceSizes) {
                ArrayList<Pair<Integer, Integer>> pairs = ItemsListGenerator.generateInstance(instanceSize, SIZE_MAX, VALUE_MAX);
                DynamicProgrammingAlgorithm algorithm = new DynamicProgrammingAlgorithm(pairs, BACKPACK_SIZE);
//                FPTASSchema algorithm = new FPTASSchema(BACKPACK_SIZE, pairs, 0.9F);
                Double averageTime = 0.0;
                for (int i = 0; i < COUNT_OF_MEASURES_TO_AVERAGE; i++) {
                    averageTime += elapsedTimeMilisec(algorithm);
                }
                averageTime /= COUNT_OF_MEASURES_TO_AVERAGE;
                stream.write(averageTime.toString() + "\n");
            }

            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void analizeQuality() {
        try {
            FileWriter file = new FileWriter("wynikiJakosc.txt");
            BufferedWriter stream = new BufferedWriter(file);
            List<Integer> instanceSizes = IntStream.rangeClosed(100, 400).filter(e -> e % 20 == 0).boxed().collect(Collectors.toList());
            stream.write(instanceSizes.toString() + "\n");
            double[] errorCoefficients = {0.5, 0.9, 1.3};
            for (double errorCoefficient : errorCoefficients) {
                System.out.println(errorCoefficient);
                for (Integer instanceSize : instanceSizes) {
                    stream.write("współczynnik: " + errorCoefficient + "\n" + "rozmiar inst: " + instanceSize + "\n");
                    ArrayList<Pair<Integer, Integer>> pairs = ItemsListGenerator.generateInstance(instanceSize, SIZE_MAX, VALUE_MAX, VALUE_MIN);
                    DynamicProgrammingAlgorithm exactAlgorithm = new DynamicProgrammingAlgorithm(pairs, BACKPACK_SIZE);
                    FPTASSchema approxAlgorithm = new FPTASSchema(BACKPACK_SIZE, pairs, (float) errorCoefficient);

                    exactAlgorithm.startDynamicProgramming();
                    Integer optimalValue = exactAlgorithm.getBestValue();
                    approxAlgorithm.startFPTASSchema();
                    Integer approxValue = approxAlgorithm.getBestValue();

                    stream.write(optimalValue.toString() + "\n");
                    stream.write(approxValue.toString() + "\n");
                }
                stream.write("\n");
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static double elapsedTimeMilisec(BranchAndBoundAlgorithm branchAndBound) {
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        branchAndBound.startBranchAndBound();
        elapsedTimeNano = nanoTime() - start;

        return (double) elapsedTimeNano / (double) 1000000;
    }

    public static double elapsedTimeMilisec(BruteForceAlgorithm bruteForce) {
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        bruteForce.startBruteForce();
        elapsedTimeNano = nanoTime() - start;

        return (double) elapsedTimeNano / (double) 1000000;
    }

    public static double elapsedTimeMilisec(DynamicProgrammingAlgorithm dynamicProg) {
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        dynamicProg.startDynamicProgramming();
        elapsedTimeNano = nanoTime() - start;

        return (double) elapsedTimeNano / (double) 1000000;
    }

    public static double elapsedTimeMilisec(FPTASSchema fptasSchema) {
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        fptasSchema.startFPTASSchema();
        elapsedTimeNano = nanoTime() - start;

        return (double) elapsedTimeNano / (double) 1000000;
    }
}
