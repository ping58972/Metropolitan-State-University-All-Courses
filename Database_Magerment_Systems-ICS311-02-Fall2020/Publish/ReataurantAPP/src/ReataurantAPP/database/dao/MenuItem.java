/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

/**
 * @author Nalongsone Danddank
 *
 */
public class MenuItem {

	private int id;
	private int menuId;
	private int itemId;
	private String active;
	/**
	 * 
	 */
	public MenuItem() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param menuId
	 * @param itemId
	 * @param active
	 */
	public MenuItem(int id, int menuId, int itemId, String active) {
		super();
		this.id = id;
		this.menuId = menuId;
		this.itemId = itemId;
		this.active = active;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the menuId
	 */
	public int getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", menuId=" + menuId + ", itemId=" + itemId + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + id;
		result = prime * result + itemId;
		result = prime * result + menuId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (id != other.id)
			return false;
		if (itemId != other.itemId)
			return false;
		if (menuId != other.menuId)
			return false;
		return true;
	}

}
