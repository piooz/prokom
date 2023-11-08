package jaka.jakakolwiek;

import jaka.jakakolwiek.nonesense.IObserver;
import jaka.jakakolwiek.types.SudokuField;

public abstract class SudokuSection implements IObserver<Integer> {

    SudokuField[] section;

    public SudokuSection(SudokuField[] section) {
        this.section = section;

        // Subscribe every field
        for (SudokuField field : section) {
            field.addObserver(this);
        }
    }

    public boolean verify() {
        for (int i = 1; i < 10; i++) {
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if (section[j].getFieldValue() == i) {
                    count++;
                }
                if (count >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void update(Integer event) {
        verify();
    }

}
