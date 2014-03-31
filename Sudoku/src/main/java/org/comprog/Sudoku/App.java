package org.comprog.Sudoku;

public class App {
  public static void main( String[] args ) {
    SudokuBoard sudoku = new SudokuBoard();
    sudoku.fillBoard();
    System.out.println(sudoku);
  }
}
