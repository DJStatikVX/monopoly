// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

import java.io.PrintStream;

import uo.mp.monopoly.sprint5.model.Player;

/**
 * Clase TaxSquare en la que el jugador que cae pierde dinero
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class TaxSquare extends Square {
	
	private double taxPrice;	// precio del impuesto
	
	/**
	 * Constructor que recibe como parámetros el nombre y la posición
	 * @param name nombre de la casilla, de tipo String
	 * @param position posición de la casilla, de tipo double
	 * @param taxPrice precio de impuesto de la casilla, de tipo double
	 */
	public TaxSquare(String name, int position, double taxPrice) {
		super(name, position);
		setTaxPrice(taxPrice);
	}
	
	/**
	 * Devuelve información útil sobre la casilla
	 * @return Información de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[TAX = ");
		
		sb.append(getName()).append(", POSITION = ").append(getPosition());
		sb.append(", TAX PRICE = ").append(getTaxPrice()).append("]");
		
		return sb.toString();
	}
	
	/**
	 * Avisa por salida de que un jugador ha alcanzado una casilla determinada
	 * @param player jugador sobre el que se va a imprimir el mensaje (Player)
	 * @param out salida de información, de tipo PrintStream
	 * @throws IllegalArgumentException si player vale null
	 */
	@Override
	public void landedOn(Player player, PrintStream out) {
		assertPlayer(player);
		
		player.payTax(this, out);
	}

	/**
	 * Devuelve el precio del impuesto de la casilla
	 * @return Precio del impuesto de la casilla, de tipo int
	 */
	public double getTaxPrice() {
		return taxPrice;
	}

	/**
	 * Asigna el precio del impuesto de la casilla (si es legal)
	 * @param taxPrice precio a asignar, de tipo double
	 * @throws "El precio es ilegal" si taxPrice es negativo
	 */
	private void setTaxPrice(double taxPrice) {
		assertPrice(taxPrice);
		
		this.taxPrice = taxPrice;
	}
	
	/**
	 * Comprueba que el precio pasado como parámetro no sea negativo
	 * @param price precio a comprobar, de tipo double
	 * @throws "El precio es ilegal" si price es negativo
	 */
	private void assertPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Square price is illegal.");
		}
	}
}