package org.aurigone.emi.CrossLib;

import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    protected ArrayList<Item> items = new ArrayList<Item>();

    public Task(int[] resources){
        for (int i = 0; i < resources.length; i++) {
            int t = i % ACTION_TYPE.TYPES_COUNT.ordinal();
            Item item = new Item(ACTION_TYPE.values()[t], resources[i]);
            items.add(item);
        }
    }

    public Iterator<Item> getItemsIter() {
        return items.iterator();
    }

}
