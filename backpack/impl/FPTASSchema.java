package backpack.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam Krysiak on 05.01.17.
 */
public class FPTASSchema {

    private Float errorCoefficient;
    private Integer backpackSize;
    private List<Pair<Integer, Integer>> originalItemsToPut;
    private List<Pair<Integer, Integer>> bestItems;
    private List<Pair<Integer, Integer>> scaledItemsToPut;
    private Map<Pair<Integer, Integer>, Pair<Integer, Integer>> itemsAndResizedItems;
    private DynamicProgrammingAlgorithm dynamicProgrammingAlgorithm;
    private Float scaleCoefficient;
    private Integer bestValue;

    public FPTASSchema(Integer backpackSize, List<Pair<Integer, Integer>> itemsToPut, Float errorCoefficient) {
        this.backpackSize = backpackSize;
        this.originalItemsToPut = itemsToPut;
        this.errorCoefficient = errorCoefficient;
    }

    public void startFPTASSchema() {
        scaleItemsToPut();
        dynamicProgrammingAlgorithm = new DynamicProgrammingAlgorithm(this.scaledItemsToPut, backpackSize);
        dynamicProgrammingAlgorithm.startDynamicProgramming();
        countBestItems();
    }

    private void countBestItems() {
        this.bestItems = new ArrayList<>();
        dynamicProgrammingAlgorithm.getBestItems().forEach(e -> bestItems.add(itemsAndResizedItems.get(e)));
    }

    private void scaleItemsToPut() {
        scaledItemsToPut = getCopyOfListOfPairs(originalItemsToPut);
        scaleCoefficient = countScaleCoefficient(BackpackImpl.getMaxItemValue(originalItemsToPut));
        System.out.println(scaleCoefficient);
        scaledItemsToPut.forEach(e -> e.setValue((int) (e.getValue() / scaleCoefficient)));
        initializeMapOfScaledItems();
    }

    private void initializeMapOfScaledItems() {
        itemsAndResizedItems = new HashMap<>();
        for (int i = 0; i < originalItemsToPut.size(); i++) {
            itemsAndResizedItems.put(scaledItemsToPut.get(i), originalItemsToPut.get(i));
        }
    }

    public List<Pair<Integer, Integer>> getBestItems() {
        return bestItems;
    }

    public Integer getBestSize() {
        return dynamicProgrammingAlgorithm.getBestSize();
    }

    public Integer getBestValue() {
        return bestItems.stream().mapToInt(Pair::getValue).sum();
    }

    private Float countScaleCoefficient(Integer maxItemValue) {
        return (this.errorCoefficient * maxItemValue) / originalItemsToPut.size();
    }

    public Integer getBackpackSize() {
        return backpackSize;
    }


    private List<Pair<Integer, Integer>> getCopyOfListOfPairs(List<Pair<Integer, Integer>> listOfPairs) {
        List<Pair<Integer, Integer>> copiedList = new ArrayList<>();
        listOfPairs.forEach(e -> copiedList.add(e.getCopy()));
        return copiedList;
    }
}
