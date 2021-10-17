// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.application;

// Importación de clases
import uo.mp.monopoly.sprint5.ui.UserInterface;
import uo.mp.monopoly.sprint5.util.logger.Logger;

/**
 * Clase Main responsable de la ejecución del juego
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Main {

	/**
	 * Método principal que llama a la ejecución del juego
	 * @param args argumentos (no utilizados)
	 */
	public static void main(String[] args) {
		new Main().run();
	}
	
	/** 
	 * Se ejecuta el juego mostrando la interfaz
	 */
	private void run() {
		try {
			new UserInterface().show();
		} catch (RuntimeException re) {
			System.err.println("An internal and non recoverable error"
					+ " has occurred. Please contact the developer.");
			System.err.println(re.getMessage());
			Logger.log(re.getMessage());
			System.exit(-1);
		}
	}
}