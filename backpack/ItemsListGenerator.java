package backpack;
import backpack.impl.Pair;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Random;

import static java.lang.StrictMath.abs;

/**
 * Created by piotrek on 09.12.16.
 */
public class ItemsListGenerator {

    /*Generuje listę przedmiotów dla problemu plecakowego
        instanceSize - liczba przedmiotów
        sizeMax - maksymalny rozmiar przedmiotu (rozmiary w zakresie <1,sizeMax>)
        valueMax - maksymalna wartość przedmiotu (wartości w zakresie <1,valueMax>)
     */
    public static ArrayList<Pair<Integer,Integer>> generateInstance(int instanceSize, int sizeMax, int valueMax){

        Random generator = new Random();
        ArrayList<Pair<Integer,Integer>> itemsList = new ArrayList<>();

        for (int i = 0; i < instanceSize; i++) {

            //generujemy rozmiar przedmiotu z zakresu <1,N>, N - rozmiar instancji
            Integer size = abs(generator.nextInt()%sizeMax) + 1;
            //generujemy wartość przedmiotu z zakresu <1,10*N>
            Integer value = abs(generator.nextInt()%valueMax) +1;

            //dodajemy przedmiot do listy
            itemsList.add(i, new Pair<>(size,value));
        }

        return itemsList;
    }
}
