package sceneAndWindow;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button button = new Button();
		button.setText("Do you wish to enter the Matrix?");
		button.setOnAction(e -> {
			boolean choice = OptionsBox.display("Matrix", "Choose your fate, Neo");
			System.out.println(choice);
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
