// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase LotSquare (casilla de propiedad para estaciones)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class RailroadSquare extends PropertySquare {
	
	/**
	 * Constructor que recibe como parámetros el nombre, la posición,
	 * el precio de compra, el precio de renta y el tipo de propiedad. 
	 * @param name nombre de la casilla, de tipo String
	 * @param position posición de la casilla, de tipo int
	 * @param buyingPrice precio de compra, de tipo double
	 * @param rentingPrice precio de renta, de tipo double
	 */
	public RailroadSquare(String name, int position, double buyingPrice,
			double rentingPrice) {
		super(name, position, buyingPrice, rentingPrice);
	}
	
	/**
	 * Devuelve información útil sobre la casilla
	 * @return Información de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[RAILROAD");
		
		sb.append(super.toString());
		
		return sb.toString();
	}
}