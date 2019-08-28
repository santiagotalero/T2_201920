package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.Viaje;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		try {
			modelo.cargarArchivos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin )
		{
			//Esperar 3,5 segundos para que el usuario pueda leer
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			view.printMenu(modelo.darTamanoCola(), (Viaje[])modelo.primeroUltimo());

			int option = lector.nextInt();
			switch(option){
			case 1:
				System.out.println("--------- \nRealizar Consulta \nDar número de hora para empezar a consultar: ");
				int hora = lector.nextInt();


				Queue cola= modelo.consulta1(hora);
				
				System.out.println("El número total de viajes del cluster más grande a partir de esa hora fue: "+ cola.size());

				Iterator iter= cola.iterator();

				while(iter.hasNext())
				{
					Viaje actual= (Viaje)iter.next();
					System.out.println("Hora: "+ actual.getHour()+" , Zona origen: "+ actual.getSourceID()+ " , Zona destino: "+ actual.getDstID() + " , Tiempo promedio: "+ actual.getMeanTravelTime());
				}

				break;


			case 2: 
				System.out.println("--------- \n Realizar consulta \n---------"); 
				System.out.println("Ingrese una hora específica");
				int hora2= lector.nextInt();
				
				System.out.println("Ingrese la cantidad de últimos viajes");
				int n= lector.nextInt();

				Queue colaViajes= modelo.consulta2(n, hora2);
				
				Iterator iter2=colaViajes.iterator();
				
				while(iter2.hasNext())
				{
					Viaje actual= (Viaje)iter2.next();
					System.out.println("Hora: "+ actual.getHour()+" , Zona origen: "+ actual.getSourceID()+ " , Zona destino: "+ actual.getDstID() + " , Tiempo promedio: "+ actual.getMeanTravelTime());
				}

				break;	

			case 3:
				System.out.println("--------- \nHasta pronto! \n ");
				lector.close();
				fin = true;
				break;

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
