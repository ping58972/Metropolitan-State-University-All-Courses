/**
 * 
 */
package ReataurantAPP;

import java.awt.EventQueue;

import ReataurantAPP.GUI.Controller_GUI;

/**
 * @author Nalongsone Danddank
 *
 */
public class Main {

	public static void main(String[] args) {
		  
		  EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	new Controller_GUI();
	            }
	        });
	}

}
