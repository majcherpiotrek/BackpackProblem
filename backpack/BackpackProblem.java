/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpack;


import backpack.impl.BackpackImpl;
import backpack.impl.BranchAndBoundAlgorithm;
import backpack.impl.BruteForceAlgorithm;
import backpack.impl.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/*TODO

-polepszyć menu -> obsługa podania złej nazwy pliku
 */
/**
 * @author piotr
 */
public class BackpackProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Pair<Integer,Integer>> problem = ItemsListGenerator.generateInstance(25,30,100);
        ItemsListFileSaver fileOutput = new ItemsListFileSaver(problem);
        fileOutput.saveToFile("problem");


        double bbTime, bfTime;
        BackpackImpl backpack = new BackpackImpl(ItemsListFileLoader.loadFromFile("problem"), 60);

        BranchAndBoundAlgorithm bb = new BranchAndBoundAlgorithm(backpack.getItemsToPut(),(double)backpack.getSize());
        BruteForceAlgorithm bf = new BruteForceAlgorithm(backpack.getItemsToPut(), backpack.getSize());

        bbTime = AlgorithmPeformanceTests.elapsedTimeMilisec_BB(bb);
        bfTime = AlgorithmPeformanceTests.elapsedTimeMilisec_BF(bf);

        System.out.println("Time bb: " + bbTime + "\nTime brute force: " + bfTime);
       /* Scanner scanner = new Scanner(System.in);
        while (!Objects.equals(scanner.nextLine(), "k")) {

            BackpackImpl plecak = new BackpackImpl();
            ArrayList<Pair<Integer,Integer>> prob = ItemsListFileLoader.loadFromFile("problem");
            plecak.setItemsToPut(prob);
            System.out.println("Podaj rozmiar plecaka: ");
            Double size = (double) scanner.nextInt();

            System.out.println(plecak);
            BranchAndBoundAlgorithm branchAndBoundAlgorithm = new BranchAndBoundAlgorithm(plecak.getItemsToPut(), size);
            branchAndBoundAlgorithm.startBranchAndBound();
            System.out.println("backpack size: " + branchAndBoundAlgorithm.getBackpackSize());
            System.out.println("best Size: " + branchAndBoundAlgorithm.getBestSize());
            System.out.println("best Value: " + branchAndBoundAlgorithm.getBestValue());
            System.out.println(branchAndBoundAlgorithm.getBestItems());


        }
*/
     /*
        System.out.print(plecak);

        plecak.packBrute();
*/


        //plecak();

    }

}
