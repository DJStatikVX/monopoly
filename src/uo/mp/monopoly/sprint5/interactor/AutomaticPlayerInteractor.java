// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

/**
 * Interactor que act�a de forma autom�tica con el juego, lanzando
 * los dados autom�ticamente cada vez que sea posible y respondiendo Yes
 * cada vez que sea posible comprar alguna propiedad.
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class AutomaticPlayerInteractor implements PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecuci�n va a esperar
	 * hasta que el jugador realice alguna acci�n espec�fica que
	 * signifique el lanzamiento de los mismos.
	 */
	@Override
	public void askToRollTheDice() {
		System.out.println("Rolling the dice AUTOMATICALLY .... ");
	}

	/**
	 * Devuelve true autom�ticamente, comprando la casilla en cuesti�n
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