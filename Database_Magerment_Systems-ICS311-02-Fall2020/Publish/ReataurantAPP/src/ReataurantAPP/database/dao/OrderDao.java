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
public class OrderDao implements Dao<Order> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Order order;
	List<Order> orders = new ArrayList<Order>();
	/**
	 * 
	 */
	public OrderDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order selectById(int id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from `order` where id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				order = new Order(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("status"), 
						rs.getDouble("subTotal"), rs.getDouble("itemDiscount"), rs.getDouble("tax"),
						rs.getDouble("shippingFee"), rs.getDouble("total"), rs.getDouble("discount"), 
						rs.getDouble("grandTotal"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));
			}
			rs.close();
			ps.close();
			con.close();
			return order;
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
	public List<Order> selectAll() {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from `order`;");
			rs = ps.executeQuery();

			while(rs.next()) {
				Order order = new Order(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("status"), 
						rs.getDouble("subTotal"), rs.getDouble("itemDiscount"), rs.getDouble("tax"),
						rs.getDouble("shippingFee"), rs.getDouble("total"), rs.getDouble("discount"), 
						rs.getDouble("grandTotal"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				orders.add(order);
			}
			rs.close();
			ps.close();
			con.close();
			return orders;
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
	public List<Order> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Order order = new Order(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("status"), 
						rs.getDouble("subTotal"), rs.getDouble("itemDiscount"), rs.getDouble("tax"),
						rs.getDouble("shippingFee"), rs.getDouble("total"), rs.getDouble("discount"), 
						rs.getDouble("grandTotal"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				orders.add(order);
			}
			rs.close();
			ps.close();
			con.close();
			return orders;
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
	public boolean insert(Order order) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into `order`(userId, status, "
					+ "subTotal, itemDiscount, tax, shippingFee, total, "
					+ "discount, grandTotal, createdAt, updatedAt, content) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);");
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getStatus());
			ps.setDouble(3, order.getSubTotal());
			ps.setDouble(4, order.getItemDiscount());
			ps.setDouble(5, order.getTax());
			ps.setDouble(6, order.getShippingFee());
			ps.setDouble(7, order.getTotal());
			ps.setDouble(8, order.getDiscount());
			ps.setDouble(9, order.getGrandTotal());
			ps.setObject(10, order.getCreatedAt());
			ps.setObject(11, order.getUpdatedAt());
			ps.setString(12, order.getContent());
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
	public boolean update(Order order) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("update `order` set userId=?, "
					+ "status=?, subTotal=?, itemDiscount=?, tax=?, shippingFee=?,"
					+ " total=?, discount=?, grandTotal=?, createdAt=?, updatedAt=?, "
					+ "content=? where id=? ;");
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getStatus());
			ps.setDouble(3, order.getSubTotal());
			ps.setDouble(4, order.getItemDiscount());
			ps.setDouble(5, order.getTax());
			ps.setDouble(6, order.getShippingFee());
			ps.setDouble(7, order.getTotal());
			ps.setDouble(8, order.getDiscount());
			ps.setDouble(9, order.getGrandTotal());
			ps.setObject(10, order.getCreatedAt());
			ps.setObject(11, order.getUpdatedAt());
			ps.setString(12, order.getContent());
			ps.setInt(13, order.getId());
			
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
			int i = st.executeUpdate("delete from `order` where id=" + id);
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
