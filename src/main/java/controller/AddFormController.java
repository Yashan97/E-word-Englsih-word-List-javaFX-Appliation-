package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddFormController implements Initializable {

    public TextField txtNewWord;
    public TextField txtMeaningOfWord;
    public TextField txtSinhalaMean;
    public Label lblAvailable;
    public Label lblDate;
    public JFXButton btnAdd;

    private boolean isWordExists(String word) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Wordlist WHERE new_word = ?");
        ps.setString(1, word);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void btnHome(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/mainFxmlForm.fxml", "Home Form");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddNewWords(ActionEvent actionEvent) throws SQLException {

       String newWord = txtNewWord.getText().trim();

        if (newWord.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a word").show();
            return;
        }
        if (isWordExists(newWord)) {
            new Alert(Alert.AlertType.WARNING, "Word already exists!").show();
            txtNewWord.setText("");
            txtMeaningOfWord.setText("");
            txtSinhalaMean.setText("");
            return;
        }

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("INSERT INTO Wordlist (new_word, meaningOfWord, sinhala_Meaning) VALUES (?,?,?)");
        psTm.setString(1, txtNewWord.getText());
        psTm.setString(2, txtMeaningOfWord.getText());
        psTm.setString(3, txtSinhalaMean.getText());
        int i = psTm.executeUpdate();

        if (i > 0) {
            new Alert(Alert.AlertType.INFORMATION, "Add New Word!").show();
            txtNewWord.clear();
            txtMeaningOfWord.clear();
            txtSinhalaMean.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "ERROR").show();
        }
    }


    public void btnView(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event,"/view/viewAllWords.fxml", "View All Word");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setDisable(false);
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
