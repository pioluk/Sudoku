package org.comprog.SudokuLab5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UIController implements Initializable {

  @FXML private GridPane grid;
  @FXML private Button reloadButton;
  
  private TextField[][] numbers;
  
  private static SudokuBoard board = new SudokuBoard();
  private static SudokuSolver randomSolver = new RandomSudokuSolver();
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initSudoku();
  }
  
  @FXML private void reloadButtonClicked(ActionEvent event) {
    initSudoku();
  } 
  
  private void initSudoku() {
    randomSolver.solve(board);
  
    if (numbers == null) {
      numbers = new TextField[9][9];
    
      for (int i = 0; i < 9; ++i) {
        numbers[i] = new TextField[9];
        for (int j = 0; j < 9; ++j) {
          numbers[i][j] = new TextField(String.valueOf(board.get(i, j)));
          numbers[i][j].setPrefColumnCount(1);
          addTextValidation(numbers[i][j]);
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
  
  private static void addTextValidation(TextField field) {
    field.textProperty().addListener((observableValue, oldValue, newValue) -> {
      if (newValue.length() > 1) {
        field.setText(newValue.substring(0, 1));
      }
      
      if (!newValue.matches("\\d")) {
        field.setStyle("-fx-background-color: red;");
      }
      else {
        field.setStyle(null);
      }
    });
  }
  
}
