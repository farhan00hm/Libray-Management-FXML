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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farhan
 */
public class StudentPasswordController implements Initializable {
    
    private String user = "root";
    private String password = "306944";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;
    
    

    @FXML
    private AnchorPane studentPasswordWindow;
    @FXML
    private Label studentPasswordControllerStatus;
    @FXML
    private TextField studentId;
    @FXML
    private TextField studentPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void studentSignUpClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SInformation.fxml"));
        
        Scene scene = new Scene(root);
    
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        Stage stage2 = (Stage) studentPasswordWindow.getScene().getWindow();
        stage2.close();
        
        
    }

    @FXML
    private void studentBackButtonAction(ActionEvent event) {
    }

    @FXML
    private void studentSignInButtonAction(ActionEvent event) {
        String id = studentId.getText();
        int pin = Integer.parseInt(studentPassword.getText());
        String query = "SELECT *FROM studentpassword ;";
        try{
            
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while(resultset.next()){
                String id2 = resultset.getString("Id");
                int pin2 = resultset.getInt("Pin");
                System.out.println(id+ "->  "+pin);
                if(id.equals(id2) && pin==pin2){
                    studentPasswordControllerStatus.setText("Password is correct");
                    break;
                }
                
                else{
                    studentPasswordControllerStatus.setText("Password is wrong");
                }
            }
            
        }catch (SQLException sqle){
            System.err.println(sqle);
        }
        
    }

    

    
    
    
}
