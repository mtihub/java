import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class Practice1 
{
	public static void main(String[] args) throws IOException 
	{
		//-------------------------------------------------------------------------------------------------------------
		// Collect Data
		//-------------------------------------------------------------------------------------------------------------
		
		String[] data = Files.readAllLines(new File("hollins.dat").toPath()).toArray(new String[0]);
		final int numberOfNodes = Integer.parseInt(((data[0].split(" "))[0]));      // Number of websites
		final int numberOfEdges = Integer.parseInt(((data[0].split(" "))[1]));		// Number of links		
		
		ArrayList<String> websites = new ArrayList<String>(numberOfNodes);			// ArrayList of string that holds the websites
		ArrayList<Edge>   edges    = new ArrayList<Edge>(numberOfEdges);			// ArrayList of Edge object that holds the edge information
		int[] outgoingEdgeCounter = new int[numberOfEdges];							// Record total number of outgoing links from a site
		
		// Read websites
		for (int i = 1; i <= numberOfNodes; i++)
			websites.add( (data[i].split(" "))[1] );
		
		// Read edges
		for (int i = (numberOfNodes+1); i <= (numberOfNodes + numberOfEdges); i++)
		{
			int source = Integer.parseInt(((data[i].split(" "))[0]));
			int destination = Integer.parseInt(((data[i].split(" "))[1]));
			
			edges.add(new Edge(source, destination));
			outgoingEdgeCounter[source-1]++;
		}
		
		
		//-------------------------------------------------------------------------------------------------------------
		// Calculations
		// ------------
		// 
		// Algorithm Used:
		// PR(Pi; 0)   = 1/N
		// PR(Pi; t+1) = (1-d)/N + d * ( (SUM OF) PR(Pj; t)/L(Pj) )
		// Or, in Matrix Notation: R(t+1) = d * transitionMatrix * R(t) + (1-d)/N
		//-------------------------------------------------------------------------------------------------------------
		
		double[][] transition    = new double[numberOfNodes][numberOfNodes];		// Transition matrix
		
		double[][] initialState  = new double[numberOfNodes][1];					// Initial state matrix
		double[][] finalState    = new double[numberOfNodes][1];					// Stores the next state
		double[][] compareState  = new double[numberOfNodes][1];					// Copy of the next state to compare it with the state after it
		
		double initialStateValue = (1.0 / numberOfNodes);							// Initial value of the state matrix elements
		double dampingFactor     = 0.85;											// Damping factor
		double[][] oneMinusDamp  = new double[numberOfNodes][1];					// Matrix that holds the (1-dumpingFactor)/N to do damping calculations later
		
		
		
		// Create Transition Matrix
		for (Edge e : edges)
			transition[e.to - 1][e.from - 1] = 1.0 / (outgoingEdgeCounter[e.from - 1]);
		
		
		// Do Initial Damping Calculations. 
		// Rest of the damping calculations done in the iterative section.
		for (int i = 0; i < numberOfNodes; i++)
		{
			for (int j = 0; j < numberOfNodes; j++)
			{
				transition[i][j] = (dampingFactor * transition[i][j]);
				//transition[i][j] = (1 - dampingFactor) + (dampingFactor * transition[i][j]);
			}
		}
		
		
		/*
		// Print Transition Matrix
		for (int i = 0; i < numberOfNodes; i++)
		{
			for (int j = 0; j < numberOfNodes; j++)
			{
				System.out.print(transition[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		
		// Initialize State Matrix
		// Initialize the Matrix that holds the (1-dumpingFactor)/N at the same time as they are of the same size
		for (int i = 0; i < numberOfNodes; i++)
		{
			initialState[i][0] = initialStateValue;
			oneMinusDamp[i][0] = (1 - dampingFactor) / numberOfNodes;
		}
			
		
		// Iterative Calculations of Next State Until Steady State is Obtained
		System.out.println("CALCULATING......");
		
		// Calculate the first next state matrix with damping calculations	
		finalState = multiply(transition, numberOfNodes, numberOfNodes, initialState, numberOfNodes, 1);
		finalState = add(finalState, oneMinusDamp, numberOfNodes, 1);	// Rest of the damping calculation
		
		
		// Iterative calculations
		DecimalFormat df = new DecimalFormat("#.######");	// Compares 6 decimal places, so Epsilon for threshold = 0.000001
		boolean isStable = false;							// boolean isStable, which is true when the steady state is obtained	
		int printDotsNicely = 0;							// A counter to print dots while the calculation is ongoing
		
		while (isStable == false)
		{
			// Keep a copy of the current next state in the compareState matrix
			for (int i = 0; i < numberOfNodes; i++)
				compareState[i][0] = finalState[i][0];
			
			// Calculate next state with dampind calculations
			finalState = multiply(transition, numberOfNodes, numberOfNodes, finalState, numberOfNodes, 1);
			finalState = add(finalState, oneMinusDamp, numberOfNodes, 1);	// Rest of the damping calculations
			
			// Check if steady state is obtained. If not, repeat the calculation one more time
			for (int i = 0; i < numberOfNodes; i++)
			{
				double number1 = Double.parseDouble(df.format(finalState[i][0]));
				double number2 = Double.parseDouble(df.format(compareState[i][0]));
				
				if (number1 != number2)
					break;
				else 
				{
					if (i == numberOfNodes-1)
						isStable = true;
				}
			}
			
			// Print dots while the calculation is ongoing
			if (printDotsNicely >= 17)
			{
				printDotsNicely = 0;
				System.out.println();
			}
			System.out.print(".");
			printDotsNicely++;
			
		}
		
		
		/*
		// Print Final State
		for (int i = 0; i < numberOfNodes; i++)
		{
			for (int j = 0; j < 1; j++)
			{
				System.out.print(finalState[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		
		
		// Create an ArrayList of State Elements objects, so that it can be sorted and the original index values can be reserved
		SortedStates sortedStates = new SortedStates(numberOfNodes);
		
		for (int i = 0; i < numberOfNodes; i++)
			sortedStates.add(new StateElement(i, finalState[i][0]));
		
		sortedStates.sort();
		
		
		/*
		// Print Sorted State
		for (StateElement e : sortedStates)
		{
			System.out.println(e.index + " " + e.value);
		}
		System.out.println();
		*/
		
		
		// Results
		PrintWriter output = new PrintWriter("output.txt");
		for (StateElement e : sortedStates)
		{
			String outputLine = Integer.toString((e.index + 1)) + " " + websites.get(e.index);
			output.println(outputLine);
			//System.out.println(outputLine);
		}
		output.close();
		
		
		// Done! 
		System.out.println("DONE\n\nResults Printed in 'output.txt'\n");
	}
	
	
	// A function to multiply two matrices
	public static double[][] multiply(double[][] first,  int rowOfFirst,  int colOfFirst, 
									  double[][] second, int rowOfSecond, int colOfSecond)
	{
		double[][] multiply = new double[rowOfFirst][colOfSecond];
		double sum = 0.0;
		for (int i = 0; i < rowOfFirst; i++)
        {
           for (int j = 0; j < colOfSecond; j++)
           {
              for (int k = 0; k < rowOfSecond; k++)
              {
                 sum += (first[i][k]*second[k][j]);
              }
              multiply[i][j] = sum;
              sum = 0;
           }
        }
		
		return multiply;
	}
	
	// A function to add two matrices
	public static double[][] add(double[][] first, double[][] second, int row, int column)
	{
		double[][] sum = new double[row][column];
		for ( int i = 0 ; i < row ; i++ )
	         for ( int j = 0 ; j < column ; j++ )
	        	 sum[i][j] = first[i][j] + second[i][j]; 
		return sum;
	}
	
	// A function to raise the power of a matrix
	public static double[][] raisePower(double[][] matrix, int row, int column, int power)
	{
		if (power <= 1) return matrix;
		else
		{
			double[][] answer = matrix;
			for (int i = 1; i < power; i++)
				answer = multiply(matrix, row, column, answer, row, column);
			return answer;
		}
	}

}


// Edge object. Used to hold the information of an edge
class Edge
{
	protected int from;
	protected int to;
	public Edge(int source, int destination) 
	{
		this.from = source;
		this.to   = destination;
	}
}


// StateElement object. Used in SortedStates class. 
// This object holds the index and the PageRank value
class StateElement
{
	protected int index;
	protected double value;
	public StateElement(int i, double v)
	{
		this.index = i;
		this.value = v;
	}
}

// SortedStates class.
// This class holds the an array of StateElement objects. This is the sorted states array
// The necessity of creating this is to preserve the original indices while sorting the values of the final state
class SortedStates implements Iterable <StateElement>
{
	protected ArrayList<StateElement> sortedStates;
	public SortedStates(int numberOfNodes)
	{
		this.sortedStates = new ArrayList<StateElement>(numberOfNodes);
	}
	
	public void add(StateElement e)
	{
		this.sortedStates.add(e);
	}
	
	public void add(int index, StateElement e)
	{
		this.sortedStates.add(index, e);
	}
	
	public StateElement get(int index)
	{
		return this.sortedStates.get(index);
	}
	
	public int size()
	{
		return this.sortedStates.size();
	}
	
	public void sort()
	{
		Collections.sort(sortedStates, new Comparator<StateElement>() 
										{
	        								@Override 
	        								public int compare(StateElement s1, StateElement s2) 
	        								{
	        									return Double.compare(s2.value, s1.value);
	        								}
										});
	}
	
	@Override
    public Iterator<StateElement> iterator() 
    {
        return sortedStates.iterator();
    }
}

