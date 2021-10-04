/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

import com.mysql.cj.jdbc.Driver;

import ReataurantAPP.GUI.UserInterface_GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Nalongsone Danddank
 *
 */
public class ConnectionFactory {

	public static final String URL = "jdbc:mysql://localhost/";
	public static final String dbName = "restaurant_management";
	public static final String user = "root";
	public static final String pwd = "root";
	/**
	 * Get a connection to databasse
	 * @return Connection object
	 *
	 */
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL + dbName, user, pwd);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database ", ex);
		}
		
	}


}
