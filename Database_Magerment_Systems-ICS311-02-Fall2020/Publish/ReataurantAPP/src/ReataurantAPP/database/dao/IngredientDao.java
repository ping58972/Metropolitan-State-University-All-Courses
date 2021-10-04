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
import java.util.List;


/**
 * @author Nalongsone Danddank
 *
 */
public class IngredientDao implements Dao<Ingredient> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Ingredient ing;
	List<Ingredient> ings = new ArrayList<Ingredient>();
	/**
	 * 
	 */
	public IngredientDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ingredient selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Ingredient ing = new Ingredient(
						rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), 
						rs.getString("type"), rs.getDouble("quantity"),
						rs.getInt("unit"),  rs.getString("content")
						);

				ings.add(ing);
			}
			rs.close();
			ps.close();
			con.close();
			return ings;
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
	public boolean insert(Ingredient ing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ingredient ing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
