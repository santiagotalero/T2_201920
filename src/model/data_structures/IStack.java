package model.data_structures;

import java.util.Iterator;

public interface IStack <Item>
{
	//Lista de metodos de la clase Stack.java

	boolean isEmpty();

	int size();

	void push (Item item);

	Item pop();

	Item peek();

	String toString();

	Iterator<Item> iterator();

	

}
