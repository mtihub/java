/* Assignment 02, Part 3
 * Program Name: BinaryTree
 * 
 *    Date                Author           
 * 10/08/2015        Md Tanvirul Islam       
 */



package Tree;


import java.util.ArrayList;
import java.util.List;



// Main Class
public class BinaryTreeMain 
{
	public static void main(String[] args) 
	{
		
		/* Experiment 1
		 * Testing with Tree:  
		 *               3
		 *              / \
		 *             9  20
		 *                /\
		 *               15 7
		 */
		System.out.println("\nTest 1:\n");
		
		// Building Tree
		LinkedBinaryTree<Integer> testTree1 = new LinkedBinaryTree<>();
		testTree1.addRoot(3);
		Node<Integer> root1 = testTree1.root();
		
		testTree1.addLeft(root1, 9);
		testTree1.addRight(root1, 20);
		
		testTree1.addLeft(root1.getRight(), 15);
		testTree1.addRight(root1.getRight(), 7);
		
		
		// Using the method that receives Level Order Traversel array and Prints it
		testTree1.printLevelOrder();
		
		
		
		/* Experiment 2
		 * Testing with Tree:  
		 *               4
		 *              / \
		 *             2   7
		 *            /\   /\
		 *           1  3 6  9
		 */
		System.out.println("\n\n\n\nTest 2:");
		
		// Building Tree
		LinkedBinaryTree<Integer> testTree2 = new LinkedBinaryTree<>();
		testTree2.addRoot(4);
		Node<Integer> root2 = testTree2.root();
		
		testTree2.addLeft(root2, 2);
		testTree2.addRight(root2, 7);
		
		testTree2.addLeft(root2.getLeft(), 1);
		testTree2.addRight(root2.getLeft(), 3);
		
		testTree2.addLeft(root2.getRight(), 6);
		testTree2.addRight(root2.getRight(), 9);
		

		// Print a simple level-order representation of the tree 
		testTree2.printTree();
		
		// Invert tree and print a simple level-order representation of the tree to portray the invertion
		testTree2.invertTree();
		System.out.println();
		testTree2.printTree();

		
		

		/* Experiment 2
		 * 
		 * First test with Tree:  
		 *               4
		 *              / \
		 *             2   9
		 *            /\   /
		 *           1  5 8  
		 *             /
		 *            3
		 * Should be Height-Balanced
		 * 
		 * Second test with Tree:
		 *               7
		 *              / \
		 *             3   10
		 *            /\   
		 *           1  5   
		 *             / \
		 *            4   6
		 * Should NOT be Height-Balanced
		 */
		System.out.println("\n\n\n\nTest 3:");
		
		// Building Tree 1
		LinkedBinaryTree<Integer> testTree3 = new LinkedBinaryTree<>();
		testTree3.addRoot(4);
		Node<Integer> root3 = testTree3.root();
		
		testTree3.addLeft(root3, 2);
		testTree3.addRight(root3, 9);
		
		testTree3.addLeft(root3.getLeft(), 1);
		testTree3.addRight(root3.getLeft(), 5);
		
		testTree3.addLeft(root3.getLeft().getRight(), 3);
		
		testTree3.addLeft(root3.getRight(), 8);

		
		// Check if tree is Height-Balanced
		if (testTree3.isHeightBalanced())
			System.out.println("\nHeight Balanced");
		else
			System.out.println("\nNOT Height Balanced");

		
		
		// Second Test
		// Building Tree 2
		LinkedBinaryTree<Integer> testTree4 = new LinkedBinaryTree<>();
		testTree4.addRoot(7);
		Node<Integer> root4 = testTree4.root();
		
		testTree4.addLeft(root4, 3);
		testTree4.addRight(root4, 10);
		
		testTree4.addLeft(root4.getLeft(), 1);
		testTree4.addRight(root4.getLeft(), 5);
		
		testTree4.addLeft(root4.getLeft().getRight(), 4);
		testTree4.addRight(root4.getLeft().getRight(), 6);
		
		
		// Check if tree is Height-Balanced
		if (testTree4.isHeightBalanced())
			System.out.println("\n\nHeight Balanced");
		else
			System.out.println("\n\nNOT Height Balanced");

		
		
		/* Experiment 4
		 * 
		 * First test with Tree:  
		 *               1
		 *              / \
		 *             2   2
		 *            /\   /\
		 *           3  4 4  3
		 * Should be Mirror to itself
		 * 
		 * Second test with Tree:
		 *               1
		 *              / \
		 *             2   2
		 *                 /\
		 *                4  3
		 * Should NOT be Mirror to itself
		 */
		System.out.println("\n\n\n\nTest 4:\n");
		
		// Building Tree1 
		LinkedBinaryTree<Integer> testTree5 = new LinkedBinaryTree<>();
		testTree5.addRoot(1);
		Node<Integer> root5 = testTree5.root();
		
		testTree5.addLeft(root5, 2);
		testTree5.addRight(root5, 2);
		
		testTree5.addLeft(root5.getLeft(), 3);
		testTree5.addRight(root5.getLeft(), 4);
		
		testTree5.addLeft(root5.getRight(), 4);
		testTree5.addRight(root5.getRight(), 3);
		
		
		// Check is tree is Mirrored
		if (testTree5.isMirror())
			System.out.println("\nThe tree is Mirror to itself");
		else
			System.out.println("\nThe tree is NOT Mirror to itself");
		
		
		
		// Second Test
		// Building Tree 2
		LinkedBinaryTree<Integer> testTree6 = new LinkedBinaryTree<>();
		testTree6.addRoot(1);
		Node<Integer> root6 = testTree6.root();
		
		testTree6.addLeft(root6, 2);
		testTree6.addRight(root6, 2);
		
		testTree6.addLeft(root6.getRight(), 4);
		testTree6.addRight(root6.getRight(), 3);
		
		
		// Check if tree is Mirrored
		if (testTree6.isMirror())
			System.out.println("\n\nThe tree is Mirror to itself");
		else
			System.out.println("\n\nThe tree is NOT Mirror to itself");		
	}

}




// Node used in the Binary Trees 
class Node<E>
{
	private E element;
	private Node<E> parent;
	private Node<E> left;
	private Node<E> right;
	
	public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild)
	{
		element = e;
		parent = above;
		left = leftChild;
		right = rightChild;
	}
	
	public E getElement() {return element;}
	public Node<E> getParent() {return parent;}
	public Node<E> getLeft() {return left;}
	public Node<E> getRight() {return right;}
	
	public void setElement(E e) {element = e;}
	public void setParent(Node<E> parentNode) {parent = parentNode;}
	public void setLeft(Node<E> leftChild) {left = leftChild;}
	public void setRight(Node<E> rightChild) {right = rightChild;}
}



// Linked Binary Tree Class
class LinkedBinaryTree<E>
{
	protected Node<E> root = null;
	private int size = 0;
	
	
	public LinkedBinaryTree() {}
	

	// A method to create a new node and return it
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right)
	{
		return new Node<E>(e, parent, left, right);
	}

	public int size() {return size;}
	public Node<E> root() {return root;}
	
	
	// A method that returns the sibling of the Node
	public Node<E> sibling(Node<E> n)
	{
		Node<E> parent = parent(n);
		
		if (parent == null) return null;
		if (n == left(parent))
			return right(parent);
		else
			return left(parent);
	}
	
	
	// A method that returns the number of children a node has
	public int numChildren(Node<E> n)
	{
		int count = 0;
		if (left(n) != null) count++;
		if (right(n) != null) count++;
		return count;
	}
	
	
	// Some Query methods
	public boolean isEmpty() {return size() == 0;}
	public boolean isInternal(Node<E> n) {return numChildren(n) > 0;}
	public boolean isExternal(Node<E> n) {return numChildren(n) == 0;}
	public boolean isRoot(Node<E> n) {return n == root();}
	
	
	
	// A method to get the parent of a node
	public Node<E> parent(Node<E> n) throws IllegalArgumentException
	{
		return (n.getParent());
	}
	 
	
	// A method to get the left child of a node
	public Node<E> left (Node<E> n) throws IllegalArgumentException
	{
		if (n == null) return null;
		return (n.getLeft());
	}
	
	
	// A method to get the right child of a node
	public Node<E> right (Node<E> n) throws IllegalArgumentException
	{
		if (n == null) return null;
		return (n.getRight());
	}
	
	
	// A method to get add the root of a binary tree
	public Node<E> addRoot(E e) throws IllegalStateException
	{
		if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
		
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	
	
	// A method to get add a node to the left of a node
	public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException
	{
		Node<E> parent = n;
		
		if (parent.getLeft() != null) 
			throw new IllegalArgumentException("ALready has a child");
		
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}
	
	
	// A method to get add a node to the right of a node
	public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException
	{
		Node<E> parent = n;
		
		if (parent.getRight() != null) 
			throw new IllegalArgumentException("ALready has a child");
		
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	
	// A method to get set a node
	public E set(Node<E> n, E e) throws IllegalArgumentException
	{
		E temp = n.getElement();
		n.setElement(e);
		return temp;
	}
	
	
	
	// A method to get add a attach two binary trees to the left and right respectively to the given node
	public void attach(Node<E> n, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException
	{
		if (isInternal(n))
			throw new IllegalArgumentException("P must be a leaf");
		
		size += t1.size() + t2.size();
		
		if (!t1.isEmpty())
		{
			t1.root.setParent(n);
			n.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		
		if (!t2.isEmpty())
		{
			t2.root.setParent(n);
			n.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}
	
	
	
	// A method to get remove a node
	public E remove(Node<E> n) throws IllegalArgumentException
	{
		if (numChildren(n) == 2)
			throw new IllegalArgumentException("P has 2 Children");
		
		Node<E> child = (n.getLeft() != null ? n.getLeft() : n.getRight());
		
		if (child != null)
			child.setParent(n.getParent());
		
		if (n == root)
			root = child;
		else
		{
			Node<E> parent = n.getParent();
			if (n == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		
		size--;
		
		E temp = n.getElement();
		
		n.setElement(null);
		n.setLeft(null);
		n.setRight(null);
		n.setParent(n);
		
		return temp;
	}

	
	
	// A method to get the depth a node
	public int depth(Node<E> n)
	{
		if (isRoot(n))
			return 0;
		else
			return 1 + depth(parent(n));
	}
	

	
	// A method that returns the Iterable List of two children of a node 
	public Iterable<Node<E>> children(Node<E> n)
	{
		List<Node<E>> snapshot = new ArrayList<>(2);
		
		
		if (left(n) != null)
			snapshot.add(left(n));
		if (right(n) != null)
			snapshot.add(right(n));
		
		
		return snapshot;
	} 
	
	
	
	// A method to find the height a node
	public int findHeight(Node<E> n)
	{
		if (n == null) 
			return -1;
		
		int leftHeight = findHeight(n.getLeft());
		int rightHeight = findHeight(n.getRight());
		
		return (Math.max(leftHeight, rightHeight) + 1);
	}
	
	
	
	// LevelOrder Traversal. 
	// It returns an ArrayList of the ArraList that contains Node Values of each level.
	public ArrayList<ArrayList<E>> levelOrder()
	{
		ArrayList<ArrayList<E>> levelOrderList = new ArrayList<ArrayList<E>>();
		ArrayList<E> singleLevelList = new ArrayList<E>();
		 
		SinglyLinkedList<Node<E>> currentLevel = new SinglyLinkedList<>();
		SinglyLinkedList<Node<E>> nextLevel = new SinglyLinkedList<>();
		 
		currentLevel.addAtBack(root());
		 
		while(!currentLevel.isEmpty())
		{
			Node<E> n = currentLevel.removeFirst();
			 
			if (n.getLeft() != null)
				nextLevel.addAtBack(n.getLeft());
			if (n.getRight() != null)
				nextLevel.addAtBack(n.getRight());
			 
			singleLevelList.add(n.getElement());
			 
			if (currentLevel.isEmpty())
			{
				currentLevel = nextLevel;
				nextLevel = new SinglyLinkedList<Node<E>>();
				levelOrderList.add(singleLevelList);
				singleLevelList= new ArrayList<E>();
			}
		}
		 
		return levelOrderList;
	}
	 
	 
	// A method to represent the level order traversel
	public void printLevelOrder()
	{
		for (ArrayList<E> level : levelOrder())
		{
			System.out.print("[");
			for (E element : level)
			{
				System.out.print(" " + element + " ");
			}
			System.out.print("]  ");
		}
	}
	 
	
	 

	// A method to invert the tree
	public void invertTree()
	{
		invertTree(root());
	}
	 
	public void invertTree(Node<E> n)
	{
		if (n == null) return;
		 
		Node<E> temp = n.getLeft();
		n.setLeft(n.getRight());
		n.setRight(temp);
		 
		// Recursively invert the left and right nodes
		invertTree(n.getLeft());
		invertTree(n.getRight());
	}
	 
	
	 
	// A method to check if the binary tree is height-balanced
	public boolean isHeightBalanced()
	{
		return isHeightBalanced(root());
	}
	 
	public boolean isHeightBalanced(Node<E> n)
	{
		if (n == null) return true;
		 
		int leftHeight = findHeight(n.getLeft());
		int rightHeight = findHeight(n.getRight());
		
		if (Math.abs(leftHeight - rightHeight) > 1)
			return false;
		else
		{
			// Recursively check check the left and right nodes
			if (! isHeightBalanced(n.getLeft()))
				return false;
			if (! isHeightBalanced(n.getRight()))
				return false;
			 
			return true;
			 
			// It can be written in one line as:
			// return (isHeightBalanced(n.getLeft()) && isHeightBalanced(n.getRight()));
		}
	}
	
	 
	 
	 // A method to check if the binary tree is Mirror to itself (Symmetric)
	 public boolean isMirror()
	 {
		 return (isMirror(root().getLeft(), root().getRight()));
	 }
	 
	 public boolean isMirror(Node<E> left, Node<E> right)
	 {
		 // End recursion when
		 if (left == null && right == null)
			 return true;
		 
		 // If one child is a leaf node, the other is not, tree is not mirrored
		 else if (left == null || right == null)
			 return false;
		 
		 // If the elements of left and right child are not same, tree is not mirrored
		 if (! left.getElement().equals(right.getElement()))
			 return false;
		 
		 
		 // Recursively check left and right child of received left node and right node
		 if (! isMirror(left.getLeft(), right.getRight()))
			 return false;
		 if (! isMirror(left.getRight(), right.getLeft()))
			 return false;
		 
		 
		 // If none of the conditions returned false
		 return true;
		 
		 
		 // It can be also written in one line as:
		 // return (isMirror(left.getLeft(), right.getRight()) && isMirror(left.getRight(), right.getLeft()));
	 }
	


	 // Simple representation of a tree in level order
	 // Used to simply portray the invertTree() method
	 public void printTree()
	 {
		 System.out.println("\n");
		 
		 for (ArrayList<E> level : levelOrder())
		 {
			 System.out.println();
			 
			 for (E element : level)
				 System.out.print(" " + element + " ");
		 }
	 } 
	
}



// Singly Linked List Class.
// Used in Level Order Traversal code
class SinglyLinkedList<T>
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
		//protected Node<T> getNextNode() {return nextNode;}
	}
	
	
	private Node<T> head;
	private Node<T> tail;
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

	// A method to return the first element
	public T getFirst()
	{
		return this.head.getData();
	}
}

