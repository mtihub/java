package sceneAndWindow;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class OptionsBox {
	static boolean choice;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		Label label = new Label();
		label.setText(message);
		
		Button yes = new Button("YES");
		Button no  = new Button("NO");
		
		yes.setOnAction(e -> {
			choice = true;
			window.close();
		});
		
		no.setOnAction(e -> {
			choice = false;
			window.close();
		});
		
		VBox layout = new VBox();
		layout.getChildren().addAll(label, yes, no);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 250, 200);
		window.setScene(scene);
		window.showAndWait();

		return choice;
	}


}
