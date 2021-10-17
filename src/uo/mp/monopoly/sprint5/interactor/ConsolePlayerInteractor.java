// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

import java.util.Scanner;

/**
 * Interactor que realiza la interacción en forma de texto plano
 * a través de la consola manualmente, siendo el usuario quien
 * interactúa con el juego a través del teclado.
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class ConsolePlayerInteractor implements PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecución va a esperar
	 * hasta que el jugador realice alguna acción específica que
	 * signifique el lanzamiento de los mismos.
	 */
	@Override
	public void askToRollTheDice() {
		System.out.print("Press ENTER key to roll the dice ...... ");
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

	/**
	 * Informa al usuario con información sobre su cantidad de dinero disponible
	 * y la propiedad a comprar. Después, espera a que el usuario realice
	 * alguna acción específica que le indique Yes/Y o No/N.
	 * @param p jugador controlado, de tipo Player
	 * @param ps casilla a intentar comprar, de tipo PropertySquare
	 * @return True si la respuesta introducida coincide con Y o YES;
	 * 		   false en cualquier otro caso
	 */
	@Override
	public boolean doYouWantToBuy(Player p, PropertySquare ps) {
        System.out.println("Do you want to buy the street " 
        		+ ps.getName() +"?");
        
        @SuppressWarnings("resource")
		String answer = new Scanner(System.in).next().toUpperCase();
        
        return (answer.equals("Y") || answer.equals("YES"));
	}

}