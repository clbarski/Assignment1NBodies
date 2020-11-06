//Initialize an interface Lists
public interface Lists<T> 
{
	//global variables for the classes
	T get(int index);
	boolean add(T value);
	int size();
	
}


//Creates a linked list class
class LinkedList<T> implements Lists<T>
{
	//Creates a Node head
	Node<T> head;
	
	int size;
	
	//Creates a class of Node that saves the information for what the node hold
	private class Node<T>
	{
		//The information in the node
		T data;
		
		//The information for whats next after the node
		Node<T> next;
		
		//Sets the node to the correct item
		public Node(T item)
		{
			this.data = item;
		}
		
	}
	
	//Creates a empty linked list
	public LinkedList()
	{
		head = null;
		size = 0;
	}
	
	//Returns the size of the linked list
	public int size()
	{
		return size;
	}
	
	//Adds an item to the link list
	public boolean add(T item)
	{
		//If the linked list is empty
		if(head == null)
		{
			//Set the item to the head of the list
			head = new Node<>(item);
			//Increment the size
			size++;
			return true;
		}
		//If its not the first index of the list
		else
		{
			//sets previous head to store whats behind the last node
			Node<T> prev = head;
			//Stops until its before the last node and stores it in prev
			for(int i = 0; i<size-1; i++)
			{
				prev = prev.next;
			}
			
			//Create a new node to temporarily save the item
			Node<T> temp = new Node<>(item);
			//Sets the next in the list to the item
			prev.next = temp;
			//increment the size
			size++;
			return true;
		}
	}
	
	//Class to get at an index
	public T get(int i)
	{
		if(i >= size || i<0)
		{
			//Checks if the index is out of bounds
			System.out.println("Index out of bounds");
		}
		
		//sets the current value to whats at the head
		Node<T> current = head;
		for(int j = 0; j<i; j++)
		{
			//Iterates to the index
			current = current.next;
		}
		//returns whats at the index
		return current.data;
	}
}

//Class to create an array list
class ArrList<T> implements Lists<T>
{
	//Create a new list type arr
	T[] arr;
	
	int size;
	
	//Initializes the arr list to 10 data points and size 0
	public ArrList()
	{
		arr = (T[]) new Object[10];
		size = 0;
	}
	
	//Returns the size
	public int size() 
	{
		return size;
	}
	
	//Function to add an item
	public boolean add(T item)
	{
		//If the array list is full
		if(size == arr.length)
		{
			//Create a new array list twice the size of the full one
			T[] newArr = (T[]) new Object[arr.length * 2];
			for(int i = 0; i< arr.length; i++)
			{
				//Iterate through and refill the list
				newArr[i] = arr[i];
			}
			//set the old array list to the new array list
			arr = newArr;
		}
		//Add the item to the index
		arr[size] = item;
		//increase the size
		size++;
		
		return true;
	}
	
	//Gets and returns whats at an index
	public T get(int index)
	{
		//If the index is out of bounds
		if(index >= size || index < 0)
		{
			System.out.println("Index out of bounds");
		}
		
		//Return the index value
		return arr[index];
	}
	
}