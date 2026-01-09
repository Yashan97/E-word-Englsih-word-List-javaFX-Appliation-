package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateFormController {


    public TextField txtSinhalaMean;
    public TextField txtMeaningOfWord;
    public TextField txtNewWord;
    public Label lblAvailable;
    public JFXButton btnUpdate;
    public Label lblId;
    public JFXButton btnDelete;

    public void btnHome(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/mainFxmlForm.fxml","Home Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnView(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/viewAllWords.fxml", "View All Word Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchQuic(ActionEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement psTm = connection.prepareStatement("SELECT * FROM wordlist WHERE new_word LIKE ? ");
        psTm.setString(1,"%"+txtNewWord.getText()+"%");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            lblAvailable.setText("Available");
            txtNewWord.setText(resultSet.getString(2));
            txtMeaningOfWord.setText(resultSet.getString(3));
            txtSinhalaMean.setText(resultSet.getString(4));
            lblId.setText(resultSet.getString(1));
            btnUpdate.setDisable(false);
        }else {
            lblAvailable.setText("Not Available ");
            txtSinhalaMean.setText("");
            txtMeaningOfWord.setText("");
            lblId.setText("");
            btnUpdate.setDisable(true);
        }

    }


    public void btnUpdateWord(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("UPDATE wordlist SET new_word = ? , meaningOfWord = ? , sinhala_Meaning = ? WHERE id = ?");
        psTm.setString(1 ,txtNewWord.getText());
        psTm.setString(2, txtMeaningOfWord.getText());
        psTm.setString(3 ,txtSinhalaMean.getText());
        psTm.setString(4, lblId.getText());
        int i = psTm.executeUpdate();
        if (i>0){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
        }else
            new Alert(Alert.AlertType.ERROR,"Can't Update ").show();
    }

    public void btnAdd(ActionEvent event) {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/addForm.fxml","Add new Word ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteWord(ActionEvent event) {

    }
}
