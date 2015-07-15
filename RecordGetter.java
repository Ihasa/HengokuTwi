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

	//総合戦績から使用率のもっとも高いキャラクターを取得
	/*private Character getMainCharacter() throws Exception{
		String sql = "select p1id, max(C) from (select p1id, count(timestamp) as C from trackrecord145 group by p1id)";
		ResultSet rs = executeQuery(sql);
		Character chara = Character.UNSPECIFIED;
		while(rs.next()){
			int id = rs.getInt(1);
			chara = Character.fromId(id);
		}
		return chara;
	}*/

	//設定したフィルタリング条件で戦績を取得
	public RecordInfo getRecordInfo(){
		return getRecordInfo(new RecordFilter());
	}
	public RecordInfo getRecordInfo(int count){
		return getRecordInfo(new RecordFilter(count));
	}
	public RecordInfo getRecordInfo(Character p1Chara, Character p2Chara){
		return getRecordInfo(new RecordFilter(p1Chara,p2Chara));
	}
	public RecordInfo getRecordInfo(int count,Character p1Chara, Character p2Chara){
		return getRecordInfo(new RecordFilter(count,p1Chara,p2Chara));
	}
	public RecordInfo getRecordInfo(RecordFilter filter){
		try{
			String source = "(select * from trackrecord145 " + filter.getQuery() + ")";

			String select = "select count(timestamp) from " + source;
			String allSql = select;
			String winsSql = select + " where p1win > p2win ";
			
			int all = executeQuery(allSql).getInt(1);
			int wins = executeQuery(winsSql).getInt(1);
			//暫定
			if(all < filter.count)
				filter.count = -1;
			return new RecordInfo(all, wins, filter);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	private ResultSet executeQuery(String sql) throws Exception{
		return statement.executeQuery(sql);
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
