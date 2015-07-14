import java.io.File;
import java.io.Reader;
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
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class RecordGetter {
	Connection connection;
	Statement statement;
	
	private RecordGetter(String dbName) throws Exception{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		statement = connection.createStatement();
		statement.setQueryTimeout(30);
	}
	
	public static RecordGetter createInstance(String dbName){
		try{
			return new RecordGetter(dbName);
		}catch(Exception e){
			return null;
		}
	}

	public RecordInfo getRecordInfo(){
		return getRecordInfo(new RecordFilter());
	}
	public RecordInfo getRecordInfo(RecordFilter filter){
		try{
			String source = "(select * from trackrecord145 " + filter.toString() + ")";
			
			String select = "select count(timestamp) from " + source;
			String allSql = select;
			String winsSql = select + " where p1win > p2win ";
			
			int all = executeQuery(allSql).getInt(1);
			int wins = executeQuery(winsSql).getInt(1);
			
			return new RecordInfo(all, wins);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	private ResultSet executeQuery(String sql) throws Exception{
		return statement.executeQuery(sql);
	}

	public void printAll() throws Exception{
		System.out.println("Result:");
		String sql = "select * from trackrecord145";
		//クエリ実行
		ResultSet rs = statement.executeQuery(sql);
			
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i));
				System.out.print(i < rsmd.getColumnCount() ? "," : "");
			}
			System.out.print(System.getProperty("line.separator"));
		}			
	}
	
	public void finalize(){
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
