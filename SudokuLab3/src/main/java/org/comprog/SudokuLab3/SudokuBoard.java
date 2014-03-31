package org.comprog.SudokuLab3;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
  
  private static final int SIZE = 9;
  private List<List<Integer>> board;
  
  public SudokuBoard() {
    board = new ArrayList<List<Integer>>(SIZE);
    for (int i = 0; i < SIZE; ++i) {
      board.add(new ArrayList<Integer>(SIZE));
      
      for (int j = 0; j < SIZE; ++j) {
        board.get(i).add(0);
      }
    }
  }
  
  public SudokuBoard(SudokuBoard other) {
    this();
    Collections.copy(this.board, other.board);
  }
  
  public int get(int x, int y) {
    return board.get(x).get(y);
  }
  
  public void set(int x, int y, int v) {
    board.get(x).set(y, v);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < SIZE; ++i) {
      for (int j = 0; j < SIZE; ++j) {
        sb.append(get(i, j));
        sb.append(" ");
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
  
 
}
