package model;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    private int value;
    private boolean fixed;
    private int solution;                  // ← novo
    private Set<Integer> draftNumbers;

    public Cell(int value, boolean fixed, int solution) {   // ← novo parâmetro
        this.value = value;
        this.fixed = fixed;
        this.solution = solution;          // ← novo
        this.draftNumbers = new HashSet<>();
    }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public boolean isFixed() { return fixed; }
    public boolean isEmpty() { return value == 0; }
    public int getSolution() { return solution; }          // ← novo

    public boolean isCorrect() {                           // ← novo
        return value == solution;
    }

    public Set<Integer> getDraftNumbers() { return draftNumbers; }
    public void addDraft(int number) { draftNumbers.add(number); }
    public void removeDraft(int number) { draftNumbers.remove(number); }

    public void clear() {
        if (!fixed) {
            this.value = 0;
            this.draftNumbers.clear();
        }
    }
}