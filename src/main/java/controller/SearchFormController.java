package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchFormController {

    public TextField txtSearch;
    public Label lblnewWord;
    public Label lblExample;
    public Label lblSinhalaMean;

    public void btnHome(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/mainFxmlForm.fxml" ,"Home page");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdd(ActionEvent event) throws RuntimeException {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/addForm.fxml", "Add Form" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnView(ActionEvent event) throws RuntimeException {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/viewAllWords.fxml", "View all Words");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchWord(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT * from wordlist WHERE new_word LIKE ?");
        psTm.setString(1,"%"+txtSearch.getText()+"%");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            lblnewWord.setText(resultSet.getString("new_word"));
            lblExample.setText(resultSet.getString(3));
            lblSinhalaMean.setText(resultSet.getString(4));
        }

    }
}
