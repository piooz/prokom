package jaka.jakakolwiek.types;

public class SudokuField {
    int value = 0;

    public SudokuField() {
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value > 9 || value < 0) {
            throw new IllegalArgumentException("value should be integer from 0 to 9");
        }

        this.value = value;
    }

}
