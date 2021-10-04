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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Nalongsone Danddank
 *
 */
public class ItemDao implements Dao<Item> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Item item;
	List<Item> items = new ArrayList<Item>();
	/**
	 * 
	 */
	public ItemDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item selectById(int id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from item where id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				item = new Item(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), 
						rs.getString("type"), rs.getDouble("price"), rs.getDouble("quantity"),
						rs.getInt("unit"), rs.getString("recipe"), rs.getString("instructions"), rs.getString("content")
						);
			}
			rs.close();
			ps.close();
			con.close();
			return item;
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
	public List<Item> selectAll() {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from item;");
			rs = ps.executeQuery();

			while(rs.next()) {
				Item item = new Item(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), 
						rs.getString("type"), rs.getDouble("price"), rs.getDouble("quantity"),
						rs.getInt("unit"), rs.getString("recipe"), rs.getString("instructions"), rs.getString("content")
						);

				items.add(item);
			}
			rs.close();
			ps.close();
			con.close();
			return items;
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
	public List<Item> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Item item = new Item(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), 
						rs.getString("type"), rs.getDouble("price"), rs.getDouble("quantity"),
						rs.getInt("unit"), rs.getString("recipe"), rs.getString("instructions"), rs.getString("content")
						);

				items.add(item);
			}
			rs.close();
			ps.close();
			con.close();
			return items;
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
	public boolean insert(Item item) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into item(userId, title, type, price, quantity, unit, recipe, instructions, content) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, item.getUserId());
			ps.setString(2, item.getTitle());
			ps.setString(3, item.getType());
			ps.setDouble(4, item.getPrice());
			ps.setDouble(5, item.getQuantity());
			ps.setInt(6, item.getUnit());
			ps.setString(7, item.getRecipe());
			ps.setString(8, item.getInstructions());
			ps.setString(9, item.getContent());
			
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

	@Override
	public boolean update(Item item) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("update item set userId=?, title=?, type=?, price=?, quantity=?, unit=?, recipe=?, instructions=?, content=? where id=? ;");
			ps.setInt(1, item.getUserId());
			ps.setString(2, item.getTitle());
			ps.setString(3, item.getType());
			ps.setDouble(4, item.getPrice());
			ps.setDouble(5, item.getQuantity());
			ps.setInt(6, item.getUnit());
			ps.setString(7, item.getRecipe());
			ps.setString(8, item.getInstructions());
			ps.setString(9, item.getContent());
			ps.setInt(10, item.getId());
			
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

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement();
			int i = st.executeUpdate("delete from item where id=" + id);
			st.close();
			con.close();

			if(i == 1) {

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    if (st != null) {
		        try {
		            st.close();
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

}
