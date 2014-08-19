package org.aurigone.emi;

import java.util.ArrayList;

public class CrossTask {

    protected ArrayList<CrossItem> items;

    CrossTask(int[] resources){
        for (int i = 0; i < resources.length; i++) {
            int t = i % ACTION_TYPE.TYPES_COUNT.ordinal();
            items.add(new CrossItem(ACTION_TYPE.values()[t], resources[i]));
        }
    }

}
