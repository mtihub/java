package tictactoe;

//
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverBox {
	private static Stage mainWindow;
	private static Stage gameOverWindow;
	private static String firstTurn;
	
	
	public static void display(String winner, Stage window, String turn) {
		mainWindow = window;
		firstTurn = turn;
		gameOverWindow = new Stage();
		gameOverWindow.setTitle("Game Over");
		gameOverWindow.initModality(Modality.APPLICATION_MODAL);  // Have to close this window first before being able to click anywhere else
		gameOverWindow.setOnCloseRequest(e -> { closeProgram(); });
	
		Button closeButton = new Button(winner + " has won!");
		closeButton.setOnAction(e -> { closeProgram(); });
		
		StackPane layout = new StackPane();
		layout.getChildren().add(closeButton);
		Scene scene = new Scene(layout, 350, 300);
		gameOverWindow.setScene(scene);
		gameOverWindow.showAndWait();
	}
	
	private static void closeProgram() {
		gameOverWindow.close();
		
		//GameScene gameScene = new GameScene(mainWindow, "X");
		MainMenuScene mainMenu = new MainMenuScene(mainWindow, firstTurn);
		mainWindow.setScene(mainMenu.getScene());
		
		mainWindow.show();
	}
}

