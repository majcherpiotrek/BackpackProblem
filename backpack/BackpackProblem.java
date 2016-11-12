/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpack;

import backpack.impl.BackpackImpl;



/**
 *
 * @author piotr
 */
public class BackpackProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BackpackImpl plecak = new BackpackImpl();
       plecak.fillFromFile("plecaczek.txt");
     /*
        System.out.print(plecak);
        
        plecak.packBrute();

        System.out.println(plecak);*/

        plecak.packDivideAndCurb();
    }
    
}
