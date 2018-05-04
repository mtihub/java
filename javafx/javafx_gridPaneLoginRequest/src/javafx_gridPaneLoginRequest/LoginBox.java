package javafx_gridPaneLoginRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginBox {
	private static boolean isCredentialsCorrect;
	private static Stage window;
	private static Label nameLabel;
	private static Label passwordLabel;
	private static TextField nameInput;
	private static PasswordField passwordInput;
	private static Button loginButton;
	private static Button changeLoginButton;
	private static String credentialFilePath;
	
	
	public static boolean display() {
		java.nio.file.Path path = FileSystems.getDefault().getPath("credential.txt").toAbsolutePath();
		credentialFilePath = path.toString();
		
		isCredentialsCorrect = false;
		
		window = new Stage();
		window.setTitle("Login");
		window.setOnCloseRequest(e -> { closeWindow(); });
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		// Username Label
		nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		// Username input box
		nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);
		
		// Password Label
		passwordLabel = new Label("Password");
		GridPane.setConstraints(passwordLabel, 0, 1);
				
		// Password input box
		//passwordInput = new TextField();
		passwordInput = new PasswordField();
		passwordInput.setPromptText("password");
		
		GridPane.setConstraints(passwordInput, 1, 1);		
		
		loginButton = new Button();
		loginButton.setText("Log In");
		GridPane.setConstraints(loginButton, 1, 2);
		
		loginButton.setOnAction(e -> {
			try {
				BufferedReader br = new BufferedReader(new FileReader(credentialFilePath));     
				if (br.readLine() == null) {
					saveCredentialAndClose();
				   	br.close();
				}
				else {
					br.close();
					readAndMatchCredential();
				}
			}
			catch (Exception ex) {
				saveCredentialAndClose();
			}
		});
		
		changeLoginButton = new Button("Change Credentials");
		GridPane.setConstraints(changeLoginButton, 1, 3);
		changeLoginButton.setOnAction(e -> { ChangeLoginBox.display(); });
		
		grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, loginButton, changeLoginButton);
		
		Scene scene = new Scene(grid, 250, 150);
		window.setScene(scene);
		window.showAndWait();
		
		return isCredentialsCorrect;
	}
	
	private static void closeWindow() {
		window.close();
	}
	
	private static void saveCredentialAndClose() {
		if (!nameInput.getText().isEmpty() && !passwordInput.getText().isEmpty()) {
			try {
				PrintWriter writer = new PrintWriter(credentialFilePath, "UTF-8");
				writer.println(nameInput.getText());
				writer.println(passwordInput.getText());
				System.out.println("Username and Password Saved.");
				isCredentialsCorrect = true;
				
				writer.close();
				closeWindow();
			} 
			catch (Exception e1) {
				System.out.println("Error while creating text file.");
			}
		}
	}
	
	private static void readAndMatchCredential() {
		try {
			FileReader fr = new FileReader(credentialFilePath);
			StringBuilder savedUsername = new StringBuilder();
			StringBuilder savedPassword = new StringBuilder();
			boolean isUsernameRead = false;
			
			int i;
			while ((i = fr.read()) != -1) {
				if (i == '\n' || i == '\r') {
					if (isUsernameRead == false) {
						isUsernameRead = true;
					}
				}
				else if (isUsernameRead == false) {
					savedUsername.append((char)i);
				}
				else {
					savedPassword.append((char)i);
				}
			}
			
			if (nameInput.getText().equals(savedUsername.toString()) && passwordInput.getText().equals(savedPassword.toString())) {
				System.out.println("CREDENTIALS MATCHED.");
				isCredentialsCorrect = true;
				closeWindow();
			}
			else {
				System.out.println("Invalid username or password");
			}
			
			fr.close();
		}
		catch (Exception ex) {
			
		}
	}
	
}
