package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Queue;
import model.data_structures.Stack;

public class TestQueue 
{
	
	private Queue cola;
	private static int tamano=100;
	
	@Before
	public void setup1()
	{
		cola= new Queue();
	}
	
	public void setup2()
	{
		for(int i =0; i< tamano; i++)
		{
			cola.enqueue(i);
		}
	}
	
	@Test
	public void testQueue() 
	{
		assertTrue(cola!=null);
		
		setup2();
		assertEquals(100, cola.size());
	}
	
	@Test
	public void testIsEmpty()
	{
		assertTrue(cola.isEmpty());
		
		setup2();
		assertFalse(cola.isEmpty());
	}
	
	
	@Test
	public void testEnqueue()
	{
		cola.enqueue("prueba");
		
		Iterator iter= cola.iterator();
		
		String ultimo=null;
		
		while(iter.hasNext())
		{
			ultimo= (String)iter.next();
		}
		
		assertEquals("prueba", ultimo );
	}
	
	@Test
	public void testDequeue()
	{
		setup2();

		int t=cola.size();
		int i=(int)cola.dequeue();
		assertEquals(0, i);
		assertEquals(cola.size(),t-1);
	}
	
	@Test
	public void testPeek()
	{
		setup2();
		assertEquals(0, cola.peek());
	}


}
