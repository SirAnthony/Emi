package org.aurigone.emi.CrossLib;

import java.util.ArrayList;
import java.util.Iterator;

public class Task {

    protected String name;
    protected ArrayList<Item> items;

    public Task(String n, int[] resources){
        name = n;
        for (int i = 0; i < resources.length; i++) {
            int t = i % ACTION_TYPE.TYPES_COUNT.ordinal();
            items.add(new Item(ACTION_TYPE.values()[t], resources[i]));
        }
    }

    public String getName() {
        return name;
    }

    public Iterator<Item> getItemsIter() {
        return items.iterator();
    }

}
