package org.comprog.SudokuLab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
  
  private static final String TITLE = "Sudoku";
  private static int WIDTH = 400;
  private static int HEIGHT = 400;
  private static final double GAP = 6.0;
  
  private Scene scene;
  private GridPane grid;
  private Label[][] numbers;
  private Button button;
  
  private static SudokuBoard board = new SudokuBoard();
  private static final SudokuSolver randomSolver = new RandomSudokuSolver();
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(TITLE);
    
    initComponents();
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void initComponents() {   
    grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(4 * GAP);
    grid.setVgap(GAP);
    grid.setPadding(new Insets(12.0));
    
    initSudoku();
    
    button = new Button("Reload");
    button.setOnAction((event) -> initSudoku());
    
    grid.add(button, 0, 9);
    
    scene = new Scene(grid);
  }

  private void initSudoku() {
    randomSolver.solve(board);
    
    if (numbers == null) {
      numbers = new Label[9][9];
      
      for (int i = 0; i < 9; ++i) {
        numbers[i] = new Label[9];
        for (int j = 0; j < 9; ++j) {
          numbers[i][j] = new Label(String.valueOf(board.get(i, j)));
          numbers[i][j].setFont(new Font("Comic Sans Ms", 36.0));
          grid.add(numbers[i][j], i, j);
        }
      }
    }
    else {
      for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
          numbers[i][j].setText(String.valueOf(board.get(i, j)));
        }
      }
    }
  }
  
  public static void main( String[] args ) {
    launch(args);
  }
  
}
