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

    public static ArrayList<Pair<Integer,Integer>> loadFromFile (String fileName) throws FileNotFoundException{

        ArrayList<Pair<Integer,Integer>> itemsList = new ArrayList<>();


        File file = new File(fileName);
        Scanner input = new Scanner(file);

        while (input.hasNextLine())
            itemsList.add(new Pair(input.nextInt(), input.nextInt()));


        return itemsList;
    }
}
