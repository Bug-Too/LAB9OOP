import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class Lab9Test {

    //ขอบคุณความช่วยเหลือจาก ธนดล.

    @Test
    void nonExceptionTest() {
        assertEquals(20.0, Lab9.calculator("10+10", 1));
        assertEquals(30.0, Lab9.calculator("10 * 3", 1));
        assertEquals(10,
                Lab9.calculator("2+2+2+2+2", 1));
    }


    @Test
    void exceptionTest(){
        Throwable thrownException = assertThrows(InputMismatchException.class,
                () -> Lab9.calculator("2)2", 1));
        assertEquals("Invalid input line: 0",
                thrownException.getMessage());

        thrownException = assertThrows(InputMismatchException.class,
                () -> Lab9.calculator("/33121", 1));
        assertEquals("Mismatch operator line: 0",
                thrownException.getMessage());

        thrownException = assertThrows(InputMismatchException.class,
                () -> Lab9.calculator("1--5", 1));
        assertEquals("Mismatch operator line: 0",
                thrownException.getMessage());

        thrownException = assertThrows(InputMismatchException.class,
                () -> Lab9.calculator("", 1));
        assertEquals("Empty line: 1",
                thrownException.getMessage());
    }

    @Test
    void readWriteFileTest() {
        String inputFileDirectory = "/home/pooh/Documents/OOP/LAB9/src/input.txt";
        String outputFileDirectory = "/home/pooh/Documents/OOP/LAB9/src/output.txt";
        Lab9.readAndWrite(inputFileDirectory,outputFileDirectory);

        try (FileReader fileReader = new FileReader(outputFileDirectory);
             BufferedReader br = new BufferedReader(fileReader)) {
            String inline;
            int currentLine = 0;
            while ((inline = br.readLine()) != null) {
                if (currentLine == 0) {
                    assertEquals("2+2+2+2+2 = 10.0" , inline);
                } else if (currentLine == 1) {
                    assertEquals("3+3+3+3+3 = 15.0" , inline);
                } else {
                    Assertions.fail("Written result file is wrong - Too many lines");
                }
                currentLine++;
            }


        } catch (IOException e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }
}