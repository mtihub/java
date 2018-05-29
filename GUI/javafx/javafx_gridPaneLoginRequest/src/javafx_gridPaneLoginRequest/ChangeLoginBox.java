package javafx_gridPaneLoginRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ChangeLoginBox {
	private static Stage window;
	private static Label nameLabel;
	private static Label passwordLabel;
	private static TextField nameInput;
	private static PasswordField passwordInput;
	private static Button loginButton;
	private static String credentialFilePath;
	private static boolean isOriginalProvided;
	
	public static void display() {
		java.nio.file.Path path = FileSystems.getDefault().getPath("credential.txt").toAbsolutePath();
		credentialFilePath = path.toString();
		
		isOriginalProvided = false;
		
		try {
			window = new Stage();
			window.setTitle("Change Credentials");
			window.initModality(Modality.APPLICATION_MODAL);
			window.setOnCloseRequest(e -> { closeWindow(); });
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(credentialFilePath));     
				if (br.readLine() == null) {
					closeWindow();
					br.close();
					return;
				}
				br.close();
			}
			catch (Exception ex) {
				return;
			}
			
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);
			
			// Username Label
			nameLabel = new Label("Current Username");
			GridPane.setConstraints(nameLabel, 0, 0);
					
			// Username input box
			nameInput = new TextField();
			GridPane.setConstraints(nameInput, 1, 0);
					
			// Password Label
			passwordLabel = new Label("Current Password");
			GridPane.setConstraints(passwordLabel, 0, 1);
							
			// Password input box
			passwordInput = new PasswordField();
			passwordInput.setPromptText("password");
			GridPane.setConstraints(passwordInput, 1, 1);		
			
			loginButton = new Button();
			loginButton.setText("Enter");
			GridPane.setConstraints(loginButton, 1, 2);
			
			loginButton.setOnAction(e -> {
				if (isOriginalProvided == true) {
					saveCredentialAndClose();
				}
				else {
					readAndMatchCredential();
				}
			});
			
			grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, loginButton);
			
			Scene scene = new Scene(grid, 320, 150);
			window.setScene(scene);
			window.showAndWait();
			
		}
		catch (Exception ex) {
			closeWindow();
			return;
		}
		
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
				isOriginalProvided = true;
				
				nameLabel.setText("Enter New Username");
				nameInput.setText("");
				
				passwordLabel.setText("Enter New Password");
				passwordInput.setText("");
				passwordInput.setPromptText("password");
				
				loginButton.setText("Change");
			}
			else {
				System.out.println("Invalid username or password");
			}
			
			fr.close();
		} 
		catch (Exception ex) {
			System.out.println("Error reading file.");
		}
	}
}
