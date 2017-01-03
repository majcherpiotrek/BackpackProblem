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

        //Tworzymy macierz na wartości upakowań plecaka o rozmiarze liczba przedmiotów x rozmiar plecaka
        this.backpackContentValues = new Integer[this.itemsNum+1][this.backpackSize+1];

        //Tworzymy macierz, przechowującą informacje o tym, który przedmiot został zapakowany jako ostatni przy danym upakowaniu
        this.lastPackedItems = new Integer[this.itemsNum+1][this.backpackSize+1];
    }

    public void startDynamicProgramming(){
        /*
        PSEUDOKOD DECYZJI
        P -> backpackContentValue
        Q -> lastPackedItem

        //Jeśli przedmiot nie mieści się w plecaku to przypisujemy wartość z wiersza wyżej
        if j < weight[i] then
            P[i][j] = P[i-1][j];
            Q[i][j] = Q[i-1][j];
        else
        //W przeciwnym wypadku wpisujemy większą z wartości:
        //  wartość z wiersza wyżej dla tego samego rozmiaru plecaka
        //  wartość plecaka o rozmiarze mniejszym o rozmiar i-tego przedmiotu powiększona o wartość i-tego przemdmiotu
            P[i][j] = max(value[i] + P[i-1][j - weight[i]], P[i-1][j])
            Q[i][j] = i || Q[i-1][j] //(zależnie od tego, co zwróciło max())
        end if
         */

        //Pętla przechodząca przez wszystkie przedmioty
        for(int i = 1; i < itemsNum+1; i++){

            //Pętla przechodząca przez wszystkie rozmiary placaka
            for(int j = 0; j < backpackSize+1; j++)
                if(j < itemsToPut.get(i-1).getSize()){
                    backpackContentValues[i][j] = backpackContentValues[i-1][j];
                    lastPackedItems[i][j] = lastPackedItems[i-1][j];
                }
                else{
                    int packed = itemsToPut.get(i-1).getValue() + backpackContentValues[i-1][j - itemsToPut.get(i-1).getSize()];
                    int notPacked = backpackContentValues[i-1][j];
                    if(packed > notPacked){
                        backpackContentValues[i][j] = packed;
                        lastPackedItems[i][j] = i;
                    }
                }
        }
    }
    
}
