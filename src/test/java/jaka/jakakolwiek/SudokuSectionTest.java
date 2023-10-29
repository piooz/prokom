package jaka.jakakolwiek;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jaka.jakakolwiek.types.SudokuField;

@ExtendWith(MockitoExtension.class)
public class SudokuSectionTest {

    @Mock
    SudokuSection section;

    @Test
    void testVerify() {
        Mockito.when(section.verify()).thenReturn(true);

        SudokuField[] array = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            array[i] = new SudokuField();
        }
        assertTrue(section.verify());
    }

    @Test
    void testTrueVerify() {
        SudokuField[] array = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            array[i] = new SudokuField(i);
        }
        assertFalse(section.verify());
    }

}
