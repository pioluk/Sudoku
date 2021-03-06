package org.comprog;

public interface SudokuSolver {

  /**
   * Method filling {@link #SudokuBoard} object
   *
   * @param board board to fill
   */
  public void solve(SudokuBoard board);
}
