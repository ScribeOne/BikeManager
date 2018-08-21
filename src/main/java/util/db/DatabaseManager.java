package util.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

  private Connection sqlConnection;

  public DatabaseManager(Connection connection) {
    sqlConnection = connection;
  }


  public void testConnection() {
    try {
      Statement statement = sqlConnection.createStatement();
      int result = 0;

      result = statement.executeUpdate("CREATE TABLE bikes (\n"
          + "id INT NOT NULL, company VARCHAR(50) NOT NULL,\n"
          + "model VARCHAR(50) NOT NULL, wheelsize INT NOT NULL,\n"
          + ""
          + "PRIMARY KEY (id));");

    } catch (SQLException e) {
      System.err.println("SQL Error");
    }
    System.out.println("Success");
  }
}
