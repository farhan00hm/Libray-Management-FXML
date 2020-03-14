/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farhan
 */
public class LibarianPasswordController implements Initializable {
    
    
    private String user = "root";
    private String password = "306944";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;

    @FXML
    private Label libarianLoginStatus;
    @FXML
    private Button libarianBackButton;
    //private Button libarianBackButtonAction;
    @FXML
    private PasswordField libarianPassword;
    @FXML
    private TextField libarianId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void libarianBackButtonAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        Stage stage2 = (Stage) libarianBackButton.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void libarianSignInButtonAction(ActionEvent event) throws IOException {
        if(isValid()==true){
        
        String a = libarianId.getText();
        //int b = Integer.parseInt(libarianPassword.getText());
        String b=libarianPassword.getText();
        String  query = "SELECT * FROM libarianpassword";
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String c = resultSet.getString("Id");
                String d = resultSet.getString("Pin");
                if(a.equals(c)&& b.equals(d)){
                    libarianLoginStatus.setText("Password Is Correct");
                    libarianId.setText("");
                    libarianPassword.setText("");
                    
                    Parent root = FXMLLoader.load(getClass().getResource("LibarianAccess.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    break;
                }
                else{
                    libarianLoginStatus.setText("Password is Incorrect");
                    libarianId.setText("");
                    libarianPassword.setText("");
//                    return;
                }
                
                /*libarianId.setText("");
                libarianPassword.setText("");*/
            }
            
//        Parent root = FXMLLoader.load(getClass().getResource("LibarianAccess.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        
        Stage stage2 = (Stage) libarianBackButton.getScene().getWindow();
        stage2.close();
            
            
        }catch (SQLException sqle){
            System.err.println(sqle);
        }
        
        }
        
       
    }
    
    boolean isValid(){
        if(libarianId.getText().equals("")){
            libarianLoginStatus.setText("Id is Empty");
            return false;
        }
        //Here has a bug when we input in string form it can not convert to integer form
        if(libarianPassword.getText().equals("")){
            libarianLoginStatus.setText("Pin is Empty");
            return false;
        }
            
        return true;
    }

    @FXML
    private void LibrarianSignUpButtonAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LibarianSignUPPage.fxml"));
        
        Scene scene = new Scene(root);
    
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
}
