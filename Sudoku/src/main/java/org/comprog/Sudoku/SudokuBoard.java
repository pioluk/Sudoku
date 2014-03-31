package org.comprog.Sudoku;

import java.util.Random;

public class SudokuBoard {
  
  private int[][] board;
  private final Random rng;
  
  public SudokuBoard() {
    board = new int[9][9];
    rng = new Random();
  }
  
  public void fillBoard() {
    int[][] boardBackup = board.clone();
    
    for (int num = 1; num < 10; ++num) {
      int backupCount = 0;
      
      for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
          boolean u = false;
          int count = 0;
          while (!u) {
            if (count > 100) {
              board = boardBackup.clone();
              
              i = 0;
              j = 0;
              ++backupCount;
              
              if (backupCount > 50) {
                for (int x = 0; x < 9; ++x)
                  for (int y = 0; y < 9; ++y) {
                    board[x][y] = 0;
                    boardBackup[x][y] = 0;
                  }
                num = 0;
                u = true;
                i = 3;
                j = 3;
              }
            }
            else {
              int k = rng.nextInt(3), l = rng.nextInt(3);
              if (board[3*i+k][3*j+l] == 0 && u != true) {
                boolean um = true;
                for (int m = 0; m < 9; ++m) {
                  if (board[3*i+k][m] == num || board[m][3*j+l] == num)
                    um = false;
                }
                if (um) {
                  board[3*i+k][3*j+l] = num;
                  u = true;
                }
              }
              
              ++count;
            }
          }
          
        }
      }
    }
  }
  
  public int get(int x, int y) {
    return board[x][y];
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        sb.append(board[i][j]);
        sb.append(" ");
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
 
}
