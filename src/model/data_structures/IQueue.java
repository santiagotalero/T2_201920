package model.data_structures;

public interface IQueue 
{
	//Metodos de la clase Queue.java

	
	boolean isEmpty();

	int size();

	Item peek();

	void enqueue(Item item);

	Item dequeue();

	String toString();

	Iterator<Item> iterator();



}
