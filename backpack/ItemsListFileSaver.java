package backpack;
import backpack.impl.Pair;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by piotrek on 09.12.16.
 */
public class ItemsListFileSaver {
    ArrayList<Pair<Integer,Integer>> itemsList;

    public ItemsListFileSaver(ArrayList<Pair<Integer,Integer>> _itemsList){
        itemsList = new ArrayList<>(_itemsList);
    }

    public void saveToFile(String fileName){
        File file = new File(fileName);
        try{
            FileWriter output = new FileWriter(file);
            String fileContent = "";

            for (int i=0; i< itemsList.size(); i++) {
                Pair item = itemsList.get(i);
                fileContent += item.getSize().toString() + " " + item.getValue().toString();

                if(i < itemsList.size() - 1)
                    fileContent += "\n";
            }

            output.write(fileContent);
            output.close();
        }catch(IOException ex){
            System.err.println(ex);
            return;
        }
    }
}
