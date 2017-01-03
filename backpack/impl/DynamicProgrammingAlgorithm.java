package backpack.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa do rozwiązaywania problemu plecakowego za pomocą algorytmu programowania dynamicznego
 * Created by piotrek on 03.01.17.
 */
public class DynamicProgrammingAlgorithm {

    private Integer backpackSize;
    private Integer itemsNum;
    private List<Pair<Integer,Integer>> itemsToPut;
    private Integer[][] backpackContentValues;
    private Integer[][] lastPackedItems;

    public DynamicProgrammingAlgorithm(List<Pair<Integer,Integer>> itemsToPut, Integer bpSize){
        this.itemsToPut = itemsToPut;
        this.backpackSize = bpSize;
        this.itemsNum = itemsToPut.size();

        //Tworzymy macierz na wartości upakowań plecaka o rozmiarze liczba przedmiotów x rozmiar plecaka+1
        this.backpackContentValues = new Integer[this.itemsNum][this.backpackSize+1];

        //Tworzymy macierz, przechowującą informacje o tym, który przedmiot został zapakowany jako ostatni przy danym upakowaniu
        this.lastPackedItems = new Integer[this.itemsNum][this.backpackSize+1];
    }

    public void startDynamicProgramming(){

    }




}
