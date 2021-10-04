/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.database;

/**
 * @author Nalongsone Danddank
 *
 */
public class DatabaseException extends Exception {

    public String errMsg;
    public DatabaseException(String msg)
    { 
        errMsg = msg;
    }
    
    public String getErrMessage()
    {
        return errMsg;
    }
}
