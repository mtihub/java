/* Program Name: ShareExchange_DoubleActionSystem
 * 
 *    Date                Author           
 * 10/27/2015        Md Tanvirul Islam       
 */



package stockTrading;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;




// Min heap comparator.It has default comparator properties
class MinHeapComparator<E> implements Comparator<E>
{
	public int compare(E arg1, E arg2) throws ClassCastException 
	{
		return ((Comparable<E>) arg1).compareTo(arg2);
	}
}

// Max heap comparator
class MaxHeapComparator<E> implements Comparator<E>
{
	public int compare(E arg1, E arg2) throws ClassCastException
	{
		if ( ((Integer) arg1) < ((Integer) arg2) )
			return 1;

		else if ( ((Integer) arg1) > ((Integer) arg2) )
			return -1;

		else
			return 0;
	}
}


// HeapPriorityQueue Class
class HeapPriorityQueue<K,V>
{
	// Entry object of the priority queue
	protected static class PQEntry<K,V>
	{
		private K k;
		private V v;
		
		public PQEntry(K key, V value)
		{
			k = key;
			v = value;
		}
		
		public K getKey() { return k; }
		public V getValue() { return v; }
		
		protected void setKey(K key) { k = key; }
		protected void setValue(V value) { v = value; }
	}
	
	
	private String heapType;
	private Comparator<K> comp;
	private ArrayList<PQEntry<K, V>> heap = new ArrayList<>();
	
	
	public HeapPriorityQueue(String type, Comparator<K> c)
	{
		this.heapType = type;
		this.comp = c;
	}
	
	
	protected int parent (int positionIndex) { return (positionIndex - 1) / 2; }
	protected int left   (int positionIndex) { return (positionIndex * 2) + 1; }
	protected int right  (int positionIndex) { return (positionIndex * 2) + 2; }
	
	public int size() {return heap.size(); }
	public String heapType() { return this.heapType; }
	
	protected boolean hasLeft  (int positionIndex) { return left(positionIndex)  < heap.size(); } 
	protected boolean hasRight (int positionIndex) { return right(positionIndex) < heap.size(); }
	
	
	
	protected int compare(PQEntry<K,V> a, PQEntry<K,V> b)
	{
		return comp.compare(a.getKey(), b.getKey());
	}
	
	protected void swap(int i, int j)
	{
		PQEntry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	protected void upheap(int j)
	{
		while (j > 0)
		{
			int p = parent(j);
			
			if (compare(heap.get(j), heap.get(p)) >= 0)
				break;
			
			swap(j, p);
			j = p;
		}
	}
	
	protected void downheap(int j)
	{
		while (hasLeft(j))
		{
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			
			if (hasRight(j))
			{
				int rightIndex = right(j);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex;
			}
			
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break;
			
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}
	
	
	protected boolean checkKey(K key) throws IllegalArgumentException
	{
		try
		{
			return (comp.compare(key, key) == 0);
		} 
		catch (ClassCastException e)
		{
			throw new IllegalArgumentException("Incompatible Key");
		}
		
	}
	
	public PQEntry<K,V> insert(K key, V value) throws IllegalArgumentException
	{
		checkKey(key);
		
		PQEntry<K,V> newEntry = new PQEntry<>(key, value);
		heap.add(newEntry);
		upheap(heap.size() - 1);
		
		return newEntry;
	}

	
	public PQEntry<K,V> first() 
	{
		if (heap.isEmpty()) return null;
		return heap.get(0);
	}
	
	
	public PQEntry<K,V> removeFirst()
	{
		if (heap.isEmpty()) return null;
		
		PQEntry<K, V> deletedValue = heap.get(0);
		swap(0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		downheap(0);
		
		return deletedValue;
	}
	
	public PQEntry<K, V> last()
	{
		if (heap.isEmpty()) return null;
		return (heap.get(heap.size() - 1));
	}
	
	
	public void print()
	{
		System.out.println(this.heapType);
		for (PQEntry<K, V> currentEntry : heap)
		{
			System.out.println("Quantity: "  + currentEntry.getValue()  +
					           "    Rate: "  + currentEntry.getKey()   );
		}
		System.out.println("\n");
	}
}


// Main Class
public class DoubleActionSystem 
{
	// Two static variable to keep track of total exchanges and values
	private static int exchanges = 0;
	private static int totalValue = 0;
	
	public static void main(String[] args) 
	{
		// Two heaps to store sell and buy shares
		// Sell-Heap is a min-heap
		// Buy-Heap is a max-heap
		HeapPriorityQueue<Integer, Integer> sellHeap = new HeapPriorityQueue<>("Sell-Heap", new MinHeapComparator<Integer>());
		HeapPriorityQueue<Integer, Integer> buyHeap  = new HeapPriorityQueue<>("Buy-Heap",  new MaxHeapComparator<Integer>());
		
		Scanner input = new Scanner(System.in);
		
		// Accepted input patterns
		String sellInputPattern = "sell (\\d+) shares at (\\d+) each";
		String buyInputPattern  = "buy (\\d+) shares at (\\d+) each" ;
		String exitPattern      = "";
		
		
		// Take a VALID input, and after taking each input match the stored stocks 
		// HIT ENTER TWICE TO STOP TAKING INPUTS AND OUTPUT THE RESULT
		while(true)
		{
			
			String action = input.nextLine();
			
			// Check if input is valid
			if (!Pattern.matches(sellInputPattern, action) &&
			    !Pattern.matches(buyInputPattern, action)  &&
				!Pattern.matches(exitPattern, action))
			{
				System.out.println("\nInvalid Input. Please Try Again.\n\n");
				continue;
			}
			
			
			// Entering empty line breaks the loop
			if (action.equalsIgnoreCase(""))
				break;
				
			// If the share is a sell
			else if (action.charAt(0) == 's' || action.charAt(0) == 'S')
			{
				insertData(action, sellHeap);
				matchStocks(buyHeap, sellHeap);
			}
			
			// If the share is a buy
			else if (action.charAt(0) == 'b' || action.charAt(0) == 'B')
			{
				insertData(action, buyHeap);
				matchStocks(buyHeap, sellHeap);
			}
		}
		
		input.close();
		
		
		// Output the Result
		System.out.println("\n"                + 
				           "shares exchanged " + exchanges +
				           " total value "     + totalValue);
		
	}
	
	
	// Inserting the data from the input line to a heap
	protected static void insertData(String inputString,
			                         HeapPriorityQueue<Integer, Integer> heap)
	{
		String[] inputStringArray = inputString.split(" ");
		int key_Rate  = Integer.parseInt(inputStringArray[4]);
		int quantity_Value = Integer.parseInt(inputStringArray[1]);
		heap.insert(key_Rate, quantity_Value);
	}
	
	
	// Check if any of the two heaps are empty
	protected static boolean isEmpty(HeapPriorityQueue<Integer, Integer> buyHeap,
                                     HeapPriorityQueue<Integer, Integer> sellHeap)
	{
		return (buyHeap.first() == null || sellHeap.first() == null);
	}
	
	
	// Take two heaps as an input and match the shares 
	protected static void matchStocks(HeapPriorityQueue<Integer, Integer> buyHeap,
			                          HeapPriorityQueue<Integer, Integer> sellHeap)
	{
		if (isEmpty(buyHeap, sellHeap))
			return;
		
		// If there is a buy-share that is greater or equal to a sell-share, then match
		while (buyHeap.first().getKey() >= sellHeap.first().getKey())
		{
			// Increment exchange
			exchanges  +=  Math.min(buyHeap.first().getValue(), sellHeap.first().getValue());
			
			// Increment total value
			totalValue += ((buyHeap.first().getKey()) * Math.min(buyHeap.first().getValue(), sellHeap.first().getValue()));
			
			// Finish the exchange
			finishExchange(buyHeap, sellHeap);
			
			// See if any of the heaps are empty after finishing the exchange
			if (isEmpty(buyHeap, sellHeap)) 
				break;
		}
	}
	
	
	// After matching, finish exchange buy modifying the number of shares in the storage
	protected static void finishExchange(HeapPriorityQueue<Integer, Integer> buyHeap,
                                         HeapPriorityQueue<Integer, Integer> sellHeap)
	{
		if (buyHeap.first().getValue() < sellHeap.first().getValue())
		{
			sellHeap.first().setValue(sellHeap.first().getValue() - buyHeap.first().getValue());
			buyHeap.removeFirst();
		}
		else if (buyHeap.first().getValue() > sellHeap.first().getValue())
		{
			buyHeap.first().setValue(buyHeap.first().getValue() - sellHeap.first().getValue());
			sellHeap.removeFirst();
		}
		else
		{
			buyHeap.removeFirst();
			sellHeap.removeFirst();
		}
	}

}

