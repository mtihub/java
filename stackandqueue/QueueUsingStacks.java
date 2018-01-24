/* Assignment 02, Part 2
 * Program Name: QueueUsingStacks
 * 
 *    Date                Author           
 * 10/08/2015        Md Tanvirul Islam       
 */


package queueWithStacks;


// Main Class
public class QueueUsingStacks
{
	public static void main(String[] args) 
	{
		// Creating a test Queue
		Queue<Integer> queue1 = new Queue<Integer>();
		
		// Creating a queue:  | 1 | 2 | 3 | 4 | 5 |
		queue1.enque(1);
		queue1.enque(2);
		queue1.enque(3);
		queue1.enque(4);
		queue1.enque(5);
		
		
		// Performing various operations and printing the result
		System.out.println("Top element of the queue: " + queue1.top() + "\n\n");
		System.out.println("Dequeing the element: " + queue1.deque() + "\n");
		System.out.println("Enqueing a new element" + "\n\n");
		queue1.enque(99);
		System.out.println("Current top element of the queue: " + queue1.top());
		
	}

}


// Queue class
class Queue<E>
{
	// Using two stacks to implement queue
	Stack<E> current = new Stack<E>();
	Stack<E> tempStack = new Stack<E>();
	
	public boolean isEmpty() {return current.isEmpty();}
	
	
	// enque() method
	public void enque(E element)
	{
		if (current.isEmpty()) 
			current.push(element);
		else
		{
			while (! current.isEmpty())
				tempStack.push(current.pop());
			current.push(element);
			while (! tempStack.isEmpty())
				current.push(tempStack.pop());
		}
	}
	
	
	// deque() method
	public E deque()
	{
		if (isEmpty())
			return null;
		return current.pop();
	}
	
	// top() method
	public E top()
	{
		if (isEmpty())
			return null;
		return current.top();
	}
}



// Stack Class
class Stack<E>
{
	// Singly Linked List Class
	// Used to make Linked Stacks
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
	
	
	// Linked Stack
	private SinglyLinkedList<E> list = new SinglyLinkedList<E>();
	
	public Stack() {}
	
	public int size() {return list.size();}
	public boolean isEmpty() {return list.isEmpty();}
	
	public void push(E element) {list.addAtFront(element);}
	public E top() {return list.getFirst();}
	
	public E pop() {return list.removeFirst();}
}
		

