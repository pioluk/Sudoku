package org.comprog.SudokuLab4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.comprog.SudokuLab4.RandomSudokuSolver;
import org.comprog.SudokuLab4.SudokuBoard;
import org.comprog.SudokuLab4.SudokuSolver;
import org.junit.BeforeClass;
import org.junit.Test;

public class RandomSudokuSolverTest {
  
  private static SudokuSolver solver;
  private static SudokuBoard board;
  
  @BeforeClass
  public static void init() {
    solver = new RandomSudokuSolver();
    board = new SudokuBoard();
    solver.solve(board);
  }
  
  private List<Boolean> createEmptyBoolList() {
    List<Boolean> list = new ArrayList<Boolean>(10);
    
    for (int i = 0; i < 10; ++i) {
      list.add(false);
    }
    
    return list;
  }

  @Test
  public void testRows() {
    List<Boolean> elements;
    
    for (int i = 0; i < 9; ++i) {
      elements = createEmptyBoolList();
      
      for (int j = 0; j < 9; ++j) {
        int index = board.get(j, i);
        if (elements.get(index)) {
          fail("Repeating number in a row");
          System.out.println(i);}
        else
          elements.set(index, true);
      }
    }
  }
  
  @Test
  public void testColumns() {
    List<Boolean> elements;
    
    for (int i = 0; i < 9; ++i) {
      elements = createEmptyBoolList();
      
      for (int j = 0; j < 9; ++j) {
        int index = board.get(i, j);
        if (elements.get(index))
          fail("Repeating number in a column");
        else
          elements.set(index, true);
      }
    }
  }
  
  @Test
  public void testBlocks() {
    List<Boolean> elements;
       
    for (int x = 0; x < 9; x += 3) {
      for (int y = 0; y < 9; y += 3) {
        elements = createEmptyBoolList();
        
        for (int i = x; (i % 3 != 0) || i == x; ++i) {
          for (int j = y; (j % 3 != 0 || j == y); ++j) {
            int index = board.get(j, i);
            if (elements.get(index))
              fail("Repeating number in a block");
            else
              elements.set(index, true);
          }
        }
      }
    }
  }

}
