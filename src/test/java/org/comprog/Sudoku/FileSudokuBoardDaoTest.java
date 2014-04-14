package org.comprog.Sudoku;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.comprog.Sudoku.Dao;
import org.comprog.Sudoku.DaoFactory;
import org.comprog.Sudoku.DaoTypes;
import org.comprog.Sudoku.Difficulty;
import org.comprog.Sudoku.RandomSudokuSolver;
import org.comprog.Sudoku.SudokuBoard;
import org.comprog.Sudoku.SudokuSolver;
import org.junit.After;
import org.junit.Test;

public class FileSudokuBoardDaoTest {

  private static final String fileName = "/tmp/FileSudokuBoardDaoTest.dat";

  @Test
  public void testSerializationCorrectness() {
    SudokuBoard board = new SudokuBoard(Difficulty.NONE);
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(board);
    
    Dao<SudokuBoard> sudokuDao = DaoFactory.create(DaoTypes.FileSuokuBoard, fileName);
    
    SudokuBoard board2 = null;
    
    try {
      sudokuDao.write(board);
      board2 = sudokuDao.read();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
    assertEquals(board, board2);
  }
  
  @After
  public void tearDown() throws Exception {
    File file = new File(fileName);
    if (!file.delete())
      throw new RuntimeException("Error deleting " + fileName);
  }

}
