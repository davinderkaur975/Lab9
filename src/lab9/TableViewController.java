/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;




/**
 * FXML Controller class
 *
 * @author Davinder Kaur
 */
public class TableViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML    private TableView<Car>  carTable;
       @FXML    private TableColumn <Car, String> makeColumn;
       @FXML    private TableColumn <Car, String> modelColumn;
       @FXML    private TableColumn <Car, Integer> yearColumn;
       @FXML    private TableColumn <Car, Integer> mileageColumn;
       @FXML    private Slider maxresolutionSlider;
       @FXML    private Label maxresolutionLabel;
       @FXML    private Slider minresolutionSlider;
       @FXML    private Label minresolutionLabel;
       @FXML private ComboBox<String> makeComboBox;
    
       
        
 
    public void loadCars() throws SQLException
    {
        
        
        ObservableList<Car> cars = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
             //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200361589", "gc200361589", "RUxpI_An__");
            
            //2. create a statement object
            //statement = conn.createStatement();
            statement = (Statement) conn.createStatement();
            //3. createthe SQL query
            resultSet = statement.executeQuery("SELECT * FROM car");
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                Car newCar = new Car(resultSet.getString("make"),
                                                  resultSet.getString("model"),
                                                  resultSet.getInt("year"),
                                                  resultSet.getInt("mileage"));
                                                  
                cars.add(newCar);
            }
            carTable.getItems().addAll(cars);
        }
        
        catch(Exception e){
            System.err.println(e.getMessage());

        }
        finally{
            if(conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }
    
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
             modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
             yearColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
             mileageColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("mileage"));
             
             
             maxresolutionSlider.setMin(2010);
             maxresolutionSlider.setMax(2016);
             maxresolutionSlider.setValue(2016); //set the default value
             maxresolutionLabel.setText(Integer.toString((int)maxresolutionSlider.getValue()));
                                                
             
             
             minresolutionSlider.setMin(2006);
             minresolutionSlider.setMax(2010);
             minresolutionSlider.setValue(2006); //set the default value
             minresolutionLabel.setText(Integer.toString((int)minresolutionSlider.getValue()));
                                                
        
             
          try{
              loadCars();
              updateComboBoxFromDB();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void maxresolutionSliderMoved() throws SQLException{
    String label = String.format("%.0f ", maxresolutionSlider.getValue());
        maxresolutionLabel.setText(label);
        UpdateSlider();
        
        
    }
    
    public void minresolutionSliderMoved() throws SQLException{
    String label = String.format("%.0f", minresolutionSlider.getValue());
        minresolutionLabel.setText(label);
        UpdateSlider();
        
    }
    
    
    
    public void UpdateSlider() throws SQLException
    {
        this.carTable.getItems().clear();
        ObservableList<Car> cars = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" +
                    "gc200361589", "gc200361589", "RUxpI_An__");
            
            statement = (Statement) conn.createStatement();
                   
            resultSet = statement.executeQuery("SELECT * FROM car WHERE year between "+minresolutionSlider.getValue()+ " and " +maxresolutionSlider.getValue());
        
            
            //4. create contact objects from each record
            while(resultSet.next())
            {
                Car newCar = new Car(resultSet.getString("make"),
                                                  resultSet.getString("model"),
                                                  resultSet.getInt("year"),
                                                  resultSet.getInt("mileage"));
                
                 
                                 
              cars.add(newCar);
               
            }
            
            carTable.getItems().addAll(cars);
            
           
        }
        
        catch(Exception e){
            System.err.println(e.getMessage());

        }
        finally{
            if(conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }
    
     
     
      public void updateComboBoxFromDB() throws SQLException 
    {
        Connection conn=null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
        
         //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es:3306/" + "gc200361589", "gc200361589", "RUxpI_An__");
        
         //2.  Prepare the query
           statement = (Statement) conn.createStatement();
          
         //3 create and execute sql query
           resultSet = statement.executeQuery("select make from car");
           
         //populate the combobox
         while(resultSet.next()){
          makeComboBox.getItems().add(resultSet.getString("make"));
         }
           
        }
         catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
           if (statement != null)
               statement.close();
            if(resultSet  != null){
               resultSet.close();
        }
                  
        
    }
        
    }
    
    
     
        
       
    
}