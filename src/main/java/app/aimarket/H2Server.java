package app.aimarket;

import java.sql.*;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 07/02/2023, Tuesday
 **/
public class H2Server{
  public static void main(String[] args) throws SQLException {
    Connection conn = DriverManager.getConnection("jdbc:h2:mem:tempdb");
    Statement stmt = conn.createStatement();
    stmt.executeUpdate("CREATE TABLE test(id int, name varchar(255));");
        stmt.executeUpdate("INSERT INTO test(id, name) VALUES(1, 'alice');");
    stmt.executeUpdate("INSERT INTO test(id, name) VALUES(2, 'bob');");
    ResultSet rs = stmt.executeQuery("SELECT * FROM test");
    while (rs.next()) {
      System.out.println(rs.getInt(1) + ": " + rs.getString(2));
    }
    stmt.close();
    conn.close();
  }
}
