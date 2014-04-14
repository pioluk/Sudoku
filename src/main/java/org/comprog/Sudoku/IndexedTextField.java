package org.comprog.Sudoku;

import javafx.scene.control.TextField;

/**
 * Class extending TextField
 * adds information about location of the control
 *
 */
public class IndexedTextField extends TextField {
  
  private int x;
  private int y;

  public IndexedTextField(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public IndexedTextField(String text, int x, int y) {
    super(text);
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}
