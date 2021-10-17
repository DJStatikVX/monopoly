// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

/**
 * Clase LotSquare (casilla de propiedad para calles y solares)
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class LotSquare extends PropertySquare {
	
	/**
	 * Constructor que recibe como par�metros el nombre, la posici�n,
	 * el precio de compra, el precio de renta y el tipo de propiedad.
	 * @param name nombre de la casilla, de tipo String
	 * @param position posici�n de la casilla, de tipo int
	 * @param buyingPrice precio de compra, de tipo double
	 * @param rentingPrice precio de renta, de tipo double
	 */
	public LotSquare(String name, int position, double buyingPrice,
			double rentingPrice) {
		super(name, position, buyingPrice, rentingPrice);
	}
	
	/**
	 * Devuelve informaci�n �til sobre la casilla
	 * @return Informaci�n de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[LOT");
		
		sb.append(super.toString());
		
		return sb.toString();
	}
}