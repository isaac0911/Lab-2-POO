import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Se encarga de comunicar a la vista con el diseño. Dirige el flujo del Programa1.
 * 
 * @author Isaac Solórzano Q.
 * @version 16/09/2021
 */
 
public class Controladorsin{  //Ver modificación No. 1 en el PDF adjuntado en la entrega de Canvas
	
	/**
	 * Manda a abrir el archivo CSV con los datos de los programas e instanciar la memoria; además, manda a ejecutar el ciclo en el que se mantendrá el usuario hasta que este desee salir.
	 * @version 16/09/2021
	 */
	public static void MetodoGeneral(){
		try{
			ArrayList<ArrayList<String>> Datos_Programas = LeerArchivo();
			
			Vistita1.MensajeBienvenida();
			
			RAM1 nuevaMemoria = CrearNuevaMemoria();
			
			ArrayList<Programa1> ListaBloques = new ArrayList<Programa1>();
			
			ArrayList<String> Cola = new ArrayList<String>();
			
			int TipoIngreso = 0;
			ArrayList<String> ProgramasAIngresar = Vistita1.PedirProgramasAEjecutar(Datos_Programas, TipoIngreso);
			
			Cola = nuevaMemoria.AgregarProgramasAlaCola(Cola, ProgramasAIngresar);
			
			ListaBloques = nuevaMemoria.AgregarProgramasAlaRAM(Cola, nuevaMemoria, ListaBloques, Datos_Programas);
			
			ciclo(nuevaMemoria, ListaBloques, Cola, Datos_Programas);
			
			
		} catch (Exception e){
			Vistita1.MostrarMensaje(e.getMessage());
		}
	}
	
	/**
	 * Abre el archivo CSV con los datos de los programas
	 * @version 16/09/2021
	 * @return Devuelve un arreglo dinámico con los datos de todos los programas
	 */
	private static ArrayList<ArrayList<String>> LeerArchivo() throws IOException{
		String nombreArchivo = "Programas.csv";
		String Directorio = "C:\\ArchivosCSV\\Programas.csv";
		try{
			ArrayList<ArrayList<String>> Datos_Programas = new ArrayList<ArrayList<String>>();
			Path filePath = Paths.get(Directorio);
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datosLinea = linea.split(",");
				ArrayList<String> datosTemporal = new ArrayList<String>();
				for (String dato : datosLinea){
					datosTemporal.add(dato);
				}
				Datos_Programas.add(datosTemporal);
			}
			
			ArrayList<String> DatosPrograma1 = Datos_Programas.get(0);
			
			if (DatosPrograma1.size() == 1){
				
				Datos_Programas.clear();
				Path filePathAux = Paths.get(Directorio);
				BufferedReader brd = Files.newBufferedReader(filePathAux);
				while ((linea = brd.readLine()) != null) {
					String[] datosLinea = linea.split(";");
					ArrayList<String> datosTemporal = new ArrayList<String>();
					for (String dato : datosLinea){
						datosTemporal.add(dato);
					}
					Datos_Programas.add(datosTemporal);
				}
				
			}
			
			return (Datos_Programas);
		
		}catch(IOException e){
			e.printStackTrace();
			String s = "\n-----------------------------------------------------------------------------------\nError. El programa no puede ser ejecutado. Compruebe que existe un archivo llamado " + nombreArchivo + " en la siguiente ubicacion: " + Directorio + ", o dirijase a las lineas 53 y 54 del codigo de la clase Controladorsin para cambiar la direccion y/o el nombre del archivo\n-----------------------------------------------------------------------------------\n";
			throw new IOException(s);
		}
	}
	
	/**
	 * Instancia una nueva memoria a partir de los datos ingresados por el usuario.
	 * @version 16/09/2021
	 * @return Devuelve una instancia de la clase RAM1.
	 */
	private static RAM1 CrearNuevaMemoria(){
		
		String TipoMemoria = "";
		
		int numTipoMemoria = Vistita1.PedirTipoMemoria();
		
		if (numTipoMemoria==1){
			TipoMemoria = "SDR";
		}else{
			TipoMemoria = "DDR";
		}
		
		RAM1 NuevaMemoria;
		
		if (numTipoMemoria == 1){
			int TamanoMem = Vistita1.PedirTamanoMemoriaSDR();
			NuevaMemoria = new RAM1(TipoMemoria, TamanoMem);
		}else{
			NuevaMemoria = new RAM1(TipoMemoria);
		}
		
		return NuevaMemoria;
	}
	
	/**
	 * Contiene el ciclo en el que se mantendrá el sistema mientras el usuario no quiera salir. Dependiendo de la accion que desee hacer el usuario, se ejecutar determinadas instrucciones.
	 * @version 16/09/2021
	 * @param nuevaMemoria Instancia de la clase RAM1.
	 * @param ListaBloques Arreglo dinámico de tipo Programa1 en el que cada elemento corresponde a un bloque ocupado de la memoria.
	 * @param Cola Arreglo dinámico de tipo String que contiene los nombres de todos los programas que están esperando para poder ser ejecutados.
	 * @param Datos_Programas Arreglo dinámico que contiene los datos de todos los programas que se pueden ejecutar.
	 */
	
	private static void ciclo(RAM1 nuevaMemoria, ArrayList<Programa1> ListaBloques, ArrayList<String> Cola, ArrayList<ArrayList<String>> Datos_Programas){
		
		int opcion = -1;
		
		while (opcion != 11){
			
			opcion = Vistita1.MostrarMenu();
			
			if (opcion==1){
				
				nuevaMemoria = CrearNuevaMemoria();
				ListaBloques = new ArrayList<Programa1>();
				Cola = new ArrayList<String>();
				int TipoIngreso = 0;
				ArrayList<String> ProgramasAIngresar = Vistita1.PedirProgramasAEjecutar(Datos_Programas, TipoIngreso);
				Cola = nuevaMemoria.AgregarProgramasAlaCola(Cola, ProgramasAIngresar);
				ListaBloques = nuevaMemoria.AgregarProgramasAlaRAM(Cola, nuevaMemoria, ListaBloques, Datos_Programas);
				
			}else if (opcion == 2){
				
				int TipoIngreso = 1;
				ArrayList<String> ProgramasAIngresar = Vistita1.PedirProgramasAEjecutar(Datos_Programas, TipoIngreso);
				Cola = nuevaMemoria.AgregarProgramasAlaCola(Cola, ProgramasAIngresar);
				
			}else if (opcion == 3){
				
				ArrayList<Integer> DatosMemoria = nuevaMemoria.ObtenerRAMTotal(nuevaMemoria);
				String mensaje = "Actualmente la memoria es de " + Integer.toString(DatosMemoria.get(0)) + "GB = " + Integer.toString(DatosMemoria.get(1)) + "MB = " + Integer.toString(DatosMemoria.get(2)) + " bloques de 64MB";
				Vistita1.MostrarMensaje(mensaje);
				
			}else if (opcion == 4){
				
				int NumBloquesDisp = nuevaMemoria.ObtenerRAMDisponible(nuevaMemoria, ListaBloques);
				double RAMDispGB = (NumBloquesDisp*1.0*64)/1024;
				int RAMDispMB = NumBloquesDisp*64;
				String mensaje = "Actualmente se encentran disponibles " + RAMDispGB + " GB = " + RAMDispMB + " MB = " + NumBloquesDisp + " bloques de 64MB";
				Vistita1.MostrarMensaje(mensaje);
				
			}else if (opcion == 5){
				
				int NumBloquesOcup = nuevaMemoria.ObtenerRAMOcupada(ListaBloques);
				double RAMOcupGB = (NumBloquesOcup*1.0*64)/1024;
				int RAMOcupMB = NumBloquesOcup*64;
				String mensaje = "Actualmente se encentran ocupados " + RAMOcupGB + " GB = " + RAMOcupMB + " MB = " + NumBloquesOcup + " bloques de 64MB";
				Vistita1.MostrarMensaje(mensaje);
				
			}else if (opcion == 6){
				
				if (ListaBloques.size()>0){
					
					ArrayList<String> ListaProgramasEnEjec = nuevaMemoria.ProgramasEnEjecucion(ListaBloques);
					String mensaje = "Los programas que estan en ejecucion son los siguientes: " ;
					Vistita1.MostrarMensaje(mensaje);
					Vistita1.ImprimirFilaString(ListaProgramasEnEjec);
					
				}else{
					
					String mensaje = "No hay ningun programa en ejecucion";
					Vistita1.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 7){
				
				if (Cola.size()>0){
					
					String mensaje = "Los programas que estan en cola son los siguientes: ";
					Vistita1.MostrarMensaje(mensaje);
					Vistita1.ImprimirFilaString(Cola);
					
				}else{
					
					String mensaje = "No hay ningun programa en cola";
					Vistita1.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 8){
				
				if (ListaBloques.size()>0){
					
					ArrayList<String> ListaProgramasEnEjec = nuevaMemoria.ProgramasEnEjecucion(ListaBloques);
					String NombrePrograma = Vistita1.IngresarPrograma(ListaProgramasEnEjec);
					ArrayList<Integer> PosicionesPrograma = nuevaMemoria.BloquesPrograma(ListaBloques, NombrePrograma);
					String mensaje = "\nEl programa '" + NombrePrograma + "' ocupa los siguientes numeros de bloque en la memoria: ";
					Vistita1.MostrarMensaje(mensaje);
					Vistita1.ImprimirFilaInteger(PosicionesPrograma);
					
				}else{
					
					String mensaje = "No se puede realizar esta accion ya que en este momento no hay ningun programa en ejecucion";
					Vistita1.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 9){
				
				String mensaje = "\nEl estado de la memoria es el sigueiente: ";
				Vistita1.MostrarMensaje(mensaje);
				Vistita1.MostrarEstado(nuevaMemoria.getnumBloques(), ListaBloques.size());
				
			}else if (opcion == 10){
				
				ListaBloques = nuevaMemoria.DisminuirNumCiclos(ListaBloques);
				
				ArrayList<String> ProgramasFinalizados = nuevaMemoria.ObtenerProgramasFinalizados(ListaBloques);
				
				ListaBloques = nuevaMemoria.EliminarProgramas(ListaBloques, ProgramasFinalizados);
				
				nuevaMemoria = nuevaMemoria.ActualizarMemoria(nuevaMemoria, ListaBloques);
				
				if (Cola.size()>0){
					ListaBloques = nuevaMemoria.AgregarProgramasAlaRAM(Cola, nuevaMemoria, ListaBloques, Datos_Programas);
				}
				
				if (ProgramasFinalizados.size()>0){
					
					String mensaje = "\nLos programas que fueron finalizados en este ciclo de reloj fueron: ";
					Vistita1.MostrarMensaje(mensaje);
					Vistita1.ImprimirFilaString(ProgramasFinalizados);
					
				}else {
					
					String mensaje = "No se finalizo ningun programa en ejecucion durante este ciclo de reloj.";
					Vistita1.MostrarMensaje(mensaje);
				}
				
			}else if (opcion == 11){
				Vistita1.MensajeDespedida();
				
			}
			
		}
	}
	
	
}