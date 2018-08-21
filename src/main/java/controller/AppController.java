package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AppModel;
import util.db.DatabaseConnector;
import util.db.DatabaseManager;

public class AppController {

  private FXMLController fxmlController;
  private DatabaseConnector databaseConnector;
  private AppModel appModel;
  private TreeView treeView;
  private Node defaultBikeIcon;
  private Node fancyBikeIcon;
  private Button bottomButton;
  private Label bottomLabel;
  private TreeController treeController;
  private DatabaseManager databaseManager;

  /**
   * Constructor
   */
  public AppController(FXMLController fxmlController) {
    this.fxmlController = fxmlController;
    appModel = new AppModel();
  }

  public void start() {
    System.out.println("Establishing Database Connection...");
    initdb();
    loadEntitiesFromFXML();
    initTreeController();
    databaseManager.testConnection();
  }


  private void initTreeController() {
    treeController = new TreeController(treeView, appModel.getCategories());
    treeController.fillTreeView(appModel.getBikes());
  }

  private void loadEntitiesFromFXML() {
    treeView = fxmlController.getTreeView();
    bottomButton = fxmlController.getBottomButton();
    bottomLabel = fxmlController.getBottomLabel();
  }


  private void loadIcons() {
    defaultBikeIcon = new ImageView(new Image("/images/defaultBike.png"));
    ((ImageView) defaultBikeIcon).setFitWidth(30);
    ((ImageView) defaultBikeIcon).setFitHeight(30);
    fancyBikeIcon = new ImageView(new Image("/images/tandem.png"));
  }


  private void initButtonListeners() {
    bottomButton.setOnAction(event -> {
      System.out.println(appModel.getCategories());
    });
  }


  private void initdb() {
    databaseConnector = new DatabaseConnector();
    if (databaseConnector.connect()) {
      databaseManager = new DatabaseManager(databaseConnector.getCon());
    }
  }

}
