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
public class OrderItemDao implements Dao<OrderItem> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	OrderItem orderItem;
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
	/**
	 * 
	 */
	public OrderItemDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public OrderItem selectById(int id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from order_item where id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				orderItem = new OrderItem(
						rs.getInt("id"), rs.getInt("orderId"), rs.getInt("itemId"),
						rs.getDouble("discount"), rs.getDouble("quantity"),rs.getInt("unit"), 
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));
			}
			rs.close();
			ps.close();
			con.close();
			return orderItem;
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
	public List<OrderItem> selectAll() {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from order_item;");
			rs = ps.executeQuery();

			while(rs.next()) {
				OrderItem orderItem = new OrderItem(
						rs.getInt("id"), rs.getInt("orderId"), rs.getInt("itemId"),
						rs.getDouble("discount"), rs.getDouble("quantity"),rs.getInt("unit"), 
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				orderItems.add(orderItem);
			}
			rs.close();
			ps.close();
			con.close();
			return orderItems;
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
	public List<OrderItem> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				OrderItem orderItem = new OrderItem(
						rs.getInt("id"), rs.getInt("orderId"), rs.getInt("itemId"),
						rs.getDouble("discount"), rs.getDouble("quantity"),rs.getInt("unit"), 
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				orderItems.add(orderItem);
			}
			rs.close();
			ps.close();
			con.close();
			return orderItems;
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
	public boolean insert(OrderItem orderItem) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into order_item(orderId, itemId, discount, quantity, unit, createdAt, updatedAt, content) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, orderItem.getOrderId());
			ps.setInt(2, orderItem.getItemId());
			ps.setDouble(3, orderItem.getDiscount());
			ps.setDouble(4, orderItem.getQuantity());
			ps.setInt(5, orderItem.getUnit());
			ps.setObject(6, orderItem.getCreatedAt());
			ps.setObject(7, orderItem.getUpdatedAt());
			ps.setString(8, orderItem.getContent());
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
	public boolean update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("update order_item set orderId=?, itemId=?, discount=?, quantity=?, unit=?, createdAt=?, updatedAt=?, content=? where id=? ;");
			ps.setInt(1, orderItem.getOrderId());
			ps.setInt(2, orderItem.getItemId());
			ps.setDouble(3, orderItem.getDiscount());
			ps.setDouble(4, orderItem.getQuantity());
			ps.setInt(5, orderItem.getUnit());
			ps.setObject(6, orderItem.getCreatedAt());
			ps.setObject(7, orderItem.getUpdatedAt());
			ps.setString(8, orderItem.getContent());
			ps.setInt(9, orderItem.getId());
			
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
			int i = st.executeUpdate("delete from order_item where id=" + id);
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
