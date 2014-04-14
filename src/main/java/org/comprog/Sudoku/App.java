package org.comprog.Sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class representing JavaFX Application
 */
public class App extends Application {
  
  private static final String TITLE = "Sudoku";
  /*private static int WIDTH = 600;
  private static int HEIGHT = 600;*/
  
  private static final String UI_RESOURCE_PREFIX = "ui/";
  private static final String FXML_FILE_PATH = UI_RESOURCE_PREFIX + "ui.fxml";
  private static final String STYLES_FILE_PATH = UI_RESOURCE_PREFIX + "style.css";
  
  private Scene scene;
  private VBox vbox;
 
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(TITLE);
    
    vbox = FXMLLoader.load(getClass().getClassLoader().getResource(FXML_FILE_PATH));
    scene = new Scene(vbox);
    scene.getStylesheets().add(STYLES_FILE_PATH);
    
    vbox.requestFocus();
    
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
  
  public static void main( String[] args ) {
    launch(args);
  }
  
}
