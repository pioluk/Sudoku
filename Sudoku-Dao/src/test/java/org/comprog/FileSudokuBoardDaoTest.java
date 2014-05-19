package org.comprog;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Test;

public class FileSudokuBoardDaoTest {
  
  private static SudokuLogger logger;
  
  static {
    try {
      logger = SudokuLogger.getInstance();
    }
    catch (SudokuException e) {
      Logger.getLogger("Sudoku").log(Level.SEVERE, e.getLocalizedMessage());
    }
  }

  private static final String FILE_NAME = "FileSudokuBoardDaoTest.dat";

  @Test
  public void testSerializationCorrectness() {
    SudokuBoard board = new SudokuBoard(Difficulty.NONE);
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(board);

    Dao<SudokuBoard> sudokuDao = DaoFactory.create(DaoTypes.FileSuokuBoard, FILE_NAME);

    SudokuBoard board2 = null;

    try {
      sudokuDao.write(board);
      board2 = sudokuDao.read();
      System.out.println(board2);
    } catch (Exception e) {
      logger.log(Level.SEVERE, e.getLocalizedMessage());
    }

    assertEquals(board, board2);
  }

  @After
  public void tearDown() throws Exception {
    File file = new File(FILE_NAME);
    if (!file.delete())
      throw new RuntimeException("Error deleting " + FILE_NAME);
  }

}
