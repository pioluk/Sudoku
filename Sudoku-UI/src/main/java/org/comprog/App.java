package org.comprog;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class representing JavaFX Application
 */
public class App extends Application {

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
  
  private static ResourceBundle bundle;

  @Override
  public void start(Stage primaryStage) throws Exception {
    stage = primaryStage;
    
    primaryStage.setTitle(TITLE);
    
    loadView(Locale.getDefault());
    
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  private void loadView(Locale locale) throws IOException {
    if (locale == null)
      locale = Locale.getDefault();
    
    bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    
    setUpMenu();
    
    vbox = FXMLLoader.load(getClass().getClassLoader().getResource(FXML_FILE_PATH),
                           bundle);
    
    vbox.getChildren().add(0, menuBar);
    
    scene = new Scene(vbox);
    scene.getStylesheets().add(STYLES_FILE_PATH);

    vbox.requestFocus();

    stage.setScene(scene);
  }

  private void setUpMenu() {
    menuBar = new MenuBar();
    menuBar.setId("menuBar");
    
    menuLanguage = new Menu(bundle.getString("language"));
    menuLanguage.setId("menuLanguage");
    
    englishMenuItem = new MenuItem(bundle.getString("english"));
    englishMenuItem.setOnAction((e) -> {
      try {
        loadView(new Locale("en", "GB"));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
    
    polishMenuItem = new MenuItem(bundle.getString("polish"));
    polishMenuItem.setOnAction((e) -> {
      try {
        loadView(new Locale("pl", "PL"));
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    });
    
    menuLanguage.getItems().addAll(englishMenuItem, polishMenuItem);
    menuBar.getMenus().add(menuLanguage);
  }

  public static void main( String[] args ) {    
    launch(args);
  }

}
