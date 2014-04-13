package org.comprog.SudokuLab5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controller class for UI elements
 */
public class UIController implements Initializable {

  
  private static final String INVALID_STYLE_CLASS_NAME = "invalid-field";
  private static final String CORRECT_STYLE = "-fx-background-color: green;";

  /**
   * Number of fields in a row/column
   */
  private static final int FIELD_NUMBER = 9;
  
  @FXML private GridPane grid;
  @FXML private Button reloadButton;
  
  private IndexedTextField[][] numbers;
  
  private static SudokuBoard board = new SudokuBoard();
  private static SudokuSolver randomSolver = new RandomSudokuSolver();
  
  /**
   * Method filling the gird on first run
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fillGrid();
  }
  
  /**
   * Method invoked on reload button click
   */
  @FXML
  private void onReloadButtonAction(ActionEvent event) {
    fillGrid();
  } 
  
  /**
   * Generates sudoku board and fills the grid with
   * text fields of corresponding value
   */
  private void fillGrid() {
    randomSolver.solve(board);
    board.clearFields(Difficulty.EASY);
  
    if (numbers == null) {
      numbers = new IndexedTextField[FIELD_NUMBER][FIELD_NUMBER];
    
      for (int i = 0; i < FIELD_NUMBER; ++i) {
        numbers[i] = new IndexedTextField[FIELD_NUMBER];
        for (int j = 0; j < FIELD_NUMBER; ++j) {
          String number = String.valueOf(board.get(i, j));
          if (number.equals("-1"))
            number = " ";
            
          numbers[i][j] = new IndexedTextField(number, i, j);
          addTextFieldValidation(numbers[i][j]);
          grid.add(numbers[i][j], i, j);
        }
      }
    }
    else {
      for (int i = 0; i < FIELD_NUMBER; ++i) {
        for (int j = 0; j < FIELD_NUMBER; ++j) {
          numbers[i][j].setText(String.valueOf(board.get(i, j)));
        }
      }
    }
  }
  
  /**
   * Mathod that ensures that single text field contains only one character.
   * If the field is empty or the character is not a number, the proper style
   * is applied to inform user of the invalid value;
   * @param text field to affect
   */
  private void addTextFieldValidation(IndexedTextField field) {
    field.setPrefColumnCount(1);
    
    field.textProperty().addListener((observableValue, oldValue, newValue) -> {
      if (newValue.length() > 1) {
        field.setText(newValue.substring(0, 1));
      }
      
      if (!newValue.matches("^\\d$") || newValue.matches("^0$")) {
        field.getStyleClass().add(INVALID_STYLE_CLASS_NAME);
      }
      else {
        field.getStyleClass().remove(INVALID_STYLE_CLASS_NAME);
        board.set(field.getX(), field.getY(), new Integer(newValue));
        System.out.println("field set");
        System.out.println(board.isCorrect());
      }
      
      boolean isBoardCorrect = board.isCorrect();
      
      for (int i = 0; i < numbers.length; ++i) {
        for (int j = 0; j < numbers[i].length; ++j) {
          if (isBoardCorrect)
            numbers[i][j].setStyle(CORRECT_STYLE);
          else
            numbers[i][j].setStyle(null);
        }
      }
      
    });
  }
  
}
