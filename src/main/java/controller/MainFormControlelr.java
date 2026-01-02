package controller;

import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainFormControlelr {




    private void loadTable(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/english_words1", "root", "1234");
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchTop(ActionEvent actionEvent) {
        loadTable();
    }
}

