package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FXMLController implements Initializable {

  @FXML
  private Label bottomLabel;

  @FXML
  private Button bottomButton;

  @FXML
  private ImageView centerImage;

  @FXML
  private MenuBar textMenu;

  @FXML
  private MenuBar iconMenu;

  @FXML
  private TreeView<String> treeView;

  @FXML
  private Button loadButton;

  @FXML
  private ImageView statusIcon;

  @FXML
  private HBox utilityBar;

  @FXML
  private GridPane detailGrid;

  @FXML
  private TextField bikeID;

  @FXML
  private TextField manufacturer;

  @FXML
  private TextField model;

  @FXML
  private TextField condition;

  @FXML
  private TextField blabla;





  /**
   * Called to initialize a controller after its root element has been completely processed.
   *
   * @param location The location used to resolve relative paths for the root object, or
   * <tt>null</tt> if the location is not known.
   * @param resources The resources used to localize the root object, or <tt>null</tt> if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public GridPane getDetailGrid() {
    return detailGrid;
  }

  public HBox getUtilityBar() {
    return utilityBar;
  }

  public ImageView getCenterImage() {
    return centerImage;
  }

  public Button getLoadButton() {
    return loadButton;
  }

  public ImageView getStatusIcon() {
    return statusIcon;
  }

  public Label getBottomLabel() {
    return bottomLabel;
  }

  public TreeView<String> getTreeView() {
    return treeView;
  }

  public Button getBottomButton() {
    return bottomButton;
  }

  public TextField getManufacturer() {
    return manufacturer;
  }

  public TextField getBikeID() {
    return bikeID;
  }

  public TextField getModel() {
    return model;
  }

  public TextField getCondition() {
    return condition;
  }
}
