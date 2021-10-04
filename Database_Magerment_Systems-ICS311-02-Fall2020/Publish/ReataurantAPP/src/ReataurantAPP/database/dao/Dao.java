/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nalongsone Danddank
 *
 */
public interface Dao<T> {
	
	T selectById(int id);
	List<T> selectAll();
	List<T> selectByQuery(String query);
	boolean insert (T t);
	boolean update(T t);
	boolean deleteById(int id);
	
}
