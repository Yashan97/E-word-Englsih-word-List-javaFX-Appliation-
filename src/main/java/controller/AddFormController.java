package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFormController {

    public TextField txtNewWord;
    public TextField txtMeaningOfWord;
    public TextField txtSinhalaMean;

    public void btnSearchTop(ActionEvent actionEvent) {
    }

    public void btnHome(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/mainFxmlForm.fxml"))));
        stage.show();
    }

    public void btnAddNewWords(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("INSERT INTO Wordlist(new_word, meaningOfWord, sinhala_Meaning) VALUES(?,?,?)");
        psTm.setString(1,txtNewWord.getText());
        psTm.setString(2,txtMeaningOfWord.getText());
        psTm.setString(3,txtSinhalaMean.getText());

        psTm.executeUpdate();

    }


}
