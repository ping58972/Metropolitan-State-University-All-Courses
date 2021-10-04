package ReataurantAPP.database.dao;
/**
 * @author Nalongsone Danddank
 */
public class ItemUser {

	private int id;
	private int itemId;
	private int userId;
	private String active;
	public ItemUser() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param itemId
	 * @param userId
	 * @param active
	 */
	public ItemUser(int id, int itemId, int userId, String active) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
		return "ItemUser [id=" + id + ", itemId=" + itemId + ", userId=" + userId + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + id;
		result = prime * result + itemId;
		result = prime * result + userId;
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
		ItemUser other = (ItemUser) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (id != other.id)
			return false;
		if (itemId != other.itemId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
