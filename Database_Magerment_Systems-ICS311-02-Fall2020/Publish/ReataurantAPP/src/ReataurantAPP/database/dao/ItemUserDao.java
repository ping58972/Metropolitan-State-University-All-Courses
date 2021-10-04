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
public class ItemUserDao implements Dao<ItemUser> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	ItemUser itemUser;
	List<ItemUser> itemUsers = new ArrayList<ItemUser>();
	/**
	 * 
	 */
	public ItemUserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemUser selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemUser> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemUser> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				ItemUser itemUser = new ItemUser(rs.getInt("id"),rs.getInt("itemId"),
						rs.getInt("userId"), rs.getString("active"));

				itemUsers.add(itemUser);
			}
			rs.close();
			ps.close();
			con.close();
			return itemUsers;
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
	public boolean insert(ItemUser itemUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ItemUser itemUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
