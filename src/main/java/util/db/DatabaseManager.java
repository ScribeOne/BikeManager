package util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.Bike;

public class DatabaseManager {

  private Connection sqlConnection;
  private Statement statement;
  private int result = 0;

  public DatabaseManager(Connection connection) {
    sqlConnection = connection;
  }

  private void reset() {
    statement = null;
    result = 0;
  }


  public void saveBikesToDB(List<Bike> bikes) {

    String querySql = "INSERT INTO bikes"
        + "(id, company, model, wheelsize, category) VALUES"
        + "(?,?,?,?,?)";

    try (PreparedStatement statement = sqlConnection.prepareStatement(querySql)) {

      for (Bike bike : bikes) {
        statement.setInt(1, bike.getId());
        statement.setString(2, bike.getManufacturer());
        statement.setString(3, bike.getModelName());
        statement.setInt(4, bike.getWheelSize());
        statement.setString(5, bike.getCategory());

        statement.addBatch();
      }

      statement.executeBatch();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public List<Bike> loadBikes() {
    ResultSet resultSet = null;
    List<Bike> bikes = new LinkedList<>();
    String selectAllBikes = "SELECT * FROM bikes";

    try {
      PreparedStatement ps = sqlConnection.prepareStatement(selectAllBikes);

      resultSet = ps.executeQuery();

      while (resultSet.next()) {
        bikes.add(
            new Bike(
                resultSet.getInt("ID"),
                resultSet.getString("company"),
                resultSet.getString("model"),
                resultSet.getString("category"),
                resultSet.getInt("wheelsize")
            )
        );
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bikes;
  }

  public void addBike() {
    reset();
    try {
      statement = sqlConnection.createStatement();
      result = statement
          .executeUpdate("INSERT INTO bikes VALUES (0,'Specialized','Stumpjumper',26)");
      sqlConnection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public void dropBikeTable() {
    try {
      PreparedStatement dropBikeTable = sqlConnection.prepareStatement(
          String.format("DROP TABLE IF EXISTS %s", "bikes")
      );
      dropBikeTable.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void dropEquipTable() {
    try {
      PreparedStatement preparedStatement = sqlConnection.prepareStatement(
          String.format("DROP TABLE IF EXISTS %s", "equipment")
      );
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public void createEquipTable() {
    PreparedStatement ps = null;
    String createEquip = "CREATE TABLE equipment("
        + "id INTEGER NOT NULL,"
        + "drivetrain VARCHAR(100) NOT NULL,"
        + "carrier BOOLEAN"
        + ")";

    try {

      ps = sqlConnection.prepareStatement(createEquip);
      ps.execute();

    } catch (SQLException e) {

      System.out.println(e.getMessage());

    }


  }


  public void createBikeTable() {
    PreparedStatement ps = null;
    String createBikeTable = "CREATE TABLE bikes("
        + "id INTEGER NOT NULL,"
        + "company VARCHAR(50) NOT NULL,"
        + "model VARCHAR(50) NOT NULL,"
        + "wheelsize INTEGER NOT NULL,"
        + "category VARCHAR(50) NOT NULL"
        + ")";

    try {

      ps = sqlConnection.prepareStatement(createBikeTable);
      ps.execute();

    } catch (SQLException e) {

      System.out.println(e.getMessage());

    }

  }
}
