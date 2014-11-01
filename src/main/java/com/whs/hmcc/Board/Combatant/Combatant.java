package com.whs.hmcc.Board.Combatant;

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
}
