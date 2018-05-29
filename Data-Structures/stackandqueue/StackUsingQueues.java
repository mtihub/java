/* Assignment 02, Part 1
 * Program Name: StackUsingQueues
 * 
 *    Date                Author           
 * 10/08/2015        Md Tanvirul Islam       
 */


package stackWithQueues;


// Main Class
public class StackUsingQueues 
{
	public static void main(String[] args) 
	{
		// Creating a test Stack
		Stack<Integer> stack1 = new Stack<Integer>();
		
		// Creating the a stack:  | 5 | 4 | 3 | 2 | 1 |
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(4);
		stack1.push(5);
		
		// Printing the initial stack
		System.out.println("Initial Stack: ");
		stack1.print();
		
		
		
		// Performing various operations to test the code
		System.out.println("\nPopped Data: " + stack1.pop() + "\n");
		System.out.println("Popped Data: " + stack1.pop() + "\n");
		
		System.out.println("Current Stack: ");
		stack1.print();
		
		stack1.push(10);
		stack1.push(11);
		
		System.out.println("\n\n\nFinal Stack: ");
		stack1.print();
	}
}


// Stack Class
class Stack<E>
{
	// Using two queues to implement stack
	Queue<E> current = new Queue<E>();
	Queue<E> tempQueue = new Queue<E>();
	
	public Stack() {}
	
	public boolean isEmpty() { return current.isEmpty(); }
	
	
	// push() method
	public void push(E element)
	{
		if (current == null)
			current.enque(element);
		else
		{
			while (! current.isEmpty())
				tempQueue.enque(current.deque());
			current.enque(element);
			while (! tempQueue.isEmpty())
				current.enque(tempQueue.deque());
		}
	}
	
	
	// pop() method
	public E pop()
	{
		if (isEmpty()) 
			return null;
		return current.deque();
	}
	
	
	// top() method
	public E top()
	{
		if (isEmpty()) 
			return null;
		return current.first();
	}
	
	
	// A method to print the stack
	public void print()
	{
		Queue<E> temp = new Queue<E>();
		
		System.out.println();
		
		while (! current.isEmpty())
		{
			E tempData = current.deque();
			System.out.print(tempData + " ");
			temp.enque(tempData);
		}
		
		System.out.println();
		
		current = temp;
		temp = null;
	}
}



// Queue class
class Queue<E>
{
	// Singly Linked List class
	// Used to make Linked Queues
	protected static class SinglyLinkedList<E>
	{
		// Node Class
		private static class Node<E>
		{
			E data;
			Node<E> nextNode;
			
			public Node(E inputObject, Node<E> pointToNode)
			{
				data = inputObject;
				nextNode = pointToNode;
			}
			
			protected E getData() {return data;}
			//protected Node<E> getNextNode() {return nextNode;}
		}
		
		
		private Node<E> head;
		private Node<E> tail;
		private int size;
		
		// Constructor
		public SinglyLinkedList()
		{
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		// A method to get size
		public int size()
		{
			return this.size;
		}
	
		// A method that tells if the list is empty or not
		public boolean isEmpty()
		{
			return (this.size == 0);
		}
		
		// A method to add an element up front of the list
		public void addAtFront(E item)
		{
			if ( isEmpty() )
			{
				this.head = new Node<E>(item, null);
				this.tail = head;
				this.size++;
			}
			else
			{
				this.head = new Node<E>(item, head);
				this.size++;
			}
		}

		// A method to add an element at the back of the list
		public void addAtBack(E item)
		{
			if ( isEmpty() )
			{
				this.head = new Node<E>(item, null);
				this.tail = head;
				this.size++;
			}
			else
			{
				Node<E> newTailNode = new Node<E>(item, null);
				this.tail.nextNode = newTailNode;
				this.tail = newTailNode;
				this.size++;
			}
		}
		
		// A method to remove the first element of the list
		public E removeFirst()
		{
			if ( isEmpty() ) 
				return null;
			
			E removedData = this.head.getData();
			
			this.head = head.nextNode;
			
			if (this.head == null)
				this.tail = null;
			
			this.size--;
			return removedData;
		}
		
		// A method to return the first element
		public E getFirst()
		{
			return this.head.getData();
		}
	}


	// Linked Queue
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public Queue(){}
	
	public int size() {return list.size();}
	public boolean isEmpty() {return list.isEmpty();}
	
	public void enque(E element) {list.addAtBack(element);} 
	
	public E first() {return list.getFirst();}
	public E deque() {return list.removeFirst();}
}



