/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nalongsone Danddank
 *
 */
public class MenuItemDao implements Dao<MenuItem> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	MenuItem menuItem;
	List<MenuItem> menuItems = new ArrayList<MenuItem>();
	/**
	 * 
	 */
	public MenuItemDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MenuItem selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				MenuItem menuItem = new MenuItem(
						rs.getInt("id"), rs.getInt("menuId"), rs.getInt("itemId"), rs.getString("active"));

				menuItems.add(menuItem);
			}
			rs.close();
			ps.close();
			con.close();
			return menuItems;
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

	@Override
	public boolean insert(MenuItem t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MenuItem t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
