/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Farhan
 */
public class LibarianSignUPPageController implements Initializable {
    
    
    private String user = "root";
    private String password = "306944";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;

    @FXML
    private TextField LSUNameField;
    @FXML
    private TextField LSUIDField;
  /*  @FXML
    private TextField LSUPasswordField;
    @FXML
    private TextField LSURepasswordField;*/
    @FXML
    private TextField LSUEmailField;
    @FXML
    private Label LSUStatusField;
    @FXML
    private PasswordField LSUPasswordField;
    @FXML
    private PasswordField LSURepasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void LSUCancelButtonAction(MouseEvent event) {
    }

    @FXML
    private void LSUSaveButtonAction(MouseEvent event) {
        LSUStatusField.setText("");
        if(isValid())
        {
            String Name =LSUNameField.getText();
            String ID = LSUIDField.getText();
            String Email = LSUEmailField.getText();
            String UserPassword=LSUPasswordField.getText();
           // int pin =Integer.parseInt(UserPassword);
            
            
            String  query = "SELECT * FROM libarianpassword";
            String query2 = "INSERT INTO libarianpassword VALUES ( '" + ID +"', '"+Name+"', '"+Email+"','"+UserPassword+"')" ;
            try{
                
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(query);
                while(resultset.next()){
                    String p2 = resultset.getString("Id");
                    if(p2.equals(ID)){
                        LSUStatusField.setText("User Name Already Exist");
                        return;
                    }
                    //LSUStatusField.setText(p2);
                    
                }
                statement.executeUpdate(query2);
                
                
                
            }catch(SQLException sqle){
                System.err.println(sqle);
            }
        }
    }
    
    
    
    boolean isValid(){
        if(LSUNameField.getText().equals("")){
            LSUStatusField.setText("Please Fill The name");
            return false ;
        }
        
        
        if(LSUIDField.getText().equals("")){
            LSUStatusField.setText("Please Fill The ID");
            return false ;
        }
        
        if(LSUPasswordField.getText().equals(""))
        {
            LSUStatusField.setText("Please Fill The name");
            return false ;
        }
        
        if(!LSURepasswordField.getText().equals(LSUPasswordField.getText()))
        {
            LSUStatusField.setText("Password does not match");
            return false ;
        }
        
        if(LSUEmailField.getText().equals(""))
        {
            LSUStatusField.setText("Please Fill The EmailField");
            return false ;
        }
        
        return true;
    }
    
    
    
    
}


