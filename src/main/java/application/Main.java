package application;

import controller.AppController;
import controller.FXMLController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

  private Stage mainStage;
  private Screen screen;
  private Scene scene;
  private Parent root;
  private FXMLController fxmlController;
  private AppController appController;


  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    createAppEntities();
    startApp();
  }

  private void startApp() {
        appController.start();
  }

  private void createAppEntities() {
    System.out.println("Create App entities...");
    initWindow();
    initFXML();
    initController();
    initScene();
    System.out.println("App entities successfully created!");
  }

  private void initController() {
    appController = new AppController(fxmlController);
  }


  private void initFXML() {
    FXMLLoader fxmlLoader = new FXMLLoader();
    try {
      root = fxmlLoader.load(getClass().getResource("/fxml/appView.fxml").openStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    fxmlController = (FXMLController) fxmlLoader.getController();
  }


  /**
   * Create Window, set Size and Position
   */
  private void initWindow() {
    mainStage.setTitle("Bike Manager");
    mainStage.getIcons().add(new Image("/images/bmLogo.png"));

    screen = Screen.getPrimary();
    Rectangle2D screenBounds = screen.getVisualBounds();

    mainStage.setWidth(screenBounds.getWidth() / 1.4);
    mainStage.setHeight(mainStage.getWidth() * 0.6);

    mainStage.setX((screenBounds.getWidth() - mainStage.getWidth()) / 2);
    mainStage.setY((screenBounds.getHeight() - mainStage.getHeight()) / 2);
  }

  private void initScene() {
    scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
