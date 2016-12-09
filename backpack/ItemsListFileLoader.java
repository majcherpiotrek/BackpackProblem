package backpack;

import backpack.impl.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by piotrek on 09.12.16.
 */
public class ItemsListFileLoader {

    public static ArrayList<Pair<Integer,Integer>> loadFromFile(String fileName){

        ArrayList<Pair<Integer,Integer>> itemsList = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
                itemsList.add(new Pair(input.nextInt(), input.nextInt()));
        } catch (FileNotFoundException ex) {
            System.err.printf("ERROR: %s\n", ex);
        }

        return itemsList;
    }
}
