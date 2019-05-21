package javafx_gridPaneLoginRequest;

import java.io.*;
import java.nio.file.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;


public class Main extends Application {
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		boolean isLoginCorrect = LoginBox.display();
		if (!isLoginCorrect) {
			return;
		}
		
		AppBox.display();
	}
	
	public void closeProgram() {
	
	}
}
