/*
 * CSE 3504
 * Project Part 1
 * PRACTICE 1
 * 
 * Md Tanvirul Islam
 * Smit Shah
 * 
 * 03/31/2017
 */



import java.util.ArrayList;
import java.util.Random;

class Point
{
	protected int x;
	protected int y;
	
	public Point(int xcoord, int ycoord) 
	{
		this.x = xcoord;
		this.y = ycoord;
	}
}

public class Practice1 
{
	public static void main(String args[])
	{
		final int size = 1000;											// Lattice Size
		int numberOfSimulations = 100;									// Number of Simulations to run
		
		double stepAverage  = 0;
		double stepVariance = 0;
		double stepStdDev   = 0;
		int tempSum     	= 0;
		
		ArrayList<Integer> acquiredSteps = new ArrayList<Integer>();	// Used to calculate variance
		
		
		for (int sim = 0; sim < numberOfSimulations; sim++)
		{
			boolean[][] visitedXY = new boolean[size+1][size+1];		// Visited Points Matrix Map
																		// (0,0)  (-1,0)  (1,0)  (-2,0)  (2,0)  ... ... ...
																		// (0,-1) (-1,-1) (1,-1) (-2,-1) (2,-1) ... ... ...
																		// (0,1)  (-1,1)  (1,1)  (-2,1)  (2,1)  ... ... ...
																		//   .		 .	    .       .	   .    ... ... ...
																		//	 .		 .		.	  	.	   .	... ... ...
																		//   .		 .		.		.      .    ... ... ...
			
			int numberOfSteps = 0;										// Number of Steps in the Simulation
			ArrayList<Point> path = new ArrayList<Point>();				// The Points Taken in the Simulation
			
			// Starting Coordinates
			int currentX = 0;
			int currentY = 0;
			
			// Mark the Starting Coordinate as Visited, and Add it to the Path
			visitedXY[getIndexValue(currentX)][getIndexValue(currentY)] = true;
			path.add(new Point(currentX, currentY));
			
			// Find the Available Neighbors
			ArrayList<Point> availNeighbors = availableNeighbors(visitedXY, size, currentX, currentY);
			
			//System.out.println("Started at: " + currentX + "," + currentY + "\n");
			
			// Run the loop as long as there are available neighbors to go to
			while ( availNeighbors.size() != 0 )
			{
				//System.out.println("Available Neighbors: ");
				//for (Point p : availNeighbors)
				//	System.out.println(p.x + "," + p.y);
				//System.out.println();
				
				// Randomly choose which neighboring point to go to
				Random rn = new Random();
				int choiceIndex = rn.nextInt((availNeighbors.size()-1) - 0 + 1 ) + 0;
				
				// Modify current coordinate
				currentX = availNeighbors.get(choiceIndex).x;
				currentY = availNeighbors.get(choiceIndex).y; 
				
				//System.out.println("Moving to: " + currentX + "," + currentY);
				//System.out.println("\n");
				
				// Mark it visited and add it to the path
				visitedXY[getIndexValue(currentX)][getIndexValue(currentY)] = true;
				path.add(new Point(currentX, currentY));
				
				// Find the next available neighbors
				availNeighbors = availableNeighbors(visitedXY, size, currentX, currentY);
				
				// One step completed
				numberOfSteps++;
			}
			
			System.out.println("•Simulation " + (sim+1));
			printPath(path);
			
			System.out.println("Number of Steps: " + numberOfSteps + "\n\n");
			
			acquiredSteps.add(numberOfSteps);
			tempSum += numberOfSteps;
		}
		
		stepAverage = tempSum/numberOfSimulations;
		
		tempSum = 0;
		for (int steps : acquiredSteps)
			tempSum += (stepAverage - steps) * (stepAverage - steps);
		
		stepVariance = tempSum / numberOfSimulations;
		stepStdDev   = Math.sqrt(stepVariance);
		
		
		System.out.println("\n---------------------- RESULTS ----------------------" + "\n" +
						   "Average Path Length: " + stepAverage + "\n" + 
						   "Standard Deviation of Path Length: " + stepStdDev + "\n" +
						   "-----------------------------------------------------\n\n");
		
	}
	
	
	// Get index value of a point on the visitedXY Matrix
	public static int getIndexValue(int coord)
	{
		int index = coord;
		if (coord < 0) index = (2*Math.abs(coord)) - 1;
		else if (coord > 0) index = 2*Math.abs(coord);
		return index;
	}
	
	
	// Get available neighbors from a point
	public static ArrayList<Point> availableNeighbors(boolean[][] visitedXY, int size, int currentX, int currentY)
	{
		ArrayList<Point> availNeighbors = new ArrayList<Point>();
		
		// Check West
		int x = currentX-1;
		int y = currentY;
		if (isPointAvailable(x, y, visitedXY, size) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check East
		x = currentX+1;
		y = currentY;
		if (isPointAvailable(x, y, visitedXY, size) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check South
		x = currentX;
		y = currentY-1;
		if (isPointAvailable(x, y, visitedXY, size) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check North
		x = currentX;
		y = currentY+1;
		if (isPointAvailable(x, y, visitedXY, size) == true)
			availNeighbors.add(new Point(x, y));
		
		return availNeighbors;
	}
	
	
	// Check if a point is previously visited or not
	private static boolean isPointAvailable(int x, int y, boolean[][] visitedXY, int size)
	{
		int xIndex = getIndexValue(x);
		int yIndex = getIndexValue(y);
		
		if ((Math.abs(x) <= size/2) && (Math.abs(y) <= size/2))
		{
			if (visitedXY[xIndex][yIndex] == false)
				return true;
		}
		
		return false;	
	}
	
	
	// Print the path of a simulation
	public static void printPath(ArrayList<Point> path)
	{
		for (Point p : path)
			System.out.print("(" + p.x + "," + p.y + ")-->");
		System.out.print("END\n\n");
	}
	
	
	// Print the visited xy point map matrix
	public static void printVisitedPointsMatrix(boolean[][] visitedXY, int size)
	{
		for (int i = 0; i < size+1; i++)
		{
			for (int j = 0; j < size+1; j++)
			{
				if (visitedXY[i][j] == true)
				{
					if (i%2 == 0)
						System.out.print("(" + (i/2) + ",");
					else
						System.out.print("(-" + ((i+1)/2) + ",");
					
					if (j%2 == 0)
						System.out.print((j/2) + ")  ");
					else
						System.out.print("-" + ((j+1)/2) + ")  ");
				}
				else
					System.out.print("------  ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

}
