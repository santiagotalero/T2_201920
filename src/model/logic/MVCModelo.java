package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Queue;
import model.data_structures.Stack;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private Stack pila;
	
	private Queue cola;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		pila = new Stack();
		cola= new Queue();
	}
	

	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoPila()
	{
		return pila.size();
	}
	
	public int darTamanoCola()
	{
		return cola.size();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		pila.push(dato);
		cola.enqueue(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	//public String buscar(String dato)
	{
		//return pila.;
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar()
	{
		cola.dequeue();
		return (String)pila.pop();
	}
	
	public void cargarArchivos() throws IOException
	{
		CSVReader reader = null;
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));

			
			reader.readNext();

			
			for(String[] nextLine : reader)
			{
				Viaje actual= new Viaje(Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]),Double.parseDouble(nextLine[5]),Double.parseDouble(nextLine[6]));
				pila.push(actual);
				cola.enqueue(actual);
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
	public Object[] primeroUltimo()
	{
		Object[] viajes= new Viaje[2];
		
		Queue clon= cola;
		Stack clon2=pila;
		
		viajes[0]= clon.dequeue();
		
		viajes[1]=clon2.pop();
		
		return viajes;
	}
	

	public Queue consulta1(int hora)
	{
		Queue colaModelo= colaAPartirDe(hora);
		
		Queue clusterFinal= new Queue();
		Queue clusterAuxiliar= new Queue();
		
		int horaMax=0;
		
		Iterator iter= colaModelo.iterator();
		
		while(iter.hasNext())
		{
			Viaje actual= (Viaje)iter.next();
			
			if(actual.getHour()>=horaMax)
			{
				horaMax=actual.getHour();
				clusterAuxiliar.enqueue(actual);
			}
			else
			{
				if(clusterAuxiliar.size()>clusterFinal.size())
				{
					clusterFinal=clusterAuxiliar;
				}
				clusterAuxiliar= new Queue();
				clusterAuxiliar.enqueue(actual);
				horaMax=actual.getHour();
			}
		}
		
		return clusterFinal;
	}
	
	public Queue colaAPartirDe(int hora)
	{
		Queue colaNueva= new Queue();
		
		Iterator iter= cola.iterator();
		
		boolean empezar=false;
		
		while(iter.hasNext())
		{
			Viaje actual= (Viaje)iter.next();
			
			
			if(empezar)
			{
				colaNueva.enqueue(actual);
			}
			else if(actual.getHour()==hora)
			{
				empezar=true;
				colaNueva.enqueue(actual);
			}
		}
		
		return colaNueva;
	}

	
	public Queue consulta2(int n,int hora)
	{
		Queue viajesHora= new Queue();
		
		Iterator iter= cola.iterator();
		
		while(iter.hasNext())
		{
			Viaje actual= (Viaje)iter.next();
			
			if(actual.getHour()==hora)
			{
				viajesHora.enqueue(actual);
			}
		}
		
		int i=0;
		int pos=viajesHora.size()-n;
		
		while(i<pos)
		{
			viajesHora.dequeue();
			i++;
		}
		
		return viajesHora;
	}
		
	


}
