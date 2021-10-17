// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.ui;

import uo.mp.monopoly.sprint5.interactor.AutomaticPlayerInteractor;
import uo.mp.monopoly.sprint5.interactor.ConsolePlayerInteractor;
import uo.mp.monopoly.sprint5.interactor.PlayerInteractor;
import uo.mp.monopoly.sprint5.util.console.Console;

/**
 * Clase encargada de pedir información al usuario para
 * poder llevar a cabo algunas de las operaciones solicitadas
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class PublicForm {
	
	/**
	 * Pide al usuario que introduzca dos posibles opciones
	 * para crear el interactor de un jugador a añadir al juego
	 * @return Interactor creado, de tipo PlayerInteractor
	 */
	public PlayerInteractor askForInteractor() {
		char type = Console.readChar("Interactor type? (a|c)");
		
		switch (type) {
			
			case 'a': return new AutomaticPlayerInteractor();
			case 'c': return new ConsolePlayerInteractor();
			
			default: return null;
			
		}
	}
}