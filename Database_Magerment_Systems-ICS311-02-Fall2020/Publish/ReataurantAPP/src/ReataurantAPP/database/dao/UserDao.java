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
public class UserDao implements Dao<User> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	User user;
	List<User> users = new ArrayList<User>();
	/**
	 * 
	 */
	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User selectById(int id) {
		// TODO Auto-generated method stub
		
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from user where id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("fName"), rs.getString("lName"),rs.getString("phone"), rs.getString("email"),
						rs.getString("pwd"), rs.getString("user_role"), rs.getString("line"),
						rs.getString("city"),rs.getString("province"),rs.getString("country"),rs.getDate("registeredAt"), rs.getString("intro"),
						rs.getDate("bdate"), rs.getString("gender"),rs.getDouble("salary"));
			}
			rs.close();
			ps.close();
			con.close();
			return user;
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
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from user;");
			rs = ps.executeQuery();

			while(rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("fName"), rs.getString("lName"),rs.getString("phone"), rs.getString("email"),
						rs.getString("pwd"), rs.getString("user_role"), rs.getString("line"),
						rs.getString("city"),rs.getString("province"),rs.getString("country"),rs.getDate("registeredAt"), rs.getString("intro"),
						rs.getDate("bdate"), rs.getString("gender"),rs.getDouble("salary"));

				users.add(user);
			}
			rs.close();
			ps.close();
			con.close();
			return users;
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

	public User selectByEmail(String email) {
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("select * from user where email = ?;");
			ps.setString(1, email);
			rs = ps.executeQuery();

			if(rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("fName"), rs.getString("lName"),rs.getString("phone"), rs.getString("email"),
						rs.getString("pwd"), rs.getString("user_role"), rs.getString("line"),
						rs.getString("city"),rs.getString("province"),rs.getString("country"),rs.getDate("registeredAt"), rs.getString("intro"),
						rs.getDate("bdate"), rs.getString("gender"),rs.getDouble("salary"));
			}
			rs.close();
			ps.close();
			con.close();
			return user;
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
 	public List<User> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("fName"), rs.getString("lName"),rs.getString("phone"), rs.getString("email"),
						rs.getString("pwd"), rs.getString("user_role"), rs.getString("line"),
						rs.getString("city"),rs.getString("province"),rs.getString("country"),rs.getDate("registeredAt"), rs.getString("intro"),
						rs.getDate("bdate"), rs.getString("gender"),rs.getDouble("salary"));

				users.add(user);
			}
			rs.close();
			ps.close();
			con.close();
			return users;
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
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("insert into user(fName, lName, "
					+ "phone, email, pwd, user_role, line, city, province, "
					+ "country, registeredAt, intro, bdate, gender, salary) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"); //15/16
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getPhone());
			ps.setString(4,  user.getEmail());
			ps.setString(5, user.getPwd());
			ps.setString(6, user.getUser_role());
			ps.setString(7, user.getLine());
			ps.setString(8, user.getCity());
			ps.setString(9, user.getProvince());
			ps.setString(10, user.getCountry());
			ps.setObject(11,  new Date());
			ps.setString(12, user.getIntro());
			ps.setObject(13, user.getBdate());
			ps.setString(14, user.getGender());
			ps.setDouble(15, user.getSalary());
			
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
	public boolean update(User user) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("update user set fName=?, lName =?, "
					+ "phone=?, email=?, pwd=?, user_role=?, line=?, city=?, province=?, "
					+ "country=?, registeredAt=?, intro=?, bdate=?, gender=?, salary=? where id=? ;");
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getPhone());
			ps.setString(4,  user.getEmail());
			ps.setString(5, user.getPwd());
			ps.setString(6, user.getUser_role());
			ps.setString(7, user.getLine());
			ps.setString(8, user.getCity());
			ps.setString(9, user.getProvince());
			ps.setString(10, user.getCountry());
			ps.setObject(11,  user.getRegisteredAt());
			ps.setString(12, user.getIntro());
			ps.setObject(13, user.getBdate());
			ps.setString(14, user.getGender());
			ps.setDouble(15, user.getSalary());
			ps.setInt(16, user.getId());
			
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
			int i = st.executeUpdate("delete from user where id=" + id);
			

			if(i == 1) {
				st.close();
				con.close();
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
