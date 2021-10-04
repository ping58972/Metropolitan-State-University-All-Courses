/**
 * 
 */
package ReataurantAPP.database;
/**
 * @author Nalongsone Danddank
 *
 */

import java.util.*;
import java.io.*;
import java.lang.*;


import ReataurantAPP.database.dao.Item;
import ReataurantAPP.database.dao.ItemDao;
import ReataurantAPP.database.dao.Transaction;
//import ReataurantAPP.database.dao.Order;
import ReataurantAPP.database.dao.User;
import ReataurantAPP.database.dao.UserDao;

public class Database {
    
    private Date    date;
    int     todaysOrderCounts;
    private User user;
    private Item item;
    private Transaction transaction;
    private List<Item> items = new ArrayList<Item>();
    private List<Transaction> transactions = new ArrayList<Transaction>();
    private List<User> users = new ArrayList<User>();
    


	public Database()
	    {

	        date = new Date();
	        todaysOrderCounts = 0;  //Load order file??
	    }
	 
	 
	public boolean loginCheck( String email, String inputPassword)
	    {
	    	UserDao userDao = new UserDao();
	    	user = userDao.selectByEmail(email);
	    	//System.out.println(user);
	    	if (user == null) {
	    		return false;
	    	}
	    	if (user.getPwd().equals(inputPassword)) {
	    		return true;
	    	}
	    	return false;
	    }
	
	public boolean getUserById(String id) {
		UserDao userDao = new UserDao();
		User user = userDao.selectById(Integer.parseInt(id));
		if (user == null) {
			return false;
		}
		this.user = user;
		return true;
			
		
	}
	
	public User getUserById(int id) {
		UserDao userDao = new UserDao();
		return userDao.selectById(id);

		
		
	}
	
	public boolean getAllUser(){
		UserDao userDao = new UserDao();
		this.users = userDao.selectAll();
		if (this.user == null) {
			return false;
		}
		return true;
	}
	
	public List<Item> getAllItemList(){
		if (this.user == null) {
			return null;
		}
		ItemDao itemDao = new ItemDao();
		return itemDao.selectAll();
	}
	public List<Item> getItemByType(String type){
		if (this.user == null) {
			return null;
		}
		ItemDao itemDao = new ItemDao();
		return itemDao.selectByQuery("select * from item where type =" + type);
	}
	public Item getItemById(String id) {
		ItemDao itemDao = new ItemDao();
		return itemDao.selectById(Integer.parseInt(id));
	}

	 /**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}



	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}



	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}



	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}



	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}



	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}



	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	 /**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}


	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
