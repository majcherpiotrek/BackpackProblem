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

    private static final int SIZE_MAX = 100;
    private static final int VALUE_MAX = 100;
    private static final int BACKPACK_SIZE = 100;
    private static final int COUNT_OF_MEASURES_TO_AVERAGE = 10;

    public static void main(String[] args) {
        try {
            FileWriter file = new FileWriter("wyniki.txt");
            BufferedWriter stream = new BufferedWriter(file);
            List<Integer> instanceSizes = IntStream.rangeClosed(1000, 10000).filter(e -> e % 100 == 0).boxed().collect(Collectors.toList());
            double[] errorCoefficients = {1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 3.0};
            for (double errorCoefficient : errorCoefficients) {
                System.out.println(errorCoefficient);
                for (Integer instanceSize : instanceSizes) {
                    ArrayList<Pair<Integer, Integer>> pairs = ItemsListGenerator.generateInstance(instanceSize, SIZE_MAX, VALUE_MAX);
//                DynamicProgrammingAlgorithm algorithm = new DynamicProgrammingAlgorithm(pairs, BACKPACK_SIZE);
                    FPTASSchema algorithm = new FPTASSchema(BACKPACK_SIZE, pairs, (float) errorCoefficient);
                    Double averageTime = 0.0;
                    for (int i = 0; i < COUNT_OF_MEASURES_TO_AVERAGE; i++) {
                        averageTime += elapsedTimeMilisec(algorithm);
                    }
                    averageTime /= COUNT_OF_MEASURES_TO_AVERAGE;
                    stream.write(averageTime.toString() + "\n");
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
