// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

/**
 * Interfaz cuyos métodos implementados representan la interacción
 * del jugador con el juego durante su transcurso.
 * @author Samuel Rodríguez Ares (UO271612)
 */
public interface PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecución va a esperar
	 * hasta que el jugador realice alguna acción específica que
	 * signifique el lanzamiento de los mismos.
	 */
	void askToRollTheDice();
	
	/**
	 * Informa al usuario con información sobre su cantidad de dinero disponible
	 * y la propiedad a comprar. Después, espera a que el usuario realice
	 * alguna acción específica que le indique Yes/Y o No/N.
	 * @param p jugador al que se ofrece la compra, de tipo Player
	 * @param ps propiedad cuya compra se oferta al jugador p
	 * @return Si el jugador quiere comprar la propiedad, de tipo boolean
	 */
	boolean doYouWantToBuy(Player p, PropertySquare ps);
	
}