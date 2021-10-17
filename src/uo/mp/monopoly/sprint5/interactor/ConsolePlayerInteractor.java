// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.interactor;

import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.PropertySquare;

import java.util.Scanner;

/**
 * Interactor que realiza la interacci�n en forma de texto plano
 * a trav�s de la consola manualmente, siendo el usuario quien
 * interact�a con el juego a trav�s del teclado.
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class ConsolePlayerInteractor implements PlayerInteractor {

	/**
	 * Indica al jugador que lance los dados. La ejecuci�n va a esperar
	 * hasta que el jugador realice alguna acci�n espec�fica que
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
	 * Informa al usuario con informaci�n sobre su cantidad de dinero disponible
	 * y la propiedad a comprar. Despu�s, espera a que el usuario realice
	 * alguna acci�n espec�fica que le indique Yes/Y o No/N.
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