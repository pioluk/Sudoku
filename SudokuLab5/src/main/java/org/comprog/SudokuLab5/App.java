package org.comprog.SudokuLab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
  
  private static final String TITLE = "Sudoku";
  private static int WIDTH = 400;
  private static int HEIGHT = 400;
  private static final double GAP = 6.0;
  private String CSS_PATH;
  
  private Scene scene;
  private VBox vbox;
  private GridPane grid;
  private Label[][] numbers;
  private Button button;
  
  private static SudokuBoard board = new SudokuBoard();
  private static final SudokuSolver randomSolver = new RandomSudokuSolver();
  
  App() {
    
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(TITLE);
    
    initComponents();
    
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private void initComponents() {   
    vbox = new VBox(6.0);
    vbox.setAlignment(Pos.CENTER);
    vbox.setPadding(new Insets(12.0));
    
    grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(6 * GAP);
    grid.setVgap(GAP);
    
    initSudoku();
    
    button = new Button("Reload");
    button.resize(380.0, 25.0);
    button.setOnAction((event) -> initSudoku());
    button.setMaxWidth(WIDTH);
    
    grid.add(button, 0, 9);
    
    vbox.getChildren().add(grid);
    vbox.getChildren().add(button);
    
    scene = new Scene(vbox);
  }

  private void initSudoku() {
    randomSolver.solve(board);
    
    if (numbers == null) {
      numbers = new Label[9][9];
      
      for (int i = 0; i < 9; ++i) {
        numbers[i] = new Label[9];
        for (int j = 0; j < 9; ++j) {
          numbers[i][j] = new Label(String.valueOf(board.get(i, j)));
          numbers[i][j].setFont(new Font("Droid Sans", 36.0));
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
