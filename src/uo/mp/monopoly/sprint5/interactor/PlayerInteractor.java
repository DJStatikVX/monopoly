// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

/**
 * Interfaz cuyos m�todos implementados representan la interacci�n
 * del jugador con el juego durante su transcurso.
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public interface PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecuci�n va a esperar
	 * hasta que el jugador realice alguna acci�n espec�fica que
	 * signifique el lanzamiento de los mismos.
	 */
	void askToRollTheDice();
	
	/**
	 * Informa al usuario con informaci�n sobre su cantidad de dinero disponible
	 * y la propiedad a comprar. Despu�s, espera a que el usuario realice
	 * alguna acci�n espec�fica que le indique Yes/Y o No/N.
	 * @param p jugador al que se ofrece la compra, de tipo Player
	 * @param ps propiedad cuya compra se oferta al jugador p
	 * @return Si el jugador quiere comprar la propiedad, de tipo boolean
	 */
	boolean doYouWantToBuy(Player p, PropertySquare ps);
	
}