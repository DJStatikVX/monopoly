// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

// Importación de clases
import java.io.PrintStream;

import uo.mp.monopoly.sprint5.model.Player;

/**
 * Clase Square para las diferentes casillas del tablero de juego
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Square {

	private String name;	// nombre del cuadrado
	private int position;   // posición del cuadrado
	
	/**
	 * Constructor de Square que recibe un nombre y una posición
	 * @param name nombre del cuadrado a instanciar
	 * @param position posición en la que se colocará el cuadrado a instanciar
	 */
	public Square(String name, int position) {
		setName(name);
		setPosition(position);	
	}
	
	/**
	 * Avisa por salida de que un jugador ha alcanzado una casilla determinada
	 * @param player jugador sobre el que se va a imprimir el mensaje (Player)
	 * @param out salida de información, de tipo PrintStream
	 * @throws IllegalArgumentException si player vale null
	 */
	public void landedOn(Player player, PrintStream out) {
		assertPlayer(player);
		
		// Se busca la redefinición del método en las subclases
	}
	
	/**
	 * Devuelve información útil sobre la casilla
	 * @return Información de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[NO FEE SQUARE = ");
		
		sb.append(getName()).append(", POSITION = ").append(getPosition());
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * Asigna la posición del cuadrado o casilla
	 * @param position posición a asignar para el cuadrado
	 * @throws "La posición no es válida" si position no está en el [1, 12]
	 */
	private void setPosition(int position) {
		assertPosition(position);
		this.position = position;
	}
	
	/**
	 * Devuelve la posición del cuadrado
	 * @return Posición del cuadrado, de tipo int (entre 1 y 12)
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Asigna el nombre del cuadrado o casilla, siempre que sea válido
	 * @param name nombre a asignar al cuadrado, de tipo String
	 * @throws "El nombre no puede ser null" si name vale null
	 * @throws "El nombre no puede ser vacío" si name está formado por blancos
	 */
	private void setName(String name) {
		assertName(name);
		
		this.name = name;
	}
	
	/**
	 * Devuelve el nombre del cuadrado
	 * @return Nombre del cuadrado, de tipo String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Comprueba que el jugador no sea nulo
	 * @param player jugador a comprobar, de tipo Player
	 * @throws "El jugador no puede ser nulo" si player vale null
	 */
	protected void assertPlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException("Player can't be null.");
		}
	}
	
	/**
	 * Comprueba que el nombre de la casilla no sea nulo
	 * @param name nombre a comprobar, de tipo String
	 * @throws "El nombre no puede ser null" si name vale null
	 * @throws "El nombre no puede ser vacío" si name está formado por blancos
	 */
	private void assertName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Player name can't be null.");
		} else if (name.equals("\n") || name.equals("\t")) {
			throw new IllegalArgumentException("Player name can't be blank.");
		}
	}
	
	/**
	 * Comprueba que la posición sea correcta
	 * @param position posición a comprobar, de tipo int
	 * @throws "La posición no es válida" si position está fuera de rango
	 */
	private void assertPosition(int position) {
		if (position < 1 || position > 40) {
			throw new IllegalArgumentException("Player position isn't valid.");
		}
	}
}