package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    public void btnHome(ActionEvent actionEvent)  {
        Stage stage = new Stage();
        stage.setTitle("Search Customer Forme");
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UpdateFormController.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnView(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/viewAllWords.fxml"))));
        stage.show();
    }

    public void btnSearchTop(ActionEvent actionEvent) {
        System.out.println("Search clicked");
    }


    public void btnAdd(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/addForm.fxml"))));
        stage.setTitle("Add New Words");
        stage.show();

    }
}
