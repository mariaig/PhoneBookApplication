package PBA.db;

//import java.sql.DriverManager;
//import java.util.*;
import java.sql.*;


import PBA.utils.ConnectionUtils;

/**
 * @author Maria
 * 	Aici sunt metodele pt conectarea la baza de date locala 
 *
 */
public class JDBCUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		
		String db_driver=ConnectionUtils.getMessage("config", "phone.book.app.db.driver");
		String db_host=ConnectionUtils.getMessage("config", "phone.book.app.db.host");
		String db_port=ConnectionUtils.getMessage("config", "phone.book.app.db.port");
		String db_name=ConnectionUtils.getMessage("config", "phone.book.app.db.name");
		String db_user=ConnectionUtils.getMessage("config", "phone.book.app.db.user");
		String db_pass=ConnectionUtils.getMessage("config", "phone.book.app.db.pass");
		
		String url="jdbc:mysql://"+db_host+":"+db_port+"/"+db_name;
		
		Class.forName(db_driver);
		Connection con=DriverManager.getConnection(url,db_user,db_pass);
		
		return con;
	}
	
	public static void close(Connection con) throws SQLException
	{
			con.close();
	}
	
	public static void closePreparedStatement(PreparedStatement ps) throws SQLException
	{
			ps.close();
	}
	
	public static void closeResult(ResultSet rs) throws SQLException
	{
			rs.close();
	}
}

