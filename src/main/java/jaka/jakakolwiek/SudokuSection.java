package jaka.jakakolwiek;

import jaka.jakakolwiek.types.SudokuField;

public abstract class SudokuSection {

    SudokuField[] section;

    public SudokuSection(SudokuField[] section) {
        this.section = section;
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
}
