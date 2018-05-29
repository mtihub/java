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
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			closeProgram(primaryStage);
		});
		
		Button button = new Button();
		button.setText("Close");
		
		button.setOnAction(e -> {
			closeProgram(primaryStage);
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void closeProgram(Stage stage) {
		boolean choice = OptionsBox.display("Sure?", "Are you sure you want to exit?");
		if (choice) {
			stage.close();
		}
	}
	
}
