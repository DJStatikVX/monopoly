// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.util.console;

// Importación de clases
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Clase Console para procesar y mostrar las opciones
 * para la interacción entre interfaz y usuario
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Console {
	
	private static PrintStream out = System.out;
	private static InputStream in = System.in;
	
	private static Scanner keyboard = new Scanner( in );

	/**
	 * Establece la salida utilizada por la consola
	 * @param out salida a establecer, de tipo PrintStream
	 */
	public static void setOut(PrintStream out) {
		Console.out = out;
	}

	/**
	 * Establece la entrada utilizada por la consola
	 * @param in entrada a establecer, de tipo InputStream
	 */
	public static void setIn(InputStream in) {
		Console.in = in;
		keyboard = new Scanner(Console.in);
	}

	/**
	 * Lee una cadena de caracteres introducida por el usuario,
	 * utilizando como delimitador los saltos de línea (separadores)
	 * @param msg mensaje (String) a mostrar para pedir la entrada al usuario
	 * @return Cadena de caracteres introducida por el usuario, de tipo String
	 */
	public static String readString(String msg) {
		out.println(msg + ": ");
		keyboard.useDelimiter(System.lineSeparator());
		String res = keyboard.next();
		keyboard.reset();
		return res;
	}

	/**
	 * Lee un número entero introducido por el usuario vía teclado
	 * @param msg mensaje (String) a mostrar para pedir la entrada al usuario
	 * @return Número entero introducido por el usuario, de tipo int
	 */
	public static int readInteger(String msg) {
		out.println(msg + ": ");
		return keyboard.nextInt();
	}

	/**
	 * Lee un caracter introducido por el usuario, tomando solo el primer
	 * caracter si se introduce una cadena e ignorando los saltos de línea
	 * @param msg mensaje (String) a mostrar para pedir la entrada al usuario
	 * @return Caracter introducido por el usuario (o procesado), de tipo char
	 */
	public static char readChar(String msg) {
		out.println(msg + ": ");
		keyboard.useDelimiter(System.lineSeparator());
		char res = keyboard.next().charAt(0);
		keyboard.reset();
		return res;
	}

	/**
	 * Muestra el mensaje pasado como parámetro por la salida de la consola
	 * @param msg mensaje a mostrar, de tipo String
	 */
	public static void println(String msg) {
		out.println(msg);
	}

	/**
	 * Muestra un String pasado como parámetro de modo formateado por salida
	 * @param fmt cadena formateada, de tipo String
	 * @param params diferentes parámetros para determinar el formato de fmt
	 */
	public static void printf(String fmt, Object... params) {
		out.printf(fmt, params);
	}
}