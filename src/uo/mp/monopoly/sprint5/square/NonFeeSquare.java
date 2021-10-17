// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase NonFreeSquare (casillas en la que no hay que pagar nada
 * al caer en ellas, como aparcamientos gratuitos, parques públicos y otros)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class NonFeeSquare extends Square {
	
	/**
	 * Constructor que recibe como parámetros el nombre y la posición
	 * @param name nombre de la casilla, de tipo String
	 * @param position posición de la casilla, de tipo int
	 */
	public NonFeeSquare(String name, int position) {
		super(name, position);
	}
}