package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MainFormController implements Initializable {

    public Label lblCount;
    public Label lblstatus;
    public Label lblDate;



    public void btnHome(ActionEvent event) throws IOException, SQLException {
        DBConnection.getInstance().switchWindow(event,"/view/mainFxmlForm.fxml" , "Home Window");

    }

    public void btnView(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/viewAllWords.fxml" , "view Form");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchTop(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/searchForm.fxml", "Search Form ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAdd(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/addForm.fxml" , "Add Word Form");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void countWordList() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT COUNT(DISTINCT new_word)FROM wordList");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            int count = resultSet.getInt(1);
            lblCount.setText(String.valueOf(count));
            if (count<=1000){
                lblstatus.setText("Weak");
                lblstatus.setTextFill(Color.RED);
            }else {
                lblstatus.setText("Strong");
                lblstatus.setTextFill(Color.GREEN);
            }


        }
    }
    
    public void showLastSeen() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT MAX(created_date)FROM wordlist");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            Timestamp timestamp1 = resultSet.getTimestamp(1);
            lblDate.setText(String.valueOf(timestamp1));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countWordList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            showLastSeen();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdate(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/UpdateFormController.fxml" ,"Update Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDelete(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/UpdateFormController.fxml","Update Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
