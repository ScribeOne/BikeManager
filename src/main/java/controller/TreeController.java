package controller;

import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Bike;

public class TreeController {

  private TreeView<String> treeView;
  private List<String> categories;

  public TreeController(TreeView<String> treeView, List<String> categories) {
    this.treeView = treeView;
    this.categories = categories;
    initListener();
  }


  private void initListener() {
    ObjectProperty<String> selectedBikeProperty = new SimpleObjectProperty<>();

    selectedBikeProperty.bind(Bindings.createObjectBinding(() -> {
      TreeItem<String> selectedBike = (TreeItem<String>) treeView.getSelectionModel()
          .getSelectedItem();
      return selectedBike == null ? null : selectedBike.getValue();
    }, treeView.getSelectionModel().selectedItemProperty()));

    selectedBikeProperty.addListener((observable, oldValue, newValue) -> {
      boolean category = false;
      if (!newValue.equals(oldValue)) {
        for (String cat : categories) {
          if (cat.equals(newValue)) {
            category = true;
          }
        }
        if (!category) {
          System.out.println("Bike was clicked in the Tree");
        }
      }
    });
  }

  public void fillTreeView(List<Bike> bikeList) {
    TreeItem<String> root = new TreeItem<>("Root Node");
    root.setExpanded(true);
    for (Bike bike : bikeList) {
      TreeItem<String> bikeLeaf = new TreeItem<>(bike.getModelName());
      boolean found = false;
      for (TreeItem<String> categoryNode : root.getChildren()) {
        if (categoryNode.getValue().contentEquals(bike.getCategory())) {
          categoryNode.getChildren().add(bikeLeaf);
          found = true;
          break;
        }
      }
      if (!found) {
        TreeItem<String> categoryNode = new TreeItem<>(bike.getCategory());
        categoryNode.getChildren().add(bikeLeaf);
        root.getChildren().add(categoryNode);
      }
    }

    TreeItem<String> otherNode = new TreeItem<>("other");
    root.getChildren().add(otherNode);
    for (int i = 1; i < 10; i++) {
      otherNode.getChildren().add(new TreeItem<>("Random Bike " + i));
    }
    treeView.setRoot(root);
  }
}
