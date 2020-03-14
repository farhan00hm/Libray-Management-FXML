/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Farhan
 */
public class FXMLDocumentController implements Initializable {
    
    private String user = "root";
    private String password = "";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;
    

    
    @FXML
    private Button libarianButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLibarianButtoAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LibarianPassword.fxml"));
        
        Scene scene = new Scene(root);
    
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
       
        Stage stage2 = (Stage) libarianButton.getScene().getWindow();
         stage2.close();
        
    }

    @FXML
    private void hadleStudentButtonAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("StudentPasswordController.fxml"));
        
        Scene scene = new Scene(root);
    
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        Stage stage2 = (Stage) libarianButton.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void handleAppDeveloperButtonAction(ActionEvent event) {
        Application a = new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        a.getHostServices().showDocument("https://www.facebook.com/farhan00.hm");
    }

    
    
}
