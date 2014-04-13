package org.comprog.SudokuLab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
  
  private static final String TITLE = "Sudoku";
  // private static int WIDTH = 600;
  // private static int HEIGHT = 600;
  
  private static String RESOURE_PREFIX = "res/";
  private static String CSS_PREFIX = "org/comprog/SudokuLab5/" + RESOURE_PREFIX;
  
  private static String FXML_PATH = RESOURE_PREFIX + "ui.fxml";
  private static String STYLE_PATH = CSS_PREFIX + "style.css";
  
  private Scene scene;
  private VBox vbox;
 
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(TITLE);
    
    vbox = FXMLLoader.load(getClass().getResource(FXML_PATH));
    scene = new Scene(vbox);
    scene.getStylesheets().add(STYLE_PATH);
    
    vbox.requestFocus();
    
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
  
  public static void main( String[] args ) {
    launch(args);
  }
  
}
