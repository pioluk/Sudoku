package org.comprog.SudokuLab5;

import javax.swing.SwingUtilities;

public class App {
  
  public static void main( String[] args ) {
    final SudokuBoard sudoku = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(sudoku);
    
    Dao<SudokuBoard> sudokuDao = (FileSudokuBoardDao) DaoFactory.create(DaoTypes.FileSuokuBoard, "/tmp/SudokuBoard.dat");
    sudokuDao.write(sudoku);
    System.out.println(sudokuDao.read());
  
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Window(sudoku);
      }
    });
    
  }
}
