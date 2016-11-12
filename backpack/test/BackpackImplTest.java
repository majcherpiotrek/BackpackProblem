package backpack.test;

import backpack.Backpack;
import backpack.impl.BackpackImpl;
import backpack.impl.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by adam on 10.11.16.
 * BackPack
 */
public class BackpackImplTest extends TestCase {
    private ArrayList<Pair<Integer, Integer>> testItems = new ArrayList<Pair<Integer, Integer>>(){{
        this.add(new Pair<>(2, 2));
        this.add(new Pair<>(1, 2));
        this.add(new Pair<>(5, 2));
        this.add(new Pair<>(2, 3));
        this.add(new Pair<>(1, 4));
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

    public void testGetItemsToPut() throws Exception {
        Backpack backpack = getInitializedBackpack();
        assertEquals(5, backpack.getItemsToPut().size());
        for(int i=0; i<backpack.getItemsToPut().size();i++){
            assertEquals(testItems.get(i),backpack.getItemsToPut().get(i));
        }
    }

    public void testFillFromFile() throws Exception {
        assertEquals(0,1);
    }

}