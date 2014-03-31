package org.comprog.SudokuLab5;

import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing sudoku board
 */
public class SudokuBoard implements Serializable {

  private static final long serialVersionUID = -3891996623822661323L;
  private List<List<SudokuField>> board;
  
  /**
   * Default constructor creating zeroed sudoku board 
   */
  public SudokuBoard() {
    board = new ArrayList<List<SudokuField>>(9);
    for (int i = 0; i < 9; ++i) {
      board.add(new ArrayList<SudokuField>(9));
      
      for (int j = 0; j < 9; ++j) {
        board.get(i).add(new SudokuField(0));
      }
    }
  }
  
  /**
   * Copy constructor
   * @param other {@link #SudokuBoard} object to copy
   */
  public SudokuBoard(SudokuBoard other) {
    this();
    Collections.copy(this.board, other.board);
  }
  
  /**
   * @param x vertical position
   * @param y horizontal position
   * @return value at position (x, y)
   */
  public int get(int x, int y) {
    return board.get(x).get(y).getValue();
  }
  
  /**
   * @param x vertical position
   * @param y horizontal position
   * @param v value to set at position (x, y)
   */
  public void set(int x, int y, int v) {
    board.get(x).get(y).setValue(v);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((board == null) ? 0 : board.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    
    if (obj == null)
      return false;
    
    if (getClass() != obj.getClass())
      return false;
    
    SudokuBoard other = (SudokuBoard) obj;
    if (board == null) {
      if (other.board != null)
        return false;
    }
    else if (!board.equals(other.board))
      return false;
    
    return true;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        sb.append(get(i, j));
        sb.append(" ");
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
 
}
