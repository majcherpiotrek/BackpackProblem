package backpack.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 09.12.16.
 */
public class BruteForceAlgorithm {

    public static final String NOTHING_TO_PACK = "Nie ma przedmiotów do zapakowania!";
    public static final String TOO_MUCH_ITEMS_FOR_BRUTE_FORCE = "Zbyt wiele przedmiotów do zapakowania. Algorytm przeglądu zupełnego niemożliwy. ";
    public static final int MAX_COUNT_OF_ITEMS_FOR_BRUTEFORCE = 27;

    private List<Pair<Integer, Integer>> itemsToPut;

    private Integer backpackSize;

    public ArrayList<Boolean> packedItems;

    public BruteForceAlgorithm(List<Pair<Integer, Integer>> itemsToPut, Integer backpackSize) {
        this.itemsToPut = itemsToPut;
        this.backpackSize = backpackSize;
        initializePackedItemsWithZeros(itemsToPut.size());
    }

    public void startBruteForce() {

        if (this.itemsToPut.isEmpty()) {
            System.out.println(NOTHING_TO_PACK);
            return;
        }

        if (this.itemsToPut.size() > MAX_COUNT_OF_ITEMS_FOR_BRUTEFORCE) {
            System.out.println(TOO_MUCH_ITEMS_FOR_BRUTE_FORCE);
            return;
        }

        int num_items = this.itemsToPut.size();
        long combinations = 1 << num_items;

        long currentBest = 0;
        int current_best_value = 0;
        int current_best_weight = 0;

        boolean anything_fits = false;

        for (long i = 0; i < combinations; i++) {
    /*
	Iterujemy po wszystkich mo¿liwoœciach. Ka¿dy bit zmiennej
	permutations odpowiada jednemu elementowi zbioru przedmiotów.
	Jedynka oznacza, ¿e do³¹czamy dany przedmiot, zero, ¿e nie.
	*/

            int sum_weight = 0;
            int sum_value = 0;

            boolean fits = false;
            long current_perm;

            /*Przechodzimy po wszystkich przedmiotach, ¿eby sprawdziæ, czy nale¿¹ do danej kombinacji*/
            for (int k = 0; k < num_items; k++) {

                current_perm = i;
			/*
			Sprawdzamy przynale¿noœæ
			-> musimy dostaæ siê do k-tego bitu permutations,
			wiêc trzeba wykonaæ k razy przesuniêcie bitowe
			w prawo, aby otrzymaæ ten bit na najni¿szej pozycji

			Za pomoc¹ AND sprawdzamy, czy ten bit to 0 czy 1, porównuj¹c go z 1
			(jeœli bêdzie jeden to w wyniku bitowego iloczynu logicznego dostaniemy
			1, w innym wypadku zero)
			1000010101000000001
			0000000000000000001
			-------------------
			0000000000000000001

			Jeœli równe 0 to znaczy, ¿e przedmiot nie nale¿y do kombinacji i kontynuujemy pêtle*/
                if (((current_perm >> k) & 1) != 1)
                    continue;


                sum_weight += this.itemsToPut.get(k).getSize();
                sum_value += this.itemsToPut.get(k).getValue();

                if (sum_weight > backpackSize) {
                    fits = false;
                    break;
                }

                fits = true;

                        /*Jeœli suma wag przedmiotów kombinacji wiêksza ni¿ pojemnoœæ plecaka to sprawdzamy kolejn¹*/
                if (!fits)
                    continue;

                anything_fits = true;

                if (sum_value > current_best_value) {
                    current_best_value = sum_value;
                    current_best_weight = sum_weight;
                    currentBest = i;
                }
            }
        }

        if (!anything_fits) {
            System.out.println("Żaden przedmiot nie mieści się w plecaku!");
            return;
        }

        /*Przechodzimy po wszystkich przedmiotach, ¿eby sprawdziæ, czy nale¿¹ do danej kombinacji*/
        for (int k = 0; k < num_items; k++) {
            long current_perm = currentBest;

            if (((current_perm >> k) & 1) != 1)
                continue;


		/*Dodajemy przedmiot do rozwi¹zania*/

            packedItems.set(k,true);
        }

        System.out.println("Plecak zapakowany!");

    }

    public ArrayList<Pair<Integer, Integer>> getBestItems() {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < packedItems.size(); i++) {
            if (packedItems.get(i)) {
                result.add(itemsToPut.get(i));
            }
        }
        return result;
    }

    public Double getBestSize(){
        Double val=0.0;
        for (int i = 0; i < packedItems.size(); i++) {
            if (packedItems.get(i)) {
                val+=itemsToPut.get(i).getSize();
            }
        }
        return val;
    }

    public Double getBestValue(){
        Double size=0.0;
        for (int i = 0; i < packedItems.size(); i++) {
            if (packedItems.get(i)) {
                size+=itemsToPut.get(i).getValue();
            }
        }
        return size;
    }

    public Integer getBackpackSize() {
        return backpackSize;
    }

    private void initializePackedItemsWithZeros(int size) {
        packedItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            packedItems.add(false);
        }
    }


}
