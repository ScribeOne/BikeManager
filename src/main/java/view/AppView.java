package view;

import javafx.scene.layout.StackPane;

public class AppView {

  private StackPane mainPane;

  public AppView(StackPane appPane) {
    mainPane = appPane;
    System.out.printf("Width: %s \n Height: %s", mainPane.getWidth(), mainPane.getHeight());
  }
}
