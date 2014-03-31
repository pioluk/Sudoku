package org.comprog.SudokuLab4;

import org.comprog.SudokuLab4.DaoFactory.DaoTypes;

public class App {
  public static void main( String[] args ) {
    SudokuBoard sudoku = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(sudoku);
    
    Dao<SudokuBoard> sudokuDao = DaoFactory.create(DaoTypes.FileSuokuBoard, "/tmp/SudokuBoard.dat");
    sudokuDao.write(sudoku);
    System.out.println(sudokuDao.read());
  }
}
