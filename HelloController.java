package com.example.lab1anisha;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
public class HelloController implements Initializable {
    @FXML
    private TableView<FamilyDoctor> tableView;
    @FXML
    private TableColumn<FamilyDoctor,Integer > id;
    @FXML
    private TableColumn<FamilyDoctor, String> ClientName;
    @FXML
    private TableColumn<FamilyDoctor,String> age;
    @FXML
    private TableColumn<FamilyDoctor,Integer> address;
    ObservableList<FamilyDoctor> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<FamilyDoctor,Integer>("id"));
        ClientName.setCellValueFactory(new
                PropertyValueFactory<FamilyDoctor,String>("ClientName"));
        age.setCellValueFactory(new
                PropertyValueFactory<FamilyDoctor,String>("age"));
        address.setCellValueFactory(new
                PropertyValueFactory<FamilyDoctor,Integer>("address"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab1_programming2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM familydoctor_appointment";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ClientName = resultSet.getString("ClientName");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                tableView.getItems().add(new FamilyDoctor(id, ClientName, age,
                        address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

