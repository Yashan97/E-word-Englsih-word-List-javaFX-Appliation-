package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;

public class AddFormController {

    public TextField txtNewWord;
    public TextField txtMeaningOfWord;
    public TextField txtSinhalaMean;
    public Label lblAvailable;
    public Label lblDate;



    public void btnHome(ActionEvent event) throws IOException {
        try {
            DBConnection.getInstance().switchWindow(event, "/view/mainFxmlForm.fxml", "Home Form");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try {
            DBConnection.getInstance().switchWindow(event,"/view/viewAllWords.fxml", "View All Word");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchQuic(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT * from wordlist WHERE new_word LIKE ?");
        psTm.setString(1,"%"+txtNewWord.getText()+"%");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            lblAvailable.setText("Available");
            txtNewWord.setText(resultSet.getString("new_word"));
            txtMeaningOfWord.setText(resultSet.getString("meaningOfWord"));
            txtSinhalaMean.setText(resultSet.getString("sinhala_Meaning"));
            lblDate.setText(resultSet.getString("created_date"));

        }else {
            lblAvailable.setText("Not Available");
            txtSinhalaMean.setText("");
            txtMeaningOfWord.setText("");
            lblDate.setText("Date");
        }
        resultSet.close();
        psTm.close();
        connection.close();
    }
}
