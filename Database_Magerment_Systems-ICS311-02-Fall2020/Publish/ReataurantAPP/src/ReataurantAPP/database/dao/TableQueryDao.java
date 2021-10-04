/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * @author Nalongsone Danddank
 *
 */
public class TableQueryDao {
	
	private Connection con;
	/**
	 * @return the row
	 */


	private PreparedStatement ps;
	private ResultSet rs;
	private Statement st;
	private LinkedHashMap row; 
	private List<LinkedHashMap> table; // = new ArrayList<Dictionary>();
	/**
	 * 
	 */
	public TableQueryDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean changeTableByQuery(String query) {
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);	
			int i = ps.executeUpdate();
			ps.close();
			con.close();
			if(i == 1) {

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}	

		return false;
	}
	public int insertGetId(String query) {
		
		try {
			con = ConnectionFactory.getConnection();
			//ps = con.prepareStatement(query);	
			st = con.createStatement();
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();
			int key = -1;
			if(rs.next()) {
				key = rs.getInt(1);
			}
			rs.close();
			con.close();
			return key;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}	
		
		return -1;
	}
	public List<LinkedHashMap> getTableByQuery(String query) {
		
		List<LinkedHashMap>	table1 = new ArrayList<LinkedHashMap>();
		
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			
			
			while(rs.next()) {
				
				LinkedHashMap row1 = new LinkedHashMap();
				
				// The column count starts from 1
				for (int i = 1; i <= columnCount; i++ ) {
				  String name = rsmd.getColumnName(i);
				  Object str = rs.getObject(i);
				  //System.out.println(name + " " + (String)str);
				  row1.put(name, str);
				}

				table1.add(row1);
			}
			rs.close();
			ps.close();
			con.close();
			return table1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}
		return null;
	}
	

	
}
