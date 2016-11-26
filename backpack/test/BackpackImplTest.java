package backpack.test;

import backpack.Backpack;
import backpack.impl.BackpackImpl;
import backpack.impl.BranchAndBoundAlgorithm;
import backpack.impl.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by adam on 10.11.16.
 * BackPack
 */
public class BackpackImplTest extends TestCase {
    private ArrayList<Pair<Integer, Integer>> testItems = new ArrayList<Pair<Integer, Integer>>(){{
        this.add(new Pair<>(2,40));
        this.add(new Pair<>(5,30));
        this.add(new Pair<>(10,50));
        this.add(new Pair<>(5,10));
        }};

    private Backpack getInitializedBackpack() {
        return new BackpackImpl(testItems, 10);
    }

    public void testGetSize() throws Exception {
        Backpack backpack = getInitializedBackpack();
        assertEquals(new Integer(10), backpack.getSize());
    }

    public void testSetSize() throws Exception {
        Backpack backpack = getInitializedBackpack();
        backpack.setSize(15);
        assertEquals(new Integer(15), backpack.getSize());
    }


    public void testBranchAndBoundAlgorithm(){
        BranchAndBoundAlgorithm branchAndBoundAlgorithm = new BranchAndBoundAlgorithm(testItems,16.0);
        branchAndBoundAlgorithm.startBranchAndBound();
        System.out.println(branchAndBoundAlgorithm.getResults());
    }

}