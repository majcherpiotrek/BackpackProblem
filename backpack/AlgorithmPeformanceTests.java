package backpack;

import backpack.impl.BackpackImpl;
import backpack.impl.BranchAndBoundAlgorithm;
import backpack.impl.BruteForceAlgorithm;

import static java.lang.System.nanoTime;

/**
 * Created by piotrek on 09.12.16.
 */
public class AlgorithmPeformanceTests {
    public static double elapsedTimeMilisec_BB(BranchAndBoundAlgorithm branchAndBound){
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        branchAndBound.startBranchAndBound();
        elapsedTimeNano = nanoTime() - start;

        return (double)elapsedTimeNano/(double)1000000;
    }

    public static double elapsedTimeMilisec_BF(BruteForceAlgorithm bruteForce){
        long start;
        long elapsedTimeNano;

        start = nanoTime();
        bruteForce.startBruteForce();
        elapsedTimeNano = nanoTime() - start;

        return (double)elapsedTimeNano/(double)1000000;
    }
}
