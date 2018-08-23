package application;

import controller.AppController;
import controller.FXMLController;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

  private Stage mainStage;
  private Stage splashStage;
  private Screen screen;
  private Scene scene;
  private Parent root;
  private FXMLController fxmlController;
  private AppController appController;
  private ProgressBar progressBar;

  private static final int SPLASH_DURATION_MILLIS =700;
  private static final int SPLASH_FADEOUT_MILLIS = 300;

  private void showSplashScreen() {
    splashStage = new Stage();
    splashStage.initModality(Modality.APPLICATION_MODAL);
    splashStage.initStyle(StageStyle.TRANSPARENT);
    splashStage.setAlwaysOnTop(true);

    StackPane stackPane = new StackPane();
    Scene splashScene = new Scene(stackPane);
    splashScene.setFill(null);

    ImageView imageView = new ImageView(new Image("/images/BMLoadingScreen.png"));
    BorderPane borderPane = new BorderPane();
    stackPane.getChildren().addAll(imageView, borderPane);
    stackPane.setAlignment(Pos.CENTER);

    progressBar = new ProgressBar(0);

    progressBar.setPrefWidth(imageView.getImage().getWidth());

    borderPane.setBottom(progressBar);

    splashStage.setScene(splashScene);
    splashStage.show();

    waitForSplashScreen(SPLASH_DURATION_MILLIS);


  }


  /**
   * Closes the Splash Screen after a set amount of time.
   *
   * @param waitMillis Time in milliseconds until the splash screen closes.
   */
  private void waitForSplashScreen(int waitMillis) {

    new Thread(() -> {

      //Wait...
      for (int i = 0; i < waitMillis; i++) {
        progressBar.setProgress(progressBar.getProgress() + 1.0 / waitMillis);
        try {
          Thread.sleep(1L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        if (i > waitMillis * 0.65 && !mainStage.isShowing()) {
          Platform.runLater(() -> mainStage.show());
        }
      }

      //Fadeout...
      Timeline timeline = new Timeline();
      KeyFrame key = new KeyFrame(Duration.millis(SPLASH_FADEOUT_MILLIS),
          new KeyValue(splashStage.getScene().getRoot().opacityProperty(), 0));
      timeline.getKeyFrames().add(key);

      timeline.setOnFinished((ae) -> Platform.runLater(() -> {
        splashStage.close();
        splashStage = null;
      }));

      timeline.play();

    }).start();

  }


  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    showSplashScreen();
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
