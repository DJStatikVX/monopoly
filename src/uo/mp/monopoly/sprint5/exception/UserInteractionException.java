// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.exception;

/**
 * Excepci�n personalizada para errores de interacci�n del usuario
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class UserInteractionException extends Exception {

	/**
	 * Serial por defecto
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que muestra el mensaje pasado como par�metro
	 * @param arg0 mensaje a mostrar, de tipo String
	 */
	public UserInteractionException(String arg0) {
		super(arg0);
	}

}