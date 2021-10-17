// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.square;

// Importación de la clase PrintStream para la salida
import java.io.PrintStream;

import uo.mp.monopoly.sprint5.model.Player;

/**
 * Clase PropertySquare (casilla de propiedad)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class PropertySquare extends Square {
	
	private double buyingPrice;		// precio de compra
	private double rentingPrice;	// precio de renta
	private boolean status;			// estado (si tiene propietario)
	private Player owner;			// propietario
	
	/**
	 * Constructor que recibe como parámetros el nombre, la posición,
	 * el precio de compra, el precio de renta y el tipo de propiedad.
	 * Inicialmente, la casilla de propiedad no tiene propietario
	 * 
	 * @param name nombre de la casilla, de tipo String
	 * @param position posición de la casilla, de tipo int
	 * @param buyingPrice precio de compra, de tipo double
	 * @param rentingPrice precio de renta, de tipo double
	 */
	public PropertySquare(String name, int position, double buyingPrice,
			double rentingPrice) {
		super(name, position);
		setBuyingPrice(buyingPrice);
		setRentingPrice(rentingPrice);
		setStatus(false);
	}
	
	/**
	 * Devuelve información útil sobre la casilla de propiedad
	 * @return Información de la casilla en cadena, de tipo String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" = ");
		String ownerFlag = " "; // almacena el nombre de prop. o "not owned"
		
		ownerFlag = calculateOwnerFlag(); // calcula el ownerFlag
		
		sb.append(getName()).append(", POSITION = ").append(getPosition());
		sb.append(", OWNER = ").append(ownerFlag).append(", BUYING PRICE = ");
		sb.append(getBuyingPrice()).append(", RENTING PRICE = ");
		sb.append(getRentingPrice()).append("]");
		
		return sb.toString(); // se devuelve la cadena
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
		
		player.payRent(this, out);
	}
	
	/**
	 * Liquida la propiedad, reduciendo el precio de venta a la mitad
	 */
	public void liquidate() {
		setBuyingPrice(getBuyingPrice() / 2);
	}

	/**
	 * Devuelve el precio de compra de la casilla
	 * @return Precio de compra, de tipo double
	 */
	public double getBuyingPrice() {
		return buyingPrice;
	}

	/**
	 * Asigna el precio de compra de la casilla (si es válido)
	 * @param buyingPrice precio de compra a asignar, de tipo double
	 * @throws "El precio es ilegal" si buyingPrice es negativo
	 */
	private void setBuyingPrice(double buyingPrice) {
		assertPrice(buyingPrice);
		this.buyingPrice = buyingPrice;
	}

	/**
	 * Devuelve el precio de renta de la casilla
	 * @return Precio de renta, de tipo double
	 */
	public double getRentingPrice() {
		return rentingPrice;
	}

	/**
	 * Asigna el precio de renta de la casilla (si es válido)
	 * @param rentingPrice precio de renta a asignar, de tipo double
	 * @throws "El precio es ilegal" si rentingPrice es negativo
	 */
	private void setRentingPrice(double rentingPrice) {
		assertPrice(rentingPrice);
		this.rentingPrice = rentingPrice;
	}

	/**
	 * Devuelve si la casilla tiene propietario o no
	 * @return True si la casilla tiene propietario; false en otro caso
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Asigna el estado de la casilla; es decir, si tiene propietario o no
	 * @param status estado de la casilla a asignar, de tipo boolean
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Devuelve el jugador propietario de la casilla
	 * @return Propietario de la casilla, de tipo Player
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Asigna el jugador propietario de la casilla
	 * @param owner jugador a asignar como propietario
	 */
	public void setOwner(Player owner) {
		this.owner = owner; // puede ser null
	}
	
	/**
	 * Calcula el String del propietario en el método toString
	 * @param ownerFlag String en el que se almacena la cadena calculada
	 * @return 
	 */
	private String calculateOwnerFlag() {
		if (getStatus()) {
			return getOwner().getName();
		} else {
			return "not owned";
		}
	}

	/**
	 * Comprueba que el precio pasado como parámetro sea válido
	 * @param price precio a comprobar, de tipo double
	 * @throws "El precio es ilegal" si price es negativo
	 */
	private void assertPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price is illegal.");
		}
	}
}