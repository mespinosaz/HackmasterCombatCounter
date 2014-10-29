package com.whs.hmcc;

/**
 * Created by root on 11/06/13.
 */
public class Combatant {
    private String mName;
    private int mCount;
    private int mSpeed;
    public Combatant(String _name, int _count, int _speed) {
        mName = _name;
        mCount = _count;
        mSpeed = _speed;
    }

    public String getName() {
        return mName;
    }

    public int getCount() {
        return mCount;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setCount(int _count) {
        mCount = _count;
    }

    public void addCount() {
        mCount++;
    }

    public void substractCount() {
        mCount = Math.max(mCount-1,1);
    }

    public String toXML() {
        return "<combatant " +
                "name=\"\""+this.mName+"\"\"" +
                "count=\"\""+this.mCount+"\"\"" +
                "speed=\"\""+this.mSpeed+"\"\"/>";
    }
}
