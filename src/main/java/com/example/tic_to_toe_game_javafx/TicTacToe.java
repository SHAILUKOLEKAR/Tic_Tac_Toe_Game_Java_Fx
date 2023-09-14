package com.example.tic_to_toe_game_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TicTacToe extends Application {

    private Button buttons[][] = new Button[3][3];
    private Label playerXScoreLable, playerOScoreLable;
    private boolean playerXTurn = true;
    private int playerXScore = 0;
    private int playerOScore = 0;

    private BorderPane createContent(){
        BorderPane root = new BorderPane();

        //Title
        Label titleLable = new Label("Tic Tac Toe");
        titleLable.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
        BorderPane.setAlignment(titleLable,Pos.CENTER); // align to center
        root.setTop(titleLable);

        //Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5,10,5,10)); // padding top right bottom left
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                buttons[i][j] = button;
                button.setStyle("-fx-font-size : 30pt; -fx-font-weight : bold;");

                button.setOnAction(event->buttonClicked(button)); // button clicked action

                button.setPrefSize(100,100);
                gridPane.add(button,j,i); // first column and second row index for pane
            }
        }

        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane,Pos.CENTER);

        //Score
        HBox scoreBoard = new HBox(20);//10 pixel space
        scoreBoard.setAlignment(Pos.CENTER); // align center
        scoreBoard.setPadding(new Insets(0,10,10,10));  // padding all side
        playerXScoreLable = new Label("Player X : 0");
        playerXScoreLable.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");

        playerOScoreLable = new Label("Player O : 0");
        playerOScoreLable.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");

        scoreBoard.getChildren().addAll(playerXScoreLable,playerOScoreLable);
        root.setBottom(scoreBoard);

        return root;
    }

    private void buttonClicked(Button button)
    {
        if(button.getText().equals(""))
        {
            if(playerXTurn)
            {
                button.setText("X");
            }
            else
            {
                button.setText("O");
            }

            playerXTurn = !playerXTurn; // making player turn true false

            checkWinner(); // check winner on every click
        }

    }

    private void checkWinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(
                    buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()
            )
            {
                // winner found
                String winnner = buttons[row][0].getText().toString();
                showWinnerDialog(winnner); // showing winner
                updateScore(winnner); // updating score
                resetBoard(); // resetting board
                return;
            }
        }

        //column

        for (int col = 0; col < 3; col++) {
            if(
                    buttons[0][col].getText().equals(buttons[1][col].getText())
                            && buttons[1][col].getText().equals(buttons[2][col].getText())
                            && !buttons[0][col].getText().isEmpty()
            )
            {
                // winner found
                String winnner = buttons[0][col].getText().toString();
                showWinnerDialog(winnner); // showing winner
                updateScore(winnner); // updating score
                resetBoard(); // resetting board
                return;
            }
        }

        //diagonal
// left
        if(
                buttons[0][0].getText().equals(buttons[1][1].getText())
                        && buttons[1][1].getText().equals(buttons[2][2].getText())
                        && !buttons[0][0].getText().isEmpty()
        )
        {
            // winner found
            String winnner = buttons[0][0].getText().toString();
            showWinnerDialog(winnner); // showing winner
            updateScore(winnner); // updating score
            resetBoard(); // resetting board
            return;
        }
//right
        if(
                buttons[2][0].getText().equals(buttons[1][1].getText())
                        && buttons[1][1].getText().equals(buttons[0][2].getText())
                        && !buttons[2][0].getText().isEmpty()
        )
        {
            // winner found
            String winnner = buttons[2][0].getText().toString();
            showWinnerDialog(winnner); // showing winner
            updateScore(winnner); // updating score
            resetBoard(); // resetting board
            return;
        }

        // tie condition
        boolean  tie = true;
        for (Button row[]:buttons)
        {
            for (Button button:row)
            {
                if(button.getText().isEmpty())
                {
                    tie = false;
                    break;
                }
            }
        }

        if(tie) {
            showTieDialog();
            resetBoard();
        }

    }

    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congrats "+winner+"! You won the game.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over Its Tie!");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X"))
        {
            playerXScore++;
            playerXScoreLable.setText("Player X : "+playerXScore);
        }
        else {
            playerOScore++;
            playerOScoreLable.setText("Player X : "+playerOScore);
        }
    }

    private void resetBoard(){
        for (Button row[]:buttons)
        {
            for (Button button:row)
            {
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);

        Image icon = new Image(new FileInputStream("E:\\ACCIO Material\\Accio Projects\\Tic To Toe Game JavaFx\\src\\main\\resources\\tic-tac-toe.png"));
        stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}