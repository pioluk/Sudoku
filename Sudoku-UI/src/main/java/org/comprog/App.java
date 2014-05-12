package org.comprog;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.comprog.SudokuException.Type;

/**
 * Class representing JavaFX Application
 */
public class App extends Application {
  
  // private static final Logger logger = Logger.getLogger("Sudoku");
  private static SudokuLogger logger;
  
  static {
    try {
      logger = SudokuLogger.getInstance();
    }
    catch (SudokuException e) {
      Logger.getLogger("Sudoku").log(Level.SEVERE, e.getLocalizedMessage());
    }
  }

  private static final String BUNDLE_NAME = "translations";

  private static final String TITLE = "Sudoku";

  // resources
  private static final String UI_RESOURCE_PREFIX = "ui/";
  private static final String FXML_FILE_PATH = UI_RESOURCE_PREFIX + "ui.fxml";
  private static final String STYLES_FILE_PATH = UI_RESOURCE_PREFIX + "style.css";

  private Stage stage;
  private Scene scene;
  private MenuBar menuBar;
  private Menu menuLanguage;
  private MenuItem englishMenuItem;
  private MenuItem polishMenuItem;
  private VBox vbox;
  
  private static ResourceBundle translationBundle;

  @Override
  public void start(Stage primaryStage) throws Exception {
    stage = primaryStage;
    
    primaryStage.setTitle(TITLE);
    
    try {
      loadView(Locale.getDefault());
    }
    catch(SudokuException e) {
      logger.log(Level.SEVERE, e.getLocalizedMessage());
      System.exit(1);
    }
    
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private void loadView(Locale locale) throws SudokuException {
    if (locale == null)
      locale = Locale.getDefault();
    
    translationBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    
    setUpMenu();
    
    
    try {
      URL url = getClass().getClassLoader().getResource(FXML_FILE_PATH);
      vbox = FXMLLoader.load(url, translationBundle);
    } 
    catch (Exception e) {
      throw new SudokuException(Type.FILE_NOT_FOUND, FXML_FILE_PATH);
    }
    
    vbox.getChildren().add(0, menuBar);
    
    scene = new Scene(vbox);
    scene.getStylesheets().add(STYLES_FILE_PATH);

    vbox.requestFocus();

    stage.setScene(scene);
  }

  private void setUpMenu() {
    menuBar = new MenuBar();
    menuBar.setId("menuBar");
    
    menuLanguage = new Menu(translationBundle.getString("language"));
    menuLanguage.setId("menuLanguage");
    
    englishMenuItem = new MenuItem(translationBundle.getString("english"));
    englishMenuItem.setOnAction((e) -> {
        try {
          Locale locale = new Locale("en", "GB");
          loadView(locale);
          SudokuExceptionBase.setLocale(locale);
        }
        catch(SudokuException e1) {
          logger.log(Level.SEVERE, e1.getLocalizedMessage());
        }
    });
    
    polishMenuItem = new MenuItem(translationBundle.getString("polish"));
    polishMenuItem.setOnAction((e) -> {
        try {
          Locale locale = new Locale("pl", "PL");
          loadView(locale);
          SudokuExceptionBase.setLocale(locale);
        }
        catch(SudokuException e2) {
          logger.log(Level.SEVERE, e2.getLocalizedMessage());
        }
    });
    
    menuLanguage.getItems().addAll(englishMenuItem, polishMenuItem);
    menuBar.getMenus().add(menuLanguage);
  }

  public static void main( String[] args ) {    
    launch(args);
    
//    SudokuLogger.close();
  }

}
