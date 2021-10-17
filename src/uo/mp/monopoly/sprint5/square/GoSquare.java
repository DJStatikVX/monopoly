// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase GoSquare (casilla de salida)
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class GoSquare extends Square {
	
	/**
	 * Constructor que recibe como par�metros el nombre y la posici�n
	 * @param name nombre de la casilla, de tipo String
	 * @param position posici�n de la casilla, de tipo int
	 */
	public GoSquare(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Devuelve informaci�n �til sobre la casilla
	 * @return Informaci�n de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[GO SQUARE, POSITION = ");
		
		sb.append(getPosition()).append("]");
		
		return sb.toString();
	}		
}