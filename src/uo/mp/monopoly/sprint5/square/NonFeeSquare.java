// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase NonFreeSquare (casillas en la que no hay que pagar nada
 * al caer en ellas, como aparcamientos gratuitos, parques p�blicos y otros)
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class NonFeeSquare extends Square {
	
	/**
	 * Constructor que recibe como par�metros el nombre y la posici�n
	 * @param name nombre de la casilla, de tipo String
	 * @param position posici�n de la casilla, de tipo int
	 */
	public NonFeeSquare(String name, int position) {
		super(name, position);
	}
}