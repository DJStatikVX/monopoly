// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

// Importaci�n de clases
import java.io.PrintStream;

import uo.mp.monopoly.sprint5.model.Player;

/**
 * Clase Square para las diferentes casillas del tablero de juego
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class Square {

	private String name;	// nombre del cuadrado
	private int position;   // posici�n del cuadrado
	
	/**
	 * Constructor de Square que recibe un nombre y una posici�n
	 * @param name nombre del cuadrado a instanciar
	 * @param position posici�n en la que se colocar� el cuadrado a instanciar
	 */
	public Square(String name, int position) {
		setName(name);
		setPosition(position);	
	}
	
	/**
	 * Avisa por salida de que un jugador ha alcanzado una casilla determinada
	 * @param player jugador sobre el que se va a imprimir el mensaje (Player)
	 * @param out salida de informaci�n, de tipo PrintStream
	 * @throws IllegalArgumentException si player vale null
	 */
	public void landedOn(Player player, PrintStream out) {
		assertPlayer(player);
		
		// Se busca la redefinici�n del m�todo en las subclases
	}
	
	/**
	 * Devuelve informaci�n �til sobre la casilla
	 * @return Informaci�n de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[NO FEE SQUARE = ");
		
		sb.append(getName()).append(", POSITION = ").append(getPosition());
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * Asigna la posici�n del cuadrado o casilla
	 * @param position posici�n a asignar para el cuadrado
	 * @throws "La posici�n no es v�lida" si position no est� en el [1, 12]
	 */
	private void setPosition(int position) {
		assertPosition(position);
		this.position = position;
	}
	
	/**
	 * Devuelve la posici�n del cuadrado
	 * @return Posici�n del cuadrado, de tipo int (entre 1 y 12)
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Asigna el nombre del cuadrado o casilla, siempre que sea v�lido
	 * @param name nombre a asignar al cuadrado, de tipo String
	 * @throws "El nombre no puede ser null" si name vale null
	 * @throws "El nombre no puede ser vac�o" si name est� formado por blancos
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
	 * @throws "El nombre no puede ser vac�o" si name est� formado por blancos
	 */
	private void assertName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Player name can't be null.");
		} else if (name.equals("\n") || name.equals("\t")) {
			throw new IllegalArgumentException("Player name can't be blank.");
		}
	}
	
	/**
	 * Comprueba que la posici�n sea correcta
	 * @param position posici�n a comprobar, de tipo int
	 * @throws "La posici�n no es v�lida" si position est� fuera de rango
	 */
	private void assertPosition(int position) {
		if (position < 1 || position > 40) {
			throw new IllegalArgumentException("Player position isn't valid.");
		}
	}
}