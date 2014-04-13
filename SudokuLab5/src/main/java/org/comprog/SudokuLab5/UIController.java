package org.comprog.SudokuLab5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
  @FXML private Button generateBoardButton;
  @FXML private RadioButton easyModeButton;
  @FXML private RadioButton mediumModeButton;
  @FXML private RadioButton hardModeButton;
  
  private ToggleGroup toggleGroup;
  
  private IndexedTextField[][] numberFields;
  
  private SudokuBoard board = new SudokuBoard(Difficulty.EASY);
  private SudokuSolver randomSolver = new RandomSudokuSolver();
  
  /**
   * Method filling the gird on first run
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // set up UI
    setUpRadioButtons();
    setUpGrid();
    fillGrid();
    
    // higlight missing fields
    for (int i = 0; i < numberFields.length; ++i) {
      for (int j = 0; j < numberFields[i].length; ++j) {
        invalidateField(numberFields[i][j]);
      }
    }
  }

  private void setUpGrid() {
    numberFields = new IndexedTextField[FIELD_NUMBER][FIELD_NUMBER];
    
    for (int i = 0; i < FIELD_NUMBER; ++i) {
      numberFields[i] = new IndexedTextField[FIELD_NUMBER];
      for (int j = 0; j < FIELD_NUMBER; ++j) {
        String number = String.valueOf(board.get(i, j));
        numberFields[i][j] = new IndexedTextField(number, i, j);
        addTextFieldValidation(numberFields[i][j]);
        grid.add(numberFields[i][j], i, j);
      }
    }
  }

  private void setUpRadioButtons() {
    toggleGroup = new ToggleGroup();
    easyModeButton.setToggleGroup(toggleGroup);
    mediumModeButton.setToggleGroup(toggleGroup);
    hardModeButton.setToggleGroup(toggleGroup);
    toggleGroup.selectedToggleProperty().addListener((ov, old, currentButton) -> {
      RadioButton radioButton = (RadioButton) currentButton;
      
      if (radioButton.getText().equals("EASY")) {
        board.setDifficulty(Difficulty.EASY);
      }
      else if (radioButton.getText().equals("MEDIUM")) {
        board.setDifficulty(Difficulty.MEDIUM);
      }
      else if (radioButton.getText().equals("HARD")) {
        board.setDifficulty(Difficulty.HARD);
      }
      
      fillGrid();
    });
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
    board.clearFields();
  
    for (int i = 0; i < FIELD_NUMBER; ++i) {
      for (int j = 0; j < FIELD_NUMBER; ++j) {
        numberFields[i][j].setText(String.valueOf(board.get(i, j)));
      }
    }
  }
  
  /**
   * Mathod that ensures that single text field contains only one character.
   * If the field is empty or the character is not a number, the proper style
   * is applied to inform user of the invalid value;
   * @param field text field to affect
   */
  private void addTextFieldValidation(IndexedTextField field) {
    field.setPrefColumnCount(1);
    
    field.textProperty().addListener((observableValue, oldValue, newValue) -> {
      if (newValue.length() > 1) {
        field.setText(newValue.substring(1, 2));
      }
      
      invalidateField(field);
      invalidateBoard();
      
    });
  }

  /**
   * Checks if the board is correct and if so
   * highlights it
   */
  private void invalidateBoard() {
    boolean isBoardCorrect = board.isCorrect();
    
    for (int i = 0; i < numberFields.length; ++i) {
      for (int j = 0; j < numberFields[i].length; ++j) {
        if (isBoardCorrect)
          numberFields[i][j].setStyle(CORRECT_STYLE);
        else
          numberFields[i][j].setStyle(null);
      }
    }
  }

  /**
   * Chcecks wheter the fields is valid ( contains only numbers
   * 1 - 9) and highlights that field if it is not correct
   */
  private void invalidateField(IndexedTextField field) {
    String value = field.getText();
    
    if (!value.matches("^\\d$") || value.matches("^0$")) {
      field.getStyleClass().add(INVALID_STYLE_CLASS_NAME);
    }
    else {
      field.getStyleClass().removeAll(INVALID_STYLE_CLASS_NAME);
      board.set(field.getX(), field.getY(), new Integer(value));
      System.out.println("field set");
      System.out.println(board.isCorrect());
    }
  }
  
}
