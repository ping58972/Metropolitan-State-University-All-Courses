/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database.dao;

import java.util.Date;

/**
 * @author Nalongsone Danddank
 *
 */
public class Order {

	private int id;
	private int userId;
	private String status;
	private double subTotal;
	private double itemDiscount;
	private double tax;
	private double shippingFee;
	private double total;
	private double discount;
	private double grandTotal;
	private Date createdAt;
	private Date updatedAt;
	private String content;
	/**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param userId
	 * @param status
	 * @param subTotal
	 * @param itemDiscount
	 * @param tax
	 * @param shippingFee
	 * @param total
	 * @param discount
	 * @param grandTotal
	 * @param createdAt
	 * @param updatedAt
	 * @param content
	 */
	public Order(int id, int userId, String status, double subTotal, double itemDiscount, double tax,
			double shippingFee, double total, double discount, double grandTotal, Date createdAt, Date updatedAt,
			String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.subTotal = subTotal;
		this.itemDiscount = itemDiscount;
		this.tax = tax;
		this.shippingFee = shippingFee;
		this.total = total;
		this.discount = discount;
		this.grandTotal = grandTotal;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.content = content;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * @return the itemDiscount
	 */
	public double getItemDiscount() {
		return itemDiscount;
	}
	/**
	 * @param itemDiscount the itemDiscount to set
	 */
	public void setItemDiscount(double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	/**
	 * @return the shippingFee
	 */
	public double getShippingFee() {
		return shippingFee;
	}
	/**
	 * @param shippingFee the shippingFee to set
	 */
	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}
	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "OrderOld [id=" + id + ", userId=" + userId + ", status=" + status + ", subTotal=" + subTotal
				+ ", itemDiscount=" + itemDiscount + ", tax=" + tax + ", shippingFee=" + shippingFee + ", total="
				+ total + ", discount=" + discount + ", grandTotal=" + grandTotal + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", content=" + content + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(grandTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(itemDiscount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(shippingFee);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		temp = Double.doubleToLongBits(subTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Order other = (Order) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (Double.doubleToLongBits(grandTotal) != Double.doubleToLongBits(other.grandTotal))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(itemDiscount) != Double.doubleToLongBits(other.itemDiscount))
			return false;
		if (Double.doubleToLongBits(shippingFee) != Double.doubleToLongBits(other.shippingFee))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(subTotal) != Double.doubleToLongBits(other.subTotal))
			return false;
		if (Double.doubleToLongBits(tax) != Double.doubleToLongBits(other.tax))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
