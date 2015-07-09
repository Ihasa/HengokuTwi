import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class HengokuTwi {
    public static void main(String[] args) throws ClassNotFoundException {

        String sql = "select * from trackrecord145";

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:sqlite:Default.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i));
                    System.out.print(i < rsmd.getColumnCount() ? "," : "");
                }
                System.out.print(System.getProperty("line.separator"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
