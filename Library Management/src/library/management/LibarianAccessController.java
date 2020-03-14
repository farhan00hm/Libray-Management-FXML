/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import static java.lang.Math.random;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Farhan
 */
public class LibarianAccessController implements Initializable {
    
    private String user = "root";
    private String password = "306944";
    private String host = "127.0.0.1";
    private String  dbName = "library";
    private String url = "jdbc:mysql://"+host+"/"+dbName;

    @FXML
    private TextField ANBEditionField;
    @FXML
    private TextField ANBPriceField;
    @FXML
    private TextField ANBBookCodeField;
    @FXML
    private TextField ANBBookNameField;
    @FXML
    private TextArea ANBBookSourcesTextArea;
    @FXML
    private ComboBox<String> ANBDepartmentComboBox;
    private ObservableList <String> departmentList = FXCollections.observableArrayList() ;
    //private ArrayList <String> departmentList;
    @FXML
    private TextField BookLocationTextFieldArea;
    @FXML
    private Label ANBStatusLabel;
    private int Quantity;
    String Department;
    boolean department=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Random random =new Random();
        String bookId = String.format("%08d",random.nextInt(10000000));
        ANBBookCodeField.setText(bookId);
        departmentList.add("CSE");
        departmentList.add("BBA");
        departmentList.add("English");
        ANBDepartmentComboBox.setItems(departmentList);
        
    }    

    @FXML
    private void handleRefreshButtonAction(MouseEvent event) {
        Random random =new Random();
        String bookId = String.format("%08d",random.nextInt(10000000));
        ANBBookCodeField.setText(bookId);
    }

    @FXML
    private void ANBHandleClearButtonAction(ActionEvent event) {
        Clear();
    }
    
    

    @FXML
    private void handleDepartmentComboBoxAction(ActionEvent event) {
        Department = ANBDepartmentComboBox.getSelectionModel().getSelectedItem();
        department = true;
    }

    @FXML
    private void ANBHandleAddButtonAction(ActionEvent event) throws SQLException {
        if (isValid()){
            String Code = ANBBookCodeField.getText();
            String Name = ANBBookNameField.getText();
           // String Department = ANBDepartmentComboBox.getSelectionModel().getSelectedItem();
            String Edition = ANBEditionField.getText();
            String Price = ANBPriceField.getText();
            String Sources = ANBBookSourcesTextArea.getText();
            String Location = BookLocationTextFieldArea.getText();
            int Quantity2 = 0  ;
            String query1 = "Select *from bookinformation";
            
            boolean q = false;
            
            try{
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(query1);
                while(resultset.next()){
                    String Name2 = resultset.getString("Name");
                    if(Name.equals(Name2))
                    {
                        System.out.println(resultset.getInt("Quantity"));
                        Quantity2= resultset.getInt("Quantity");
                        q=true;
                    }
                }
                
                Quantity=Quantity2+1;
                
                if(q==false)
                {
                    Quantity = 1;
                }
                
                
                
                String query2 = "INSERT INTO bookinformation VALUES ( '"+Code+"', '"+Name+"','"+Department+"','"+
                    Edition+"','"+Price+"','"+Sources+"','"+Location+"',"+Quantity+")";
                
                statement.executeUpdate(query2);
                
            }catch(SQLException sqle)
            {
                System.err.println(sqle);
            }
            
           // Clear();
        }
        
        else
        {
            
        }
        
        Random random =new Random();
        String bookId = String.format("%08d",random.nextInt(10000000));
        ANBBookCodeField.setText(bookId);
        
        
    }
    
    
    boolean isValid()
    {
        if(ANBBookCodeField.getText().equals(""))
        {
            return false;
        }
        
        
        
        if(ANBBookNameField.getText().equals(""))
        {
            ANBStatusLabel.setText("Please Enter the Book Name");
            return false;
        }
        
        if(department==false)
        {
            ANBStatusLabel.setText("Please Select the department");
            return false;
        }
        
        if(ANBEditionField.getText().equals("")){
            ANBStatusLabel.setText("Edition field is empty");
            return false;
        }
        
        if(ANBPriceField.getText().equals("")){
            ANBStatusLabel.setText("Please Enter the price");
            return false;
        }
        
        if(ANBBookSourcesTextArea.getText().equals("")){
            ANBStatusLabel.setText("Book Sources is Empty");
            return false;
        }
        
        if(BookLocationTextFieldArea.getText().equals(""))
        {
            ANBStatusLabel.setText("Book Location is Empty");
            return false;
        }
        
        return true;
    }
    
    
    void Clear()
    {
        ANBBookNameField.setText("");
        ANBEditionField.setText("");
        ANBPriceField.setText("");
        ANBBookSourcesTextArea.setText("");
        BookLocationTextFieldArea.setText("");
        ANBDepartmentComboBox.setValue(null);
        ANBStatusLabel.setText("");
        department=false;
    }
    
}
