package tictactoe;


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

public class MainMenuScene
{
    Stage window;
    Scene mainMenuScene;
    String firstTurn;
    
    public MainMenuScene(Stage primaryStage, String turn)
    {
        this.window = primaryStage;
        this.firstTurn = turn;
        
        setupScene();
    }
    
    private void setupScene() {
        Button startGameButton = new Button("Start");
        startGameButton.setOnAction(e -> {
            GameScene gameScene = new GameScene(window, firstTurn);
            window.setScene(gameScene.getScene());
            window.show();
        });
        
        Button endGameButton   = new Button("Exit");
        endGameButton.setOnAction(e -> {
            window.close();
        });
        
        //StackPane stackPane = new StackPane();
        //stackPane.getChildren().addAll(startGameButton);
        VBox vbox = new VBox(30);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(startGameButton, endGameButton);
    
        this.mainMenuScene = new Scene(vbox, 350, 300);
    }
    
    public Scene getScene() {
        return this.mainMenuScene;
    }
}
