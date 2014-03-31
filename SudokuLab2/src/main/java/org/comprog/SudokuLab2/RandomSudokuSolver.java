package org.comprog.SudokuLab2;

import java.util.Random;

public class RandomSudokuSolver implements SudokuSolver {

  private Random rng = new Random();
  
  public void solve(SudokuBoard board) {  
    
    SudokuBoard boardBackup = new SudokuBoard(board);

    for (int num = 1; num < 10; ++num) {
      int backupCount = 0;
            
      boardBackup = new SudokuBoard(board);
      
      for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
          boolean u = false;
          int count = 0;
          while (!u) {
            if (count > 100) {
              board = new SudokuBoard(boardBackup);
              
              i = 0;
              j = 0;
              ++backupCount;
              
              if (backupCount > 50){
                for (int x = 0; x < 9; ++x)
                  for (int y = 0; y < 9; ++y) {
                    board.set(x, y, 0);
                    boardBackup.set(x, y, 0);
                  }
                num = 0;
                u = true;
                i = 3;
                j = 3;
              }
            }
            else {
              int k = rng.nextInt(3), l = rng.nextInt(3);
              if (board.get(3*i+k, 3*j+l) == 0 && u != true) {
                boolean um = true;
                for (int m = 0; m < 9; ++m) {
                  if (board.get(3*i+k, m) == num || board.get(m, 3*j+l) == num)
                    um = false;
                }
                if (um) {
                  board.set(3*i+k, 3*j+l, num);
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

}
