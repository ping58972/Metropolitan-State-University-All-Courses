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
public class MenuDao implements Dao<Menu> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Menu menu;
	List<Menu> menus = new ArrayList<Menu>();
	/**
	 * 
	 */
	public MenuDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Menu menu = new Menu(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), 
						rs.getString("type"),  rs.getString("content")
						);

				menus.add(menu);
			}
			rs.close();
			ps.close();
			con.close();
			return menus;
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
	public boolean insert(Menu t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Menu t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
