// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.ui;

import uo.mp.monopoly.sprint5.util.console.Console;

/**
 * Interfaz de usuario para la interacción entre el cliente y el juego
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Menu {
	
	private String[] options = {
			"New plaYer (y or Y)",
			"Delete player (d or D)",
			"New Board (b or B)",
			"Let's Play (p or P)",
		};
		
	/**
	 * Lee el caracter introducido por el usuario
	 * @return Caracter introducido por el usuario, de tipo char
	 */
	public char readOption() {
		return Console.readChar("Option");
	}

	/**
	 * Muestra por consola la lista de opciones disponibles
	 * para que el usuario pueda escoger una de ellas
	 */
	public void show() {
		for (String line : options) {
			if ("".equals(line)) {
				Console.println("");
				continue;
			}
			
			Console.printf("\t%s\n", line);

		}
	}
}