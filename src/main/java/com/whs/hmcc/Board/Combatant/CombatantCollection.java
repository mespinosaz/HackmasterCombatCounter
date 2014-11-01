package com.whs.hmcc.Board.Combatant;

import java.util.ArrayList;
import java.util.Collections;

public class CombatantCollection {
    private ArrayList<Combatant> collection;

    public CombatantCollection() {
        collection = new ArrayList<Combatant>();
    }

    public void add(Combatant combatant) {
        collection.add(combatant);
    }

    public void remove(int index) {
        collection.remove(index);
    }

    public Combatant get(int index) {
        return collection.get(index);
    }

    public int size() {
        return collection.size();
    }

    public void sort() {
        Collections.sort(collection, new CombatantComparator());
    }
}
