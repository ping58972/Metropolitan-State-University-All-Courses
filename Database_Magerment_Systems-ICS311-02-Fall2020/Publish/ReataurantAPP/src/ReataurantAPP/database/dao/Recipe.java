/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

/**
 * @author Nalongsone Danddank
 *
 */
public class Recipe {

	private int id;
	private int itemId;
	private int ingredientId;
	private double quantity;
	private int unit;
	private String instructions;
	/**
	 * 
	 */
	public Recipe() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param itemId
	 * @param ingredientId
	 * @param quantity
	 * @param unit
	 * @param instructions
	 */
	public Recipe(int id, int itemId, int ingredientId, double quantity, int unit, String instructions) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.ingredientId = ingredientId;
		this.quantity = quantity;
		this.unit = unit;
		this.instructions = instructions;
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
	 * @return the ingredientId
	 */
	public int getIngredientId() {
		return ingredientId;
	}
	/**
	 * @param ingredientId the ingredientId to set
	 */
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}
	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}
	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", itemId=" + itemId + ", ingredientId=" + ingredientId + ", quantity=" + quantity
				+ ", unit=" + unit + ", instructions=" + instructions + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ingredientId;
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + itemId;
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + unit;
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
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		if (ingredientId != other.ingredientId)
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (itemId != other.itemId)
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (unit != other.unit)
			return false;
		return true;
	}

}
