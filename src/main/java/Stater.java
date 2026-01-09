import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stater extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage)   {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/mainFxmlForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("EWords Application");
        stage.setResizable(false);
        stage.show();
    }
}
