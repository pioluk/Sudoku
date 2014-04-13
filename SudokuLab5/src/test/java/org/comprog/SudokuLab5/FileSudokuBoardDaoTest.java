package org.comprog.SudokuLab5;

import static org.junit.Assert.*;

import java.io.File;

import org.comprog.SudokuLab5.Dao;
import org.comprog.SudokuLab5.DaoFactory;
import org.comprog.SudokuLab5.DaoTypes;
import org.comprog.SudokuLab5.FileSudokuBoardDao;
import org.comprog.SudokuLab5.RandomSudokuSolver;
import org.comprog.SudokuLab5.SudokuBoard;
import org.comprog.SudokuLab5.SudokuSolver;
import org.junit.After;
import org.junit.Test;

public class FileSudokuBoardDaoTest {

  private static final String fileName = "/tmp/FileSudokuBoardDaoTest.dat";

  @Test
  public void testSerializationCorrectness() {
    SudokuBoard board = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(board);
    
    Dao<SudokuBoard> sudokuDao = DaoFactory.create(DaoTypes.FileSuokuBoard, fileName);
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
