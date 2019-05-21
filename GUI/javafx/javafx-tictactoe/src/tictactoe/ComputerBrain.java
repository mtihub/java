package tictactoe;
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

public class ComputerBrain
{
    Button buttonGrid[][];
    ArrayList<Button> availableButtons;
    String userSign;
    String computerSign;
    boolean isFirstTurn;
    
    public ComputerBrain(Button grid[][], ArrayList<Button> available, String user, String computer, boolean isFirst) {
        this.buttonGrid       = grid;
        this.availableButtons = available;
        this.userSign         = user;
        this.computerSign     = computer;
        this.isFirstTurn      = isFirst;
    }
    
    public void makeMove() {
    	if (this.isFirstTurn == true) {
    		makeAPseudoRandomMove();
    	}
	    else {
	    	if (makeAWinningMove() == false) {
				int blockingMove[] = getWinningMove(this.buttonGrid, this.userSign);
		    	if (blockingMove[0] != -1) {
		    		this.buttonGrid[blockingMove[0]][blockingMove[1]].fire();
		    	}
		    	else {
		    		if (makeAPotentialWinningMove() == false) {
		    			makeAPseudoRandomMove();
		    		}
		    	}
			}
	    }
    }
    
    private boolean makeAWinningMove() {
    	int winningMove[] = getWinningMove(this.buttonGrid, this.computerSign);
    	
    	if (winningMove[0] != -1) {
    		this.buttonGrid[winningMove[0]][winningMove[1]].fire();
    		return true;
    	}
    	return false;
    }
    
    private boolean makeAPotentialWinningMove() {
    	Button simulateGrid[][] = getGridCopy();
    	
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (simulateGrid[i][j].isDisable() == false && simulateGrid[i][j].getText() == "") {
    				simulateGrid[i][j].setText(this.computerSign);
    				
    				int winningMove[] = getWinningMove(simulateGrid, this.computerSign);
    				if (winningMove[0] != -1) {
    					this.buttonGrid[i][j].fire();
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

    private int[] getWinningMove(Button grid[][], String sign) {
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			// Button (0,0)
    			if (i == 0 && j == 0 && grid[i][j].getText() == sign) {
    				if ( checkImmediateRight(grid,i,j) ) {
    					return getImmediateRight(i,j);
        	        }
    				if ( checkFarRight(grid,i,j) ) {
    					return getFarRight(i,j);
        	        }
    				if ( checkImmediateBottom(grid,i,j) ) {
    					return getImmediateBottom(i,j);
        	        }
    				if ( checkFarBottom(grid,i,j) ) {
    					return getFarBottom(i,j);
        	        }
    				if ( checkImmediateLowerRight(grid,i,j) ) {
    					return getImmediateLowerRight(i,j);
        	        }
    				if ( checkFarLowerRight(grid,i,j) ) {
    					return getFarLowerRight(i,j);
    				}
    			}
    			
    			// Button (0,1)
    			if (i == 0 && j == 1 && grid[i][j].getText() == sign) {
    				if ( checkImmediateBottom(grid,i,j) ) {
    					return getImmediateBottom(i,j);
        	        }
    				if ( checkFarBottom(grid,i,j) ) {
    					return getFarBottom(i,j);
        	        }
    			}
    			
    			// Button (0,2)
    			if (i == 0 && j == 2 && grid[i][j].getText() == sign) {
    				if ( checkImmediateLeft(grid,i,j) ) {
    					return getImmediateLeft(i,j);
        	        }
    				if ( checkFarLeft(grid,i,j) ) {
    					return getFarLeft(i,j);
        	        }
    				if ( checkImmediateLowerLeft(grid,i,j) ) {
    					return getImmediateLowerLeft(i,j);
        	        }
    				if ( checkFarLowerLeft(grid,i,j) ) {
    					return getFarLowerLeft(i,j);
        	        }
    				if ( checkImmediateBottom(grid,i,j) ) {
    					return getImmediateBottom(i,j);
        	        }
    				if ( checkFarBottom(grid,i,j) ) {
    					return getFarBottom(i,j);
        	        }
    			}
    			
    			// Button (1,0)
    			if (i == 1 && j == 0 && grid[i][j].getText() == sign) {
    				if ( checkImmediateRight(grid,i,j) ) {
    					return getImmediateRight(i,j);
        	        }
    				if ( checkFarRight(grid,i,j) ) {
    					return getFarRight(i,j);
        	        }
    			}
    			
    			// Button (1,2)
    			if (i == 1 && j == 2 && grid[i][j].getText() == sign) {
    				if ( checkFarLeft(grid,i,j) ) {
    					return getFarLeft(i,j);
        	        }
    			}
    			
    			// Button (2,0)
    			if (i == 2 && j == 0 && grid[i][j].getText() == sign) {
    				if ( checkFarTop(grid,i,j) ) {
    					return getFarTop(i,j);
        	        }
    				if ( checkFarUpperRight(grid,i,j) ) {
    					return getFarUpperRight(i,j);
        	        }
    				if ( checkImmediateRight(grid,i,j) ) {
    					return getImmediateRight(i,j);
        	        }
    				if ( checkFarRight(grid,i,j) ) {
    					return getFarRight(i,j);
        	        }
    			}
    			
    			// Button (2,1)
    			if (i == 2 && j == 1 && grid[i][j].getText() == sign) {
    				if ( checkFarTop(grid,i,j) ) {
    					return getFarTop(i,j);
        	        }
    			}
    			
    			// Button (2,2)
    			if (i == 2 && j == 2 && grid[i][j].getText() == sign) {
    				if ( checkFarLeft(grid,i,j) ) {
    					return getFarLeft(i,j);
        	        }
    				if ( checkFarUpperLeft(grid,i,j) ) {
    					return getFarUpperLeft(i,j);
        	        }
    				if ( checkFarTop(grid,i,j) ) {
    					return getFarTop(i,j);
        	        }
    			}
    		}
    	}
    	
    	int noBlockingMove[] = {-1, -1};
        return noBlockingMove;
    }
    

    
    //---------------------------------------------------------------------------------------
    // Methods for getting blocks in different directions
    //---------------------------------------------------------------------------------------
    private int[] getImmediateRight(int i, int j) {
    	int immediateRight[] = {i, j+1};
    	return immediateRight;
    }
    private int[] getFarRight(int i, int j) {
    	int farRight[] = {i, j+2};
    	return farRight;
    }
    
    private int[] getImmediateLeft(int i, int j) {
    	int immediateLeft[] = {i, j-1};
    	return immediateLeft;
    }
    private int[] getFarLeft(int i, int j) {
    	int farLeft[] = {i, j-2};
    	return farLeft;
    }
    
    private int[] getImmediateBottom(int i, int j) {
    	int immediateBottom[] = {i+1, j};
    	return immediateBottom;
    }
    private int[] getFarBottom(int i, int j) {
    	int farBottom[] = {i+2, j};
    	return farBottom;
    }
    
    private int[] getImmediateTop(int i, int j) {
    	int immediateTop[] = {i-1, j};
    	return immediateTop;
    }
    private int[] getFarTop(int i, int j) {
    	int farTop[] = {i-2, j};
    	return farTop;
    }
    
    private int[] getImmediateLowerRight(int i, int j) {
    	int immediateLowerRight[] = {i+1, j+1};
    	return immediateLowerRight;
    }
    private int[] getFarLowerRight(int i, int j) {
    	int farLowerRight[] = {i+2, j+2};
    	return farLowerRight;
    }
    private int[] getImmediateUpperRight(int i, int j) {
    	int immediateUpperRight[] = {i-1, j+1};
    	return immediateUpperRight;
    }
    private int[] getFarUpperRight(int i, int j) {
    	int farUpperRight[] = {i-2, j+2};
    	return farUpperRight;
    }
    
    private int[] getImmediateLowerLeft(int i, int j) {
    	int immediateLowerLeft[] = {i+1, j-1};
    	return immediateLowerLeft;
    }
    private int[] getFarLowerLeft(int i, int j) {
    	int farLowerLeft[] = {i+2, j-2};
    	return farLowerLeft;
    }
    private int[] getImmediateUpperLeft(int i, int j) {
    	int immediateUpperLeft[] = {i-1, j-1};
    	return immediateUpperLeft;
    }
    private int[] getFarUpperLeft(int i, int j) {
    	int farUpperLeft[] = {i-2, j-2};
    	return farUpperLeft;
    }
    //---------------------------------------------------------------------------------------
   
    
    
    //---------------------------------------------------------------------------------------
    // Methods to check if a move should be made or not
    //---------------------------------------------------------------------------------------
    private boolean checkImmediateRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateRight[] = getImmediateRight(i, j);
    	int farRight[]       = getFarRight(i, j);
    	
    	if ( grid[farRight[0]][farRight[1]].getText() == sign && grid[immediateRight[0]][immediateRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateRight[] = getImmediateRight(i, j);
    	int farRight[]       = getFarRight(i, j);
    	
    	if ( grid[immediateRight[0]][immediateRight[1]].getText() == sign && grid[farRight[0]][farRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLeft[] = getImmediateLeft(i, j);
    	int farLeft[]       = getFarLeft(i,j);
    	
    	if ( grid[farLeft[0]][farLeft[1]].getText() == sign && grid[immediateLeft[0]][immediateLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLeft[] = getImmediateLeft(i, j);
    	int farLeft[]       = getFarLeft(i,j);
    	
    	if ( grid[immediateLeft[0]][immediateLeft[1]].getText() == sign && grid[farLeft[0]][farLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateBottom(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateBottom[] = getImmediateBottom(i, j);
    	int farBottom[]       = getFarBottom(i, j);
    	
    	if ( grid[farBottom[0]][farBottom[1]].getText() == sign && grid[immediateBottom[0]][immediateBottom[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarBottom(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateBottom[] = getImmediateBottom(i, j);
    	int farBottom[]       = getFarBottom(i, j);
    	
    	if ( grid[immediateBottom[0]][immediateBottom[1]].getText() == sign && grid[farBottom[0]][farBottom[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateTop(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateTop[] = getImmediateTop(i, j);
    	int farTop[]       = getFarTop(i, j);
    	
    	if ( grid[farTop[0]][farTop[1]].getText() == sign && grid[immediateTop[0]][immediateTop[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarTop(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateTop[] = getImmediateTop(i, j);
    	int farTop[]       = getFarTop(i, j);
    	
    	if ( grid[immediateTop[0]][immediateTop[1]].getText() == sign && grid[farTop[0]][farTop[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateLowerRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLowerRight[] = getImmediateLowerRight(i, j);
    	int farLowerRight[]       = getFarLowerRight(i, j);
    
    	if ( grid[farLowerRight[0]][farLowerRight[1]].getText() == sign && grid[immediateLowerRight[0]][immediateLowerRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarLowerRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLowerRight[] = getImmediateLowerRight(i, j);
    	int farLowerRight[]       = getFarLowerRight(i, j);
    
    	if ( grid[immediateLowerRight[0]][immediateLowerRight[1]].getText() == sign && grid[farLowerRight[0]][farLowerRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateLowerLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLowerLeft[] = getImmediateLowerLeft(i, j);
    	int farLowerLeft[]       = getFarLowerLeft(i, j);
    	
    	if ( grid[farLowerLeft[0]][farLowerLeft[1]].getText() == sign && grid[immediateLowerLeft[0]][immediateLowerLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarLowerLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateLowerLeft[] = getImmediateLowerLeft(i, j);
    	int farLowerLeft[]       = getFarLowerLeft(i, j);
    	
    	if ( grid[immediateLowerLeft[0]][immediateLowerLeft[1]].getText() == sign && grid[farLowerLeft[0]][farLowerLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateUpperRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateUpperRight[] = getImmediateUpperRight(i, j);
    	int farUpperRight[]       = getFarUpperRight(i, j);
    	
    	if ( grid[farUpperRight[0]][farUpperRight[1]].getText() == sign && grid[immediateUpperRight[0]][immediateUpperRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarUpperRight(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateUpperRight[] = getImmediateUpperRight(i, j);
    	int farUpperRight[]       = getFarUpperRight(i, j);
    	
    	if ( grid[immediateUpperRight[0]][immediateUpperRight[1]].getText() == sign && grid[farUpperRight[0]][farUpperRight[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkImmediateUpperLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateUpperLeft[] = getImmediateUpperLeft(i, j);
    	int farUpperLeft[]       = getFarUpperLeft(i, j);
    	
    	if ( grid[farUpperLeft[0]][farUpperLeft[1]].getText() == sign && grid[immediateUpperLeft[0]][immediateUpperLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    
    private boolean checkFarUpperLeft(Button grid[][], int i, int j) {
    	String sign = grid[i][j].getText();
    	int immediateUpperLeft[] = getImmediateUpperLeft(i, j);
    	int farUpperLeft[]       = getFarUpperLeft(i, j);
    	
    	if ( grid[immediateUpperLeft[0]][immediateUpperLeft[1]].getText() == sign && grid[farUpperLeft[0]][farUpperLeft[1]].getText() == "" ) {
    		return true;
    	}
    	return false;
    }
    //---------------------------------------------------------------------------------------
    
    
    private char[][] getCharGrid() {
    	char charGrid[][] = new char[3][3]; 
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (this.buttonGrid[i][j].isDisable()) {
    				charGrid[i][j] = 'D';
    			}
    			else if (this.buttonGrid[i][j].getText() == "") {
    				charGrid[i][j] = 'N';
    			}
    			else if (this.buttonGrid[i][j].getText() == "X") {
    				charGrid[i][j] = 'X';
    			}
    			else {
    				charGrid[i][j] = 'O';
    			}
    		}
    	}
    	return charGrid;
    }
    
    private Button[][] getGridCopy() {
    	Button gridCopy[][] = new Button[3][3];
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			Button button = new Button();
    			if (this.buttonGrid[i][j].isDisable()) {
    				button.setDisable(true);
    			}
    			else {
    				button.setText(this.buttonGrid[i][j].getText());
    			}
				gridCopy[i][j] = button;
    		}
    	}
    	return gridCopy;
    }
    
    
    private void makeAPseudoRandomMove() {
        int randButton = getRandNumber(this.availableButtons.size()-1, 0); 
        if (this.isFirstTurn == true) {
        	while (isCornerOrCenter(availableButtons.get(randButton))) {
        		randButton = getRandNumber(this.availableButtons.size()-1, 0); 
        	}
        }
        availableButtons.get(randButton).fire();
    }
    
    private int getRandNumber(int max, int min) {
        // Generate two random numbers, one for row, one for column
        Random random = new Random();
        int rand = random.nextInt(((max-min)+1)+min);
        return rand;
    }
    
    private boolean isCornerOrCenter(Button button) {
    	if ( this.buttonGrid[0][0].equals(button) || this.buttonGrid[0][2].equals(button) || 
    		 this.buttonGrid[2][0].equals(button) || this.buttonGrid[2][2].equals(button) ||
    		 this.buttonGrid[1][1].equals(button)) {
    		return true;
    	}
    	return false;
    }
}
