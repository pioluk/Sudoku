package org.comprog.SudokuLab4;

public class App {
  public static void main( String[] args ) {
    SudokuBoard sudoku = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(sudoku);
    
    Dao<SudokuBoard> sudokuDao = (FileSudokuBoardDao) DaoFactory.create(DaoTypes.FileSuokuBoard, "/tmp/SudokuBoard.dat");
    sudokuDao.write(sudoku);
    System.out.println(sudokuDao.read());
  }
}
