package javafx_gridPaneLoginRequest;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppBox {
	private static Stage window;
	
	public static void display() {
		window = new Stage();
		window.setTitle("App");
		window.setOnCloseRequest(e -> { closeProgram(); });
	
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> { closeProgram(); });
		
		StackPane layout = new StackPane();
		layout.getChildren().add(closeButton);
		Scene scene = new Scene(layout, 350, 300);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private static void closeProgram() {
		boolean choice = ConfirmBox.display("Sure?", "Are you sure you want to quit?");
		if (choice) {
			window.close();
		}
	}
}
