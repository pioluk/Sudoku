package org.comprog.SudokuLab2;

import java.util.Arrays;

public class SudokuBoard {
  
  private int[][] board;
  
  public SudokuBoard() {
    board = new int[9][9];
  }
  
  public SudokuBoard(SudokuBoard other) {
    this();
    board = Arrays.copyOf(other.board, other.board.length);
  }
  
  public int get(int x, int y) {
    return board[x][y];
  }
  
  public void set(int x, int y, int v) {
    board[x][y] = v;
  }
  
//  public void fillBoard() {
//
//  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        sb.append(board[i][j] + " ");
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
  
 
}
