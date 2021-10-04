/**
 * @author Nalongsone Danddank
 */
package ReataurantAPP.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import ReataurantAPP.database.Database;
import ReataurantAPP.database.DatabaseException;
import ReataurantAPP.database.dao.Item;
import ReataurantAPP.database.dao.ItemDao;
import ReataurantAPP.database.dao.Order;
import ReataurantAPP.database.dao.OrderDao;
import ReataurantAPP.database.dao.OrderItem;
import ReataurantAPP.database.dao.OrderItemDao;
import ReataurantAPP.database.dao.TableQueryDao;
import ReataurantAPP.database.dao.Transaction;
import ReataurantAPP.database.dao.TransactionDao;
import ReataurantAPP.database.dao.User;
import ReataurantAPP.database.dao.UserDao;

import java.util.*;

/**
 * @author Nalongsone Danddank/
 *
 */
public class UserInterface_GUI extends JFrame implements ActionListener {

	private Container       con;
    private Controller_GUI  rcController;
    private String          currentUserName;
    private Database 		db;
       
    
    //-------- Master panel -------------- 
    //Main content panel(CENTER)
    private JPanel         mainPanel;
    
    private JButton        headBtnLogout;
    
    //Main button panel(WEST)
    private JPanel         mainBtnsPanel;
    // Main buttons

    private JButton        mainBtnShowMenu;
    private JButton        mainBtnManageOrder;
    private JButton        mainBtnMemberProfile;
    // Main buttons for management
    private JButton        mainBtnManageEmployee;
    private JButton        mainBtnManageMenuItem;
    private JButton        mainBtnOrder;
    private JButton        mainBtnShowTransaction;
    
    //Information panel(SOUTH)
    private JPanel         infoPanel;
    private JLabel         labelLoginUserName;
 //   private JButton         btnCheckout;
    private JTextArea      taMessage;
    
    //-------- Contents panel --------------
    // components for home panel
    private JPanel         homePanel;
    private RegisterPanel		cRegisterPanel;
    private LoginPanel          cLoginPanel;
    private MenuListPanel       cMenuListPanel;
    private DetailMenuItemPanel cDetailMenuItemPanel;
    private OrderListPanel      cOrderListPanel;
    private OrderDetailPanel    cOrderDetailPanel;
    private ProfilePanel		cProfilePanel;
    private MemberListPanel   cEmployeeListPanel;
    private EditMemberPanel   cEditEmployeePanel;
    private MenuManagementPanel       cMenuManagementPanel;
    private EditMenuItemPanel       cEditMenuItemPanel;
    private TansactionPanel       cTransactionPanel;
    private TransactionDetailPanel        cTransactionDetailPanel;


    private final static int WINDOW_X = 100;
    private final static int WINDOW_Y = 100;
    private final static int WINDOW_WIDTH = 900;
    private final static int WINDOW_HEIGHT = 600;    
    
   


	/**
     * Constructor for objects of class UserInterface_GUI
     */
    public UserInterface_GUI(Controller_GUI rController) {
        this.rcController = rController;
        this.db = new Database();
        this.con = getContentPane();
        this.con.setBackground(Color.DARK_GRAY);
        // Set frame
        setTitle("Restaurant Application");
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMasterPanelConpornents();

        setLoginUserName("");
        
        //------- Create main content panels

        cLoginPanel = new LoginPanel();
        mainPanel.add("Login", cLoginPanel);      
       
        cRegisterPanel = new RegisterPanel();
        mainPanel.add("Register", cRegisterPanel); 
        cMenuListPanel = new MenuListPanel();
        mainPanel.add("MenuList", cMenuListPanel);
        cDetailMenuItemPanel = new DetailMenuItemPanel();
        mainPanel.add("DetailItem", cDetailMenuItemPanel);
        cOrderListPanel = new OrderListPanel();
        mainPanel.add("OrderList", cOrderListPanel);
        cOrderDetailPanel = new OrderDetailPanel();
        mainPanel.add("OrderDetail", cOrderDetailPanel);
        cProfilePanel = new ProfilePanel();
        mainPanel.add("Profile", cProfilePanel);
        cEmployeeListPanel = new MemberListPanel();
        mainPanel.add("EmployeeList", cEmployeeListPanel);
        cEditEmployeePanel = new EditMemberPanel();
        mainPanel.add("EditEmployee", cEditEmployeePanel);
        cMenuManagementPanel = new MenuManagementPanel();
        mainPanel.add("MenuManagement", cMenuManagementPanel);
        cEditMenuItemPanel = new EditMenuItemPanel();
        mainPanel.add("EditMenuItem", cEditMenuItemPanel);
        cTransactionPanel = new TansactionPanel();
        mainPanel.add("TansactionPanel", cTransactionPanel);
        cTransactionDetailPanel = new TransactionDetailPanel();
        mainPanel.add("TransactionDetailPanel", cTransactionDetailPanel);
        
        changeMode(MODE_ANONYMOUS);
    }
    
    
    private void createMasterPanelConpornents()
    {

        
        //----------- Create main panels ------------
       // con.setLayout(new BorderLayout());
        
      
        //main content panel
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        con.add(mainPanel, BorderLayout.CENTER);
        
        //main operate buttons panel
        mainBtnsPanel = new JPanel();

        mainBtnsPanel.setLayout(new GridLayout(0,1));
        
        mainBtnsPanel.setBackground(Color.DARK_GRAY);
        
        mainBtnShowMenu = new JButton("MENU");
        mainBtnShowMenu.setForeground(Color.WHITE);
        mainBtnShowMenu.setBackground(Color.DARK_GRAY);
        mainBtnShowMenu.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowMenu);
        
        mainBtnOrder = new JButton("ORDER");
        mainBtnOrder.setForeground(Color.WHITE);
        mainBtnOrder.setBackground(Color.DARK_GRAY);
        mainBtnOrder.addActionListener(this);
        mainBtnsPanel.add(mainBtnOrder);
        
        mainBtnManageOrder = new JButton("MANAGE ORDER");
        mainBtnManageOrder.setForeground(Color.WHITE);
        mainBtnManageOrder.setBackground(Color.DARK_GRAY);
        mainBtnManageOrder.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageOrder);
        
        mainBtnShowTransaction = new JButton("TRANSACTION");
        mainBtnShowTransaction.setForeground(Color.WHITE);
        mainBtnShowTransaction.setBackground(Color.DARK_GRAY);
        mainBtnShowTransaction.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowTransaction);
        
        mainBtnMemberProfile = new JButton("PROFILE");
        mainBtnMemberProfile.setForeground(Color.WHITE);
        mainBtnMemberProfile.setBackground(Color.DARK_GRAY);
        mainBtnMemberProfile.addActionListener(this);
        mainBtnsPanel.add(mainBtnMemberProfile);
        
        mainBtnManageEmployee = new JButton("MANAGE MEMBER");
        mainBtnManageEmployee.setForeground(Color.WHITE);
        mainBtnManageEmployee.setBackground(Color.DARK_GRAY);
        mainBtnManageEmployee.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageEmployee);
        
        mainBtnManageMenuItem = new JButton("MANAGE MENU");
        mainBtnManageMenuItem.setForeground(Color.WHITE);
        mainBtnManageMenuItem.setBackground(Color.DARK_GRAY);
        mainBtnManageMenuItem.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageMenuItem);
        

        

        
        con.add(mainBtnsPanel, BorderLayout.EAST);
        
        //Information panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        labelLoginUserName = new JLabel();
        labelLoginUserName.setPreferredSize(new Dimension(150, 50));
        taMessage = new JTextArea(3,40);
        taMessage.setEditable(false);
        taMessage.setText("Wellcome!!");
        taMessage.setOpaque(true);
//        btnCheckout = new JButton("Checkout");
//        btnCheckout.setEnabled(false);
//        btnCheckout.addActionListener(this);
        //LineBorder border = new LineBorder(Color.BLACK, 3, true);
        //taMessage.setBorder(border);
        taMessage.setBackground(Color.WHITE);
        

        headBtnLogout = new JButton("Logout");
        headBtnLogout.addActionListener(this);
       // 
       // infoPanel.add(btnRegister);
        infoPanel.add(headBtnLogout);
       // infoPanel.add(btnCheckout);
        infoPanel.add(taMessage);
        infoPanel.add(labelLoginUserName);
        infoPanel.setBackground(Color.DARK_GRAY);
        con.add(infoPanel, BorderLayout.SOUTH);
    }
    

    public void setLoginUserName(String newName)
    {
        currentUserName = newName;
        labelLoginUserName.setForeground(Color.WHITE);
         if(newName == "")
         {
             labelLoginUserName.setText("Please login first.");
         }
         else
         {
            labelLoginUserName.setText("<html>Login By: <br>" + newName + "</html>");
        }
    }

    public final static byte MODE_ANONYMOUS = 0;
    public final static byte MODE_EMPLOYEE = 1;
    public final static byte MODE_MANAGER = 2;
    public final static byte MODE_CUSTOMER = 3;
    
    public void changeMode(byte state)
    {
        switch(state)
        {
            case MODE_ANONYMOUS:
                headBtnLogout.setEnabled(false);
                headBtnLogout.setVisible(false);
                mainBtnShowMenu.setEnabled(false);
                mainBtnShowMenu.setVisible(false);
                mainBtnManageOrder.setEnabled(false);
                mainBtnManageOrder.setVisible(false);
                mainBtnMemberProfile.setEnabled(false);
                mainBtnMemberProfile.setVisible(false);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageEmployee.setVisible(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnManageMenuItem.setVisible(false);
                mainBtnOrder.setEnabled(false);
                mainBtnOrder.setVisible(false);
                mainBtnShowTransaction.setEnabled(false);
                mainBtnShowTransaction.setVisible(false);
                break;
            case MODE_EMPLOYEE:
                headBtnLogout.setEnabled(true);
                headBtnLogout.setVisible(true);
                mainBtnShowMenu.setEnabled(true);
                mainBtnShowMenu.setVisible(true);
                mainBtnMemberProfile.setEnabled(true);
                mainBtnMemberProfile.setVisible(true);
                mainBtnManageOrder.setEnabled(true);
                mainBtnManageOrder.setVisible(true);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageEmployee.setVisible(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnManageMenuItem.setVisible(false);
                mainBtnOrder.setEnabled(true);
                mainBtnOrder.setVisible(true);
                mainBtnShowTransaction.setEnabled(true);
                mainBtnShowTransaction.setVisible(true);
                break;
           case MODE_MANAGER:
               headBtnLogout.setEnabled(true);
               headBtnLogout.setVisible(true);
               mainBtnShowMenu.setEnabled(true);
               mainBtnShowMenu.setVisible(true);
               mainBtnMemberProfile.setEnabled(true);
               mainBtnMemberProfile.setVisible(true);
               mainBtnManageOrder.setEnabled(true);
               mainBtnManageOrder.setVisible(true);
                mainBtnManageEmployee.setEnabled(true);
                mainBtnManageEmployee.setVisible(true);
                mainBtnManageMenuItem.setEnabled(true);
                mainBtnManageMenuItem.setVisible(true);
                mainBtnOrder.setEnabled(true);
                mainBtnOrder.setVisible(true);
                mainBtnShowTransaction.setEnabled(true);
                mainBtnShowTransaction.setVisible(true);
                break;
           case MODE_CUSTOMER:
               headBtnLogout.setEnabled(true);
               headBtnLogout.setVisible(true);
               mainBtnShowMenu.setEnabled(true);
               mainBtnShowMenu.setVisible(true);
               mainBtnMemberProfile.setEnabled(true);
               mainBtnMemberProfile.setVisible(true);
               mainBtnManageOrder.setEnabled(true);
               mainBtnManageOrder.setVisible(true);
               mainBtnManageEmployee.setEnabled(false);
               mainBtnManageEmployee.setVisible(false);
               mainBtnManageMenuItem.setEnabled(false);
               mainBtnManageMenuItem.setVisible(false);
               mainBtnOrder.setEnabled(true);
               mainBtnOrder.setVisible(true);
               mainBtnShowTransaction.setEnabled(true);
               mainBtnShowTransaction.setVisible(true);
               break;
        }
    }
      

    //--------------------------------------------------------
    // Display message on an information panel
    //--------------------------------------------------------
    public void displayMessage(String message)
    {
        taMessage.setForeground(Color.BLACK);
        taMessage.setText(message);
    }
    
    public void displayErrorMessage(String message)
    {
        taMessage.setForeground(Color.RED);
        taMessage.setText(message);
    }
    
    //========================================================
    // Show dialog message
    //========================================================
    final static int DIALOG_YES = JOptionPane.YES_OPTION;
    final static int DIALOG_NO = JOptionPane.NO_OPTION;
    final static int DIALOG_CANCEL = JOptionPane.CANCEL_OPTION;
    
    public int showYesNoDialog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public int showYesNoCancelDiaglog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_CANCEL_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public void showErrorDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showConfirmDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }
         
    
    private int getIDfromString(String stringLine, int length)
    {
        int index = stringLine.indexOf("ID:"); //Search string of "ID:"
        if(index == -1)
        {
            showErrorDialog("Error", "String 'ID:' is not found!!");
            return -1;
        }
        
        try
        {
            String strID = stringLine.substring(index + 3, index + 3 + length);
            int id = Integer.parseInt(strID.trim());
            return id;
        }
        catch(Exception e)
        {
            showErrorDialog("Error", "Parse error");
            return -1;
        }
    }
    //========================================================
    // Master panel action
    //========================================================
    
    
    
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == mainBtnShowMenu)
        {
            changeMainPanel("MenuList");
            cMenuListPanel.init();
        }
        else if (ae.getSource() == mainBtnOrder)
        {
            changeMainPanel("OrderDetail");
            cOrderDetailPanel.setCurrentOrderID(0);
            cOrderDetailPanel.init();
        }
        else if (ae.getSource() == mainBtnManageOrder)
        {
            changeMainPanel("OrderList");
            cOrderListPanel.init();
        }
        else if(ae.getSource() == mainBtnMemberProfile) {
        	changeMainPanel("Profile");
        	cProfilePanel.init();
        }
        else if (ae.getSource() == mainBtnManageEmployee)
        {
            changeMainPanel("EmployeeList");
            cEmployeeListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageMenuItem)
        {
            changeMainPanel("MenuManagement");
            cMenuManagementPanel.init();
        }

        else if (ae.getSource() == mainBtnShowTransaction)
        {
          changeMainPanel("TansactionPanel");
          cTransactionPanel.init();

        }
        else if (ae.getSource() == headBtnLogout) {
            if( showYesNoDialog("Logout","Are you sure to logout?") == DIALOG_YES)
            {
              //  rcController.userLogout();
                headBtnLogout.setEnabled(false);
                setLoginUserName("");
                changeMainPanel("Login");

                changeMode(MODE_ANONYMOUS);
            }
        }

    }
  
    
    private void  changeMainPanel(String panelName)
    {
        ((CardLayout) mainPanel.getLayout()).show( mainPanel, panelName);
        displayMessage("Main panel change :" + panelName);
    }
    
    /****************************************************************
     * Login panel
    *****************************************************************/
    public class LoginPanel extends JPanel implements ActionListener
    {
        // components for login panel
        private JPanel         loginPanel;

        private JLabel          email;
        private JTextField      emailF;
        private JLabel          pwd;
        private JPasswordField  pwdF;
        

        // private JCheckBox       chbIsManager;
        private JButton         btnLoginOK;
        private JButton        btnRegister;

        
        public LoginPanel()
        {
        	this.setLayout( new BorderLayout());
            loginPanel = new JPanel();
            GridBagLayout gbLayout = new GridBagLayout();
            loginPanel.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            
            email = new JLabel("Email: ");
            email.setForeground(Color.WHITE);
            email.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbLayout.setConstraints(email, gbc);
            loginPanel.add(email);
            loginPanel.add(email);
            
            emailF = new JTextField(20);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(emailF, gbc);
            loginPanel.add(emailF);
            
            pwd = new JLabel("Password:");
            pwd.setForeground(Color.WHITE);
            pwd.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(pwd, gbc);
            loginPanel.add(pwd);
            
            pwdF = new JPasswordField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(pwdF, gbc);
            loginPanel.add(pwdF);
            

        
            btnLoginOK = new JButton("Login");
            btnLoginOK.setBackground(Color.WHITE);

            btnLoginOK.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbLayout.setConstraints(btnLoginOK, gbc);
            loginPanel.add(btnLoginOK);
            loginPanel.setBackground(Color.DARK_GRAY);
            
//            loginPanel.getRootPane().setDefaultButton(btnLoginOK);
//            btnLoginOK.setMnemonic(KeyEvent.VK_A);
//            btnLoginOK.addActionListener(new ActionListener() {
//               public void actionPerformed(ActionEvent ae) {
//                  System.out.println("Button pressed!");
//                  btnLoginOK.setEnabled(!btnLoginOK.isEnabled());
//               }
//            });
            

            btnRegister = new JButton("Register");
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnRegister, gbc);
            loginPanel.add(btnRegister);
            btnRegister.setBackground(Color.WHITE);
            btnRegister.addActionListener(this);
            
            add(loginPanel, BorderLayout.CENTER);
          
        }

        
        public void init()
        {
        	emailF.setText("");
        	pwdF.setText("");
            emailF.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
         
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnLoginOK)
            {

                if (btnLoginOK.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnLoginOK.requestFocusInWindow();
                    if (!btnLoginOK.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
  
                char[] password;
                
                byte state = -1;
                
                String inputID = emailF.getText().trim();
               
                if(inputID.equals(""))
                {
                    displayErrorMessage("Enter user ID");
                    return;
                }
                
     
                password= pwdF.getPassword();
                String inputPassword = new String(password);
                if(inputPassword.equals(""))
                {
                    displayErrorMessage("Enter password");
                    return;
                }
                
                
               if( db.loginCheck(inputID, inputPassword)) 
                {
                	if(db.getUser().getUser_role().equals("admin")) {
                        showConfirmDialog("Message", "Login success!!");
                        displayMessage("Wellcome, " + currentUserName);
                        emailF.setText("");
                        pwdF.setText("");
                        changeMainPanel("MenuList");
                        cMenuListPanel.init();
                        changeMode(MODE_MANAGER);
                        setLoginUserName(db.getUser().getlName());
                	}
                	else if (db.getUser().getUser_role().toLowerCase().equals("customer")) {
                		showConfirmDialog("Customer", "Login success!!");
                		displayMessage("Wellcome, " + currentUserName);
                        emailF.setText("");
                        pwdF.setText("");
                        changeMainPanel("MenuList");
                        cMenuListPanel.init();
                        changeMode(MODE_CUSTOMER);
                        setLoginUserName(db.getUser().getlName());
                	}
                	else if (db.getUser().getUser_role().toLowerCase().equals("chef") 
                			|| db.getUser().getUser_role().toLowerCase().equals("employee")
                			|| db.getUser().getUser_role().toLowerCase().equals("vendor")) {
                		showConfirmDialog("Customer", "Login success!!");
                		displayMessage("Wellcome, " + currentUserName);
                        emailF.setText("");
                        pwdF.setText("");
                        changeMainPanel("MenuList");
                        cMenuListPanel.init();
                        changeMode(MODE_EMPLOYEE);
                        setLoginUserName(db.getUser().getlName());
                	}

                }
                else
                {
                    displayErrorMessage(rcController.getErrorMessage());
                }
            } 
            else if (ae.getSource() == btnRegister ) {
                changeMainPanel("Register");
                headBtnLogout.setEnabled(false);
                cRegisterPanel.init();
                displayMessage("Enter your infos to register.");
            }

        }

    }
    
    /****************************************************************
     * Register panel
    *****************************************************************/
    private class RegisterPanel extends JPanel implements ActionListener {
        private JLabel          fName;
        private JLabel          lName;       
        private JTextField      fNameF;
        private JTextField      lNameF;
        private JLabel          email;
        private JTextField      emailF;
        private JLabel          phone;
        private JTextField      phoneF;
        private JLabel			bDateD;
        private JLabel          bDate;
        private JTextField      bDateF;
        private JLabel          pwd;
        private JPasswordField  pwdF;
        private JLabel          bdate;
        private JTextField      bdateF;
        private JLabel			addressD;
        private JLabel          address;
        private JTextField      addressF;
        private JButton         registerBtn;
        private JButton         cancleBtn;
        
        public RegisterPanel() {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            fName = new JLabel("First Name:");
            fName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(fName, gbc);
            this.add(fName);          
            fNameF = new JTextField(10);
            fNameF.setText("Jone");
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(fNameF, gbc);
            this.add(fNameF);
            
            lName = new JLabel("Last Name:");
            lName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lName, gbc);
            this.add(lName);           
            lNameF = new JTextField(10); 
            lNameF.setText("Smith");
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(lNameF, gbc);
            this.add(lNameF);
            
            Integer randomNum = new Random().nextInt(899999999) + 10000000;
            
            email = new JLabel("Email:");
            email.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(email, gbc);
            this.add(email);        
            emailF = new JTextField(20);
            emailF.setText("Jone.Smith" + randomNum.toString() + "@email.usa");
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(emailF, gbc);
            this.add(emailF);
            
            phone = new JLabel("Phone Number:");
            
            phone.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(phone, gbc);
            this.add(phone);        
            phoneF = new JTextField(20);
            phoneF.setText(randomNum.toString());
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(phoneF, gbc);
            this.add(phoneF);
            
            bDateD = new JLabel("MM-dd-yyyy");
            bDateD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDateD, gbc);
            this.add(bDateD);
            bDate = new JLabel("Birth Day:");
            bDate.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDate, gbc);
            this.add(bDate);        
            bDateF = new JTextField(20);
            bDateF.setText("11-11-2011");
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbLayout.setConstraints(bDateF, gbc);
            this.add(bDateF);
            
            pwd = new JLabel("Password:");
            pwd.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(pwd, gbc);
            this.add(pwd);           
            pwdF = new JPasswordField(10);
            pwdF.setText("123456");
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbLayout.setConstraints(pwdF, gbc);
            this.add(pwdF);
            
            addressD = new JLabel("Line, city, province, country, gender, intro");
            addressD.setPreferredSize(new Dimension(250, 30));
            gbc.gridx = 1;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(addressD, gbc);
            this.add(addressD); 
            address = new JLabel("Address&Ohter:");
            address.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(address, gbc);
            this.add(address);         
            addressF = new JTextField(40);
            addressF.setText("1087 Maneland Ave W, St. Paul, MN, USA, Male, I am Customer");
            gbc.gridx = 1;
            gbc.gridy = 8;
            gbLayout.setConstraints(addressF, gbc);
            this.add(addressF);
            
            registerBtn = new JButton("Register");
            registerBtn.addActionListener(this);
          gbc.gridx = 1;
          gbc.gridy = 9;
          gbc.gridwidth = 3;
          gbLayout.setConstraints(registerBtn, gbc);
          this.add(registerBtn);
          
          
          cancleBtn = new JButton("Cancle");
          cancleBtn.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbLayout.setConstraints(cancleBtn, gbc);
        this.add(cancleBtn);

        }
        
		public void init() {

			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 if (e.getSource() == registerBtn) {
				 Date ddate = null;
				 try {
					ddate = new SimpleDateFormat("MM-dd-yyyy").parse(bDateF.getText().trim());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			String[] infos = new String[7];
				infos = addressF.getText().trim().split(", ");

				 
				 User user = new User(0, fNameF.getText().trim(),lNameF.getText().trim(), phoneF.getText().trim(),
						  emailF.getText().trim(), pwdF.getText().trim(), "Customer",
						 infos[0].trim(), infos[1].trim(), infos[2].trim(), infos[3].trim(), 
						 new Date(), infos[5].trim(), ddate, infos[4].trim(), 0);
						 
				 UserDao userDao = new UserDao();
				 if(userDao.insert(user)) {

			               // rcController.userLogout();
			                headBtnLogout.setEnabled(false);
			                
			                changeMainPanel("Login");

			                changeMode(MODE_ANONYMOUS);
			                displayErrorMessage("Register Success!");

					 
				 } else {
					 displayErrorMessage("Register Fail!");
				 }
		            
			 } else if  (e.getSource() == cancleBtn) {
	             //   rcController.userLogout();
	                headBtnLogout.setEnabled(false);;
	                
	                changeMainPanel("Login");

	                changeMode(MODE_ANONYMOUS);
			 }
		}
    	
    }
    
    
    /****************************************************************
     * Profile panel
    *****************************************************************/ 
    private class ProfilePanel extends JPanel implements ActionListener {


		private JLabel          fName;
        private JLabel          lName;       
        private JTextField      fNameF;
        private JTextField      lNameF;
        private JLabel          email;
        private JTextField      emailF;
        private JLabel          phone;
        private JTextField      phoneF;
        private JLabel			bDateD;
        private JLabel          bDate;
        private JTextField      bDateF;
        private JLabel          pwd;
        private JPasswordField  pwdF;
        private JLabel          bdate;
        private JTextField      bdateF;
        private JLabel			addressD;
        private JLabel          address;
        private JTextField      addressF;
        private JLabel			roleD;
        private JButton         editBtn;
        private JButton         updateBtn;
        private JButton         cancleBtn;
        
        public ProfilePanel() {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            fName = new JLabel("First Name:");
            fName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(fName, gbc);
            this.add(fName);          
            fNameF = new JTextField(10);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(fNameF, gbc);
            this.add(fNameF);
            
            lName = new JLabel("Last Name:");
            lName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lName, gbc);
            this.add(lName);           
            lNameF = new JTextField(10); 
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(lNameF, gbc);
            this.add(lNameF);
            
            
            email = new JLabel("Email:");
            email.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(email, gbc);
            this.add(email);        
            emailF = new JTextField(20);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(emailF, gbc);
            this.add(emailF);
            
            phone = new JLabel("Phone Number:");
            
            phone.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(phone, gbc);
            this.add(phone);        
            phoneF = new JTextField(20);
           // phoneF.setInputVerifier(new IntegerInputVerifier(1,99999999));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(phoneF, gbc);
            this.add(phoneF);
            
            bDateD = new JLabel("MM-dd-yyyy");
            bDateD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDateD, gbc);
            this.add(bDateD);
            bDate = new JLabel("Birth Day:");
            bDate.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDate, gbc);
            this.add(bDate);        
            bDateF = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbLayout.setConstraints(bDateF, gbc);
            this.add(bDateF);
            
            pwd = new JLabel("Password:");
            pwd.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(pwd, gbc);
            this.add(pwd);           
            pwdF = new JPasswordField(10);
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbLayout.setConstraints(pwdF, gbc);
            this.add(pwdF);
            
            addressD = new JLabel("Line, city, province, country, gender, intro");
            addressD.setPreferredSize(new Dimension(250, 30));
            gbc.gridx = 1;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(addressD, gbc);
            this.add(addressD); 
            address = new JLabel("Address&Ohter:");
            address.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(address, gbc);
            this.add(address);         
            addressF = new JTextField(40);
            gbc.gridx = 1;
            gbc.gridy = 8;
            gbLayout.setConstraints(addressF, gbc);
            this.add(addressF);
            
            roleD = new JLabel("Role: ");
            roleD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(roleD, gbc);
            this.add(roleD);
            
            
            editBtn = new JButton("Edit");
            editBtn.addActionListener(this);
          gbc.gridx = 0;
          gbc.gridy = 10;
          gbc.gridwidth = 3;
          gbLayout.setConstraints(editBtn, gbc);
          this.add(editBtn);
          
          updateBtn = new JButton("Update");
          updateBtn.addActionListener(this);
          gbc.gridx = 1;
          gbc.gridy = 10;
          gbc.gridwidth = 3;
          gbLayout.setConstraints(updateBtn, gbc);
          this.add(updateBtn);
                   
          cancleBtn = new JButton("Cancle");
          cancleBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbLayout.setConstraints(cancleBtn, gbc);
        this.add(cancleBtn);

        }
        
		public void init() {
			User user = db.getUser();
            fNameF.setText(user.getfName());
            fNameF.setEditable(false);
            
            lNameF.setText(user.getlName());
            lNameF.setEditable(false);
            
            emailF.setText(user.getEmail());
            emailF.setEditable(false);
            
            phoneF.setText(""+user.getPhone());
            phoneF.setEditable(false); 
 
            bDateF.setText(new SimpleDateFormat("MM-dd-yyyy").format(user.getBdate()));
            bDateF.setEditable(false); 

            roleD.setText("Role: " + db.getUser().getUser_role());
            
            pwdF.setText(user.getPwd());
            pwdF.setEditable(false);
            
            addressF.setText(user.getLine()+", "
            		+user.getCity()+", "+ user.getProvince()+", "+user.getCountry()
            		+", "+user.getGender()+", "+user.getIntro()); 
            addressF.setEditable(false);
            editBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            cancleBtn.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 if (e.getSource() == editBtn) {

		            fNameF.setEditable(true);

		            lNameF.setEditable(true);

		            emailF.setEditable(true);

		            phoneF.setEditable(true); 

		            bDateF.setEditable(true); 

		            pwdF.setEditable(true);

		            addressF.setEditable(true);
		            editBtn.setEnabled(false);
		            cancleBtn.setEnabled(true);
		            updateBtn.setEnabled(true);
        
			 } else if (e.getSource() == updateBtn) {
				 
				 Date ddate = null;
				 try {
					ddate = new SimpleDateFormat("MM-dd-yyyy").parse(bDateF.getText().trim());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			String[] infos = new String[7];
				infos = addressF.getText().trim().split(", ");

				 
				 User user = new User(db.getUser().getId(), fNameF.getText().trim(),lNameF.getText().trim(), 
						 phoneF.getText().trim(),
						  emailF.getText().trim(), pwdF.getText().trim(), db.getUser().getUser_role(),
						 infos[0].trim(), infos[1].trim(), infos[2].trim(), infos[3].trim(), 
						 db.getUser().getRegisteredAt(), infos[5].trim(), ddate, infos[4].trim(), 0);
						 
				 UserDao userDao = new UserDao();
				 if(userDao.update(user)) {

			                displayErrorMessage("Update Success!");
			                db.setUser(user);
			                init();


					 
				 } else {
					 displayErrorMessage("Update Fail!");
				 }
		    
			 }
			 
			 else if  (e.getSource() == cancleBtn) {
				 init();
				 
			 }
		}
    	
    }
    
    /****************************************************************
     * Menu list panel
    *****************************************************************/       
    private class MenuListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JList           displayList;
        private JPanel          btnPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
        private JButton         btnAlcohol;
        private JButton         btnDessert;
        private JButton         btnDetail;
        
        public MenuListPanel()
        {
            this.setLayout( new BorderLayout());
            displayArea = new JTextArea();
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane(displayList);
            scrollPanel.setPreferredSize(new Dimension(200, 400));
            add(scrollPanel, BorderLayout.CENTER);
            
           btnPanel = new JPanel();
           btnPanel.setLayout( new FlowLayout());
           btnAll = new JButton("All");
           btnAll.addActionListener(this);
           btnMain = new JButton("Main");
           btnMain.addActionListener(this);
           btnDrink = new JButton("Drink");
           btnDrink.addActionListener(this);
           btnAlcohol = new JButton("Alcohol");
           btnAlcohol.addActionListener(this);
           btnDessert = new JButton("Dessert");
           btnDessert.addActionListener(this);
           btnDetail = new JButton("Detail");
           btnDetail.addActionListener(this);
           
           btnPanel.add(btnAll);
           btnPanel.add(btnMain);
           btnPanel.add(btnDrink);
           btnPanel.add(btnAlcohol);
           btnPanel.add(btnDessert);
           btnPanel.add(btnDetail);
           
           add(btnPanel, BorderLayout.SOUTH);
        }
    
        public void init()
        {
            showMenuList("ALL");
        }
        

        
        private void showMenuList(String menuType)
        {
      	

        	if(menuType.equals("ALL")) {
        	List<String> list = new ArrayList<String>();
        	for(Item i : db.getAllItemList()) {
        		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                      i.getId(),i.getTitle(), i.getPrice(),i.getType()));
        	}
        	displayList.setListData(list.toArray());
            scrollPanel.getViewport().setView(displayList);
        	} else if (menuType.equals("MAIN")) {
        		List<String> list = new ArrayList<String>();
            	for(Item i : db.getItemByType("'Pizza' or type = 'Burgers' or type = 'Sandwiches' or type = 'Salads' or type = 'Main';")) {
            		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                          i.getId(),i.getTitle(), i.getPrice(),i.getType()));
            	}
            	displayList.setListData(list.toArray());
                scrollPanel.getViewport().setView(displayList);
        	} else if (menuType.equals("Drink") || menuType.equals("Alcohol") || menuType.equals("Dessert") ) {
        		List<String> list = new ArrayList<String>();
            	for(Item i : db.getItemByType("'" +menuType+"'")) {
            		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                          i.getId(),i.getTitle(), i.getPrice(),i.getType()));
            	}
            	displayList.setListData(list.toArray());
                scrollPanel.getViewport().setView(displayList);
        	}
            
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAll)
            {
                showMenuList("ALL");

            }
            else if (ae.getSource() == btnMain)
            {
                showMenuList("MAIN");

            }
            else if (ae.getSource() == btnDrink)
            {
                showMenuList("Drink");

            }
            else if (ae.getSource() == btnAlcohol)
            {
                showMenuList("Alcohol");
            }
            else if (ae.getSource() == btnDessert)
            {
                showMenuList("Dessert");
            }
            else if (ae.getSource()== btnDetail) {
            	String itemLine = (String)displayList.getSelectedValue();
            	if (itemLine == null) {
            		return;
            	}
            	
            	int id = getIDfromString( itemLine, 4);

            	showDetail(id);
            }
        }

		private void showDetail(int id) {

			cDetailMenuItemPanel.init(id);
            changeMainPanel("DetailItem");
		}
    }
    
    
    /****************************************************************
     * Edit menu item panel
    *****************************************************************/       
    private class EditMenuItemPanel extends JPanel implements ActionListener
    {
    	private JLabel          itemIdD;
        private JLabel          chefIdD;
        private JTextField      chefIdF;
        private JLabel          titleD;
        private JTextField      titleF;
        private JLabel          priceD;
        private JTextField      priceF;
        private JLabel          typeD;
        private JComboBox       typeCB;

        private JLabel			quantD;
        private JTextField		quantF;
        private JLabel			recipeD;
        private JTextField		recipeF;
        private JLabel			instrD;
        private JTextField		instrF;
        private JLabel			flavorD;
        private JTextField		flavorF;
        private JLabel			unitD;
        private JTextField		unitF;
        
        private JButton         btnOK;
        private JButton         btnCancle;
        
        Item item;
        
        public EditMenuItemPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            itemIdD = new JLabel("Item ID:");
            itemIdD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(itemIdD, gbc);
            this.add(itemIdD);  
            
            chefIdD = new JLabel("Chef ID:");
            chefIdD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(chefIdD, gbc);
            this.add(chefIdD);     
            chefIdF = new JTextField(4);
            chefIdF.setInputVerifier(new IntegerInputVerifier(1000,9999));
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(chefIdF, gbc);
            this.add(chefIdF);
            
            titleD = new JLabel("Menu item name:");
            titleD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(titleD, gbc);
            this.add(titleD);            
            titleF = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(titleF, gbc);
            this.add(titleF);
            
            priceD = new JLabel("Menu item price:");
            priceD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(priceD, gbc);
            this.add(priceD);            
            priceF = new JTextField(10);
            priceF.setInputVerifier(new DoubleInputVerifier(0.01,10000));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(priceF, gbc);
            this.add(priceF);
            
            typeD = new JLabel("Menu item type:");
            typeD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbLayout.setConstraints(typeD, gbc);
            this.add(typeD);
            
            String[] combodata = {"Main", "Drink", "Alcohol", "Dessert", "Pizza", "Burgers","Sandwiched","Salads"};
            typeCB = new JComboBox(combodata);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbLayout.setConstraints(typeCB, gbc);
            this.add(typeCB);
            
            quantD = new JLabel("Item Quantity:");
            quantD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbLayout.setConstraints(quantD, gbc);
            this.add(quantD);            
            quantF = new JTextField(10);
            quantF.setInputVerifier(new DoubleInputVerifier(0.01,10000));
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbLayout.setConstraints(quantF, gbc);
            this.add(quantF);
            
            unitD = new JLabel("Item Unit:");
            unitD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbLayout.setConstraints(unitD, gbc);
            this.add(unitD);            
            unitF = new JTextField(10);
            unitF.setInputVerifier(new IntegerInputVerifier(0,9999));
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbLayout.setConstraints(unitF, gbc);
            this.add(unitF);
            
            recipeD = new JLabel("Item Recipe:");
            quantD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbLayout.setConstraints(recipeD, gbc);
            this.add(recipeD);            
            recipeF = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 7;
            gbLayout.setConstraints(recipeF, gbc);
            this.add(recipeF);
            
            flavorD = new JLabel("Instructions:");
            flavorD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbLayout.setConstraints(flavorD, gbc);
            this.add(flavorD);            
            flavorF = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 8;
            gbLayout.setConstraints(flavorF, gbc);
            this.add(flavorF);
            
            instrD = new JLabel("Instructions:");
            instrD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbLayout.setConstraints(instrD, gbc);
            this.add(instrD);            
            instrF = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbLayout.setConstraints(instrF, gbc);
            this.add(instrF);

            
            
            btnOK = new JButton("OK");
            btnOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnOK, gbc);
            this.add(btnOK);
            
            btnCancle = new JButton("Cancle");
            btnCancle.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 10;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnCancle, gbc);
            this.add(btnCancle);
        }
        
        public void init(int menuItemID)
        {
            //------------- Add new menu item ------------
            if( menuItemID == 0)    
            {
            	item = null;
            	itemIdD.setText("Item ID: New ID");
            	chefIdF.setText("1001"); 
            	titleF.setText("Main Food");                 
            	priceF.setText("9.99");                 
            	typeCB.setSelectedIndex(0);
            	quantF.setText("10"); 
            	recipeF.setText("Food");
            	instrF.setText("Eat");
            	flavorF.setText("No Spicy");
               	unitF.setText("1");         	
                return;
            }
            
            //------------- Update menu item ------------
            
            ItemDao itemDao = new ItemDao();
            item = itemDao.selectById(menuItemID);
            if (item == null) {
            	showErrorDialog("Error", "Get menu item data failed.");
            	return;
            }
            itemIdD.setText("Item ID: "+item.getId());
        	chefIdF.setText(""+item.getUserId()); 
        	titleF.setText(item.getTitle());                 
        	priceF.setText(""+item.getPrice());                 
        	typeCB.setSelectedItem(item.getType());
        	quantF.setText(""+item.getQuantity()); 
        	recipeF.setText(item.getRecipe());
        	instrF.setText(item.getInstructions());
        	flavorF.setText(item.getContent());
           	unitF.setText(""+item.getUnit());
            
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnOK)
            {
            	if (item == null) {
            		
            		Item i = new Item();
            		i.setId(0);
            		i.setUserId(Integer.parseInt(chefIdF.getText()));
            		i.setTitle(titleF.getText());
            		i.setPrice(Double.parseDouble(priceF.getText()));
            		i.setType((String)typeCB.getSelectedItem());
            		i.setQuantity(Double.parseDouble(quantF.getText()));
            		i.setContent(flavorF.getText());
            		i.setInstructions(instrF.getText());
            		i.setUnit(Integer.parseInt(unitF.getText()));
            		i.setRecipe(recipeF.getText());
            		ItemDao dao = new ItemDao();
            		if(dao.insert(i)) {
            			showConfirmDialog("Message", "Add Item successful!!");
            			changeMainPanel("MenuManagement");
                        cMenuManagementPanel.init();
                        return;
            		} else {
            			showErrorDialog("Error", rcController.getErrorMessage());
            			return;
            		}

            	}

        		item.setUserId(Integer.parseInt(chefIdF.getText()));
        		item.setTitle(titleF.getText());
        		item.setPrice(Double.parseDouble(priceF.getText()));
        		item.setType((String)typeCB.getSelectedItem());
        		item.setQuantity(Double.parseDouble(quantF.getText()));
        		item.setContent(flavorF.getText());
        		item.setInstructions(instrF.getText());
        		item.setUnit(Integer.parseInt(unitF.getText()));
        		item.setRecipe(recipeF.getText());
        		ItemDao dao = new ItemDao();
        		if(dao.update(item)) {
        			showConfirmDialog("Message", "Update Item successful!!");
        			changeMainPanel("MenuManagement");
                    cMenuManagementPanel.init();
                    return;
        		} else {
        			showErrorDialog("Error", rcController.getErrorMessage());
        			return;
        		}
 
            } 
            if(ae.getSource() == btnCancle) {
            	changeMainPanel("MenuManagement");
                cMenuManagementPanel.init();
            }
        }
    }
   
    
    /****************************************************************
     * MenuManagementPanel
    *****************************************************************/    
    private class MenuManagementPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnAddNewMenuItem;
        private JButton         btnEditMenuItem;
        private JButton         btnDeleteMenuItem;
        
        public MenuManagementPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 3;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddNewMenuItem     = new JButton("Add new menu item");
            btnAddNewMenuItem.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddNewMenuItem, gbc);
            this.add(btnAddNewMenuItem);
            
            btnEditMenuItem    = new JButton("Edit menu item");
            btnEditMenuItem.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditMenuItem, gbc);
            this.add(btnEditMenuItem);
            
            btnDeleteMenuItem   = new JButton("Delete menu item");
            btnDeleteMenuItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteMenuItem, gbc);
            this.add(btnDeleteMenuItem);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showMenuList();
        }
        
        private void showMenuList()
        {
        	List<String> list = new ArrayList<String>();
        	for(Item i : db.getAllItemList()) {
        		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                      i.getId(),i.getTitle(), i.getPrice(),i.getType()));}
            displayList.setListData(list.toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        
        private int getSelectedMenuID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
            return getIDfromString( orderLine, 6);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddNewMenuItem)
            {
                cEditMenuItemPanel.init(0);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnEditMenuItem)
            {
                int menuID = getSelectedMenuID();
                if( menuID == -1)    return;
                cEditMenuItemPanel.init(menuID);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnDeleteMenuItem)
            {
                int deleteMenuID = getSelectedMenuID();
                ItemDao itemDao = new ItemDao();
                if(!itemDao.deleteById(deleteMenuID)) {
                	showErrorDialog("Error", rcController.getErrorMessage());
                } else {
                	displayMessage("Item Deleted.");
                	init();
                }

            }
        }
    }
    
    /****************************************************************
     * Edit menu item panel
    *****************************************************************/       
    private class DetailMenuItemPanel extends JPanel implements ActionListener
    {
    	private JLabel          itemIdD;
        private JLabel          chefIdD;
        private JLabel          titleD;
        private JLabel          priceD;
        private JLabel          typeD;

        private JLabel			quantD;
        private JLabel			recipeD;
        private JLabel			instrD;
        private JLabel			flavorD;
        private JLabel			unitD;
        
        private JButton         btnCancle;
        
        
        public DetailMenuItemPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            itemIdD = new JLabel("Item ID:");
            itemIdD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(itemIdD, gbc);
            this.add(itemIdD);  
            
            chefIdD = new JLabel("Chef ID:");
            chefIdD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(chefIdD, gbc);
            this.add(chefIdD);     
                        
            titleD = new JLabel("Menu item name:");
            titleD.setPreferredSize(new Dimension(350, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(titleD, gbc);
            this.add(titleD);            
                        
            priceD = new JLabel("Menu item price:");
            priceD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(priceD, gbc);
            this.add(priceD);            
                        
            typeD = new JLabel("Menu item type:");
            typeD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbLayout.setConstraints(typeD, gbc);
            this.add(typeD);
            
                      
            quantD = new JLabel("Item Quantity:");
            quantD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbLayout.setConstraints(quantD, gbc);
            this.add(quantD);            
                        
            unitD = new JLabel("Item Unit:");
            unitD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbLayout.setConstraints(unitD, gbc);
            this.add(unitD);            
                        
            recipeD = new JLabel("Item Recipe:");
            quantD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbLayout.setConstraints(recipeD, gbc);
            this.add(recipeD);            
                       
            flavorD = new JLabel("Instructions:");
            flavorD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbLayout.setConstraints(flavorD, gbc);
            this.add(flavorD);            
                        
            instrD = new JLabel("Instructions:");
            instrD.setPreferredSize(new Dimension(300, 30));
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbLayout.setConstraints(instrD, gbc);
            this.add(instrD);            
                       
           
            btnCancle = new JButton("Cancle");
            btnCancle.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnCancle, gbc);
            this.add(btnCancle);
        }
        
        public void init(int menuItemID)
        {
            
            ItemDao itemDao = new ItemDao();
           Item item = itemDao.selectById(menuItemID);
            if (item == null) {
            	showErrorDialog("Error", "Get menu item data failed.");
            	return;
            }
            itemIdD.setText("Item ID: "+item.getId());
        	chefIdD.setText("Chef Id: "+item.getUserId()); 
        	titleD.setText("Title: "+item.getTitle());                 
        	priceD.setText("Price: "+item.getPrice());                 
        	typeD.setText("Type: "+item.getType());
        	quantD.setText("Quantity: "+item.getQuantity()); 
        	recipeD.setText("Recipe: "+item.getRecipe());
        	instrD.setText("Instruction: "+item.getInstructions());
        	flavorD.setText("Flavour: "+item.getContent());
           	unitD.setText("Unit: "+item.getUnit());
            
        }
        
        public void actionPerformed(ActionEvent ae) {

            if(ae.getSource() == btnCancle) {
                changeMainPanel("MenuList");
                cMenuListPanel.init();
            }
        }
    }
    
    /****************************************************************
     * Employee list panel
    *****************************************************************/       
    private class MemberListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnAddMem;
        private JButton         btnEditMem;
        private JButton         btnDeleteMem;
        private JButton         btnShowStaff;
        private JButton         btnShowCus;
        private JButton         btnShowAll;
        
        
        public MemberListPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 6;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddMem     = new JButton("Add Member");
            btnAddMem.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.15;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddMem, gbc);
            this.add(btnAddMem);
            
            btnEditMem    = new JButton("Edit Member");
            btnEditMem.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditMem, gbc);
            this.add(btnEditMem);
            
            btnDeleteMem   = new JButton("Delete Member");
            btnDeleteMem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteMem, gbc);
            this.add(btnDeleteMem);
            
            btnShowStaff  = new JButton("Show Staff");
            btnShowStaff.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnShowStaff, gbc);
            this.add(btnShowStaff);
            
            btnShowCus  = new JButton("Show Customer");
            btnShowCus.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnShowCus, gbc);
            this.add(btnShowCus);
            
            btnShowAll  = new JButton("Show All");
            btnShowAll.addActionListener(this);
            gbc.gridx = 5;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnShowAll, gbc);
            this.add(btnShowAll);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showStaffList("all");
        }
        
        public void showStaffList(String show)
        {    
        	List<String> list = new ArrayList<String>();
        	UserDao userDao = new UserDao();
        	if(show.toLowerCase().equals("all")) {
        	
        	if(db.getAllUser()) {
        		for(User user : db.getUsers()) {
        			list.add(String.format("ID:%4d  Name:%-25s Role:%s",
                          user.getId(), user.getfName() + " "+ user.getlName(), user.getUser_role()));
        		}
        	}
        	} else if (show.toLowerCase().equals("staff")) {
        		
        		List<User> userList = userDao.selectByQuery("select * from user where user_role != 'customer';");
            		for(User user : userList) {
            			list.add(String.format("ID:%4d  Name:%-25s Role:%s",
                              user.getId(), user.getfName() + " "+ user.getlName(), user.getUser_role()));
            		}
        	}
         else if (show.toLowerCase().equals("customer")) {
        	
        	List<User> userList = userDao.selectByQuery("select * from user where user_role = 'customer';");
        	for(User user : userList) {
        		list.add(String.format("ID:%4d  Name:%-25s Role:%s",
        				user.getId(), user.getfName() + " "+ user.getlName(), user.getUser_role()));
        	}
        }
            		
        	displayList.setListData(list.toArray());
        	scrollPanel.getViewport().setView(displayList);
        	
        	
        }
        
        private int getSelectedStaffID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
            return getIDfromString( orderLine, 4);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddMem)
            {
                cEditEmployeePanel.init(0);
                changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnEditMem)
            {
                int staffID = getSelectedStaffID();
                if( staffID == -1)    return;
                 cEditEmployeePanel.init(staffID);
                 changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnDeleteMem)
            {
                int deleteStaffID = getSelectedStaffID();
                if( deleteStaffID == -1)    return;
                
                if( showYesNoDialog("", "Are you sure to delete the staff?") == DIALOG_YES)
                {
                	UserDao userDao = new UserDao();
                    if(!userDao.deleteById(deleteStaffID))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                    }
                    else
                    {
                        displayMessage("Deleted.");
                        init();
                    }
                }
            }
            else if (ae.getSource() == btnShowStaff)
            {
            	showStaffList("staff");
            } 
            else if (ae.getSource() == btnShowCus) {
            	showStaffList("customer");
            }
            else if (ae.getSource() == btnShowAll) {
            	showStaffList("all");
            }
        }
    }
    
    /****************************************************************
     * Edit employee panel
    *****************************************************************/       
    private class EditMemberPanel extends JPanel implements ActionListener
    {
    	
    	private JLabel          fName;
        private JLabel          lName;       
        private JTextField      fNameF;
        private JTextField      lNameF;
        private JLabel          email;
        private JTextField      emailF;
        private JLabel          phone;
        private JTextField      phoneF;
        private JLabel			bDateD;
        private JLabel          bDate;
        private JTextField      bDateF;
        private JLabel          pwd;
        private JPasswordField  pwdF;
        private JLabel          bdate;
        private JTextField      bdateF;
        private JLabel			addressD;
        private JLabel          address;
        private JTextField      addressF;
        private JLabel			roleD;
        private JTextField		roleF;
        private JButton         addBtn;
        private JButton         updateBtn;
        private JButton         cancleBtn;
        
        private User member;
        
        public EditMemberPanel() {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            fName = new JLabel("First Name:");
            fName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(fName, gbc);
            this.add(fName);          
            fNameF = new JTextField(10);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(fNameF, gbc);
            this.add(fNameF);
            
            lName = new JLabel("Last Name:");
            lName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lName, gbc);
            this.add(lName);           
            lNameF = new JTextField(10); 
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(lNameF, gbc);
            this.add(lNameF);
            
            
            email = new JLabel("Email:");
            email.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(email, gbc);
            this.add(email);        
            emailF = new JTextField(20);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(emailF, gbc);
            this.add(emailF);
            
            phone = new JLabel("Phone Number:");
            
            phone.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(phone, gbc);
            this.add(phone);        
            phoneF = new JTextField(20);
            //phoneF.setInputVerifier(new IntegerInputVerifier(1,99999999));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(phoneF, gbc);
            this.add(phoneF);
            
            bDateD = new JLabel("MM-dd-yyyy");
            bDateD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDateD, gbc);
            this.add(bDateD);
            bDate = new JLabel("Birth Day:");
            bDate.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(bDate, gbc);
            this.add(bDate);        
            bDateF = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbLayout.setConstraints(bDateF, gbc);
            this.add(bDateF);
            
            pwd = new JLabel("Password:");
            pwd.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(pwd, gbc);
            this.add(pwd);           
            pwdF = new JPasswordField(10);
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbLayout.setConstraints(pwdF, gbc);
            this.add(pwdF);
            
            addressD = new JLabel("Line, city, province, country, gender, intro");
            addressD.setPreferredSize(new Dimension(250, 30));
            gbc.gridx = 1;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(addressD, gbc);
            this.add(addressD); 
            address = new JLabel("Address&Ohter:");
            address.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(address, gbc);
            this.add(address);         
            addressF = new JTextField(40);
            gbc.gridx = 1;
            gbc.gridy = 8;
            gbLayout.setConstraints(addressF, gbc);
            this.add(addressF);
            
            roleD = new JLabel("Role: ");
            roleD.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(roleD, gbc);
            this.add(roleD);
            roleF = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbLayout.setConstraints(roleF, gbc);
            this.add(roleF);
            
            addBtn = new JButton("Add");
            addBtn.addActionListener(this);
          gbc.gridx = 0;
          gbc.gridy = 10;
          gbc.gridwidth = 3;
          gbLayout.setConstraints(addBtn, gbc);
          this.add(addBtn);
          
          updateBtn = new JButton("Update");
          updateBtn.addActionListener(this);
          gbc.gridx = 1;
          gbc.gridy = 10;
          gbc.gridwidth = 3;
          gbLayout.setConstraints(updateBtn, gbc);
          this.add(updateBtn);
                   
          cancleBtn = new JButton("Cancle");
          cancleBtn.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbLayout.setConstraints(cancleBtn, gbc);
        this.add(cancleBtn);

        }
        
		public void init(int id) {
			if (id == 0) {
				fNameF.setText("Jone");
	            fNameF.setEditable(true);
	            
	            lNameF.setText("Smith");
	            lNameF.setEditable(true);
	            
	            Integer randomNum = new Random().nextInt(89999999) + 10000000;
	            
	            emailF.setText("Jone.Smith"+randomNum+"@email.usa");
	            emailF.setEditable(true);
	            
	           
	            
	            phoneF.setText(""+randomNum);
	            phoneF.setEditable(true); 
	 
	            bDateF.setText("11-11-1999");
	            bDateF.setEditable(true); 
	            
	           // 
	            roleD.setText("Role: ");
	            roleF.setText("customer");
	            
	            pwdF.setText("123456");
	            pwdF.setEditable(true);
	            
	            addressF.setText("1087 Maneland Ave W"+", "
	            		+"St. Paul"+", "+ "MN"+", "+"USA"
	            		+", "+"Female"+", "+"I am a customer!"); 
	            addressF.setEditable(true);
	            addBtn.setEnabled(true);
	            addBtn.setText("Add");
	            addBtn.setVisible(true);
	            updateBtn.setEnabled(false);
	            updateBtn.setVisible(false);
	            cancleBtn.setEnabled(true);
			} else {
			member = db.getUserById(id);
			if(member == null) {
				return;
			}
            fNameF.setText(member.getfName());
            fNameF.setEditable(true);
            
            lNameF.setText(member.getlName());
            lNameF.setEditable(true);
            
            emailF.setText(member.getEmail());
            emailF.setEditable(true);
            
            phoneF.setText(""+member.getPhone());
            phoneF.setEditable(true); 
 
            bDateF.setText(new SimpleDateFormat("MM-dd-yyyy").format(member.getBdate()));
            bDateF.setEditable(true); 
            
           // 
            roleD.setText("Role: ");
            roleF.setText(member.getUser_role());
            
            pwdF.setText(member.getPwd());
            pwdF.setEditable(true);
            
            addressF.setText(member.getLine()+", "
            		+member.getCity()+", "+ member.getProvince()+", "+member.getCountry()
            		+", "+member.getGender()+", "+member.getIntro()); 
            addressF.setEditable(false);
            addBtn.setEnabled(false);
            addBtn.setVisible(false);
            updateBtn.setEnabled(true);
            updateBtn.setVisible(true);
            cancleBtn.setEnabled(true);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 if (e.getSource() == addBtn) {
				 
				 Date ddate = null;
				 try {
					ddate = new SimpleDateFormat("MM-dd-yyyy").parse(bDateF.getText().trim());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			String[] infos = new String[7];
				infos = addressF.getText().trim().split(", ");

				 
				 User user = new User(0, fNameF.getText().trim(),lNameF.getText().trim(), 
						 phoneF.getText().trim(),
						  emailF.getText().trim(), pwdF.getText().trim(), roleF.getText().trim(),
						 infos[0].trim(), infos[1].trim(), infos[2].trim(), infos[3].trim(), 
						 new Date(), infos[5].trim(), ddate, infos[4].trim(), 0);
						 
				 UserDao userDao = new UserDao();
				 if(userDao.insert(user)) {

			                displayErrorMessage("Add Member Success!");
			                changeMainPanel("EmployeeList");
				            cEmployeeListPanel.init();


					 
				 } else {
					 displayErrorMessage("Add Member Fail!");
				 }
        
			 } else if (e.getSource() == updateBtn) {
				 Date ddate = null;
				 try {
					ddate = new SimpleDateFormat("MM-dd-yyyy").parse(bDateF.getText().trim());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			String[] infos = new String[7];
				infos = addressF.getText().trim().split(", ");

				 
				 User user = new User(member.getId(), fNameF.getText().trim(),lNameF.getText().trim(), 
						 phoneF.getText().trim(),
						  emailF.getText().trim(), pwdF.getText().trim(), roleF.getText().trim(),
						 infos[0].trim(), infos[1].trim(), infos[2].trim(), infos[3].trim(), 
						 member.getRegisteredAt(), infos[5].trim(), ddate, infos[4].trim(), 0);
						 
				 UserDao userDao = new UserDao();
				 if(userDao.update(user)) {

			                displayErrorMessage("Add Member Success!");
			                changeMainPanel("EmployeeList");
				            cEmployeeListPanel.init();


					 
				 } else {
					 displayErrorMessage("Add Member Fail!");
				 }
		    
			 }
			 
			 else if  (e.getSource() == cancleBtn) {
				// init();
				 changeMainPanel("EmployeeList");
		            cEmployeeListPanel.init();
			 }
		}
    	
    	
    }
    
    /****************************************************************
     * OrderOld list panel
    *****************************************************************/       
    private class OrderListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        //private JTextArea       displayArea;
        private JPanel          btnPanel;
        private JButton         btnEditOrder;
        private JButton         btnCloseOrder;
        private JButton         btnCancelOrder;
        private JButton         btnDeleteOrder;
        private JButton         btnShowCheckOrder;
        private JButton         btnShowAllOrder;
        
        private JLabel          lblTotalSales;
        private JLabel          lblTotalCount;
        private JLabel          lblCancelTotal;
        private JLabel          lblCancelCount;
        private JList           displayList;

        private TableQueryDao		tableDao;
        List<LinkedHashMap> table;
        
        public OrderListPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            /*displayArea = new JTextArea();
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));*/
            scrollPanel = new JScrollPane();
            scrollPanel.setPreferredSize(new Dimension(730, 470));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 8;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            
            btnEditOrder    = new JButton("Edit Order");
            btnEditOrder.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
              gbc.gridwidth = 1;
              gbc.weightx = 0.25;
           gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnEditOrder, gbc);
            this.add(btnEditOrder);
            
            btnCloseOrder   = new JButton("Close Order");
            btnCloseOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnCloseOrder, gbc);
            this.add(btnCloseOrder);
            
            btnCancelOrder  = new JButton("Cancel Order");
            btnCancelOrder.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnCancelOrder, gbc);
            this.add(btnCancelOrder);
            
            btnDeleteOrder     = new JButton("Delete Order");
            btnDeleteOrder.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 1;           
            gbLayout.setConstraints(btnDeleteOrder, gbc);
            this.add(btnDeleteOrder);
            
            btnShowCheckOrder     = new JButton("Checkout Statue");
            btnShowCheckOrder.addActionListener(this);
            gbc.gridx = 5;
            gbc.gridy = 1;           
            gbLayout.setConstraints(btnShowCheckOrder, gbc);
            this.add(btnShowCheckOrder);
            
            btnShowAllOrder     = new JButton("All Status");
            btnShowAllOrder.addActionListener(this);
            gbc.gridx = 6;
            gbc.gridy = 1;           
            gbLayout.setConstraints(btnShowAllOrder, gbc);
            this.add(btnShowAllOrder);
            
            displayList = new JList();
        }
        
        private void setTotalCount( int count)
        {
            lblTotalCount.setText("Today's orderOld: " + count);
        }
        
        private void setTotalSales( double sales)
        {
            lblTotalSales.setText("Total:$ " + sales);
        }
        
        private void setCancelCount( int count)
        {
            lblCancelCount.setText("Canceled orders: " + count);
        }
        
        private void setCancelTotal( double sales)
        {
            lblCancelTotal.setText("Cancel total:$ " + sales);
        }
        
        private void showOrderList()
        {
        	int userId = db.getUser().getId();
        	tableDao = new TableQueryDao();
        	if(db.getUser().getUser_role().equalsIgnoreCase("admin")) {
        		table = tableDao.getTableByQuery("select o.id, "
            			+ "concat(u.fName,' ', u.lName) name, u.email, o.shippingFee, "
            			+ "o.discount, o.grandTotal, o.status from `order` o join user u on o.userId = u.id;");
        	} else {
        		table = tableDao.getTableByQuery("select o.id, "
            			+ "concat(u.fName,' ', u.lName) name, u.email, o.shippingFee, "
            			+ "o.discount, o.grandTotal, o.status from `order` o "
            			+ "join user u on o.userId = u.id where o.userId = "+ userId +";");
        	}
        	
        	
    		
        	List<String> list = new ArrayList<String>();
        	if(table.isEmpty()) {
        		list.add("");
        	} else {
        		for(LinkedHashMap row : table) {
        			String str = String.format("ID:%5d   Total:%5.2f  State:%-10s  Name:%-20s   Email:%-50s "
        					+ "  Shipping Fee:%5.2f   Discount:%5.2f",row.get("id"), row.get("grandTotal"),
                            row.get("status"), row.get("name"), row.get("email"),
                            row.get("shippingFee"), row.get("discount"));
        			list.add(str);
        		}
        	}
        	
            displayList.setListData(list.toArray());
            scrollPanel.getViewport().setView(displayList);

            
        }
        
        public void showOrderByStatus(String status) {
        	int userId = db.getUser().getId();
        	tableDao = new TableQueryDao();
        	if(db.getUser().getUser_role().equalsIgnoreCase("admin")) {
        		table = tableDao.getTableByQuery("select o.id, "
            			+ "concat(u.fName,' ', u.lName) name, u.email, o.shippingFee, "
            			+ "o.discount, o.grandTotal, o.status from `order` o join user u on o.userId = u.id where o.status='"+status+"';");
        	} else {
        		table = tableDao.getTableByQuery("select o.id, "
            			+ "concat(u.fName,' ', u.lName) name, u.email, o.shippingFee, "
            			+ "o.discount, o.grandTotal, o.status from `order` o "
            			+ "join user u on o.userId = u.id where o.userId = "+ userId +" and o.status='"+status+"';");
        	}
        	
        	
    		
        	List<String> list = new ArrayList<String>();
        	if(table.isEmpty()) {
        		list.add("");
        	} else {
        		for(LinkedHashMap row : table) {
        			String str = String.format("ID:%5d   Total:%5.2f  State:%-10s  Name:%-20s   Email:%-50s "
        					+ "  Shipping Fee:%5.2f   Discount:%5.2f",row.get("id"), row.get("grandTotal"),
                            row.get("status"), row.get("name"), row.get("email"),
                            row.get("shippingFee"), row.get("discount"));
        			list.add(str);
        		}
        	}
        	
            displayList.setListData(list.toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        public void init()
        {
            showOrderList();
        }
        
        private int getSelectedOrderID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
               
            return getIDfromString( orderLine, 6);

        }
                
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnEditOrder)
            {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
               //  || 
                OrderDao dao = new OrderDao();
                Order order = dao.selectById(orderID);
                if(order.getStatus().toLowerCase().equals("cancled")) {
                	showConfirmDialog("Order", "Order ID: "+orderID+" already cancled!");
                	 return;
                }
                ((CardLayout) mainPanel.getLayout()).show( mainPanel, "OrderDetail");

                cOrderDetailPanel.setCurrentOrderID(orderID);
                cOrderDetailPanel.init();
            }
            else if (ae.getSource() == btnCloseOrder)
            {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
                OrderDao dao = new OrderDao();
                Order order = dao.selectById(orderID);
                if(order.getStatus().equalsIgnoreCase("closed") || order.getStatus().equalsIgnoreCase("cancled")) return;
                if( showYesNoDialog("Close orderOld","Are you sure to close the orderOld?") == DIALOG_YES)
                {
                	TableQueryDao tableDao = new TableQueryDao();
                    if( !tableDao.changeTableByQuery("update `order` set status = 'closed' where id = " + orderID + ";"))
                        displayErrorMessage("Updated Your Order's Status!");
                    showOrderList();
                } 
            }
            else if (ae.getSource() == btnCancelOrder)
            {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
                OrderDao dao = new OrderDao();
                Order order = dao.selectById(orderID);
                if(order.getStatus().equalsIgnoreCase("closed") || order.getStatus().equalsIgnoreCase("cancled")) return;
                
                if( showYesNoDialog("Close orderOld","Are you sure to Cancle the orderOld?") == DIALOG_YES)
                {
                	TableQueryDao tableDao = new TableQueryDao();
                	OrderDao orderDao = new OrderDao();
                    if(!tableDao.changeTableByQuery("update `order` set status = 'cancled' where id = " + orderID + ";"))
                        displayErrorMessage("Canceled the Order!");
                    showOrderList();
                }
            }
            else if(ae.getSource() == btnDeleteOrder) {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
                OrderDao dao = new OrderDao();
                TableQueryDao tableDao = new TableQueryDao();
                Order order = dao.selectById(orderID);
                if(order.getStatus().equalsIgnoreCase("closed")) return;
                
                if( showYesNoDialog("Delete order","Are you sure to Delete the order?") == DIALOG_YES)
                {
                	boolean oib = tableDao.changeTableByQuery("delete from order_item where orderId="+order.getId()+";");
                	boolean ob = dao.deleteById(orderID);
                    if(!( oib&&ob))
                        displayErrorMessage("Delete the Order!");
                    showOrderList();
                }
            }
            else if(ae.getSource() == btnShowAllOrder) {
                changeMainPanel("OrderList");
                cOrderListPanel.init();
            }
            else if(ae.getSource() == btnShowCheckOrder) {
            	changeMainPanel("OrderList");
            	cOrderListPanel.showOrderByStatus("checkout");
            }
        }
    
    }
    
    /****************************************************************
     * OrderOld detail panel
    *****************************************************************/       
    private class OrderDetailPanel extends JPanel implements ActionListener, ListSelectionListener
    {
        //Right
        private JLabel          lblRightTitle;
       
        private JScrollPane     menuScrollPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
        private JButton         btnAlcohol;
        private JButton         btnDessert;
        
        //Left
        private JLabel          lbOrderId;
        private JLabel          lblLeftInfo;
        private JScrollPane     orderScrollPanel;
        //private JTextArea       displayArea;
        private JPanel          btnPanel;
        private JButton         btnCreateOrder;
        private JButton         btnAddItem;
        private JButton         btnDeleteItem;
        private JButton         btnCheckout;
        
        
        private JLabel          lblQuantity;
        private JTextField      tfQuantity;
        
        private JLabel              lblTotalSales;
        private JLabel              lblOrderState;
        private JLabel              lblStaffName;
        private JList               orderItemList;
        private JList               menuList;
        
        private int             currentOrderID;


		private int             orderItemCnt;
        private int             currentOrderState;
        
        private JPanel          orderDetailPanel;
        private JPanel          menuListPanel;
        
        
        public OrderDetailPanel()
        {
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            
            orderDetailPanel = new JPanel();
            
            GridBagLayout gbLayout = new GridBagLayout();
            orderDetailPanel.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lbOrderId = new JLabel("ID: ");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbLayout.setConstraints(lbOrderId, gbc);
            orderDetailPanel.add(lbOrderId);
            

            
            btnCreateOrder = new JButton("Create Order");
            btnCreateOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbLayout.setConstraints(btnCreateOrder, gbc);
            orderDetailPanel.add(btnCreateOrder);
            
            orderScrollPanel = new JScrollPane();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 6;
            gbc.gridheight = 1;
          orderScrollPanel.setPreferredSize(new Dimension(200, 300));
            gbLayout.setConstraints(orderScrollPanel, gbc);
            orderDetailPanel.add(orderScrollPanel);
            
            lblTotalSales = new JLabel("Total:      ");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weighty = 0;
            gbLayout.setConstraints(lblTotalSales, gbc);
            orderDetailPanel.add(lblTotalSales);
            
            lblOrderState = new JLabel("Status:       ");
            gbc.gridx = 3;
            gbc.gridy = 5;
            gbLayout.setConstraints(lblOrderState, gbc);
            orderDetailPanel.add(lblOrderState);
                        
            lblQuantity = new JLabel("Quantity");
            gbc.ipadx = 20;
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblQuantity, gbc);
            orderDetailPanel.add(lblQuantity);
            
            tfQuantity = new JTextField();
            tfQuantity.setInputVerifier(new IntegerInputVerifier(1,100));
            tfQuantity.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbLayout.setConstraints(tfQuantity, gbc);
            orderDetailPanel.add(tfQuantity);
            
            btnAddItem  = new JButton("Add");
            btnAddItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbLayout.setConstraints(btnAddItem, gbc);
            orderDetailPanel.add(btnAddItem);
            
            btnDeleteItem   = new JButton("Delete");
            btnDeleteItem.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 6;
            gbLayout.setConstraints(btnDeleteItem, gbc);
            orderDetailPanel.add(btnDeleteItem);
            
            btnCheckout   = new JButton("Checkout");
            btnCheckout.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 7;
            gbLayout.setConstraints(btnCheckout, gbc);
            orderDetailPanel.add(btnCheckout);
            
            
            
            //Right panel            
            menuListPanel = new JPanel();
            
            menuListPanel.setLayout( gbLayout);
            
            lblRightTitle = new JLabel("Menu list");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipadx = 0;
            gbc.gridwidth = 5;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(lblRightTitle, gbc);
            menuListPanel.add(lblRightTitle);
            
            menuScrollPanel = new JScrollPane();
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            
            gbLayout.setConstraints(menuScrollPanel, gbc);
            menuListPanel.add(menuScrollPanel);
            
            btnAll  = new JButton("All");
            btnAll.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(btnAll, gbc);
            menuListPanel.add(btnAll);
            
            btnMain  = new JButton("Main");
            btnMain.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnMain, gbc);
            menuListPanel.add(btnMain);
            
            btnDrink  = new JButton("Drink");
            btnDrink.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDrink, gbc);
            menuListPanel.add(btnDrink);
            
             btnAlcohol  = new JButton("Alcohol");
             btnAlcohol.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnAlcohol, gbc);
            menuListPanel.add(btnAlcohol);
            
            btnDessert  = new JButton("Dessert");
            btnDessert.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDessert, gbc);
            menuListPanel.add(btnDessert);
            
            LineBorder border = new LineBorder(Color.BLACK, 1, false);
            menuListPanel.setBorder(border);
            orderDetailPanel.setBorder(border);
            this.add(orderDetailPanel);
            this.add(menuListPanel);
            
            orderItemList   = new JList();
            orderItemList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            orderItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            menuList = new JList();
            menuList.addListSelectionListener(this);
            menuList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
       }
        
        public void init()
        {

			btnAddItem.setEnabled(false);
            btnDeleteItem.setEnabled(false);
            btnCheckout.setEnabled(false);
            lblQuantity.setEnabled(false);
            tfQuantity.setEnabled(false);

            
            refleshOrderDetailList();
            showMenuList("ALL");
            tfQuantity.setText("1");
            tfQuantity.setBackground( UIManager.getColor( "TextField.background" ) );

        }
        
        private void setTotal(double total)
        {
            lblTotalSales.setText("Total: $" + total);
        }
        
        private void setOrderState(String state)
        {
            lblOrderState.setText("state: " + state);
        }
        
        private void setStaffName(String name)
        {
            lblStaffName.setText("Staff name: " + name);
        }
        
        private void refleshOrderDetailList()
        {
        	OrderItemDao oiDao = new OrderItemDao();
        	List<OrderItem> orderItems = new ArrayList<OrderItem>();
        	ArrayList<String> list = new ArrayList<String>();
        	lbOrderId.setText("ID:"+this.currentOrderID);        
            btnAddItem.setEnabled(true);
            btnDeleteItem.setEnabled(true);
            btnCheckout.setEnabled(true);
            btnCreateOrder.setEnabled(true);
            lblQuantity.setEnabled(true);
            tfQuantity.setEnabled(true);
            
        	if(this.currentOrderID==0) {
        		lbOrderId.setText("");
        		btnAddItem.setEnabled(false);
                btnDeleteItem.setEnabled(false);
                btnCheckout.setEnabled(false);
                lblQuantity.setEnabled(false);
                tfQuantity.setEnabled(false);
                btnCreateOrder.setEnabled(true);
                lblTotalSales.setText("Total:");
                lblOrderState.setText("state:");
        		list.clear();
        		orderItemList.setListData(list.toArray());

                orderScrollPanel.getViewport().setView(orderItemList);
                
                return;
        	}
        	orderItems =  oiDao.selectByQuery("select * from order_item where orderId="+this.currentOrderID+";");
        	OrderDao orderDao = new OrderDao();
        	Order order = orderDao.selectById(this.currentOrderID);
        	if(order != null) {
        		if(order.getStatus().equalsIgnoreCase("closed")) {
        			btnAddItem.setEnabled(false);
                    btnDeleteItem.setEnabled(false);
                    btnCheckout.setEnabled(false);
                    lblQuantity.setEnabled(false);
                    tfQuantity.setEnabled(false);
                    btnCreateOrder.setEnabled(false);

                    
        		} else {
        			btnCreateOrder.setEnabled(false);
        		}

        	}
            setTotal(order.getSubTotal());
            setOrderState(order.getStatus());
        	
        	List<LinkedHashMap> itemList = new ArrayList<LinkedHashMap>();
        	TableQueryDao tableDao = new TableQueryDao();
        	itemList = tableDao.getTableByQuery("select oi.itemId, i.title, oi.quantity, i.price from item i join order_item oi on i.id = oi.itemId where oi.orderId = "+this.currentOrderID+";");
        	
        	if (itemList.isEmpty()) {
        		
        		orderItemList.setListData(list.toArray());

                orderScrollPanel.getViewport().setView(orderItemList);
                return;
        	}

        	for(LinkedHashMap oi: itemList) {
        		String str = String.format("ItemID:%3d Quantity:%3.1f Price:%3.1f Name:%-20s ",oi.get("itemId"), oi.get("quantity"),oi.get("price"),oi.get("title"));
        		list.add(str);
        		orderItemList.setListData(list.toArray());
        		orderScrollPanel.getViewport().setView(orderItemList);
        	}

            orderItemCnt = list.size();
            

        }
        
        private LinkedHashMap getOrderDetailIndexFromString(String orderLine)
        {
            try
            {
                String strIndex = orderLine.substring(7, 10);
                String strQuant = orderLine.substring(20, 24);
                int id = Integer.parseInt(strIndex.trim());
                double quant = Double.parseDouble(strQuant.trim());
                LinkedHashMap lm = new LinkedHashMap();
                lm.put("id", id);
                lm.put("quant", quant);
                return lm;
            }
            catch(Exception e)
            {
                //showErrorDialog("Error", "Parse error");
                return null;
            }

        }
 
        private void showMenuList(String menuType)
        {
        	ItemDao itemDao = new ItemDao();

        	if(menuType.equals("ALL")) {
        	List<String> list = new ArrayList<String>();
        	for(Item i : itemDao.selectAll()) {
        		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                      i.getId(),i.getTitle(), i.getPrice(),i.getType()));
        	}
        	menuList.setListData(list.toArray());
        	menuScrollPanel.getViewport().setView(menuList);
        	} else if (menuType.equals("Main")) {
        		List<String> list = new ArrayList<String>();
            	for(Item i : itemDao.selectByQuery("select * from item where type = 'Pizza' or type = 'Burgers' or type = 'Sandwiches' or type = 'Salads' or type = 'Main';")) {
            		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                          i.getId(),i.getTitle(), i.getPrice(),i.getType()));
            	}
            	menuList.setListData(list.toArray());
            	menuScrollPanel.getViewport().setView(menuList);
        	} else if (menuType.equals("Drink") || menuType.equals("Alcohol") || menuType.equals("Dessert") ) {
        		List<String> list = new ArrayList<String>();
            	for(Item i : itemDao.selectByQuery("select * from item where type =" + "'" +menuType+"';")) {
            		list.add(String.format("ID:%4d  Name:%-45s  Price:%5.2f Type:%s",
                          i.getId(),i.getTitle(), i.getPrice(),i.getType()));
            	}
            	menuList.setListData(list.toArray());
            	menuScrollPanel.getViewport().setView(menuList);
        	}
        }
        
        public void actionPerformed(ActionEvent ae) {
        	if(ae.getSource() == btnCreateOrder) {
    			btnCreateOrder.setEnabled(false);
    			lbOrderId.setVisible(true);
        		String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        		TableQueryDao dao = new TableQueryDao();
        		int id = dao.insertGetId("insert into `order`(userId, status, tax, shippingFee, createdAt) value("+db.getUser().getId() +", 'new', " 
        		+ "0.07, "+ "8, '" + time+"');");
    			this.currentOrderID = id;
    			lbOrderId.setText("ID: "+this.currentOrderID);
                refleshOrderDetailList();
        		

        	}
        	else if (ae.getSource() == btnAddItem)
            {
                if (btnAddItem.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnAddItem.requestFocusInWindow();
                    if (!btnAddItem.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
                
                String menuLine = (String)menuList.getSelectedValue();
                if (menuLine == null)
                    return;

                int     itemId = getIDfromString( menuLine, 4);
                if(itemId == -1)
                    return;
                if( tfQuantity.getText().equals(""))
                {
                    showErrorDialog("Error", "Enter quantity!!");
                    return;
                }
                int quant = Integer.parseInt(tfQuantity.getText().trim());
                OrderItemDao oiDao = new OrderItemDao();
                OrderItem oi = new OrderItem();
                oi.setOrderId(this.currentOrderID);
                oi.setItemId(itemId);
                oi.setQuantity(quant);
                oi.setCreatedAt(new Date());
                if(oiDao.insert(oi)){
                	refleshOrderDetailList();
                }

                
            }
            else if (ae.getSource() == btnDeleteItem)
            {
                String orderLine = (String)orderItemList.getSelectedValue();
                if(orderLine == null)
                    return;
                    
                LinkedHashMap idQaunt = getOrderDetailIndexFromString(orderLine);
                if(idQaunt == null)
                    return;

                TableQueryDao dao = new TableQueryDao();
                boolean b = dao.changeTableByQuery("delete from order_item where orderId="+ this.currentOrderID+" and itemId="+idQaunt.get("id") + " and quantity="+idQaunt.get("quant")+";");
                if(!b) {
                	displayErrorMessage("deleteOrderItem Error!!\n" + rcController.getErrorMessage());
                	return;
                }
                refleshOrderDetailList();

            }
            else if (ae.getSource() == btnCheckout) {
            	int orderId = this.currentOrderID;
            	OrderDao orderDao = new OrderDao();
            	Order order = orderDao.selectById(orderId);
            	Transaction tran = new Transaction();
            	tran.setUserId(order.getUserId());
            	tran.setOrderId(orderId);
            	tran.setCode("XXX-XX");
            	tran.setType("Credit");
            	tran.setMode("Offline");
            	tran.setCreatedAt(new Date());
            	tran.setStatus("Processing");
            	TransactionDao tranDao = new TransactionDao();
            	int tranId = tranDao.insertAndGetId(tran);
            	if(tranId == -1)return;
            	
            	changeMainPanel("TransactionDetailPanel");
            	cTransactionDetailPanel.init(tranId);
            }
             else if (ae.getSource() == btnAll)
            {
            	 showMenuList("ALL");

            }
             else if (ae.getSource() == btnMain)
            {
            	 showMenuList("Main");

            }
             else if (ae.getSource() == btnDrink)
            {
            	 showMenuList("Drink");

            }
             else if (ae.getSource() == btnAlcohol)
            {
            	 showMenuList("Alcohol");

            }
             else if (ae.getSource() == btnDessert)
            {
            	 showMenuList("Dessert");

            }
        }
        
        public void valueChanged( ListSelectionEvent e ) {
            if( e.getValueIsAdjusting() == true ){  //when mouce click happens
                if( e.getSource() == menuList ){
                     tfQuantity.setText("1");
                }
            }
        }
        /**
		 * @return the currentOrderID
		 */
		public int getCurrentOrderID() {
			return currentOrderID;
		}

		/**
		 * @param currentOrderID the currentOrderID to set
		 */
		public void setCurrentOrderID(int currentOrderID) {
			this.currentOrderID = currentOrderID;
		}
    
    }
    
    /****************************************************************
     * Transaction panel
    *****************************************************************/   
    private class TansactionPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnShowDetail;
        private JButton         btnDelete;
        private JPanel			 btnPanel;
        
        
        public TansactionPanel()
        {

            btnPanel = new JPanel();
            btnPanel.setLayout( new FlowLayout());
            this.setLayout( new BorderLayout());

            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPanel = new JScrollPane(displayList);
            scrollPanel.setPreferredSize(new Dimension(200, 400));
            add(scrollPanel, BorderLayout.CENTER);

            
            btnShowDetail    = new JButton("Show Detial");
            btnShowDetail.addActionListener(this);

            btnPanel.add(btnShowDetail);
            
            btnDelete    = new JButton("Delete");
            btnDelete.addActionListener(this);
            btnPanel.add(btnDelete);
      
            this.add(btnPanel, BorderLayout.SOUTH);
        }
        
      
        private void showOrderList()
        {
        	TransactionDao tranDao = new TransactionDao();
        	List<Transaction> trans; 
        	List<String> list = new ArrayList<String>();
        	int userid = db.getUser().getId();
        	if( db.getUser().getUser_role().equalsIgnoreCase("admin")) {
        		trans = tranDao.selectAll();
        		
        	} else {
        		trans = tranDao.selectByQuery("select * from transaction where userId="+userid+";");
        		
        	}
        	if (trans.isEmpty()) {
        		list.add("No Transaction Yet!");
        		return;
        	}
        	for(Transaction tran : trans) {
    			list.add(String.format("ID:%4d  User ID:%4d Order ID:%4d  Status:%-20s Created At:%-30s Code:%-10s Type:%-10s" ,
                      tran.getId(), tran.getUserId(), tran.getOrderId(), tran.getStatus(), tran.getCreatedAt().toString(), tran.getCode(), tran.getType()));
    		}
            displayList.setListData(list.toArray());
            scrollPanel.getViewport().setView(displayList);

        }
        
        public void init()
        {
            showOrderList();
        }
        
        private int getSelectedOrderID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
               
            return getIDfromString( orderLine, 6);

        }
          
        
        public void actionPerformed(ActionEvent ae) {
        	int tranId = getSelectedOrderID();
        	
            if (ae.getSource() == btnShowDetail)
            {
            	changeMainPanel("TransactionDetailPanel");
            	//cOrderListPanel.showOrderByStatus("checkout");
            	cTransactionDetailPanel.init(tranId);
            }
            else if (ae.getSource()==btnDelete) {
            	if( showYesNoDialog("Delete Transaction","Are you sure to delete it?") == DIALOG_YES) {
                	TransactionDao tranDao = new TransactionDao();
                	if(tranDao.deleteById(tranId)) {
                		displayMessage("The Transaction have been delete.");
                		showOrderList();
                	}
            	}
            	return;

            }
        }
    }
    
   
    private class TransactionDetailPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JButton         btnPrint;
        private JButton         btnPayClose;
        private JButton         btnCancleOrder;
        private int currTranId;
        
        public TransactionDetailPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            displayArea = new JTextArea();
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPanel = new JScrollPane(displayArea);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnPrint = new JButton("Print");
            btnPrint.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnPrint, gbc);
            this.add(btnPrint);
            
            btnPayClose = new JButton("Pay&Close Order");
            btnPayClose.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnPayClose, gbc);
            this.add(btnPayClose);
            
            btnCancleOrder = new JButton("Cancle Order");
            btnCancleOrder.setEnabled(false);
            btnCancleOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnCancleOrder, gbc);
            this.add(btnCancleOrder);
        }
        

        
        public void init(int tranId)
        {
        	this.currTranId = tranId;
        	TransactionDao tranDao = new TransactionDao();
        	Transaction tran = tranDao.selectById(tranId);
        	if(tran.getStatus().equalsIgnoreCase("processing")) {
        		btnCancleOrder.setEnabled(true);
        		btnPayClose.setEnabled(true);
        	} else {
        		btnCancleOrder.setEnabled(false);
        		btnPayClose.setEnabled(false);
        	}
        	UserDao userDao = new UserDao();
        	OrderDao orderDao = new OrderDao();
        	Order order = orderDao.selectById(tran.getOrderId());
        	User user = userDao.selectById(tran.getUserId());
        	String str = String.format("Transaction ID:%5d\nCustomer Name: %-20s\nEmail:%-50s\nOrder "
        			+ "ID:%4d\nTotal Food Cost:$%5.2f\nTax:$%2.2f\nShipping Free:$%2.2f\nDiscount:$%2.2f\nTotal Payment:$%5.2f"
        			+ "\nType:%8s\nCode:%7s\nMode: %10s\nStatus:%15s\nDate:%15s\nCustomer Address:%30s",
        			
        			tranId, user.getfName()+" "+user.getlName(), user.getEmail(), order.getId(), order.getSubTotal(),order.getTax(),order.getShippingFee(),
        			order.getDiscount(),order.getGrandTotal(),tran.getType() ,tran.getCode(),tran.getMode(), tran.getStatus(),tran.getCreatedAt().toString(),user.getLine()+" "+user.getCity());
        	  
        	displayArea.setText(str);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnPrint)
            {

            	String createFineName = printTotextfile(this.currTranId);
                if( createFineName == null)
                    displayErrorMessage(rcController.getErrorMessage());
                else {
                	showConfirmDialog("Print", "The Transaction Text file, ID:"+this.currTranId+" have been generated!");
                	displayMessage(createFineName + " have been generated.");
                }
                    
            }
            else if (ae.getSource() == btnPayClose)
            {

            		OrderDao orderDao = new OrderDao();
                	Transaction tran = new Transaction();
                	TransactionDao tranDao = new TransactionDao();
                	tran = tranDao.selectById(this.currTranId);
                	if(tran==null)return;
                	Order order = new Order();
                	order = orderDao.selectById(tran.getOrderId());
                	if(order==null)return;
                	order.setStatus("closed");
                	if(orderDao.update(order)) {
                		tran.setStatus("Success");
                		if(tranDao.update(tran)) {
                			showConfirmDialog("success", "Your Payment Sucessfull, ID:"+this.currTranId);
                			changeMainPanel("TansactionPanel");
                            cTransactionPanel.init();
                		}
                	}
            	
            	return;
            }
            else if (ae.getSource()==btnCancleOrder) {
            	if (showYesNoDialog("", "Are you sure to cancle the Order?") == DIALOG_YES) {
            		OrderDao orderDao = new OrderDao();
                	Transaction tran = new Transaction();
                	TransactionDao tranDao = new TransactionDao();
                	tran = tranDao.selectById(this.currTranId);
                	if(tran==null)return;
                	Order order = new Order();
                	order = orderDao.selectById(tran.getOrderId());
                	if(order==null)return;
                	order.setStatus("cancled");
                	if(orderDao.update(order)) {
                		tran.setStatus("Canclled");
                		if(tranDao.update(tran)) {
                			changeMainPanel("TansactionPanel");
                            cTransactionPanel.init();
                		}
                		
                	}
            	}
            	return;
            	
            }
           
        }



		public String printTotextfile(int tranId) {
			// TODO Auto-generated method stub
			Writer          writer = null;
		       String          line;
		       String          fileName;
		       File            newFile;
		       
		       TransactionDao tranDao = new TransactionDao();
	        	Transaction tran = tranDao.selectById(tranId);
	        	UserDao userDao = new UserDao();
	        	OrderDao orderDao = new OrderDao();
	        	Order order = orderDao.selectById(tran.getOrderId());
	        	User user = userDao.selectById(tran.getUserId());
	        	line = String.format("Transaction ID:%5d\nCustomer Name: %-20s\nEmail:%-50s\nOrder "
	        			+ "ID:%4d\nTotal Food Cost:$%5.2f\nTax:$%2.2f\nShipping Free:$%2.2f\nDiscount:$%2.2f\nTotal Payment:$%5.2f"
	        			+ "\nType:%8s\nCode:%7s\nMode: %10s\nStatus:%15s\nDate:%15s\nCustomer Address:%30s",
	        			
	        			tranId, user.getfName()+" "+user.getlName(), user.getEmail(), order.getId(), order.getSubTotal(),order.getTax(),order.getShippingFee(),
	        			order.getDiscount(),order.getGrandTotal(),tran.getType() ,tran.getCode(),tran.getMode(), tran.getStatus(),tran.getCreatedAt().toString(),user.getLine()+" "+user.getCity());
	        	  

	 	       fileName = "./PaymentRecord/ID_"+tranId+"_payment.txt";
		       newFile = new File(fileName);
		       newFile.getParentFile().mkdirs();
		       try {
				writer = new BufferedWriter(new FileWriter(newFile));
				
				writer.write(line);
		           
			} catch (Exception e) {
				
		           newFile.delete();
		           return null;
			} finally {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           
			}
		          changeMainPanel("TansactionPanel");
		          cTransactionPanel.init();  
			return fileName;
		}
    }
    /****************************************************************
     * Input validation
    *****************************************************************/
    
    private class IntegerInputVerifier extends InputVerifier{
        private int state = 0;  //0:no range check 1:min check 2:min and max check
        private int MAX = 0;
        private int MIN = 0;
        
        public IntegerInputVerifier()
        {
            super();
        }
        
        public IntegerInputVerifier(int min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public IntegerInputVerifier(int min, int max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                int number = Integer.parseInt(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            //UIManager.getLookAndFeel().provideErrorFeedback(c);
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum input is " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Only number is allowed.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }
    
    private class DoubleInputVerifier extends InputVerifier{
        private int state = 0;  //0:no range check 1:min check 2:min and max check
        private double MAX = 0;
        private double MIN = 0;
        
        public DoubleInputVerifier()
        {
            super();
        }
        
        public DoubleInputVerifier(double min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public DoubleInputVerifier(double min, double max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                double number = Double.parseDouble(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            //UIManager.getLookAndFeel().provideErrorFeedback(c);
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum input is " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Only number is allowed.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }

}
