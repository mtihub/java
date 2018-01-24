/* Program Name: VoteSystemWithHashMap
 * 
 *    Date                Author           
 * 11/17/2015        Md Tanvirul Islam       
 */


package votingSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



// Entry Object
class MapEntry<K, V>
{
	private K k;
	private V v;
	
	public MapEntry(K key, V value)
	{
		k = key;
		v = value;
	}
	
	public K getKey()   { return k; }
	public V getValue() { return v; }
	
	protected void setKey(K key) { k = key; }
	protected V setValue(V value) 
	{
		V oldValue = v;
		v = value; 
		return oldValue;
	}
}


// Linear Probing HashMap class
class ProbeHashMap<K, V>
{
	// array
	private MapEntry<K, V>[] table;
	
	// DEFUNCTION element
	private MapEntry<K, V> DEFUNCTION = new MapEntry<K ,V> (null, null);
	
	
	protected int numberOfEntries = 0;
	protected int tableCapacity;
	
	// Values used in the hash function
	private int prime;
	private long a_scale;
	private long b_shift;
	
	
	// Constructor
	public ProbeHashMap(int cap, int p)
	{
		prime = p;
		tableCapacity = cap;
		
		// Make the array table
		table = (MapEntry<K, V> []) new MapEntry[tableCapacity];
		
		// quantities for hash function
		Random rand = new Random();
		a_scale = rand.nextInt(prime - 1) + 1;
		b_shift = rand.nextInt(prime);

		
	}
	
	public ProbeHashMap(int cap) { this(cap, 109345121); } // Default Prime Number 
	public ProbeHashMap() { this(17); } // Default Capacity
	
	
	// A method that uses MAD hash function to calculate the hashValue of a given hashCode
	private int hashValue(K key)
	{
		return (int) ( ((Math.abs(a_scale*key.hashCode() + b_shift)) % prime) % tableCapacity );
	}
	
	
	// Returns true if the index is empty or DEFUNCTION
	private boolean isAvailable(int j)
	{
		return (table[j] == null || table[j] == DEFUNCTION);
	}
	
	
	// Function that basically does the linear probing
	// Takes the hashValue (or index) h, and the key as input
	// Finds the available index, starting from the hashValue index
	// If the key already exists, returns that index
	// Returns index as the form -(index+1)
	private int findSlot(int h, K k)
	{
		int avail = -1;
		int j = h;
		
		do 
		{
			if (isAvailable(j))
			{
				if (avail == -1) avail = j;
				if (table[j] == null) break;
			}
			else if (table[j].getKey().equals(k))
				return j;
			j = (j+1) % tableCapacity;
			
		} while (j != h); // completes iteration once
		
		// Return form is -(avail+1).
		// It returns positive index if the key already exists, the negative sign differentiates it from that
		// plus 1 in order to account for index 0
		return -(avail + 1);
	}
	
	
	// bucketGet method. Takes the hashValue and the key as input, and finds that in the "bucket"
	private V bucketGet(int h, K k)
	{
		int j = findSlot(h, k);
		
		if (j < 0) return null;
		return table[j].getValue();
	}
	
	
	// bucketPut method. Takes the hashValue and the key as input, and puts that in the "bucket"
	private V bucketPut(int h, K k, V v)
	{
		int j = findSlot(h, k);
		
		// if (j >= 0)
		//	 return table[j].setValue(v);
		
		
		// If the element already exists, its going to add 1 to the value it holds.
		// Basically its counting the votes
		if (j >= 0)
		{
			Integer intValue = (int) table[j].getValue() + 1;
			V newValue = (V)intValue;
			return table[j].setValue(newValue);
		}
		
		
		// findSlot returns index in the form -(index+1). 
		// -(j+1) converts it to valid index
		table[-(j + 1)] = new MapEntry<K, V>(k, v);
		
		// Increment numberOfEntries
		numberOfEntries++;
		
		return null;
	}
	
	
	
	// bucketRemove method. Takes the hashValue and the key as input, and finds that in the "bucket" and removes it
	protected V bucketRemove(int h, K k)
	{
		int j = findSlot(h, k);
		
		if (j < 0) return null; // The value doesn't exits in the bucket array
		
		V removedValue = table[j].getValue();
		table[j] = DEFUNCTION;
		numberOfEntries--;
		
		return removedValue;
	}
	
	
	// An Iterable collection of all the entries in the bucket
	public Iterable<MapEntry<K, V>> entrySet()
	{
		ArrayList<MapEntry<K, V>> buffer = new ArrayList<>(numberOfEntries);
		
		for (int h = 0; h < tableCapacity; h++)
			if (!isAvailable(h)) 
				buffer.add(table[h]);
		
		return buffer;
	}
	
	
	// A method that resizes the hash table
	private void resize(int newCapacity)
	{
		ArrayList<MapEntry<K, V>> buffer = new ArrayList<>(numberOfEntries);
		
		for (MapEntry<K, V> e : entrySet())
			buffer.add(e);
		
		tableCapacity = newCapacity;
		table = (MapEntry<K, V> []) new MapEntry[tableCapacity];
		
		numberOfEntries = 0; // put() method will increment it and make it correct after the end of loop
		
		for (MapEntry<K, V> e : buffer)
			put(e.getKey(), e.getValue());
	}
	
	
	
	
	// A method that returns the size of the hashTable
	public int size() { return numberOfEntries; }
	
	
	// A method that gets the value that the given key holds
	public V get(K key) { return bucketGet(hashValue(key), key); }
	
	
	// A method that removes the element of given key
	public V remove(K key) { return bucketRemove(hashValue(key), key); }
	
	
	// A method that puts a new element in the table
	public V put(K key, V value)
	{
		V answer = bucketPut(hashValue(key), key, value);
		
		
		// if (numberOfEntries > (tableCapacity / 2))
		//	 resize(2*tableCapacity - 1);
		
		
		// The load factor = n/N, which should be less than half for better performance
		// It makes sure that the load factor stays less than half
		if (numberOfEntries > tableCapacity / 2)     
	    {
	    	if (numberOfEntries == 1)
	    		resize(2 * tableCapacity);
	    	else 
	    		resize(2 * tableCapacity - 1); 
	    }  
		
		return answer;
	}
	
	
	
	// A method for a visual representation of the table
	public void printHashTable()
	{
		System.out.println("\n");
		for (MapEntry<K, V> entry : entrySet())
		{
			System.out.println("Voter ID: " + entry.getKey() + ", Votes Received: " + entry.getValue());
		}
		System.out.println("\n\n");
	}
	
}



// Main Class
public class HashtableVoting 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		int numberOfVotes = 0;
		boolean isValid = false;
		
		
		// Take valid inputs
		while (isValid == false)
		{
			try
			{
				numberOfVotes = input.nextInt();
			}
			catch (InputMismatchException excp)
			{
				System.out.println("\nInvalid Total Number of Votes. Try again.\n");
				input.nextLine();
				continue;
			}
			
			isValid = true;
		}
		
		
		// Create a hash table, using the voter ID as keys or indices
		ProbeHashMap<Integer, Integer> voteTable = new ProbeHashMap<Integer, Integer>(numberOfVotes);
		
		int counter = numberOfVotes;
		isValid = false;
		
		
		// Take valid input
		while (isValid == false)
		{
			while (counter > 0)
			{
				try
				{
					int ID = input.nextInt();
					voteTable.put(ID, 1); // Put in the hash table that holds ID as the key
					counter--;
				}
				catch (InputMismatchException excp)
				{
					System.out.println("\nInvalid Voter ID. Try again.\n");
					input.nextLine();
					continue;
				}
			}
			
			isValid = true;
		}
		
		input.close();
		
		//voteTable.printHashTable();
		
		// Get winner
		int[] winner = getWinner(voteTable, numberOfVotes);
		
		// Display Results
		if (winner == null)
			System.out.println("no candidate received a majority of votes");
		else
			System.out.println(winner[0] + " wins with " + winner[1] + " votes out of " + numberOfVotes);

	}
	
	
	// A method that accepts the hash table of voters and the total number of votes
	// it outputs an integer array with the winners voter ID that person's number of votes
	// Returns null if no one wins
	public static int[] getWinner(ProbeHashMap<Integer, Integer> voteHashMap, int totalNumberOfVotes)
	{
		int[] winner = {-1, -1};
		
		for (MapEntry<Integer, Integer> entry : voteHashMap.entrySet())
		{
			// Wins only if the person get votes strictly higher than half of the total votes
			if (entry.getValue() > (int)(totalNumberOfVotes / 2) )
			{
				winner[0] = entry.getKey();
				winner[1] = entry.getValue();
				break;
			}
		}
		
		if (winner[0] == -1) return null;
		
		return winner;
	}

}

