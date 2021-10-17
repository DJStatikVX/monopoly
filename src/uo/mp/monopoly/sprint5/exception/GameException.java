// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.exception;

/**
 * Excepci�n personalizada para errores de usuario/aplicaci�n/l�gicos
 * relacionados con el juego
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class GameException extends Exception {

	/**
	 * Serial por defecto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que muestra el mensaje pasado como par�metro
	 * @param arg0 mensaje a mostrar, de tipo String
	 */
	public GameException(String arg0) {
		super(arg0);
	}

}