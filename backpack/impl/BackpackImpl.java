package backpack.impl;

import backpack.Backpack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by adam on 10.11.16.
 * BackPack
 */
public class BackpackImpl implements Backpack<Integer, Integer> {
    public static final String NOTHING_TO_PACK = "Nie ma przedmiotów do zapakowania!";
    public static final String TOO_MUCH_ITEMS_FOR_BRUTE_FORCE = "Zbyt wiele przedmiotów do zapakowania. Algorytm przeglądu zupełnego niemożliwy. ";
    public static final int MAX_COUNT_OF_ITEMS_FOR_BRUTEFORCE = 27;
    private ArrayList<Pair<Integer, Integer>> itemsToPut;
    private ArrayList<Pair<Integer, Integer>> itemsInBackpack;
    private Integer backpack_size;

    public BackpackImpl() {
        this.itemsToPut = new ArrayList<>();
        this.backpack_size = 0;
        this.itemsInBackpack = new ArrayList<>();
    }

    public BackpackImpl(ArrayList<Pair<Integer, Integer>> it, Integer s) {
        this.itemsToPut = it;
        this.backpack_size = s;
        this.itemsInBackpack = new ArrayList<>();
    }

    @Override
    public Integer getSize() {
        return backpack_size;
    }

    @Override
    public void setSize(Integer s) {
        backpack_size = s;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getItemsToPut() {
        return itemsToPut;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getItemsInBackpack() {
        return itemsInBackpack;
    }

    @Override
    public void setItemsToPut(ArrayList<Pair<Integer,Integer>> itemsList) {
        itemsToPut = itemsList;
    }

    public int getBackpackValue() {
        if (this.itemsInBackpack.isEmpty())
            return 0;
        final Integer[] val = {0};
        itemsInBackpack.forEach(e -> val[0] += e.getValue());
        return val[0];
    }

    @Override
    public String toString() {
        String ret = "Lista przedmiotów do zapakowania:\n";

        if (this.itemsToPut.isEmpty())
            ret += "Lista przedmiotów jest pusta!\n";
        else
            for (int i = 0; i < this.itemsToPut.size(); i++)
                ret += this.itemsToPut.get(i).toString() + "\n";

        ret += "\n";

        return ret;
    }
}