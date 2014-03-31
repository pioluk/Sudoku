package org.comprog.SudokuLab5;

public class SudokuField implements Cloneable, Comparable<SudokuField> {

  // private int x;
  // private int y;
  private int value;
  
  SudokuField(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
  
  @Override
  public SudokuField clone() {
    return new SudokuField(value);
  }

  @Override
  public int compareTo(SudokuField other) {
    if (value > other.value)
      return 1;
    else if (value < other.value)
      return -1;
    
    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SudokuField other = (SudokuField) obj;
    if (value != other.value)
      return false;
    return true;
  }
  
  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}
