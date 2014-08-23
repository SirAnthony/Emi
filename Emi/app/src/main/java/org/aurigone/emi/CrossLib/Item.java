package org.aurigone.emi.CrossLib;

public class Item {
    protected final ACTION_TYPE type;
    protected final int length;

    Item(ACTION_TYPE t, int l){
        type = t;
        length = l;
    }

    public ACTION_TYPE getType() {
        return type;
    }

    public int getLength() {
        return length;
    }
}
