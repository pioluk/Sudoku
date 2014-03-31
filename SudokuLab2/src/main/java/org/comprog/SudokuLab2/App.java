package org.comprog.SudokuLab2;

public class App {
  public static void main( String[] args ) {
    SudokuBoard sudoku = new SudokuBoard();
//    sudoku.fillBoard();
    SudokuSolver rSolver = new RandomSudokuSolver();
    rSolver.solve(sudoku);
    System.out.println(sudoku);
  }
}
