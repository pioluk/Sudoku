package org.comprog.Sudoku;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.comprog.Sudoku.SudokuBoard;
import org.junit.Test;

public class SudokuBoardTest {
  
  private final static SudokuBoard sudoku;
  
  static {
    sudoku = new SudokuBoard();
    sudoku.fillBoard();
    System.out.println(sudoku);
  }

  @Test
  public void testRows() {
    boolean[] elements = new boolean[10];
    
    for (int i = 0; i < 9; ++i) {
      Arrays.fill(elements, false);
      
      for (int j = 0; j < 9; ++j) {
        int index = sudoku.get(j, i);
        if (elements[index]) {
          fail("Repeating number in a row");
          System.out.println(i);}
        else
          elements[index] = true;
      }
    }
  }
  
  @Test
  public void testColumns() {
    boolean[] elements = new boolean[10];
    
    for (int i = 0; i < 9; ++i) {
      Arrays.fill(elements, false);
      
      for (int j = 0; j < 9; ++j) {
        int index = sudoku.get(i, j);
        if (elements[index])
          fail("Repeating number in a column");
        else
          elements[index] = true;
      }
    }
  }
  
  @Test
  public void testBlocks() {
    boolean[] elements = new boolean[10];
       
    for (int x = 0; x < 9; x += 3) {
      for (int y = 0; y < 9; y += 3) {
        Arrays.fill(elements, false);
        
        for (int i = x; (i % 3 != 0) || i == x; ++i) {
          for (int j = y; (j % 3 != 0 || j == y); ++j) {
            int index = sudoku.get(j, i);
            if (elements[index])
              fail("Repeating number in a block");
            else
              elements[index] = true;
          }
        }
      }
    }
  }

}
