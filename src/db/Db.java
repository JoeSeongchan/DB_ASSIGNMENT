package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

  public final Connection connection;
  // mysql 서버 정보.
  private String server = "localhost";
  private String database = "assignment";
  private String userName = "root";
  private String password = "root";

  public Db() throws SQLException {
    connection = DriverManager.getConnection(
        "jdbc:mysql://" + server + "/" + database,
        userName, password);
  }
}
