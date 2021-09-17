import java.util.ArrayList;

/**
 * Se encarga de instanciar un Programa; además contiene los setters y getters correspondientes.
 * 
 * @author Isaac Solórzano Q.
 * @version 16/09/2021
 */

public class Programa1{  //Ver modificación No. 1 en el PDF adjuntado en la entrega de Canvas
	
	private String nombre;
	private int NumCiclos;
	private int numBloquesPrograma;
	
	/**
	 * Instancia un objeto de la clase Programa1.
	 * @version 16/09/2021
	 * @param DatosPrograma Arreglo que contiene los valores que se asignarán a cada atributo del Programa1.
	 */
	public Programa1 (ArrayList<String> DatosPrograma){
		
		nombre = DatosPrograma.get(0);
		NumCiclos = Integer.parseInt(DatosPrograma.get(2));
		
		int TamanoMB = Integer.parseInt(DatosPrograma.get(1));
		
		double division = Double.valueOf(TamanoMB/64);
		
		Double divisionWC = division;
		
		numBloquesPrograma = divisionWC.intValue() + 1;
		
	}
	
	/**
	 * Método getter
	 * @version 16/09/2021
	 * @return Devuelve el contenido del atributo numBloquesPrograma.
	 */
	public int getnumBloquesPrograma(){
		
		return numBloquesPrograma;
		
	}
	
	/**
	 * Método getter
	 * @version 16/09/2021
	 * @return Devuelve el contenido del atributo nombre.
	 */
	public String getNombre(){
		
		return nombre;
		
	}
	
	/**
	 * Método setter que se encarga de sumar 1 al contenido del atributo NumCiclos
	 * @version 16/09/2021
	 */
	public void setNumCiclos(){   //Ver modificación No. 3 en el PDF adjuntado en la entrega de Canvas
		
		NumCiclos --;
		
	}
	
	/**
	 * Método getter
	 * @version 16/09/2021
	 * @return Devuelve el contenido del atributo NumCiclos.
	 */
	public int getNumCiclos(){
		
		return NumCiclos;
		
	}
	
}