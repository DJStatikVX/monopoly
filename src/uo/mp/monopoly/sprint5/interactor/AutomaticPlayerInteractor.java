// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

/**
 * Interactor que actúa de forma automática con el juego, lanzando
 * los dados automáticamente cada vez que sea posible y respondiendo Yes
 * cada vez que sea posible comprar alguna propiedad.
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class AutomaticPlayerInteractor implements PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecución va a esperar
	 * hasta que el jugador realice alguna acción específica que
	 * signifique el lanzamiento de los mismos.
	 */
	@Override
	public void askToRollTheDice() {
		System.out.println("Rolling the dice AUTOMATICALLY .... ");
	}

	/**
	 * Devuelve true automáticamente, comprando la casilla en cuestión
	 * siempre que sea posible
	 * @param p jugador controlado, de tipo Player
	 * @param ps casilla a intentar comprar, de tipo PropertySquare
	 * @return True
	 */
	@Override
	public boolean doYouWantToBuy(Player p, PropertySquare ps) {
		return true;
	}

}