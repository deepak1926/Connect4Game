package com.example.connect4game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane gridPane = loader.load();
        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Pane menuPane = (Pane) gridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);
        controller = loader.getController();
        controller.createPlayground();
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setTitle("Connect Four");
        stage.setResizable(false);
        stage.show();
    }

    private MenuBar createMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(actionEvent -> controller.resetGame());
        MenuItem resetGame = new MenuItem("Reset game");
        resetGame.setOnAction(actionEvent -> controller.resetGame());
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit game");
        exitGame.setOnAction(actionEvent -> exitGame());
        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        //help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutConnect4 = new MenuItem("About Connect4");
        aboutConnect4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutConnect4();
            }
        });
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutMe();
            }
        });
        helpMenu.getItems().addAll(aboutConnect4, separator, aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void aboutMe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Deepak Satankar");
        alert.setContentText("I love to play around with code and create games. \nConnect 4 is one of the game which is developed by me.\n");
        alert.show();
    }

    private void aboutConnect4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect 4");
        alert.setHeaderText("How To Play");
        alert.setContentText("The Connect 4 game is a classic strategy game in which 2 players go head-to-head in a battle to own the grid!\n" +
                "\n" +
                "Players choose yellow or red discs. They drop the discs into the grid, starting in the middle or at the edge to stack their colored discs upwards, horizontally, or diagonally. Use strategy to block opponents while aiming to be the first player to get 4 in a row to win. The Connect 4 game is a great choice for a play date, a rainy day activity, or anytime your kids want a fun game to play with a friend. It's fun to go 4 the win! For ages 6 and up.");
        alert.show();
    }

    private void exitGame() {
        Platform.exit();
        System.exit(0);

    }



    public static void main(String[] args) {
        launch();
    }
}