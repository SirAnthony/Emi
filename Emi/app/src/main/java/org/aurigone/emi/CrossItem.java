package org.aurigone.emi;

public class CrossItem {
    protected ACTION_TYPE type;
    protected int length;

    CrossItem(ACTION_TYPE t, int l){
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
