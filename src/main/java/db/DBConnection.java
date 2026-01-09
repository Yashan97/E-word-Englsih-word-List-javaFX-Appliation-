package db;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private  Connection connection;
    private  DBConnection() throws SQLException {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/english_words1", "root", "1234");


    }
    public Connection getConnection(){
        return connection;
    }
    public static DBConnection getInstance() throws SQLException {
        if (instance == null){
            return  new DBConnection();
        }else {
            return instance;
        }
    }

    public  void switchWindow(ActionEvent event, String fxmlPath, String title) throws IOException {


        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        newStage.setScene(new Scene(root));
        newStage.setTitle(title);
        newStage.setResizable(false);
        newStage.show();


        Stage currentStage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        currentStage.close();
    }



}
