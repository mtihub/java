package tictactoe;


//
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CustomButtonGrid {
    Stage window;
    Button buttonGrid[][];
    int boardStatus[][];
    private int totalRow;
    private int totalCol;

    private String whiteImagePath;
    
    private String firstTurn;
    private String secondTurn;
    private String currentTurn;
    private int whosTurn;
    private int totalTurnsMade;
    private boolean isFirstTurn;

    private int buttonWidth;
    private int buttonHeight;
    private String buttonFont;
    private int buttonFontSize;
    
    ArrayList<Button> availableButtons;
    
    CustomButtonGrid(int totalRow, int totalCol, String whitePath, String turn, Stage primaryStage) {
        this.window = primaryStage;
        this.totalRow = totalRow;
        this.totalCol = totalCol;
        this.buttonGrid = new Button[this.totalRow][this.totalCol];
        
        this.availableButtons = new ArrayList<Button>();
        
        this.whiteImagePath = "file:" + whitePath;
        
        this.firstTurn   = turn;
        this.currentTurn = turn;
        if (this.firstTurn == "X") {
        	this.secondTurn = "O";
        }
        else {
        	this.secondTurn = "X";
        }
        this.totalTurnsMade = 0;
        this.isFirstTurn = true;
        
        whosTurn = 1;
        
        this.buttonWidth  = 150;
        this.buttonHeight = 150;
        this.buttonFont = "Comic Sans MS";
        this.buttonFontSize = 60;
        
        for (int i = 0; i < this.totalRow; i++) {
            for (int j = 0; j < this.totalCol; j++) {
                Button newButton = makeButton();
                this.buttonGrid[i][j] = newButton;
                this.availableButtons.add(newButton);
            }
        }
        
    }
    
    private Button makeButton() {
        Button button = new Button();
        
        Image whiteImage = new Image(this.whiteImagePath);
        button.setGraphic(new ImageView(whiteImage));
        button.setText("");
        
        button.setPrefSize(this.buttonWidth, this.buttonHeight);
        button.setMaxSize(this.buttonWidth, this.buttonHeight);
        button.setStyle("-fx-background-color: #ffffff");
        
        button.setOnMouseEntered(e -> { button.setStyle("-fx-background-color: #000000"); });
        button.setOnMouseExited (e -> { button.setStyle("-fx-background-color: #ffffff"); });
        button.setOnAction(e -> buttonClick(button) );
        
        return button;
    }
    
    private void buttonClick(Button button) {
        
        if (this.currentTurn.equals("X")) {
            button.setGraphic(null);
            button.setText("X");
            button.setTextAlignment(TextAlignment.CENTER);
            button.setFont(Font.font(this.buttonFont, FontWeight.BOLD, this.buttonFontSize));
            this.currentTurn = "O";
        }
        else if (this.currentTurn.equals("O")) {
            button.setGraphic(null);
            button.setText("O");
            button.setTextAlignment(TextAlignment.CENTER);
            button.setFont(Font.font(this.buttonFont, FontWeight.BOLD, this.buttonFontSize));
            this.currentTurn = "X";
        }
        
        button.setDisable(true);
        this.availableButtons.remove(button);
        this.totalTurnsMade++;
        
        String winner = findWinner();
        if (winner != null) {
            this.whosTurn = 0;
            GameOverBox.display(winner, window, firstTurn);
        }
        
       
       if (this.whosTurn == 1) {
            this.whosTurn = 0;
            ComputerBrain brain = new ComputerBrain(this.buttonGrid, this.availableButtons, this.firstTurn, this.secondTurn, this.isFirstTurn);
            brain.makeMove();
       }

       this.whosTurn = 1;
       
       if (this.isFirstTurn == true) {
       	this.isFirstTurn = false;
       }
    }
    
    
    private String findWinner() {
        if (this.totalTurnsMade < 5) {
            return null;
        }
        
        if (!buttonGrid[0][0].getText().equals("")) {
            if ( ((buttonGrid[0][0].getText() == buttonGrid[0][1].getText()) && (buttonGrid[0][0].getText() == buttonGrid[0][2].getText())) ||
                 ((buttonGrid[0][0].getText() == buttonGrid[1][1].getText()) && (buttonGrid[0][0].getText() == buttonGrid[2][2].getText())) ||
                 ((buttonGrid[0][0].getText() == buttonGrid[1][0].getText()) && (buttonGrid[0][0].getText() == buttonGrid[2][0].getText()))) {
                return buttonGrid[0][0].getText();
            }
        }
        if (!buttonGrid[1][0].getText().equals("")) {
            if ( (buttonGrid[1][0].getText() == buttonGrid[1][1].getText()) && (buttonGrid[1][0].getText() == buttonGrid[1][2].getText()) ) {
                 return buttonGrid[1][0].getText();
            }
        }
        
        if (!buttonGrid[2][0].getText().equals("")) {
            if ( ((buttonGrid[2][0].getText() == buttonGrid[2][1].getText()) && (buttonGrid[2][0].getText() == buttonGrid[2][2].getText())) ||
                 ((buttonGrid[2][0].getText() == buttonGrid[1][1].getText()) && (buttonGrid[2][0].getText() == buttonGrid[0][2].getText()))) {
                 return buttonGrid[2][0].getText();
            }
        }
        if (!buttonGrid[0][1].getText().equals("")) {
            if ( (buttonGrid[0][1].getText() == buttonGrid[1][1].getText()) && (buttonGrid[0][1].getText() == buttonGrid[2][1].getText()) ) {
                 return buttonGrid[0][1].getText();
            }
        }
        if (!buttonGrid[0][2].getText().equals("")) {
            if ( (buttonGrid[0][2].getText() == buttonGrid[1][2].getText()) && (buttonGrid[0][2].getText() == buttonGrid[2][2].getText()) ) {
                 return buttonGrid[0][2].getText();
            }
        }
        
        
        if (this.totalTurnsMade==9)
        {
            return "None";  
        }
        return null;
    }
    
    
    public Button getButton(int row, int col) {
        return buttonGrid[row][col];
    }
}
    
    
    
    
