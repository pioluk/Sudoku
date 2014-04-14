package org.comprog.Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Difficulty levels
 * NONE is used for tests
 */
enum Difficulty {
  NONE, EASY, MEDIUM, HARD
}

/**
 * Class representing sudoku board
 */
public class SudokuBoard implements Serializable {

  private static final long serialVersionUID = 4285504207420599661L;
  private static final int FIELDS_PER_DIMENSION = 9;
  private List<List<SudokuField>> board;
  private Difficulty difficulty = Difficulty.EASY;
  
  /**
   * Default constructor creating zeroed sudoku board 
   */
  public SudokuBoard(Difficulty difficulty) {
    this.difficulty = difficulty;
    
    board = new ArrayList<List<SudokuField>>(FIELDS_PER_DIMENSION);
    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      board.add(new ArrayList<SudokuField>(FIELDS_PER_DIMENSION));
      
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
        board.get(i).add(new SudokuField(0));
      }
    }
  }
  
  /**
   * Copy constructor
   * @param other {@link #SudokuBoard} object to copy
   */
  public SudokuBoard(SudokuBoard other) {
    this(Difficulty.EASY);
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
  
  /**
   * Clear number of fields in board based on difficulty
   */
  public void clearFields() {
    Random rand = new Random();
    
    int amount = 0;
    
    if (difficulty == Difficulty.EASY)
      amount = 3;
    else if (difficulty == Difficulty.MEDIUM)
      amount = 12;
    else if (difficulty == Difficulty.HARD)
      amount = 36;
    
    for (int i = 0; i < amount; ++i) {
      set(rand.nextInt(FIELDS_PER_DIMENSION), rand.nextInt(FIELDS_PER_DIMENSION), 0);
    }
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
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
    
    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
        sb.append(get(i, j));
        sb.append(" ");
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }

  /**
   * @return true, if the board is correct sudoku board, false, otherwise
   */
  public boolean isCorrect() {
    return isCorrectRows() && isCorrectColumns() && isCorrectBlocks();
  }
  
  private boolean isCorrectRows() {
    boolean[] elements = new boolean[10];
    
    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      Arrays.fill(elements, false);
      
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
        int index = get(j, i);
        if (elements[index] || index == 0)
          return false;
        else
          elements[index] = true;
      }
    }
    
    return true;
  }
  
  private boolean isCorrectColumns() {
    boolean[] elements = new boolean[10];
    
    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      Arrays.fill(elements, false);
      
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
        int index = get(i, j);
        if (elements[index] || index == 0)
          return false;
        else
          elements[index] = true;
      }
    }
    
    return true;
  }
  
  private boolean isCorrectBlocks() {
    boolean[] elements = new boolean[10];
       
    for (int x = 0; x < FIELDS_PER_DIMENSION; x += 3) {
      for (int y = 0; y < FIELDS_PER_DIMENSION; y += 3) {
        Arrays.fill(elements, false);
        
        for (int i = x; (i % 3 != 0) || i == x; ++i) {
          for (int j = y; (j % 3 != 0 || j == y); ++j) {
            int index = get(j, i);
            if (elements[index] || index == 0)
              return false;
            else
              elements[index] = true;
          }
        }
      }
    }
    
    return true;
  }
 
}
