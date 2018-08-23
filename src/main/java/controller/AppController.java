package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AppModel;
import model.Bike;
import util.db.DatabaseConnector;
import util.db.DatabaseManager;

public class AppController {

  private final String GREENICONPATH = "/images/greenIcon.png";
  private final String REDICONPATH = "/images/redIcon.png";
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
  private boolean connectedToDB;


  /**
   * Constructor
   */
  public AppController(FXMLController fxmlController) {
    this.fxmlController = fxmlController;
    appModel = new AppModel();
  }


  private List<Bike> createTestData() {
    return Arrays.asList(
        new Bike(0, "Rotwild", "X2", "erasmus", 26),
        new Bike(1, "Trek", "Fuel 2.0", "private", 26),
        new Bike(2, "Peugeot", "Milano", "erasmus", 28),
        new Bike(3, "Specialized", "Stumpjumper", "own", 26),
        new Bike(4, "Diamondback", "Tour", "erasmus", 28)
    );
  }

  public void start() {
    loadEntitiesFromFXML();

    initdb();
    databaseManager.dropBikeTable();
    databaseManager.dropEquipTable();
    databaseManager.createBikeTable();
    databaseManager.createEquipTable();
    databaseManager.saveBikesToDB(createTestData());
    appModel.setBikes(databaseManager.loadBikes());

    initTreeController();
  }


  public void updateView(int id) {
    Bike bike = appModel.getBikes().stream().filter(p -> p.getId() == id)
        .collect(Collectors.toList()).get(0);
    if (bike != null) {
      fxmlController.getBikeID().setText(String.format("%s", id));
      fxmlController.getManufacturer().setText(bike.getManufacturer());
      fxmlController.getModel().setText(bike.getModelName());
    }
  }


  private void setStatusIcon() {
    ImageView statusIcon = fxmlController.getStatusIcon();
    if (connectedToDB) {
      statusIcon.setImage(new Image(GREENICONPATH));
    } else {
      statusIcon.setImage(new Image(REDICONPATH));
    }
  }


  private void initTreeController() {
    treeController = new TreeController(treeView, appModel.getCategories(), this);
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
    connectedToDB = databaseConnector.connect();
    if (connectedToDB) {
      databaseManager = new DatabaseManager(databaseConnector.getCon());
    }
    setStatusIcon();
  }

}
