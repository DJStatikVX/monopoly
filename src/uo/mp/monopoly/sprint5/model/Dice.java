// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importación de clases
import java.util.Random;

/**
 * Clase Dice para determinar los movimientos de los jugadores en cada turno
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Dice {
	
	/**
	 * Obtiene una puntuación al azar y la devuelve
	 * @return Puntuación aleatoria obtenida
	 */
	public int roll() {
		Random r = new Random(); // se inicializa la clase Random
		// Se obtiene un número al azar entre 1 y 6
		int randomNumber = r.nextInt(6) + 1;
		
		return randomNumber; // se devuelve el número obtenido
	}
}