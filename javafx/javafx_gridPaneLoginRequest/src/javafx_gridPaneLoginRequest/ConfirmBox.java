package javafx_gridPaneLoginRequest;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	static boolean answer;
	static Button yes;
	static Button no;
	
	public static boolean display(String title, String message) {
		answer = false;
		
		Stage window = new Stage();							// Blank window
		window.initModality(Modality.APPLICATION_MODAL);	// Close this window first to access other window
		window.setTitle(title);								// Set the title of the window 
		//window.setMaxWidth(250);							// Max width of the window
		
		// Create a label and set the given text in the parameter
		Label label = new Label();
		label.setText(message);
		
		// Two buttons
		yes = new Button();
		yes.setText("YES");
		yes.setOnAction(e -> { answer = true; window.close(); });
		
		no = new Button();
		no.setText("NO");
		no.setOnAction(e -> { answer = false; window.close(); });
		
		VBox layout = new VBox(30);
		layout.getChildren().addAll(label, yes, no);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 250, 200);
		window.setScene(scene);
		window.showAndWait();
	
		return answer;
	}
}
