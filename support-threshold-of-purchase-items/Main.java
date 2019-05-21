/* Program Name: FrequentlyBoughtProductSets
 * 
 *    Date                Author           
 * 12/08/2015        Md Tanvirul Islam       
 */


package frequentPurchases;

import java.io.*;
import java.util.*;

 

public class Main 
{
	public static void main(String[] args) 
	{
		/* Reading the Input from a .txt file that has the recorded data-table 
		 * in following form: 
	    
	       1 A,B,C,N 01/03/2013
	       1 B,C,D,A,F 02/05/2013
	       2 A,C,V,N,J 03/03/2013
	       3 A,C,J,D 03/04/2014
	    
		*/
		
		try
		{
			// An array to find out the unique individual products that has been bought.
			int[] uniqueAlphabetProductArray = new int[26];
			
			// An array list holds the unique products bought.
			// It will be used to find combinations of different products sets.
			ArrayList<String> uniqueProducts = new ArrayList<String>();
			
			// Purchases from the data will be stored here
			ArrayList<String> purchases = new ArrayList<String>();
			
			
			Scanner input = new Scanner(System.in);
			
			System.out.print("\nEnter the Threshold Percentage: ");
			final double threshold = (input.nextDouble() / 100.0); // Threshold value
			
			input.close();
			
			// Total purchases
			double totalPurchases = 0.0;
			
			
			FileReader fw = new FileReader("RecordTable.txt");
			BufferedReader bw = new BufferedReader(fw);
			
			
			// Reading from the input, and at the same time finding out
			// the individual products that have been bought
			String str;
			while (( str = bw.readLine() ) != null)
			{
				// Skip the customer ID number and go to the purchase list in the data table
				str = skipToPurchasedItems(str);
				
				
				// Using a string builder to hold each purchases, which will be added to the ArrayList
				StringBuilder sb = new StringBuilder();
				String currentItem;
				
				
				while (str.charAt(1) != ' ')
				{
					if (uniqueAlphabetProductArray[(str.charAt(0) - 65)] == 0) // Using ASCII table
					{
						uniqueAlphabetProductArray[(str.charAt(0) - 65)] = 1;
						uniqueProducts.add(Character.toString(str.charAt(0))); // Adding the unique product to the list as a String
					}
					else 
						uniqueAlphabetProductArray[(str.charAt(0) - 65)]++;
					
					currentItem = str.substring(0, 1);
					sb.append(currentItem); // Add the item to the StringBuilder that holds current purchased Items as a String
					str = str.substring(2);
				}
				
				if (uniqueAlphabetProductArray[(str.charAt(0) - 65)] == 0)
				{
					uniqueAlphabetProductArray[(str.charAt(0) - 65)] = 1;
					uniqueProducts.add(Character.toString(str.charAt(0)));
				}
				else 
					uniqueAlphabetProductArray[(str.charAt(0) - 65)]++;
				
				currentItem = str.substring(0, 1);
				sb.append(currentItem); // Add the item to the StringBuilder that holds current purchased Items as a String
				str = str.substring(2);
			
				// Sort the currently purchases item String and add that to purchases ArrayList
				String currentPurchasesSorted = getSortedCurrentPurchases(sb);
				purchases.add(currentPurchasesSorted);
				
				totalPurchases++; // End of reading a line from file. Increment total purchases
			}
			
			
			bw.close();
			
			
			// Sort the unique products ArrayList
			uniqueProducts = sortUniqueProductsList(uniqueProducts);
		
	
			// All the unique products can have many different combinations, whose support should be compared to threshold value to get the answers
			// But from those combinations, if only one product individually has a support lower than the threshold, that combination won't be on the answer
			// So acquiring individual products that have a support higher than the threshold. Will also greatly reduce time complexity
			ArrayList<String> possibleUniqueProducts = getPossibleAnswerComponents(uniqueProducts,uniqueAlphabetProductArray, totalPurchases, threshold);
			
			// An ArrayList of String arrays will hold all the possible combinations that those unique products will make
			// Each String array is will hold nCi, varying the i.
			ArrayList<String[]> combinations = new ArrayList<String[]>();
			
			// Get the combinations
			for (int i = 1; i <= possibleUniqueProducts.size(); i++)
				combinations.add(getNCR(possibleUniqueProducts, possibleUniqueProducts.size(), i));
			
			
			
			System.out.println();
			
			// FINALLY, PRINT OUTPUT IN THE REQUIRED FORMAT
			for (String[] currentLineOfNCR : combinations)
			{
				for (String currentSet : currentLineOfNCR)
				{
					// If the combination is Frequent, print it.
					if (isFrequent(currentSet, purchases, totalPurchases, threshold))	
					{
						char[] currentSetArray = currentSet.toCharArray();
						
						System.out.print("{");
						for (int i = 0; i < currentSetArray.length; i++)
						{
							System.out.printf("%c", currentSetArray[i]);
							if ((i+1) != currentSetArray.length) System.out.print(",");
						}
						System.out.print("}; ");
					}
				}
			}
		
		}
		
		catch(IOException e)
		{
			System.out.println("SORRY, FILE NOT FOUND \n");
		}
		

	}

	
	
	// All the unique products can have many different combinations, whose support should be compared to threshold value to get the answers
	// But from those combinations, if only one product individually has a support lower than the threshold, that combination won't be on the answer
	// This method checks individual support of the unique products, and returns the ones that has a minimum support
	public static ArrayList<String> getPossibleAnswerComponents(ArrayList<String> uniqueProducts, int[] uniqueAlphabetProductArray, double totalPurchases, double threshold)
	{
		ArrayList<String> possibleProducts = new ArrayList<String>();
		for (String s : uniqueProducts)
			if (checkIndividualSupport(s, uniqueAlphabetProductArray, totalPurchases, threshold))
				possibleProducts.add(s);
		
		return possibleProducts;
		
	}
	
	// Checks if support of a product meets the threshold
	public static boolean checkIndividualSupport(String str, int[] uniqueAlphabetProductArray, double totalPurchases, double threshold)
	{
		char character = str.charAt(0);
		
		if (uniqueAlphabetProductArray[character - 65] / totalPurchases >= threshold)
			return true;
		else
			return false;
	}
	
	
	
	// Takes ArrayList that holds unique products as Strings, and value of n and r as input
	// Returns nCr of those Products
	// This function mainly uses combinationUtil()
	public static String[] getNCR(ArrayList<String> arrayList, int n, int r)
	{
		// A temporary ArrayList to store all combinations one by one
		ArrayList<String> data = new ArrayList<String>(r);
		
		// Initialize the ArrayList for future operations
		for (int i = 0; i < r; i++)
			data.add("");	

		// A string that gets all the nCr combinations
		String currentLineCombinations = getParticularCombintaion(arrayList, data, 0, n-1, 0, r);
		
		// Split that String on Spaces
		// Return an array of Strings that has individual set of combination as its element
		return currentLineCombinations.split("\\s+");
	}
			
	private static String getParticularCombintaion(ArrayList<String> arrayList, ArrayList<String> data, int start, int end, int index, int r)
	{
		if (index == r)
		{
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < r; j++)
				sb.append(data.get(j));
				
			sb.append(" ");
			return sb.toString();		
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
			
		String str = "";
			
		for (int i = start; i <= end && end-i+1 >= r-index; i++)
		{
			data.set(index, arrayList.get(i));
			str += (getParticularCombintaion(arrayList, data, i+1, end, index+1, r));
		}
			
		return str;
	}
	
	
	
	// Check if a Set of product meets the threshold value
	public static boolean isFrequent(String currentSet, ArrayList<String> purchases, double totalPurchases, double threshold)
	{
		// As the Strings were sorted, it will basically check if the string is subsequence of the purchases and if it meets the threshold value
		double subsequenceCount = totalPurchases;
		
		for (String purchase : purchases)
		{
			if (!isSubsequence(currentSet, purchase))
				subsequenceCount--;
			if (subsequenceCount < (totalPurchases * threshold))
				return false;
		}
		
		if ((subsequenceCount / totalPurchases) >= threshold)
			return true;
		else
			return false;
	}
	
	
	// Check if a String is subsequence of another
	public static boolean isSubsequence(String small, String big)
	{
		int truthCounter = small.length();
		
		outerLoop:
		for (int i = 0; i < small.length(); i++)
		{
			for (int j = 0; j <= (big.length() - (small.length()-i)); j++)
			{
				if (big.charAt(j) == small.charAt(i))
				{
					truthCounter--;
					continue outerLoop;
				}
			}
		}
		
		if (truthCounter == 0)
			return true;
		else 
			return false;
	}
	
	
	
	// A method to skip reading the customer ID on the .txt file and move to purchase item list
	public static String skipToPurchasedItems(String str)
	{
		while (str.charAt(0) != ' ')
			str = str.substring(1);
	
		return (str = str.substring(1));
	}
	
	
	
	// Accepts a String Builder Object
	// Returns a sorted String 
	public static String getSortedCurrentPurchases(StringBuilder sb)
	{
		char[] currentPurchasesArray = new char[sb.length()];
		sb.getChars(0, sb.length(), currentPurchasesArray, 0);
		Arrays.sort(currentPurchasesArray);
		
		return (new String(currentPurchasesArray));
	}
	
	
	// Sorts an ArrayList of Strings and Returns it
	public static ArrayList<String> sortUniqueProductsList(ArrayList<String> uniqueProducts)
	{
		Collections.sort(uniqueProducts, new Comparator<String>() 
		{
			@Override
			public int compare(String x, String y) 
			{
				return ((Comparable<String>) x).compareTo(y);
			}
		});
		
		return uniqueProducts;
	}

}
