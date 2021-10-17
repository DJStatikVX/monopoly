// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importaci�n de clases
import java.util.Random;

/**
 * Clase Dice para determinar los movimientos de los jugadores en cada turno
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class Dice {
	
	/**
	 * Obtiene una puntuaci�n al azar y la devuelve
	 * @return Puntuaci�n aleatoria obtenida
	 */
	public int roll() {
		Random r = new Random(); // se inicializa la clase Random
		// Se obtiene un n�mero al azar entre 1 y 6
		int randomNumber = r.nextInt(6) + 1;
		
		return randomNumber; // se devuelve el n�mero obtenido
	}
}