package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

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


  public Label getBottomLabel() {
    return bottomLabel;
  }

  public TreeView<String> getTreeView() {
    return treeView;
  }

  public Button getBottomButton() {
    return bottomButton;
  }
}
