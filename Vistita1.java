import java.util.Scanner;
import java.util.ArrayList;

/**
 * Se encarga de desplegar mensajes en pantalla y recibir información ingresada a través del teclado.
 * 
 * @author Isaac Solórzano Q.
 * @version 16/09/2021
 */

public class Vistita1{  //Ver modificación No. 1 en el PDF adjuntado en la entrega de Canvas
	
	/**
	 * Muestra en pantalla el mensaje que recibe como parámetro.
	 * @version 16/09/2021
	 * @param mensaje Caracteres que se mostrarán en pantalla.
	 */
	public static void MostrarMensaje(String mensaje){
		System.out.println(mensaje);
	}
	
	/**
	 * Muestra en pantalla un mensaje de bienvenida; es el primer mensaje que ve el usuario.
	 * @version 16/09/2021
	 */
	public static void MensajeBienvenida(){
		System.out.println("\nBIENVENIDO AL SIMULADOR DE MEMORIA RAM1");
	}
	
	/**
	 * Muestra en pantalla un mensaje de despedida; es el último mensaje que ve el usuario al salir del sistema.
	 * @version 16/09/2021
	 */
	public static void MensajeDespedida(){
		System.out.println("\nTenga un buen dia :)");
	}
	
	/**
	 * Le muestra al usuario una lista enumerada de los tipos de memoria que se pueden crear y este ingresa el número que corresponda con el tipo que desea.
	 * @version 16/09/2021
	 * @return Devuelve el número que el usuario ingresa luego de que se le muestran las opciones de memoria que existen.
	 */
	public static int PedirTipoMemoria(){
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el tipo de memoria\n1.\tSDR\n2.\tDDR\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>2){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 2\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		return (numero);
		
	}
	
	/**
	 * Le muestra al usuario una lista enumerada de los tipos de capacidad de memoria SDR, en GB, que se pueden crear, y este ingresa el número que corresponda con el tipo de capacidad que desea para su  memoria.
	 * @version 16/09/2021
	 * @return Devuelve la capacidad en GB elegida por el usuario.
	 */
	public static int PedirTamanoMemoriaSDR(){
		int tamMemoria = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el tamano de la memoria\n1.\t4GB\n2.\t8GB\n3.\t12GB\n4.\t16GB\n5.\t32GB\n6.\t64GB\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			tamMemoria = Integer.parseInt(dato);
		}
		
		while (validacion = false || tamMemoria<1 || tamMemoria>6){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 6\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				tamMemoria = Integer.parseInt(dato);
			}
		}
		
		
		int EspMemoria = 0;
		if (tamMemoria==1){
			EspMemoria = 4;
		}else if (tamMemoria==2){
			EspMemoria = 8;
		}else if (tamMemoria==3){
			EspMemoria = 12;
		}else if (tamMemoria==4){
			EspMemoria = 16;
		}else if (tamMemoria==5){
			EspMemoria = 32;
		}else if (tamMemoria==6){
			EspMemoria = 64;
		}
		
		return (EspMemoria);
		
	}
	
	/**
	 * Se muestra en pantalla una lista enumerada de los nombres de los programas que existen, y el usuario ingresa el número del Programa1 que desee ejecutar
	 * @version 16/09/2021
	 * @param Datos_Programas Arreglos dinámico que contiene los datos de todos los programas que se pueden ejecutar.
	 * @return Devuelve un arreglo dinámico de tipo String con los nombres de los programas elegidos por el usuario.
	 */
	public static ArrayList<String> PedirProgramasAEjecutar(ArrayList<ArrayList<String>> Datos_Programas, int TipoIngreso){
		
		ArrayList<String> ProgramasAEjecutar = new ArrayList<String>();
		
		int resp = 1;
		
		if(TipoIngreso == 0){
			System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el Programa que desee ejecutar");
		}else if(TipoIngreso == 1){
			System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el Programa que desee agregar a la cola");
		}
		
		while (resp == 1){
			
			int numPrograma = 0;
			boolean validacion = false;
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("\n");
			
			for (int i=0;i<Datos_Programas.size();i++){
				ArrayList<String> Programa1 = Datos_Programas.get(i);
				System.out.println((i+1) + "\t" + Programa1.get(0));
			}
			
			System.out.println("\nIngrese el numero:");
			
			String dato = scan.nextLine();
			
			validacion = comprobarNumero(dato);
			
			if (validacion){
			numPrograma = Integer.parseInt(dato);
			}
		
			while (validacion = false || numPrograma<1 || numPrograma>Datos_Programas.size()){
				System.out.println("\nERROR!! Ingrese un numero entre 1 y " + Datos_Programas.size() +"\n");
				dato = scan.nextLine();
				validacion = comprobarNumero(dato);
				if (validacion){
					numPrograma = Integer.parseInt(dato);
				}
			}
			
			ArrayList<String> Programa1 = Datos_Programas.get(numPrograma-1);
			
			String NombrePrograma = Programa1.get(0);
			
			ProgramasAEjecutar.add(NombrePrograma);
			
			//------------------------------------------------------------------------------------------------------
			
			validacion = false;
			
			System.out.println("\nDesea ingresar otro Programa?\n1.\tSI\n2.\tNO");
			
			dato = scan.nextLine();
			
			validacion = comprobarNumero(dato);
			
			if (validacion){
			resp = Integer.parseInt(dato);
			}
			
			while (!validacion || resp<1 || resp>2){
				System.out.println("\nERROR!! Ingrese un numero entre 1 y 2\n");
				dato = scan.nextLine();
				validacion = comprobarNumero(dato);
				if (validacion){
					resp = Integer.parseInt(dato);
				}
			}
		}
		return ProgramasAEjecutar;
		
	}
	
	/**
	 * Se muestra una lista enumerada de todas las funciones que puede realizar el sistema, y el usuario ingresa el número de la función que desea ejecutar.
	 * @version 16/09/2021
	 * @return Devuelve el número correspondiente con la función elegida por el usuario.
	 */
	public static int MostrarMenu(){
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese la opcion que desea ejecutar\n1.\tInicializar todo\n2.\tIngresar programas a la cola\n3.\tVer la cantidad de memoria RAM total\n4.\tVer la cantidad de memoria RAM disponible\n5.\tVer la cantidad de memoria RAM en uso\n6.\tVer los programas en ejecucion\n7.\tVer los programas en cola\n8.\tVer los numeros de bloque que ocupa un programa en particular\n9.\tVer el estado de memoria\n10.\tRealizar un ciclo de reloj\n11.\tSalir\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>11){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 11\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		return (numero);
		
	}
	
	/**
	 * Se muestran en pantalla los nombres de los programas que están en ejecución y el usuario elige uno de ellos.
	 * @version 16/09/2021
	 * @param ListaProgramasEnEjec Arreglo que contiene los nombres de los programas que están en ejecución.
	 * @return Devuelve el nombre del Programa1 elegido por el usuario.
	 */
	public static String IngresarPrograma (ArrayList<String> ListaProgramasEnEjec){
		
		int pos = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIngrese el Programa que desea analizar\n");
		
		for (int i=0;i<ListaProgramasEnEjec.size();i++){
			
			System.out.println(i+1 + "\t " + ListaProgramasEnEjec.get(i));
			
		}
		
		System.out.println("Ingrese el numero:");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			pos = Integer.parseInt(dato);
		}
		
		while (validacion = false || pos<1 || pos>ListaProgramasEnEjec.size()){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y " + ListaProgramasEnEjec.size() + "\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				pos = Integer.parseInt(dato);
			}
		}
		
		String NombrePrograma = ListaProgramasEnEjec.get(pos-1);
		
		return NombrePrograma;
	}
	
	/**
	 * Se muestra en pantalla el estado del la memoria; es decir, se depliegan los bloques representados por corchetes y adentro la palabra "ocupado" o "disponible", según cada caso.
	 * @version 16/09/2021
	 * @param numBloques Número de bloques con los que cuenta la memoria.
	 * @param numBloquesOcup Número de bloques de la memoria que están ocupados
	 */
	public static void MostrarEstado(int  numBloques, int numBloquesOcup){
		
		for (int i=0; i<numBloquesOcup; i++){
			
			System.out.print("[ocupado]");
			
		}
		
		for (int i=0; i<((numBloques)-(numBloquesOcup));i++){
			
			System.out.print("[disponible]");
			
		}
		
	}
	
	/**
	 * Se muestran en pantalla los elementos de la lista que se recibe como parámetro, cada uno en una línea distinta.
	 * @version 16/09/2021
	 * @param Fila Arreglo que contiene todos los elementos de tipo String que se deseean imprimir en pantalla.
	 */
	public static void ImprimirFilaString(ArrayList<String> Fila){   //Ver modificación No. 2 en el PDF adjuntado en la entrega de Canvas
		
		for (int i=0; i<Fila.size();i++){
			
			System.out.println(Fila.get(i));
			
		}
	}
	
	/**
	 * Se muestran en pantalla los elementos de la lista que se recibe como parámetro, cada uno en una línea distinta.
	 * @version 16/09/2021
	 * @param Fila Arreglo que contiene todos los elementos de tipo Integer que se deseean imprimir en pantalla.
	 */
	public static void ImprimirFilaInteger(ArrayList<Integer> Fila){   //Ver modificación No. 2 en el PDF adjuntado en la entrega de Canvas
		
		for (int i=0; i<Fila.size();i++){
			
			System.out.print(Fila.get(i) + "-");
			
		}
		
		System.out.println("\n");
	}
	
	/**
	 * Verifica que si el valor que recibe como parámetro es un entero. Si es así, devuelve true, de lo contrario, devuelve false.
	 * @version 16/09/2021
	 * @param conjuntoCaracteres Valor del cual se verificará si es un entero o no.
	 * @return Devuelve una variable tipo boolean que indica si el parámetro recibido es un entero o no.
	 */
	private static boolean comprobarNumero(String conjuntoCaracteres){
		try{
			Integer.parseInt(conjuntoCaracteres);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
	
}