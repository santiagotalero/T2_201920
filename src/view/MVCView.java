package view;

import model.logic.MVCModelo;
import model.logic.Viaje;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu(int cantidadViajes, Viaje[] primeroUltimo)
		{
			System.out.println("\nConsulta de viajes UBER\n");
			
			System.out.println("Se registró la siguiente cantidad de viajes para el primer trimestre del año:"+ cantidadViajes + "\n");
			
			System.out.println("Primer viaje \n Zona de origen:"+ primeroUltimo[0].getSourceID()+" zona de destino: "+ primeroUltimo[0].getDstID()+ " hora: "+ primeroUltimo[0].getHour()+" tiempo promedio: "+ primeroUltimo[0].getMeanTravelTime());
			
			System.out.println("Último viaje \n Zona de origen:"+ primeroUltimo[1].getSourceID()+" zona de destino: "+ primeroUltimo[1].getDstID()+ " hora: "+ primeroUltimo[1].getHour()+" tiempo promedio: "+ primeroUltimo[1].getMeanTravelTime());

			System.out.println("\nOprima 1 para iniciar una consulta de clusters por hora, luego oprimir tecla Return: (e.g., 1):\n");
			
			System.out.println("Oprima 2 para iniciar una consulta de los últimos viajes de una hora dada, luego oprimir tecla Return: (e.g., 2):\n");
			
			System.out.println("Oprima 3 para salir del programa, luego oprimir tecla Return: (e.g., 3):\n");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
}
