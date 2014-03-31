package org.comprog.SudokuLab3;

public class App {
  public static void main( String[] args ) {
    SudokuBoard sudoku = new SudokuBoard();
    SudokuSolver randomSolver = new RandomSudokuSolver();
    randomSolver.solve(sudoku);
    System.out.println(sudoku);
  }
}
