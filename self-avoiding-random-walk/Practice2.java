/*
 * CSE 3504
 * Project Part 1
 * PRACTICE 2
 * 
 * Md Tanvirul Islam
 * Smit Shah
 * 
 * 03/31/2017
 */


import java.util.ArrayList;
import java.util.Random;

public class Practice2 
{
	public static void main(String args[])
	{
		// Variables
		int numberOfSimulations = 100;
		
		int distanceSum 		= 0;
		double distanceMean 	= 0;
		double distanceVariance = 0;
		double distanceStdDev 	= 0;
		
		int latticeSizeSum 		   = 0;
		double latticeSizeMean 	   = 0;
		double latticeSizeVariance = 0;
		double latticeSizeStdDev   = 0;
		
		ArrayList<Integer> acquiredDistances    = new ArrayList<Integer>(numberOfSimulations);		// Used to Calculate Variance
		ArrayList<Integer> acquiredLatticeSizes = new ArrayList<Integer>(numberOfSimulations);		// Used to Calculate Variance
		
		
		// Run Simulations
		for (int sim = 1; sim <= numberOfSimulations; sim++)
		{
			Simulation currentSim = new Simulation(sim, 0, 0);
			
			distanceSum += currentSim.getDistance();
			latticeSizeSum += currentSim.getLatticeSize();
			
			acquiredDistances.add(currentSim.getDistance());
			acquiredLatticeSizes.add(currentSim.getLatticeSize());
		}
		
		// Calculate Means
		distanceMean = distanceSum / numberOfSimulations;
		latticeSizeMean = latticeSizeSum / numberOfSimulations;
		
		// Calculate Variance and Standard Deviation for Distance
		int tempSum = 0;
		for (int distance : acquiredDistances)
			tempSum += (distanceMean - distance) * (distanceMean - distance);
		distanceVariance = tempSum / numberOfSimulations;
		distanceStdDev = Math.sqrt(distanceVariance);
		
		// Calculate Variance and Standard Deviation for Lattice Size	
		tempSum = 0;
		for (int latticeSize : acquiredLatticeSizes)
			tempSum += (latticeSizeMean - latticeSize) * (latticeSizeMean - latticeSize);
		latticeSizeVariance = tempSum / numberOfSimulations;
		latticeSizeStdDev = Math.sqrt(latticeSizeVariance);
		
		
		// Print Results
		System.out.println("----------------------- RESULTS -----------------------" + "\n" +
						   "Distance Mean: "     		   	   + distanceMean + "\n" +
						   "Distance Variance: " 		   	   + distanceVariance + "\n" +
						   "Distance Standard Deviation: " 	   + distanceStdDev + "\n\n" + 
						   "Lattice Size Mean: "               + latticeSizeMean + "\n" +
						   "Lattice Size Variance: "       	   + latticeSizeVariance + "\n" +
						   "Lattice Size Standard Deviation: " + latticeSizeStdDev + "\n" +
						   "-------------------------------------------------------\n\n");
	}
}


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

class Simulation
{
	private boolean[][] visitedXY;		// Visited Points Matrix Map
	private int simulationNumber,
				numberOfSteps,
				distance,
				latticeSize,
				startingX, startingY,
				endX, endY,
				maxX, maxY,
				minX, minY;
	ArrayList<Point> path;
	
	public Simulation(int simNumber, int startingXCoord, int startingYCoord)
	{
		this.simulationNumber = simNumber;
		this.visitedXY 	   = new boolean[1000+1][1000+1];
		this.numberOfSteps = 0;
		this.distance 	   = 0;
		this.latticeSize   = 0;
		this.startingX 	   = startingXCoord;
		this.startingY 	   = startingYCoord;
		this.endX 		   = startingXCoord;
		this.endY 		   = startingYCoord;
		this.maxX 		   = startingXCoord;
		this.maxY 		   = startingYCoord;
		this.minX 		   = startingXCoord;
		this.minY 		   = startingYCoord;
		this.path 		   = new ArrayList<Point>();
		
		runSimulation();
	}
	
	private void runSimulation()
	{
		int currentX = this.startingX;
		int currentY = this.startingY;	
		
		visitedXY[getIndexedValue(currentX)][getIndexedValue(currentY)] = true;
		path.add(new Point(currentX, currentY));
		
		ArrayList<Point> availNeighbors = availableNeighbors(currentX, currentY);
		
		//System.out.println("Started at: " + currentX + "," + currentY + "\n");
		
		while ( availNeighbors.size() != 0  && numberOfSteps <= 5000)
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
			
			// Check if the current X and Y Coordinates are min or max X,Y
			if (currentX > this.maxX) this.maxX = currentX;
			if (currentY > this.maxY) this.maxY = currentY;
			if (currentX < this.minX) this.minX = currentX;
			if (currentY < this.minY) this.minY = currentY;
			
			// Mark it visited and add it to the path
			visitedXY[getIndexedValue(currentX)][getIndexedValue(currentY)] = true;
			path.add(new Point(currentX, currentY));
			
			// Find the next available neighbors
			availNeighbors = availableNeighbors(currentX, currentY);
			
			// One step completed
			numberOfSteps++;
		}
		
		// Ending X and Y Coordinates
		this.endX = currentX;
		this.endY = currentY;
		
		// Calculate Distance
		this.distance = (Math.abs(this.startingX - this.endX) + Math.abs(this.startingY - this.endY));
		
		// Calculate Lattice Size
		this.latticeSize = (this.maxX - this.minX) * (this.maxY - this.minY);
		
		
		System.out.println("•Simulation " + this.simulationNumber );
		printPath();
		System.out.println("Number of Steps: " + this.numberOfSteps);
		System.out.println("Distance: " + this.distance);
		System.out.println("Lattice Size: " + this.latticeSize + "\n\n");
	}
	
	
	// Get index value of a point on the visitedXY Matrix
	private int getIndexedValue(int coord)
	{
		int index = coord;
		if (coord < 0) index = (2*Math.abs(coord)) - 1;
		else if (coord > 0) index = 2*Math.abs(coord);
		return index;
	}
	
	// Get available neighbors from a point
	private ArrayList<Point> availableNeighbors(int currentX, int currentY)
	{
		ArrayList<Point> availNeighbors = new ArrayList<Point>();
		
		// Check West
		int x = currentX-1;
		int y = currentY;
		if (isPointAvailable(x, y) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check East
		x = currentX+1;
		y = currentY;
		if (isPointAvailable(x, y) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check South
		x = currentX;
		y = currentY-1;
		if (isPointAvailable(x, y) == true)
			availNeighbors.add(new Point(x, y));
		
		// Check North
		x = currentX;
		y = currentY+1;
		if (isPointAvailable(x, y) == true)
			availNeighbors.add(new Point(x, y));
		
		return availNeighbors;
	}
	
	
	// Check if a point is previously visited or not
	private boolean isPointAvailable(int x, int y)
	{
		int currentSize = this.visitedXY.length;
		int xIndex = getIndexedValue(x);
		int yIndex = getIndexedValue(y);
		
		if ((Math.abs(x) > currentSize/2) || (Math.abs(y) > currentSize/2))
			resizeVisitedXYMatrix();
		
		if (this.visitedXY[xIndex][yIndex] == false)
			return true;
		
		return false;	
	}
	
	
	// Resize the current VisitedXY Matrix if its overflowed
	private void resizeVisitedXYMatrix()
	{
		int newSize = (this.visitedXY.length * 2) + 1;
		
		boolean[][] newVisitedXYMatrix = new boolean[newSize][newSize];
		
		for (int i = 0; i < this.visitedXY.length; i++)
		{
			for (int j = 0; j < this.visitedXY.length; j++)
			{
				newVisitedXYMatrix[i][j] = this.visitedXY[i][j];
			}
		}
		
		this.visitedXY = newVisitedXYMatrix;
	}
	
	
	// Print the path of the simulation
	private void printPath()
	{
		for (Point p : this.path)
			System.out.print("(" + p.x + "," + p.y + ")-->");
		System.out.print("END\n\n");
	}
	
	// Print the visited xy point map matrix
	private void printVisitedPointsMatrix()
	{
		int size = this.visitedXY.length;
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
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
	
	
	// Get the path length of this simulation
	public int getNumberOfSteps()
	{
		return this.numberOfSteps;
	}
	
	// Get the starting xy coordinates of this simulation
	public int[] getStartingXY()
	{
		int[] xy = {this.startingX, this.startingY};
		return xy;
	}
	
	// Get the ending xy coordinates of this simulation
	public int[] getEndingXY()
	{
		int[] xy = {this.endX, this.endY};
		return xy;
	}
	
	// Get the max X and max Y points of this simulation
	public int[] getMaxXY()
	{
		int[] xy = {this.maxX, this.maxY};
		return xy;
	}
	
	// Get the min X and min Y points of this simulation
	public int[] getMinXY()
	{
		int[] xy = {this.minX, this.minY};
		return xy;
	}
	
	// Get the distance of thie simulation
	public int getDistance()
	{
		return this.distance;
	}
	
	// Get the lattice size of this simulation
	public int getLatticeSize()
	{
		return this.latticeSize;
	}
}

