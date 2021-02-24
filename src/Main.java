import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
/**
 * Clase inicial que realiza la captura de texto y la impresion por pantalla
 * de las cosas relevantes, tales como la captura de texto, la impresion del menu
 * las distintas opciones del programa y su finalización.
 * @author angel
 *
 */
public class Main {
	
	/**
	 * Objeto de la clase Fuente, que contiene todas las operaciones que realizará el programa
	 */
	private static Fuente fuente;
	
	/**
	 * Para poder leer elementos por consola
	 */
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * El método main de todo el programa
	 * @param args
	 */
	public static void main(String args[]) {
		
		System.out.println(inicioPrograma());
		String texto;
		try {
			texto = introducirArchivo();
			fuente = new Fuente(texto);
			int n = escogerSplit();
			fuente.run(n);
			opciones();
			System.out.println(finPrograma());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Mensaje de inicio del programa
	 * @return Texto de bienvenida
	 */
	private static String inicioPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("FUENTE DE INFORMACIÓN ASOCIADA A UN TEXTO");
		cad.append("\n");
		return cad.toString();
	}
	
	/**
	 * Metodo para introducir la ruta del archivo y capturar el texto del mismo
	 * @return texto contenido en el archivo
	 * @throws IOException
	 */
	private static String introducirArchivo() throws IOException{
		
		System.out.println("\nINTRODUCE LA RUTA ABSOLUTA DEL ARCHIVO CON EL TEXTO:\n");
		String ruta = in.nextLine();
		String linea;
		StringBuffer contenido = new StringBuffer("");
		String texto;
		
		try {
			FileReader f = new FileReader(ruta);
		    BufferedReader b = new BufferedReader(f);
		    while((linea = b.readLine())!=null) {
		    	
		    	contenido.append(linea);
		    	contenido.append("\n");
		    	
		    }
		    b.close();
		}catch (IOException e) {
			throw e;
		}
		
		texto = contenido.substring(contenido.indexOf("\"") + 1, contenido.lastIndexOf("\""));
		return texto;
	}
	
	/**
	 * Metodo que nos permite elegir el split que le haremos al texto
	 * @return Numero de caracteres que tendrá el simbolo
	 */
	private static int escogerSplit() {
		System.out.println("ESCOGE LA LONGITUD DE LOS SÍMBOLOS: \n");
		String l = in.nextLine();
		boolean b = false;
		while(!b) {
			try {
	            Integer.parseInt(l);
	            b = true;
	        } catch (NumberFormatException excepcion) {
	            System.out.println("INTRODUCE UN NUMERO, POR FAVOR: \n");
	            l = in.nextLine();
	        }
		}
		
		return Integer.valueOf(l);
	}
	
	/**
	 * Opciones que se pueden realizar en el programa
	 */
	private static void opciones() {
		System.out.println(fuente.displayMenu());
		String menu = in.nextLine();
		
		while (!menu.equals("0")) {
			fuente.menu(menu);
			System.out.println(fuente.displayMenu());
			menu = in.nextLine();
		}
	}
	 
	/**
	 * Cadena de despedida del programa
	 * @return Texto de despedida del programa
	 */
	private static String finPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("- FIN EJECUCIÓN PROGRAMA -");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("\n");
		return cad.toString();
	}
	
}
