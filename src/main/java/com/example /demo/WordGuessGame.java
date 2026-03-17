package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WordGuessGame extends Application {

    String targetWord = "JAVAPROGRAM";
    String hiddenWord = "___________";
    int attempts = 5;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(15);

        Label enterLabel = new Label("Enter your guess:");
        Label lengthLabel = new Label("Word length: " + targetWord.length() + " characters");

        Label wordDisplay = new Label("Word: " + hiddenWord);
        TextField inputField = new TextField();

        Button guessBtn = new Button("Guess");
        Label attemptsLabel = new Label("Attempts left: " + attempts);

        grid.add(enterLabel, 0, 0);
        grid.add(lengthLabel, 1, 0);
        grid.add(wordDisplay, 0, 1);
        grid.add(inputField, 1, 1);
        grid.add(guessBtn, 0, 2);
        grid.add(attemptsLabel, 1, 2);


        guessBtn.setOnAction(e -> {
            String guessText = inputField.getText().toUpperCase();

            if (!guessText.isEmpty() && attempts > 0) {

                char guessChar = guessText.charAt(0);
                boolean foundMatch = false;

                char[] hiddenChars = hiddenWord.toCharArray();

                for (int i = 0; i < targetWord.length(); i++) {
                    if (targetWord.charAt(i) == guessChar) {
                        hiddenChars[i] = guessChar;
                        foundMatch = true;
                    }
                }

                if (foundMatch) {
                    hiddenWord = new String(hiddenChars);
                    wordDisplay.setText("Word: " + hiddenWord);
                } else {
                    attempts--;
                    attemptsLabel.setText("Attempts left: " + attempts);
                }

                inputField.clear();
            }
        });


        Scene scene = new Scene(grid, 500, 250);
        primaryStage.setTitle("Word Guessing Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
