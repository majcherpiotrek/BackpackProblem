package backpack.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Krysiak on 26.11.16.
 */
public class BranchAndBoundAlgorithm {

    private List<Pair<Integer, Integer>> itemsToPut;
    private Double backpackSize;
    private Double actualBestWealth = 0.0;
    private ArrayList<Boolean> actualBestItems;

    public BranchAndBoundAlgorithm(List<Pair<Integer, Integer>> itemsToPut, Double backpackSize) {
        this.itemsToPut = itemsToPut;
        this.backpackSize = backpackSize;
        initializeActualBestWithZeros(itemsToPut);
    }


    public void startBranchAndBound() {

        itemsToPut.sort(Pair::integerComparator);
        ArrayList<Boolean> items = new ArrayList<>();
        items.addAll(actualBestItems);

        items.set(0, true);
        function(items, 0.0, 0.0, 0);
        items.set(0, false);
        function(items, 0.0, 0.0, 0);

    }

    public ArrayList<Pair<Integer, Integer>> getResults() {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < actualBestItems.size(); i++) {
            if (actualBestItems.get(i)) {
                result.add(itemsToPut.get(i));
            }
        }
        return result;
    }

    private void function(ArrayList<Boolean> items, Double wealthOfItemsAlreadyIn, Double sizeOfItemsAlreadyIn, Integer recursionLevel) {
        Pair<Integer, Integer> consideredItem = itemsToPut.get(recursionLevel);
        if (isPuttedToBackpack(items, recursionLevel)) {
            if (isBiggerThanPossible(sizeOfItemsAlreadyIn, consideredItem))
                return;
            wealthOfItemsAlreadyIn += consideredItem.getValue();
            sizeOfItemsAlreadyIn += consideredItem.getSize();
            if (isWorthMoreThanActualTheBest(wealthOfItemsAlreadyIn)) {
                makeItNewBest(items, wealthOfItemsAlreadyIn);
            }
        }
        Double possibleOnThisWay = countPossibleTopBoarder(wealthOfItemsAlreadyIn, sizeOfItemsAlreadyIn, itemsToPut.subList(recursionLevel + 1, itemsToPut.size()));
        if (isNotPossibleToBeTheBest(possibleOnThisWay) || isLastItem(recursionLevel))
            return;

        startFunctionOnSons(items, wealthOfItemsAlreadyIn, sizeOfItemsAlreadyIn, recursionLevel);


    }

    private void startFunctionOnSons(ArrayList<Boolean> items, Double wealthOfItemsAlreadyIn, Double sizeOfItemsAlreadyIn, Integer recursionLevel) {
        recursionLevel++;

        items.set(recursionLevel, true);
        function(items, wealthOfItemsAlreadyIn, sizeOfItemsAlreadyIn, recursionLevel);
        items.set(recursionLevel, false);
        function(items, wealthOfItemsAlreadyIn, sizeOfItemsAlreadyIn, recursionLevel);
    }

    private void makeItNewBest(ArrayList<Boolean> items, Double wealthOfItemsAlreadyIn) {
        actualBestItems = (ArrayList<Boolean>) items.clone();
        actualBestWealth = wealthOfItemsAlreadyIn;
    }

    private Double countPossibleTopBoarder(Double wealthOfItemsAlreadyIn, Double sizeOfItemsAlreadyIn, List<Pair<Integer, Integer>> itemsToPut) {
        Double topBoarder = wealthOfItemsAlreadyIn;
        for (Pair<Integer, Integer> item : itemsToPut) {
            if (isSmallEnaughToFit(sizeOfItemsAlreadyIn, item)) {
                topBoarder += item.getValue();
                sizeOfItemsAlreadyIn += item.getSize();
            } else {
                return topBoarder + item.getValue() * (backpackSize * 1.0 - sizeOfItemsAlreadyIn * 1.0) / (item.getSize() * 1.0);
            }
        }
        return topBoarder;
    }

    private boolean isLastItem(Integer recursionLevel) {
        return recursionLevel + 1 == itemsToPut.size();
    }

    private boolean isNotPossibleToBeTheBest(Double possibleOnThisWay) {
        return possibleOnThisWay < actualBestWealth;
    }

    private boolean isWorthMoreThanActualTheBest(Double wealthOfItemsAlreadyIn) {
        return actualBestWealth < wealthOfItemsAlreadyIn;
    }

    private boolean isBiggerThanPossible(Double sizeOfItemsAlreadyIn, Pair<Integer, Integer> consideredItem) {
        return consideredItem.getSize() + sizeOfItemsAlreadyIn > backpackSize;
    }

    private Boolean isPuttedToBackpack(ArrayList<Boolean> items, Integer recursionLevel) {
        return items.get(recursionLevel);
    }


    private boolean isSmallEnaughToFit(Double sizeOfItemsAlreadyIn, Pair<Integer, Integer> item) {
        return sizeOfItemsAlreadyIn + item.getSize() <= backpackSize;
    }

    private void initializeActualBestWithZeros(List<Pair<Integer, Integer>> itemsToPut) {
        actualBestItems = new ArrayList<>();
        for (int i = 0; i < itemsToPut.size(); i++) {
            actualBestItems.add(false);
        }
    }

}
