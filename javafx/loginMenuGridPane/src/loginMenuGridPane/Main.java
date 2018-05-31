package loginMenuGridPane;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);	
	}
	
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Using GridPane Layout");
		
		// Set up GridPane layout
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		// Username label
		Label usernameLabel = new Label();
		usernameLabel.setText("Username: ");
		GridPane.setConstraints(usernameLabel, 0, 0);
		
		// Username input
		TextField usernameInput = new TextField("JohnDoe");
		GridPane.setConstraints(usernameInput, 1, 0);
		
		// Password Label
		Label passwordLabel = new Label();
		passwordLabel.setText("Password: ");
		GridPane.setConstraints(passwordLabel, 0, 1);
	
		// Password input
		PasswordField passwordInput = new PasswordField();
		passwordInput.setPromptText("password");
		GridPane.setConstraints(passwordInput, 1, 1);
		
		// Login button
		Button loginButton = new Button();
		loginButton.setText("Log In");
		loginButton.setOnAction(e -> validateInputs(usernameInput.getText(), passwordInput.getText(), window));
		GridPane.setConstraints(loginButton, 1, 2);
		
		// Add to layout
		grid.getChildren().addAll(	usernameLabel, usernameInput,
								  	passwordLabel, passwordInput,
								  	loginButton);
		
		// Set scene and add to stage
		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}

	static private void validateInputs(String username, String password, Stage window) {
		if ( (!username.isEmpty()) && (!username.contains(" ")) && (password.length() >= 8)) {
			window.close();
		}
		else {
			if (username.contains(" ")) {
				System.out.println("Username cannot contain spaces");
			}
			if (password.length() < 8) {
				System.out.println("Password must have at least 8 characters");			
			}
		}
	}
	
}
