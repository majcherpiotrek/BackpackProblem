/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpack;


import backpack.impl.BackpackImpl;
import backpack.impl.BranchAndBoundAlgorithm;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author piotr
 */
public class BackpackProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (!Objects.equals(scanner.nextLine(), "k")) {
            System.out.println("podaj nazwe pliku: ");
            String fileName = scanner.nextLine();
            BackpackImpl plecak = new BackpackImpl();
            plecak.fillFromFile("/home/adam/Workspace/JAVA_v2/BackpackProblem/backpack/" + fileName);
            System.out.println("Podaj rozmiar plecaka: ");
            Double size = (double) scanner.nextInt();

            /*System.out.println(plecak);
            BranchAndBoundAlgorithm branchAndBoundAlgorithm = new BranchAndBoundAlgorithm(plecak.getItemsToPut(), size);
            branchAndBoundAlgorithm.startBranchAndBound();
            System.out.println("backpack size: " + branchAndBoundAlgorithm.getBackpackSize());
            System.out.println("best Size: " + branchAndBoundAlgorithm.getBestSize());
            System.out.println("best Value: " + branchAndBoundAlgorithm.getBestValue());
            System.out.println(branchAndBoundAlgorithm.getBestItems());
            */

        }

     /*
        System.out.print(plecak);

        plecak.packBrute();
*/


        //plecak();

    }

}
