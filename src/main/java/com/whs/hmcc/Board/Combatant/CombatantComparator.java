package com.whs.hmcc.Board.Combatant;

import java.util.Comparator;

public class CombatantComparator implements Comparator<Combatant> {
    public static final int ORDER_BY_COUNT = 1;
    public static final int ORDER_BY_NAME = 2;
    private int mOrder;

    public CombatantComparator() {
        mOrder = ORDER_BY_COUNT;
    }

    public CombatantComparator(int _order) {
        mOrder = _order;
    }
    @Override
    public int compare(Combatant c1, Combatant c2) {
        switch(mOrder) {
            case ORDER_BY_COUNT:default:
                return ((c1.count() < c2.count()) ? -1 : ((c1.count() == c2.count()) ? 0 : 1));
            case ORDER_BY_NAME:
                return c1.name().compareTo(c2.name());
        }
    }
}