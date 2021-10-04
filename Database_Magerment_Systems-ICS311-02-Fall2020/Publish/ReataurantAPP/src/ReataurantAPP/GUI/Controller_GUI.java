/**
 * 
 */
package ReataurantAPP.GUI;


import ReataurantAPP.database.Database;
import ReataurantAPP.database.DatabaseException;

import ReataurantAPP.database.dao.User;
import ReataurantAPP.database.dao.UserDao;

import java.util.*;
import java.text.*;
/**
 * @author Nalongsone Danddank
 *
 */
public class Controller_GUI {
	private UserInterface_GUI cView;
    private Database    cDatabase;
    private int         userType;
    private int         currentUserID;
    private String      currentUserName;
    private String      todaysDate;
    
    private int         todaysOrderCnt;     //Today's order count
    private double      totalSales;         //Today's total sales
    private int         todaysCancelCnt;    //Today's cancel count
    private double      cancelTotal;        //Total cost of today's canceled orders
    
    
    private String      errorMessage;
    
    //define user type
    public final static int USER_ANONYMOUS = 0;
    public final static int USER_EMPLOYEE = 1;
    public final static int USER_MANAGER = 2;
	public Controller_GUI() {
		this.cDatabase = new Database();

        
        cView = new UserInterface_GUI( this);
        
        Date date = new Date();
        SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
        todaysDate = stf.format(date);
        cView.setVisible(true);
        this.userType = USER_ANONYMOUS;
        
        todaysOrderCnt = 0;
        totalSales = 0;
        todaysCancelCnt = 0;
        cancelTotal = 0;
	}
	
	private void  setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
    
    public String  getErrorMessage()
    {
        String result = this.errorMessage;
        this.errorMessage = "";
        return result;
    }
    


}
