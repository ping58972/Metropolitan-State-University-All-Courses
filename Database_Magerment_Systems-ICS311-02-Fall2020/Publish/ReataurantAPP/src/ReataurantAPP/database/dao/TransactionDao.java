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
public class TransactionDao implements Dao<Transaction> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Transaction transaction;
	List<Transaction> transactions = new ArrayList<Transaction>();
	/**
	 * 
	 */
	public TransactionDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Transaction selectById(int id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from transaction where id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				transaction = new Transaction(
						rs.getInt("id"), rs.getInt("userId"), rs.getInt("orderId"),
						rs.getString("code"), rs.getString("type"), rs.getString("mode"),rs.getString("status"),
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));
			}
			rs.close();
			ps.close();
			con.close();
			return transaction;
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
	public List<Transaction> selectAll() {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from transaction;");
			rs = ps.executeQuery();

			while(rs.next()) {
				Transaction transaction = new Transaction(
						rs.getInt("id"), rs.getInt("userId"), rs.getInt("orderId"),
						rs.getString("code"), rs.getString("type"), rs.getString("mode"),rs.getString("status"),
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				transactions.add(transaction);
			}
			rs.close();
			ps.close();
			con.close();
			return transactions;
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
	public List<Transaction> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Transaction transaction = new Transaction(
						rs.getInt("id"), rs.getInt("userId"), rs.getInt("orderId"),
						rs.getString("code"), rs.getString("type"), rs.getString("mode"),rs.getString("status"),
						rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("content"));

				transactions.add(transaction);
			}
			rs.close();
			ps.close();
			con.close();
			return transactions;
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
	public boolean insert(Transaction transaction) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into transaction(userId, orderId, code, type, mode, status, createdAt, updatedAt, content) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, transaction.getUserId());
			ps.setInt(2, transaction.getOrderId());
			ps.setString(3, transaction.getCode());
			ps.setString(4, transaction.getType());
			ps.setString(5, transaction.getMode());
			ps.setString(6, transaction.getStatus());
			ps.setObject(7, transaction.getCreatedAt());
			ps.setObject(8, transaction.getUpdatedAt());
			ps.setString(9, transaction.getContent());
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

	public int insertAndGetId(Transaction transaction) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into transaction(userId, orderId, code, type, mode, status, createdAt, updatedAt, content) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, transaction.getUserId());
			ps.setInt(2, transaction.getOrderId());
			ps.setString(3, transaction.getCode());
			ps.setString(4, transaction.getType());
			ps.setString(5, transaction.getMode());
			ps.setString(6, transaction.getStatus());
			ps.setObject(7, transaction.getCreatedAt());
			ps.setObject(8, transaction.getUpdatedAt());
			ps.setString(9, transaction.getContent());
			int i = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int key = -1;
			if(rs.next()) {
				key = rs.getInt(1);
			}
			rs.close();
			ps.close();
			con.close();
			if(i == 1) {
				
				return key;
			}
			
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
		return -1;
	}

	@Override
	public boolean update(Transaction transaction) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("update transaction set userId=?, orderId=?, code=?, type=?, mode=?, status=?, createdAt=?, updatedAt=?, content=? where id=? ;");
			ps.setInt(1, transaction.getUserId());
			ps.setInt(2, transaction.getOrderId());
			ps.setString(3, transaction.getCode());
			ps.setString(4, transaction.getType());
			ps.setString(5, transaction.getMode());
			ps.setString(6, transaction.getStatus());
			ps.setObject(7, transaction.getCreatedAt());
			ps.setObject(8, transaction.getUpdatedAt());
			ps.setString(9, transaction.getContent());
			ps.setInt(10, transaction.getId());
			
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
			int i = st.executeUpdate("delete from transaction where id=" + id);
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
