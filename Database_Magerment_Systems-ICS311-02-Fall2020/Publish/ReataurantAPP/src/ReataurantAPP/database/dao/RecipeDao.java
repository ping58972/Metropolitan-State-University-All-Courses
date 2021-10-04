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
public class RecipeDao implements Dao<Recipe> {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	Recipe recipe;
	List<Recipe> recipes = new ArrayList<Recipe>();
	/**
	 * 
	 */
	public RecipeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Recipe selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> selectByQuery(String query) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				Recipe recipe = new Recipe(
						rs.getInt("id"), rs.getInt("itemId"), rs.getInt("ingredientId"),
						rs.getDouble("quantity"), rs.getInt("unit"),  rs.getString("instructions")
						);

				recipes.add(recipe);
			}
			rs.close();
			ps.close();
			con.close();
			return recipes;
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
	public boolean insert(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
