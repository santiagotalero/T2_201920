package model.data_structures;

import java.util.Iterator;

public interface IQueue <Item>
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
