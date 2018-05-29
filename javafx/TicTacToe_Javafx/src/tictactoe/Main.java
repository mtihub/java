package tictactoe;


//Main class
 


import java.io.*;
import java.nio.file.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Main extends Application {
    Stage window;
    CustomButtonGrid customButtonGrid;
    String firstTurn;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Window
        window = primaryStage;
        window.setTitle("TicTacToe Experiment");
    
        
        // First Turn
        firstTurn = "X";
        
        
        MainMenuScene mainMenu = new MainMenuScene(window, firstTurn);
        
        window.setScene(mainMenu.getScene());
        window.show();
        
    }
}


