package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public Label lblCount;

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

    public void btnHome(ActionEvent event) throws IOException {
      switchWindow(event,"/view/mainFxmlForm.fxml" , "Home Window");

    }

    public void btnView(ActionEvent event) throws IOException {
        switchWindow(event,"/view/viewAllWords.fxml" , "view Form");
    }

    public void btnSearchTop(ActionEvent actionEvent) {
        System.out.println("Search clicked");
    }


    public void btnAdd(ActionEvent event) throws IOException {
        switchWindow(event, "/view/addForm.fxml" , "Add Word Form");

    }
    public void countWordList() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement("SELECT COUNT(DISTINCT new_word)FROM wordList");
        ResultSet resultSet = psTm.executeQuery();
        if (resultSet.next()){
            int count = resultSet.getInt(1);
            lblCount.setText(String.valueOf(count));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countWordList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
