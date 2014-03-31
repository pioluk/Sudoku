package org.comprog.SudokuLab5;

import javax.swing.JFrame;

public class Window extends JFrame {

  private static final long serialVersionUID = 6496147365412721401L;
  private static final String TITLE = "Sudoku";
  private final static int WIDTH = 400;
  private final static int HEIGHT = 400;
  
  Window(SudokuBoard board) {
    setTitle(TITLE);
    setSize(WIDTH, HEIGHT);
    setVisible(true);
    initComponents();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  private void initComponents() {
    
  }
  
}
