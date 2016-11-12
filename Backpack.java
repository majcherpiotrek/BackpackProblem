/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpack;

import backpack.impl.Pair;
import java.util.ArrayList;


/**
 * @author adam and piotr
 */
public interface Backpack <T,X> {

    public Integer getSize();
    public void setSize(Integer size);
    public ArrayList<Pair<T,X>> getItems();
    public void fillFromFile(String filename);

}
