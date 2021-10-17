// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase GoSquare (casilla de salida)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class GoSquare extends Square {
	
	/**
	 * Constructor que recibe como parámetros el nombre y la posición
	 * @param name nombre de la casilla, de tipo String
	 * @param position posición de la casilla, de tipo int
	 */
	public GoSquare(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Devuelve información útil sobre la casilla
	 * @return Información de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[GO SQUARE, POSITION = ");
		
		sb.append(getPosition()).append("]");
		
		return sb.toString();
	}		
}