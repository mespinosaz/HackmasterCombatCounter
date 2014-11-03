package com.whs.hmcc.Board.Combatant;

public class Combatant {
    public static final String NAME_FIELD = "name";
    public static final String COUNT_FIELD = "count";
    public static final String SPEED_FIELD = "speed";

    private String combatantName;
    private int combatantCount;
    private int combatantSpeed;
    public Combatant(String name, int count, int speed) {
        combatantName = name;
        combatantCount = count;
        combatantSpeed = speed;
    }

    public String name() {
        return combatantName;
    }

    public int count() {
        return combatantCount;
    }

    public int speed() {
        return combatantSpeed;
    }

    public void setCount(int count) {
        combatantCount = count;
    }

    public void addCount() {
        combatantCount++;
    }

    public void substractCount() {
        combatantCount = Math.max(combatantCount-1,1);
    }
}
