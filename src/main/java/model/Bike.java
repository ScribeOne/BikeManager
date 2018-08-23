package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bike {

  private int id;
  private SimpleStringProperty manufacturer;
  private SimpleStringProperty modelName;
  private SimpleStringProperty category;
  private SimpleIntegerProperty wheelSize;

  public Bike(Integer id, String manufacturer, String model, String category, int wheelsize) {
    this.manufacturer = new SimpleStringProperty(manufacturer);
    this.modelName = new SimpleStringProperty(model);
    this.category = new SimpleStringProperty(category);
    this.wheelSize = new SimpleIntegerProperty(wheelsize);
    this.id = id;
  }

  public Bike() {
    this.manufacturer = new SimpleStringProperty("Test");
    this.modelName = new SimpleStringProperty("Test");
    this.category = new SimpleStringProperty("Test");
    this.wheelSize = new SimpleIntegerProperty(1);
  }

  public String getManufacturer() {
    return manufacturer.get();
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer.set(manufacturer);
  }

  public String getModelName() {
    return modelName.get();
  }

  public void setModelName(String modelName) {
    this.modelName.set(modelName);
  }

  public String getCategory() {
    return category.get();
  }

  public void setCategory(String category) {
    this.category.set(category);
  }

  public int getWheelSize() {
    return wheelSize.get();
  }

  public int getId() {
    return id;
  }

  public void setWheelSize(int wheelSize) {
    this.wheelSize.set(wheelSize);
  }
}
