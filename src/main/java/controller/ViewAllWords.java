package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.WordList;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAllWords implements Initializable {

    public TableColumn colSinhala;
    public TableColumn colMeaning;
    public TableColumn colWord;
    public TableView tblAllWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    List<WordList> wordLists =new ArrayList<>();

    public void btnHome(ActionEvent actionEvent) {
    }

    public void btnReload(ActionEvent actionEvent) {
        loadTable();
    }

    private void loadTable() {
        ResultSet resultSet;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM Wordlist");
            while (resultSet.next()){
                    wordLists.add(new WordList(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    ));
                colWord.setCellValueFactory(new PropertyValueFactory<>("new_word"));
                colMeaning.setCellValueFactory(new PropertyValueFactory<>("meaningOfWord"));
                colSinhala.setCellValueFactory(new PropertyValueFactory<>("sinhala_Meaning"));
                ObservableList<WordList> observableList = FXCollections.observableArrayList();
                wordLists.forEach(wordList -> {
                    observableList.add(wordList);
                });
                tblAllWord.setItems(observableList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
