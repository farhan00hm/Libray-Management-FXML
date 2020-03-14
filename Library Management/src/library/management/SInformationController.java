/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Farhan
 */
public class SInformationController implements Initializable {
    
    
    private String user = "root";
    private String password = "306944";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;

    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField studentEmailField;
    @FXML
    private PasswordField studentPasswordField;
    @FXML
    private Label studentInformationStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        if(isValid()==true){
            String name = studentNameField.getText();
            String Id = studentIdField.getText();
            String email = studentEmailField.getText();
            int password2 = Integer.parseInt(studentPasswordField.getText());
            String query = "INSERT INTO studentpassword VALUES ( '"+Id+"','"+name+"','"+email+"',"+password2+");";
            try{
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                studentInformationStatus.setText("Information updated");
                
            }catch (SQLException sqle){
                System.err.println(sqle);
                
            }
        }
        
        else{
            studentInformationStatus.setText("Something is missing");
        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
    }
    
    
    boolean isValid(){
        if(studentNameField.getText().equals(""))
            return false;
        if(studentIdField.getText().equals(""))
            return false;
        if(studentEmailField.getText().equals(""))
            return false;
        if(studentPasswordField.getText().equals(""))
            return false;
        
        else
            return true;
        
    }
    
}
