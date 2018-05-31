package tictactoe;

//
import java.nio.file.FileSystems;

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

public class GameScene {
	Stage window;
	Scene gameScene;
	CustomButtonGrid customButtonGrid;
	String firstTurn;
	String whiteImagePath;
	GridPane grid;
	
	public GameScene(Stage primaryStage, String turn) {
		// Image file paths
		java.nio.file.Path path = FileSystems.getDefault().getPath("white.png").toAbsolutePath();
		String pathString = path.toString();
		
		this.window = primaryStage;
		this.firstTurn = turn;
		this.whiteImagePath = pathString;
		
		window.setTitle("TicTacToe Experiment");
		
		// Create buttons
		this.customButtonGrid = new CustomButtonGrid(3, 3, whiteImagePath, firstTurn, window);
		
		// Setup Grid
		setupGrid();

		// Set scene
		this.gameScene = new Scene(grid, 458, 459);
		//window.setScene(gameScene);
		//window.show();
	}
	
	private void setupGrid() {
		this.grid = new GridPane();
		this.grid.setStyle("-fx-background-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				GridPane.setConstraints(this.customButtonGrid.getButton(i, j), j, i);
				this.grid.getChildren().add(this.customButtonGrid.getButton(i, j));
			}
		}
	}
	
	public Scene getScene() {
		return this.gameScene;
	}
	
	
}
