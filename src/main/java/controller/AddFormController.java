package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddFormController {

    public TextField txtNewWord;
    public TextField txtMeaningOfWord;
    public TextField txtSinhalaMean;
    public Label lblAvailable;

    public void switchWindow(ActionEvent event, String fxmlPath, String title) throws IOException {


        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        newStage.setScene(new Scene(root));
        newStage.setTitle(title);
        newStage.show();


        Stage currentStage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        currentStage.close();
    }

    public void btnSearchTop(ActionEvent actionEvent) {

    }

    public void btnHome(ActionEvent event) throws IOException {
        switchWindow(event, "/view/mainFxmlForm.fxml", "Home Form");
    }

    public void btnAddNewWords(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("INSERT INTO Wordlist(new_word, meaningOfWord, sinhala_Meaning) VALUES(?,?,?)");
        psTm.setString(1,txtNewWord.getText());
        psTm.setString(2,txtMeaningOfWord.getText());
        psTm.setString(3,txtSinhalaMean.getText());

        if (psTm.executeUpdate()>0){
            new Alert(Alert.AlertType.INFORMATION,"Add New Word !").show();
            txtNewWord.setText("");
            txtMeaningOfWord.setText("");
            txtSinhalaMean.setText("");
        }else{
            new Alert(Alert.AlertType.ERROR,"ERROR").show();
        }

    }


    public void btnView(ActionEvent event) throws IOException {
        switchWindow(event,"/view/viewAllWords.fxml", "View All Word");
    }

    public void btnSearchQuic(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT * from wordlist WHERE new_word LIKE ?");
        psTm.setString(1,"%"+txtNewWord.getText()+"%");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            lblAvailable.setText("Available");
            txtMeaningOfWord.setText(resultSet.getString("meaningOfWord"));
            txtSinhalaMean.setText(resultSet.getString("sinhala_Meaning"));

        }else {
            lblAvailable.setText("Not Available");
            txtSinhalaMean.setText("");
            txtMeaningOfWord.setText("");
        }
        resultSet.close();
        psTm.close();
        connection.close();
    }
}
