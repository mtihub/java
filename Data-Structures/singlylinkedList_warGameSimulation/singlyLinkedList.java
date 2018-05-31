/* Program Name: SinglyLinkedList
 * 
 *    Date                Author           
 * 09/22/2015        Md Tanvirul Islam       
 */


package singlyLinkedList;


class List<T>
{
	// Node Class
	private static class Node<T>
	{
		T data;
		Node<T> nextNode;
		
		public Node(T inputObject, Node<T> pointToNode)
		{
			data = inputObject;
			nextNode = pointToNode;
		}
		
		protected T getData() {return data;}
		protected Node<T> getNextNode() {return nextNode;}
	}
	
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	// Constructor
	public List()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	// A method to get size
	public int getSize()
	{
		return this.size;
	}
	
	
	// A method to manually count size (Needed for Merging and Intersection-Point methods)
	public int countSize()
	{
		if (this.head == null)
		{
			this.size = 0;
			return this.size;
		}
		else
		{
			Node<T> position = this.head;
			this.size = 1;
			
			while (position.getNextNode() != null)
			{
				this.size++;
				position = position.getNextNode();
			}
			
			return this.size;
		}
	}
	
	
	// A method that tells if the list is empty or not
	public boolean isEmpty()
	{
		return (this.size == 0);
	}
	
	
	
	// A method to add an element up front of the list
	public void addAtFront(T item)
	{
		if ( isEmpty() )
		{
			this.head = new Node<T>(item, null);
			this.tail = head;
			this.size++;
		}
		else
		{
			this.head = new Node<T>(item, head);
			this.size++;
		}
	}

	
	// A method to add an element at the back of the list
	public void addAtBack(T item)
	{
		if ( isEmpty() )
		{
			this.head = new Node<T>(item, null);
			this.tail = head;
			this.size++;
		}
		else
		{
			Node<T> newTailNode = new Node<T>(item, null);
			this.tail.nextNode = newTailNode;
			this.tail = newTailNode;
			this.size++;
		}
	}
	
	
	
	// A method to remove the first element of the list
	public T removeFirst()
	{
		if ( isEmpty() ) 
			return null;
		
		T removedData = this.head.getData();
		
		this.head = head.nextNode;
		
		if (this.head == null)
			this.tail = null;
		
		this.size--;
		return removedData;
	}
	
	
	// A method to remove the last element of the list
	public T removeLast()
	{
		if ( isEmpty() ) 
			return null;
		
		T removedItem = tail.getData();
		
		if (this.size == 1)
		{
			this.head = this.tail = null;
			size--;
		}
		else
		{
			Node<T> current = this.head;
			
			while (current.nextNode.nextNode != null)
			{
				current = current.nextNode;
			}
			
			this.tail = current;
			current.nextNode = null;
			size--;
		}
		
		return removedItem;
	}
	
	
	// // A method to remove all elements of a specific value from the list
	public void removeElement(T element)
	{
		if ( isEmpty() ) return;
		
		outerLoop:
		while (true)
		{	
			if (this.size == 1)
			{
				if (this.head.getData() == element)
				{
					this.head = this.tail = null;
					this.size--;
				}
				break outerLoop;
			}
			
			else if (this.head.getData().equals(element)) 
			{
				this.head = head.nextNode;
				size--;
				continue outerLoop;
			}
			
			else
			{
				Node<T> currentPosition = this.head;
				
				while (currentPosition.getNextNode() != null)
				{
					if (currentPosition.getNextNode().getData().equals(element))
					{
						currentPosition.nextNode = currentPosition.nextNode.nextNode;
						size--;
					}
					else
					{
						currentPosition = currentPosition.nextNode;
					}
				}
				break outerLoop;
			}
		}
	}
	
	

	// A method to merge current list with the provided list at the provided location.
	// Written to test getIntersectionPointWith method
	public void mergeListAt(List<T> list, T element)
	{
		Node<T> givenListPosition = list.head;
		
		while (givenListPosition.getData() != element)
		{
			givenListPosition = givenListPosition.getNextNode();
		}
		
		this.tail.nextNode = givenListPosition;
		this.tail = list.tail;
		
		this.size = countSize();
	}
	
	
	// Find an intersection point of two lists
	public T getIntersectionPointWith(List<T> list)
	{
		Node<T> startPosition1 = this.head;
		Node<T> startPosition2 = list.head;
		
		if (startPosition1.equals(startPosition2))
			return startPosition1.getData();
		
		int sizeOfList1 = this.countSize();
		int sizeOfList2 = list.countSize();
		int difference = sizeOfList1 - sizeOfList2;
		
		if (difference > 0)
		{
			while (difference != 0)
			{
				startPosition1 = startPosition1.getNextNode();
				difference--;
			}
		}
		else if (difference < 0)
		{
			difference = Math.abs(difference);
			while (difference != 0)
			{
				startPosition2 = startPosition2.getNextNode();
				difference--;
			}
		}
		
		while (startPosition1 != null && startPosition2 != null)
		{
			if (startPosition1.equals(startPosition2))
				return startPosition1.getData();
				
			else
			{
				startPosition1 = startPosition1.getNextNode();
				startPosition2 = startPosition2.getNextNode();
			}
		}
		
		return null;
	}
	
	
	
	// A method to reverse the list with recursion
	public void reverseList()
	{
		reverseList(this.head);
	}
	
	private void reverseList(Node<T> listHead)
	{
		if (listHead.nextNode == null)
		{
			this.head = listHead;
			return;
		}
		reverseList(listHead.nextNode);
		
		Node<T> q = listHead.nextNode;
		q.nextNode = listHead;
		listHead.nextNode = null;
	}
	


	// A method to create a cycle in the list
	// Written to test hasCycle method
	public void makeCycle(List<T> list)
	{
		list.tail.nextNode = list.head;
	}
	
	
	// A method that checks if the list has cycles
	public boolean hasCycle()
	{
		return hasCycle(this.head);
	}
	
	private boolean hasCycle(Node<T> head)
	{
	   Node<T> slower, faster;
	   slower = head;
	   faster = head.nextNode;
	   
	   while(true) 
	   {
	      if( faster == null || faster.nextNode == null)
	    	  return false;
	      
	      else if (faster == slower || faster.nextNode == slower)
	         return true;
	     
	      else
	      {
	         slower = slower.nextNode;
	         faster = faster.nextNode.nextNode;
	      }
	   }
	}
	
	
	
	// A method to print the element of the list, provided the list is not empty or cycled
	@Override
	public String toString() 
	{
		if ( isEmpty() || hasCycle())
			return null;

		StringBuilder outputString = new StringBuilder();
		
		Node<T> current = this.head;
		while (current != null)
		{
			outputString.append("  " + current.data);
			current = current.nextNode;
		}
		
		return outputString.toString();
	}
}


public class singlyLinkedList 
{
	public static void main(String[] args) 
	{
		// Two test lists
		List<Integer> testList1 = new List<Integer> ();
		List<Integer> testList2 = new List<Integer> ();
		
		// Setting up list 1
		testList1.addAtBack(1);
		testList1.addAtBack(2);
		testList1.addAtBack(3);
		testList1.addAtBack(4);
		testList1.addAtBack(99);
		testList1.addAtBack(99);
		testList1.addAtBack(99);
		testList1.addAtBack(5);
		testList1.addAtBack(99);
		
		System.out.println("Test List 1:" + testList1.toString());
		
		// Remove a specific element from list 1
		testList1.removeElement(99);
		System.out.println("Test List 1:" + testList1.toString());
		
		// Check if list 1 has cycles
		if (testList1.hasCycle())
			System.out.println("\nThe list is Cycled\n");
		else
			System.out.println("\nThe list is not Cycled\n");
		
		// Reverse list 1
		testList1.reverseList();
		System.out.println("Test List 1:" + testList1.toString());
		
		System.out.println("\n");
		
		// Set up test list 2
		testList2.addAtBack(-1);
		testList2.addAtBack(-2);
		
		// Merge list 2 with list 1 at the given point
		testList2.mergeListAt(testList1, 3);
		System.out.println("Test List 2:" + testList2.toString());
		
		// Get the intersection point
		System.out.println("The intersection point is: " + testList2.getIntersectionPointWith(testList1));
	
	}
}
