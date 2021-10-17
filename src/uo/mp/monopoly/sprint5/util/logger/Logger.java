// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.util.logger;

// Importaci�n de clases
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Una implementaci�n b�sica de Logger que simplemente env�a 
 * los mensajes de error a la salida est�ndar de errores
 */
public class Logger {

	/**
	 * Registra el mensaje pasado como par�metro en un fichero logger
	 * @param msg mensaje a registrar, de tipo String
	 * @throws RuntimeException si se produce alg�n error al escribir
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