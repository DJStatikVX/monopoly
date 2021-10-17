// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.util.logger;

// Importación de clases
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Una implementación básica de Logger que simplemente envía 
 * los mensajes de error a la salida estándar de errores
 */
public class Logger {

	/**
	 * Registra el mensaje pasado como parámetro en un fichero logger
	 * @param msg mensaje a registrar, de tipo String
	 * @throws RuntimeException si se produce algún error al escribir
	 * 		   en el fichero de log
	 */
	public static void log(String msg) {
		final boolean APPEND = true;
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream("logger", 
					APPEND));
			
			try {
				out.println(msg);
			} finally {
				out.close();
			}
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Failed to write logger file.");
		}
	}
}