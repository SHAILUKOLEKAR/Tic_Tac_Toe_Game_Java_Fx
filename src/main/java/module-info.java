module com.example.tic_to_toe_game_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tic_to_toe_game_javafx to javafx.fxml;
    exports com.example.tic_to_toe_game_javafx;
}