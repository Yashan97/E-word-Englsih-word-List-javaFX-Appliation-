package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.WordList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllWords {

    public TableColumn colSinhala;
    public TableColumn colMeaning;
    public TableColumn colWord;
    public TableView tblAllWord;

    List<WordList> wordLists =new ArrayList<>();
    public void btnSearchTop(ActionEvent actionEvent) {

    }

    public void btnHome(ActionEvent actionEvent) {
    }

    public void btnReload(ActionEvent actionEvent) {
        loadTable();
    }

    private void loadTable() {
        Connection connection = null;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/english_words1", "root", "1234");
            resultSet = connection.createStatement().executeQuery("SELECT * FROM Wordlist");
            while (resultSet.next()){
                    wordLists.add(new WordList(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
