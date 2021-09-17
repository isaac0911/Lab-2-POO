import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Se encarga de instaciar una memoria RAM y contiene todos los métodos relacionados a las acciones que se pueden realizar con ella.
 * 
 * @author Isaac Solórzano Q.
 * @version 16/09/2021
 */

public class RAM1{  //Ver modificación No. 1 en el PDF adjuntado en la entrega de Canvas
	
	private String nombre;
	private int tamanoMem;
	private int numBloques;
	
	/**
	 * Instancia una memoria de tipo SDR
	 * @version 16/09/2021
	 * @param Tipo Tipo de la memoria.
	 * @param Tamano Tamaño de la memoria en GB
	 */
	public RAM1(String Tipo, int Tamano){
		
		nombre = Tipo;
		tamanoMem = Tamano;
		numBloques = (Tamano*1024)/64;
		
	}
	
	/**
	 * Instancia una memoria de tipo DDR
	 * @version 16/09/2021
	 * @param Tipo Tipo de la memoria.
	 */
	public RAM1 (String Tipo){
		
		nombre = Tipo;
		tamanoMem = 4;
		numBloques = 64;
	}
	
	/**
	 * Agrega a la cola los nombres de los programas que el usuario desea ejecutar.
	 * @version 16/09/2021
	 * @param Cola Arreglo que contiene los nombres de programas que están en cola esperando para ser ejecutados
	 * @param ProgramasAIngresar Arreglo que contiene los nombres de los programas que el usuario ingresó y que desea ejecutar.
	 * @return Devuelve el mismo arreglo llamado "Cola" que recibió como parámetro, pero con más elementos.
	 */
	public ArrayList<String> AgregarProgramasAlaCola(ArrayList<String> Cola, ArrayList<String> ProgramasAIngresar){
		for (int i = 0; i<ProgramasAIngresar.size();i++){
			Cola.add(ProgramasAIngresar.get(i));
		}
		return Cola;
	}
	
	/**
	 * Contiene todas las condiciones e instrucciones necesarias para poder agregar programas a la memoria de manera correcta, pero para agregar los programas en sí, invoca el método "Agregar" de esta misma clase.
	 * @version 16/09/2021
	 * @param Cola Arreglo que contiene los nombres de programas que están en cola esperando para ser ejecutados
	 * @param nuevaMemoria Instancia de la clase RAM1 que contiene las propiedades de la memoria.
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @param Datos_Programas Arreglo que contiene los datos de todos los programas que se pueden ejecutar.
	 * @return Devuelve el arreglo ListaBloques que se recibió como parámetro pero actualizado, habiéndole agregado los programas de la Cola que fueran posible.
	 */
	public ArrayList<Programa1> AgregarProgramasAlaRAM(ArrayList<String> Cola, RAM1 nuevaMemoria, ArrayList<Programa1> ListaBloques, ArrayList<ArrayList<String>> Datos_Programas){
		
		if (nuevaMemoria.nombre == "SDR"){
			
			boolean puede = true;
			
			while (puede){
				String nombrePrograma = Cola.get(0);
				
				int pos=-1;
				
				for(int i=0; i<Datos_Programas.size();i++){
					
					ArrayList<String> Programa1 = Datos_Programas.get(i);
					
					if (Programa1.get(0) == nombrePrograma){
						pos = i;
					}
				}
				
				Programa1 NuevoPrograma = new Programa1(Datos_Programas.get(pos));
				
				int bloquesNecesarios = ListaBloques.size() + NuevoPrograma.getnumBloquesPrograma();
				
				int numBloquesMaximo = nuevaMemoria.numBloques;
				
				if (numBloquesMaximo>=bloquesNecesarios){
					
					nuevaMemoria.Agregar(NuevoPrograma, ListaBloques);
					
					Cola.remove(0);
					
				}else{
					puede = false;
				}
				
				if (Cola.size() == 0){
					
					puede = false;
					
				}
				
			}
		}else if (nuevaMemoria.nombre == "DDR"){
			
			boolean puede = true;
			
			while (puede){
				String nombrePrograma = Cola.get(0);
				
				int pos = -1;
				for(int i=0; i<Datos_Programas.size();i++){
					ArrayList<String> Programa1 = Datos_Programas.get(i);
					
					if (Programa1.get(0) == nombrePrograma){
						pos = i;
					}
				}
				
				Programa1 NuevoPrograma = new Programa1(Datos_Programas.get(pos));
				
				int bloquesNecesarios = ListaBloques.size() + NuevoPrograma.getnumBloquesPrograma();
				
				int numBloquesMaximo = nuevaMemoria.numBloques;
				
				if (numBloquesMaximo>=bloquesNecesarios){
					
					nuevaMemoria.Agregar(NuevoPrograma, ListaBloques);
					Cola.remove(0);
					
				}else{
					
					boolean SePudoAgregarPrograma=false;
					
					while ((nuevaMemoria.tamanoMem<64) && (!SePudoAgregarPrograma)){
						
						nuevaMemoria = nuevaMemoria.AgrandarMemoria(nuevaMemoria);
						
						bloquesNecesarios = ListaBloques.size() + NuevoPrograma.getnumBloquesPrograma();
				
						numBloquesMaximo = nuevaMemoria.numBloques;
						
						if (numBloquesMaximo>=bloquesNecesarios){
					
							nuevaMemoria.Agregar(NuevoPrograma, ListaBloques);
							Cola.remove(0);
							SePudoAgregarPrograma=true;
						}
					}
					
				}
				
				if (nuevaMemoria.tamanoMem == 64){
						puede = false;
				}
				
				if (Cola.size() == 0){
					
					puede = false;
					
				}
				
			}
			
		}
		
		return ListaBloques;
		
	}
	
	/**
	 * Agrega un Programa1 en específico a la memoria.
	 * @version 16/09/2021
	 * @param NuevoPrograma Instancia de la clase Programa1 que se agregará a la memoria.
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve el arreglo ListaBloques que se recibió como parámetro pero actualizado, habiéndole agregado el Programa1 que también se recibió como parámetro.
	 */
	private ArrayList<Programa1> Agregar (Programa1 NuevoPrograma, ArrayList<Programa1> ListaBloques){
		
		int numBloquesAREllenar = NuevoPrograma.getnumBloquesPrograma();
		
		for (int i=0; i<numBloquesAREllenar; i++){
			
			ListaBloques.add(NuevoPrograma);
			
		}
		
		return ListaBloques;
		
	}
	
	/**
	 * Modifica el contenido de algunos atributos de la memoria para simular que esta se agrandó.
	 * @version 16/09/2021
	 * @param nuevaMemoria Es la memoria, una instancia de la clase RAM1.
	 * @return Devuelve la memoria que recibió como parámetro paro con sus atributos modificados.
	 */
	private RAM1 AgrandarMemoria(RAM1 nuevaMemoria){
		
		if (nuevaMemoria.tamanoMem==4){
			
			nuevaMemoria.tamanoMem = 8;
			nuevaMemoria.numBloques = 128;
			
		}else if (nuevaMemoria.tamanoMem==8){
			
			nuevaMemoria.tamanoMem = 12;
			nuevaMemoria.numBloques = 192;
			
		}else if (nuevaMemoria.tamanoMem==12){
			
			nuevaMemoria.tamanoMem = 16;
			nuevaMemoria.numBloques = 256;
			
		}else if (nuevaMemoria.tamanoMem==16){
			
			nuevaMemoria.tamanoMem = 32;
			nuevaMemoria.numBloques = 512;
			
		}else if (nuevaMemoria.tamanoMem==32){
			
			nuevaMemoria.tamanoMem = 64;
			nuevaMemoria.numBloques = 1024;
			
		}
		
		return nuevaMemoria;
		
	}
	
	/**
	 * Obtiene el tamaño total de la memoria, tanto en GB, como en MB, como en bloques.
	 * @version 16/09/2021
	 * @param nuevaMemoria Es la memoria, una instancia de la clase RAM1.
	 * @return Devuelve un arreglo con las especificaciones de la memoria: su capacidad en GB, en MB y en bloques.
	 */
	public ArrayList<Integer> ObtenerRAMTotal(RAM1 nuevaMemoria){
		
		int EspacioGB = nuevaMemoria.tamanoMem;
		int EspacioMB = EspacioGB*1024;
		int numeroDeBloques = nuevaMemoria.numBloques;
		
		ArrayList<Integer> DatosMemoria = new ArrayList<Integer>();
		
		DatosMemoria.add(EspacioGB);
		DatosMemoria.add(EspacioMB);
		DatosMemoria.add(numeroDeBloques);
		
		return DatosMemoria;
	}
	
	/**
	 * Obtiene el tamaño de la memoria que se encuentra disponible
	 * @version 16/09/2021
	 * @param nuevaMemoria Es la memoria, una instancia de la clase RAM1.
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve la cantidad de bloques disponibles (vacíos) que hay en la memoria.
	 */
	public int ObtenerRAMDisponible(RAM1 nuevaMemoria, ArrayList<Programa1> ListaBloques){
		
		int NumBloquesDisp = (nuevaMemoria.numBloques)-(ListaBloques.size());
		
		return NumBloquesDisp;
		
	}
	
	/**
	 * Obtiene el tamaño de la memoria que se encuentra ocupado
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve la cantidad de bloques ocupados que hay en la memoria.
	 */
	public int ObtenerRAMOcupada(ArrayList<Programa1> ListaBloques){
		
		int NumBloquesOcup = ListaBloques.size();
		
		return NumBloquesOcup;
		
	}
	
	/**
	 * Obtiene los nombres de los programas que están en ejecución
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve un arreglo de String con los nombres de todos los programas que están en ejecución.
	 */
	public ArrayList<String> ProgramasEnEjecucion (ArrayList<Programa1> ListaBloques){
		
		ArrayList<String> NombresProgramasEnEjec = new ArrayList<String>();
		
		for (int i=0;i<ListaBloques.size();i++){
			
			Programa1 Programa1 = ListaBloques.get(i);
			String nombrePrograma = Programa1.getNombre();
			
			NombresProgramasEnEjec.add(nombrePrograma);
			
		}
		
		Set<String> ListaAuxiliar = new HashSet<>(NombresProgramasEnEjec);
		NombresProgramasEnEjec.clear();
		NombresProgramasEnEjec.addAll(ListaAuxiliar);
		
		return NombresProgramasEnEjec;
	}
	
	/**
	 * Obtiene los números de bloque que ocupa un determinado Programa1 dentro de la memoria
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @param NombrePrograma Nombre del Programa1 del cual se desea saber los números de bloque que ocupa
	 * @return Devuelve un arreglo de tipo Integer con todos los número de bloque que ocupa el Programa1.
	 */
	public ArrayList<Integer> BloquesPrograma (ArrayList<Programa1> ListaBloques, String NombrePrograma){
		
		ArrayList<String> NombresProgramasEnEjec = new ArrayList<String>();
		
		for (int i=0;i<ListaBloques.size();i++){
			
			Programa1 Programa1 = ListaBloques.get(i);
			String nombrePrograma = Programa1.getNombre();
			
			NombresProgramasEnEjec.add(nombrePrograma);
			
		}
		
		ArrayList<Integer> PosicionesPrograma = new ArrayList<Integer>();
		
		for (int i=0; i<NombresProgramasEnEjec.size(); i++){
			
			if (NombrePrograma == NombresProgramasEnEjec.get(i)){
				
				PosicionesPrograma.add(i+1);
				
			}
			
		}
		
		return PosicionesPrograma;
		
	}
	
	/**
	 * Le resta 1 a la propiedad "NumCiclos" de todos los programas que están en ejecución
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve el mismo arreglo que recibió como parámetro pero habiéndo modificado la propiedad "NumCiclos" de todos sus elementos.
	 */
	public ArrayList<Programa1> DisminuirNumCiclos (ArrayList<Programa1> ListaBloques){
		
		ArrayList<Programa1> BloquesNoRepetidos = new ArrayList<Programa1>();
		
		for (int i=0; i<ListaBloques.size(); i++){
			
			if (!(BloquesNoRepetidos.contains(ListaBloques.get(i)))){
				
				BloquesNoRepetidos.add(ListaBloques.get(i));
				
			}
			
		}
		
		for (int i=0; i<BloquesNoRepetidos.size(); i++){
			
			Programa1 NuevoPrograma = BloquesNoRepetidos.get(i);
			
			NuevoPrograma.setNumCiclos();
			
		}
		
		return ListaBloques;
		
	}
	
	/**
	 * Obtiene los nombres de los programas que finalizaron su ejecución al realizar el ciclo de reloj.
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios ocupados de la memoria.
	 * @return Devuelve un arreglos de tipo String con los nombres de los programas que terminaron su ejecución.
	 */
	public ArrayList<String> ObtenerProgramasFinalizados (ArrayList<Programa1> ListaBloques){   //Ver modificación No. 5 en el PDF adjuntado en la entrega de Canvas
		
		ArrayList<String> ProgramasFinalizados = new ArrayList<String>();
		ArrayList<Programa1> BloquesNoRepetidos = new ArrayList<Programa1>();
		
		for (int i=0; i<ListaBloques.size(); i++){
			
			if (!(BloquesNoRepetidos.contains(ListaBloques.get(i)))){
				
				BloquesNoRepetidos.add(ListaBloques.get(i));
				
			}
			
		}
		
		for (int i=0; i<BloquesNoRepetidos.size(); i++){
			
			Programa1 NuevoPrograma = BloquesNoRepetidos.get(i);
			
			if (NuevoPrograma.getNumCiclos() == 0){
				
				ProgramasFinalizados.add(NuevoPrograma.getNombre());
				
			}
			
		}
		
		return ProgramasFinalizados;
		
	}
	
	/**
	 * Elimina los programas de la memoria cuya propiedad "numCiclos" sea igual a 0, es decir, aquellos que hayan terminado su ejecución.
	 * @version 16/09/2021
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios de la memoria.
	 * @param ProgramasFinalizados Arreglo que contiene los nombres de los programas que ya terminaron de ejecutarse.
	 * @return Devuelve el mismo arreglos "ListaBloques" que recibió como parámetro, pero habiéndole eliminado los programas que terminaron su ejecución.
	 */
	public ArrayList<Programa1> EliminarProgramas(ArrayList<Programa1> ListaBloques, ArrayList<String> ProgramasFinalizados){   //Ver modificación No. 6 en el PDF adjuntado en la entrega de Canvas
		
		if(ProgramasFinalizados.size()>0){
			
			boolean ExisteProgramaAEliminar = true;
			int posicion;
			int contador;
		
			while (ExisteProgramaAEliminar){
				
				contador = 0;
				posicion = 0;
				
				for (int i=0; i<ListaBloques.size(); i++){
				
					Programa1 NuevoPrograma = ListaBloques.get(i);
				
					if (NuevoPrograma.getNumCiclos() == 0){
					
						posicion = i;
						contador ++;
					
					
					}
				
				}
			
				ListaBloques.remove(posicion);
				
				if (contador==1){
					
					ExisteProgramaAEliminar = false;
					
				}
			
			}
			
		}
		
		return ListaBloques;
		
	}
	
	/**
	 * Contiene todos los ciclos, condiciones e instrucciones necesarios para poder actualizar la memoria a un tamaño más pequeño en caso de no necesitar tantos bloques de almacenamiento; sin embargo, para realizar la acción de modificar el tamaño de la memoria en sí, se invoca el método "AcortarMemoria" de esta misma clase.
	 * @version 16/09/2021
	 * @param nuevaMemoria Es la memoria, una instancia de la clase RAM1.
	 * @param ListaBloques Arreglo de instancias de la clase Programa1 que se encarga de modelar los espacios de la memoria.
	 * @return Devuelve la memoria (instancia de la clase RAM1) pero con sus atributos modificados, en caso haya sido necesario reducirla a una capacidad inferior.
	 */
	public RAM1 ActualizarMemoria(RAM1 nuevaMemoria, ArrayList<Programa1> ListaBloques){
		
		boolean SeNecesitaAcortarMemoria = true;
		
		while (SeNecesitaAcortarMemoria){
			
			int BloquesMaximos = nuevaMemoria.numBloques;
			
			int BloquesAcuales = ListaBloques.size();
			int BloquesMinimos = -1;
			
			if (BloquesMaximos == 128){
				
				BloquesMinimos = 64;
				
			}else if (BloquesMaximos == 192){
				
				BloquesMinimos = 128;
				
			}else if (BloquesMaximos == 256){
				
				BloquesMinimos = 192;
				
			}else if (BloquesMaximos == 512){
				
				BloquesMinimos = 256;
				
			}else if (BloquesMaximos == 1024){
				
				BloquesMinimos = 512;
			}
			
			if (BloquesAcuales<=BloquesMinimos){
			
				nuevaMemoria = nuevaMemoria.AcortarMemoria(nuevaMemoria);
			
			}else{
			
				SeNecesitaAcortarMemoria = false;
			
			}
			
		}
		
		
		return nuevaMemoria;
	}
	
	/**
	 * Modifica las propiedades de la memoria para reducirla a una capacidad de almacenamiento menor.
	 * @version 16/09/2021
	 * @param nuevaMemoria Es la memoria, una instancia de la clase RAM1.
	 * @return Devuelve mimsa memoria que recibió como parámetro, pero con sus atributos modificados.
	 */
	private RAM1 AcortarMemoria(RAM1 nuevaMemoria){
		
		int BloquesMaximos = nuevaMemoria.numBloques;
		
		if (BloquesMaximos == 128){
			
			nuevaMemoria.tamanoMem = 4;
			nuevaMemoria.numBloques = 64;
			
		}else if (BloquesMaximos == 192){
			
			nuevaMemoria.tamanoMem = 8;
			nuevaMemoria.numBloques = 128;
			
		}else if (BloquesMaximos == 256){
			
			nuevaMemoria.tamanoMem = 12;
			nuevaMemoria.numBloques = 192;
			
		}else if (BloquesMaximos == 512){
			
			nuevaMemoria.tamanoMem = 16;
			nuevaMemoria.numBloques = 256;
			
		}else if (BloquesMaximos == 1024){
			
			nuevaMemoria.tamanoMem = 32;
			nuevaMemoria.numBloques = 512;
			
		}
		
		return nuevaMemoria;
	}
	
	/**
	 * Método getter
	 * @version 16/09/2021
	 * @return Devuelve el contenido del atributo numBloques.
	 */
	public int getnumBloques(){   //Ver modificación No. 4 en el PDF adjuntado en la entrega de Canvas
		
		return numBloques;
	}
	
}