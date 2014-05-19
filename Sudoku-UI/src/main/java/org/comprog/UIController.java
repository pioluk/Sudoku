package org.comprog;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.comprog.SudokuException.Type;

/**
 * Controller class for UI elements
 */
public class UIController implements Initializable {


  private static final String INVALID_STYLE_CLASS_NAME = "invalid-field";
  private static final String CORRECT_STYLE = "-fx-background-color: green;";
  private static final String INCORRECT_STYLE = "-fx-background-color: red;";

  /**
   * Number of fields in a row/column
   */
  private static final int FIELDS_PER_DIMENSION = 9;

  @FXML private VBox vbox;
  @FXML private GridPane grid;
  @FXML private Button generateBoardButton;
  @FXML private RadioButton superEasyModeButton;
  @FXML private RadioButton easyModeButton;
  @FXML private RadioButton mediumModeButton;
  @FXML private RadioButton hardModeButton;
  
  private Stage stage;
  private MenuBar menuBar;

  private ToggleGroup toggleGroup;

  private IndexedTextField[][] numberFields;

  private SudokuBoard board = new SudokuBoard(Difficulty.SUPER_EASY);
  private SudokuSolver randomSolver = new RandomSudokuSolver();
  
  private ResourceBundle translations;

  /**
   * Method filling the gird on first run
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    translations = resources;
    
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

  public void setUpMenu() {
    Menu file = new Menu(translations.getString("file"));
    
    MenuItem fileOpen = new MenuItem(translations.getString("open"));
    fileOpen.setOnAction((e) -> onFileOpen(e));
    
    MenuItem fileSave = new MenuItem(translations.getString("save"));
    fileSave.setOnAction((e) -> onFileSave(e));
    
    MenuItem fileQuit = new MenuItem(translations.getString("quit"));
    fileQuit.setOnAction((e) -> System.exit(0));
    
    file.getItems().addAll(fileOpen, fileSave, fileQuit);
    menuBar.getMenus().add(0, file);
  }

  private void setUpGrid() {
    numberFields = new IndexedTextField[FIELDS_PER_DIMENSION][FIELDS_PER_DIMENSION];

    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      numberFields[i] = new IndexedTextField[FIELDS_PER_DIMENSION];
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
        String number = String.valueOf(board.get(i, j));
        numberFields[i][j] = new IndexedTextField(number, i, j);
        addTextFieldValidation(numberFields[i][j]);
        grid.add(numberFields[i][j], i, j);
      }
    }
  }

  /**
   * Sets up radio buttons and action listeners for them
   */
  private void setUpRadioButtons() {
    toggleGroup = new ToggleGroup();
    superEasyModeButton.setToggleGroup(toggleGroup);
    easyModeButton.setToggleGroup(toggleGroup);
    mediumModeButton.setToggleGroup(toggleGroup);
    hardModeButton.setToggleGroup(toggleGroup);
    toggleGroup.selectedToggleProperty().addListener((ov, old, currentButton) -> {
      RadioButton radioButton = (RadioButton) currentButton;

      if (radioButton.getText().equals(translations.getString("super_easy"))) {
        board.setDifficulty(Difficulty.SUPER_EASY);
      }
      else if (radioButton.getText().equals(translations.getString("easy"))) {
        board.setDifficulty(Difficulty.EASY);
      }
      else if (radioButton.getText().equals(translations.getString("medium"))) {
        board.setDifficulty(Difficulty.MEDIUM);
      }
      else if (radioButton.getText().equals(translations.getString("hard"))) {
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

    for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
      for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
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
    boolean isBoardFull = board.isFull();

    for (int i = 0; i < numberFields.length; ++i) {
      for (int j = 0; j < numberFields[i].length; ++j) {
        if (isBoardCorrect && isBoardFull)
          numberFields[i][j].setStyle(CORRECT_STYLE);
        else if (isBoardFull)
          numberFields[i][j].setStyle(INCORRECT_STYLE);
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
    }
  }
  
  /**
   * Action tiggered on file open
   * @param event
   * @throws SudokuException
   */
  private void onFileOpen(ActionEvent event) throws SudokuException {
    FileChooser chooser = new FileChooser();
    chooser.setTitle(translations.getString("open"));
    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File file = chooser.showOpenDialog(stage);
    
    Difficulty difficulty = board.getDifficulty();
    
    Dao reader = DaoFactory.create(DaoTypes.FileSuokuBoard, file.toString());
    
    try {
      board = (SudokuBoard) reader.read();
      board.setDifficulty(difficulty);
      
      for (int i = 0; i < FIELDS_PER_DIMENSION; ++i) {
        for (int j = 0; j < FIELDS_PER_DIMENSION; ++j) {
          numberFields[i][j].setText(String.valueOf(board.get(i, j)));
        }
      }
    }
    catch (Exception e) {
      throw new SudokuException(Type.FILE_NOT_FOUND, file.toString());
    }
  }
  
  /**
   * Action triggered on file save
   * @param event
   * @throws SudokuException
   */
  private void onFileSave(ActionEvent event) throws SudokuException {
    FileChooser chooser = new FileChooser();
    chooser.setTitle(translations.getString("save"));
    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
    File file = chooser.showSaveDialog(stage);
    
    Dao writer = DaoFactory.create(DaoTypes.FileSuokuBoard, file.toString());
    
    try {
      writer.write(board);
    }
    catch (Exception e) {
      throw new SudokuException(Type.IO_EXCEPTION, "");
    }
  }
  
  /**
   * Assigns stage
   * @param stage
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }
  
  /**
   * Assigns menubar
   * @param menubar
   */
  public void setMenuBar(MenuBar menuBar) {
    this.menuBar = menuBar;
  }

}
