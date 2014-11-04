package com.whs.hmcc.Board;

import com.whs.hmcc.Board.Combatant.Combatant;
import com.whs.hmcc.Board.Combatant.CombatantCollection;

public class Board {
    public static final int INITIAL_COUNT = 1;

    private CombatantCollection combatantList;
    private int currentCount;

    public Board() {
        resetBoard();
    }

    public void resetBoard() {
        currentCount = INITIAL_COUNT;
        resetCombatants();
    }

    public void resetCombatants() {
        combatantList = new CombatantCollection();
    }


    public void addCombatant(String name, int start_count, int speed) {
        addCombatant(new Combatant(name, start_count, speed));
    }

    public void addCombatant(Combatant combatant) {
        combatantList.add(combatant);
    }

    public void removeCombatant(int index) {
        combatantList.remove(index);
    }

    public void addCombatantCount(int index) {
        combatantList.get(index).addCount();
    }

    public int getCombatantCount(int index) {
        return combatantList.get(index).count();
    }

    public void decreaseCombatantCount(int index) {
        combatantList.get(index).substractCount();
    }

    public void increaseCount() {
        currentCount++;
        updateCombatantsCount();
    }

    private void updateCombatantsCount() {
        for(int i=0;i< combatantList.size();i++) {
            while(combatantList.get(i).count()< currentCount) {
                combatantList.get(i).setCount(combatantList.get(i).count() + combatantList.get(i).speed());
            }
        }
    }

    public void decreaseCount() {
        currentCount = Math.max(currentCount-1,1);
    }

    public void resetCount() {
        currentCount = 1;
    }

    public int currentCount() {
        return currentCount;
    }

    public void sortCombatants() {
        combatantList.sort();
    }

    public int getNumberOfCombatants() {
        return combatantList.size();
    }

    public Combatant getCombatant(int index) {
        return combatantList.get(index);
    }

    public CombatantCollection getCombatantList() {
        return combatantList;
    }

    public void setCurrentCount(int count) {
        currentCount = count;
    }

    public boolean isCombatantInCurrentCount(int index) {
        return getCombatantCount(index) == currentCount();
    }


}