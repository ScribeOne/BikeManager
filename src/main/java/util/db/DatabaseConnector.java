package util.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {

  private Connection con = null;


  public boolean connect() {

    boolean connected = false;

    try {
      Class.forName("org.hsqldb.jdbc.JDBCDriver");
      con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bikedb", "SA", "");

      if (con != null) {
        System.out.println("Connection successfully established!");
        connected = true;
      } else {
        System.out.println("Could not connect to database.");
        connected = false;
      }

    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
    return connected;
  }

  public Connection getCon() {
    return con;
  }
}