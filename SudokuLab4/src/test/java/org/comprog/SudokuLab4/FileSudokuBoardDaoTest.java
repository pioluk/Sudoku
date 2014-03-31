package org.comprog.SudokuLab4;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.After;
import org.junit.Test;

public class FileSudokuBoardDaoTest {

  private static final String fileName = "/tmp/FileSudokuBoardDaoTest.dat";

  @Test
  public void testBoard() {
    SudokuBoard board = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(board);
    
    Dao<SudokuBoard> sudokuDao = (FileSudokuBoardDao) DaoFactory.create(DaoTypes.FileSuokuBoard, fileName);
    sudokuDao.write(board);
    SudokuBoard board2 = sudokuDao.read();
    
    assertEquals(board, board2);
  }
  
  @After
  public void tearDown() throws Exception {
    File file = new File(fileName);
    if (!file.delete())
      throw new RuntimeException("Error deleting " + fileName);
  }

}
