package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Stack;

public class TestStack 
{
	
	private Stack pila;
	private static int tamano=100;
	
	@Before
	public void setup1()
	{
		pila= new Stack();
	}
	
	public void setup2()
	{
		for(int i =0; i< tamano; i++)
		{
			pila.push(i);
		}
	}
	
	@Test
	public void testStack() 
	{
		assertTrue(pila!=null);
		
		setup2();
		assertEquals(100, pila.size());
	}
	
	@Test
	public void testIsEmpty()
	{
		assertTrue(pila.isEmpty());
		
		setup2();
		assertFalse(pila.isEmpty());
	}
	
	
	@Test
	public void testPush()
	{
		pila.push("prueba");
		
		Iterator iter= pila.iterator();
		
		String ultimo=null;
		
		while(iter.hasNext())
		{
			ultimo= (String)iter.next();
		}
		
		assertEquals("prueba", ultimo );
	}
	
	@Test
	public void testPop()
	{
		pila.push("prueba");
		int t=pila.size();
		String i=(String)pila.pop();
		assertEquals("prueba", i);
		assertEquals(pila.size(),t-1);
	}
	
	@Test
	public void testPeek()
	{
		setup2();
		assertEquals(99, pila.peek());
	}


}
