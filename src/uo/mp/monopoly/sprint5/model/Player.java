// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importación de clase
import java.io.PrintStream;
import java.util.ArrayList;

import uo.mp.monopoly.sprint5.interactor.PlayerInteractor;
import uo.mp.monopoly.sprint5.square.PropertySquare;
import uo.mp.monopoly.sprint5.square.Square;
import uo.mp.monopoly.sprint5.square.TaxSquare;

/**
 * Clase Player para los jugadores participantes
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Player {
	
	private String name;					// nombre del jugador
	private Square square;					// cuadrado en el que se halla
	private double money;					// cantidad de dinero que tiene
	private boolean bankrupt;				// si está en bancarrota o no
	private PlayerInteractor interactor;	// interactor del jugador
	
	private ArrayList<PropertySquare> properties; 	// lista de propiedades
	
	/**
	 * Constructor de Player que recibe como parámetro el nombre
	 * @param name nombre del jugador a crear, de tipo String
	 */
	public Player(String name) {
		setName(name);
		properties = new ArrayList<PropertySquare>();
	}

	/**
	 * Representa un movimiento del jugador a un nuevo cuadrado en el tablero,
	 * y muestra la información de la casilla alcanzada
	 * @param square cuadrado al que se moverá el personaje (tipo Square)
	 * @param out salida de información, de tipo PrintStream
	 */
	public void goTo(Square square, PrintStream out) {
		setSquare(square);
		square.landedOn(this, out);
	}
	
	/**
	 * Devuelve un String con la representación textual del objeto jugador,
	 * mostrando su nombre, la casilla que ocupa y cuánto dinero posee
	 * @return Cadena de caracteres del siguiente formato: Ana-ONE-1500,00
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player ");
		
		sb.append(getName()).append(", at square = ");
		sb.append(getSquare().toString()).append(", isBankrupt() = ");
		sb.append(isBankrupt()).append(", money = ").append(getMoney());
		sb.append(", properties = ").append(propertyNames());
		
		return sb.toString();		
	}
	
	/**
	 * El jugador pasa por la casilla de salida, gana dinero en este caso
	 * @param out salida, de tipo PrintStream
	 */
	public void passesGO(PrintStream out) {
		out.println("Player " + getName() + " passed GO square");
		addMoney(200.0, out);
	}
	
	/**
	 * Paga el precio de la renta al propietario de la casilla
	 * @param square casilla por la que debe pagar la renta
	 * @param out salida, de tipo PrintStream
	 */
	public void payRent(Square square, PrintStream out) {
		if (square instanceof PropertySquare) {
			PropertySquare property = (PropertySquare) square;
			
			// Si la casilla tiene propietario, le paga el precio de renta
			if (property.getStatus()) {
				
				printPaidRent( property.getOwner(), out);
				takeMoneyAway(property.getRentingPrice(), out);
				property.getOwner().addMoney(property.getRentingPrice(), out);
							
			} else { 
				// Si no, se ofrece opción de comprarla				
				if (wantsToBuy(property)) {
					buyProperty(property, out); // si acepta, compra la casilla
				}
			}
		}
	}
	
	/**
	 * Imprime por pantalla que un jugador ha pagado la renta a otro jugador
	 * @param owner propietario de la casilla, de tipo Player
	 * @param out salida, de tipo PrintStream
	 */
	private void printPaidRent(Player owner, PrintStream out) {
		out.println("Player " + getName() + " paid the rent to "
				+ owner.getName());
	}
		
	/**
	 * Paga la tasa de una casilla de impuestos
	 * @param square casilla por la que debe pagar el impuesto
	 * @param out salida, de tipo PrintStream
	 */
	public void payTax(Square square, PrintStream out) {
		if (square instanceof TaxSquare) {
			takeMoneyAway(((TaxSquare) square).getTaxPrice(), out);
			if (!isBankrupt()) {
				printPaidTax(out);
			}
		}
	}
	
	/**
	 * Informa de que el jugador ha pagado un impuesto
	 * @param out salida, de tipo PrintStream
	 */
	protected void printPaidTax(PrintStream out) {
		out.println("Player " + getName() + " paid a tax of "
				+ ((TaxSquare) getSquare()).getTaxPrice() + "€");
	}
	
	/**
     * Comprueba si el jugador quiere comprar la casilla
     * @return True si quiere comprar la casilla y false si no quiere
     */
    private boolean wantsToBuy(PropertySquare square) {
        return getInteractor().doYouWantToBuy(this, square); 
    }
    
    /**
     * Compra la propiedad pasada como parámetro
     * @param square casilla a comprar, de tipo Square
     * @param out salida, de tipo PrintStream
     */
    protected void buyProperty(Square square, PrintStream out) {
    	// Se comprueba que sea una casilla de propiedad
    	if (square instanceof PropertySquare) {
    		PropertySquare property = (PropertySquare) square; // down-cast
    		
    		// Si tiene dinero suficiente para comprarla...
    		if (getMoney() >= property.getBuyingPrice()) {
    			takeMoneyAway(property.getBuyingPrice(), out); // resta dinero
    			property.setStatus(true);
    			property.setOwner(this);  // ahora es el propietario
    			properties.add(property); // se añade a la lista de propiedades

    			
    		} else { // si no, imprime un mensaje
    			out.println("You don't have enough money to buy this property");
    		}
    	}
    }
    
    /**
     * Imprime que un jugador ha comprado una propiedad
     * @param property propiedad que se ha comprado, de tipo PropertySquare
     * @param out salida, de tipo PrintStream
     */
    @SuppressWarnings("unused")
	private void printBoughtProperty(PropertySquare property, PrintStream out) {
    	out.println("Player " + getName() + " bought the property " 
    			+ property.getName());
    }
    
	/**
	 * Devuelve una cadena con los nombres de las propiedades del jugador
	 * @return Cadena con los nombres de las propiedades del jugador
	 */
	private String propertyNames() {
		StringBuilder sb = new StringBuilder();
		
		// Itera por la lista de propiedades y concatena sus nombres
		for (int i = 0; i < properties.size(); i++) {	
			if (i < properties.size() - 1) {
				sb.append(properties.get(i).getName()).append(", ");
			} else {
				sb.append(properties.get(i).getName());
			}
		}
		
		return sb.toString(); // se devuelve la cadena
		
	}
	
	/**
	 * Hace que se liquiden los bienes del jugador, produciendo
	 * dinero (la mitad del precio de venta de cada propiedad)
	 * @return Cantidad total liquidada, de tipo double
	 */
	private double liquidate() {
		double liquidatedValues = 0.0; // valor liquidado
		
		// Liquida todas las propiedades y se eliminan de su lista 
		for (PropertySquare prop : properties) {
			prop.liquidate();
			liquidatedValues += prop.getBuyingPrice();
			
			prop.setStatus(false);
			prop.setOwner(null);
		}
		
		return liquidatedValues; // se devuelve el valor total liquidado
	}
	
	/**
	 * Paga las deudas correspondientes al acreedor
	 */
	private void payDebts(PrintStream out) {
		double totalMoney = getMoney() + liquidate(); // dinero en total
		
		// Sólo salda sus deudas en casillas de propiedad con propietario
		if (this.getSquare() instanceof PropertySquare) {
			PropertySquare liquidatingSquare = (PropertySquare)this.getSquare();
			double renting = liquidatingSquare.getRentingPrice();
			
			// Si la casilla tiene dueño
			if (liquidatingSquare.getStatus()) {
				
				// Si tiene dinero suficiente, paga toda la deuda
				if (totalMoney >= renting) {
					
					liquidatingSquare.getOwner().addMoney(renting, out);
					this.takeMoneyAway(getMoney(), out);
					
					printPaidDebt(renting, liquidatingSquare.getOwner(), out);
				
				// En caso contrario, paga lo que tiene
				} else {
					
					liquidatingSquare.getOwner().addMoney(totalMoney, out);
					this.takeMoneyAway(getMoney(), out);
					
					printPaidDebt(totalMoney, liquidatingSquare.getOwner(),out);
				}
			}
		}
	}
	
	/**
	 * Informa de que el jugador ha pagado sus deudas a un acreedor
	 * @param amount cantidad que se ha pagado al acreedor, de tipo double
	 * @param owner acreedor (propietario de la casilla donde entra en bankrupt)
	 * @param out salida, de tipo PrintStream
	 */
	private void printPaidDebt(double amount, Player owner, PrintStream out) {
		out.println("Player " + getName() + " has paid his debts ("
				+ amount + "€) to " + owner.getName());
	}
	
	/**
	 * Quita el dinero del jugador si tiene suficiente
	 * @param amount cantidad de dinero a perder
	 * @param out salida, de tipo PrintStream
	 */
	public void takeMoneyAway(double amount, PrintStream out) {
		assertMoney(amount);
		// Si debe más de lo que tiene, entra en bancarrota
		if (amount > getMoney()) {
			printNotEnoughMoney(out);
			goBankrupt(out);
			payDebts(out);
			
		// Si no, paga la cantidad correspondiente
		} else {
			setMoney(getMoney() - amount);
			// printMoneyLoss(amount, out);
		}
	}
	
	/**
	 * Informa de que el jugador debe más de lo que tiene
	 * @param out salida, de tipo PrintStream
	 */
	private void printNotEnoughMoney(PrintStream out) {
		out.println("Player " + getName() + " owes more money than owns");
	}
	
	/**
	 * Imprime informando de que el jugador ha perdido una cantidad de dinero
	 * @param amount cantidad de dinero perdida, de tipo double
	 * @param out salida, de tipo PrintStream
	 */
	@SuppressWarnings("unused")
	private void printMoneyLoss(double amount, PrintStream out) {
		out.println("Player " + getName() + " has lost " + amount + "€");
	}
	
	/**
	 * Aumenta el dinero del jugador
	 * @param amount cantidad de dinero a ganar
	 * @param out salida, de tipo PrintStream
	 */
	private void addMoney(double amount, PrintStream out) {
		assertMoney(amount);
		
		setMoney(getMoney() + amount);
		
		printAddMoney(amount, out);
	}
	
	/**
	 * Imprime informando de que el jugador ha ganado una cantidad de dinero
	 * @param amount cantidad de dinero obtenida, de tipo double
	 * @param out salida, de tipo PrintStream
	 */
	private void printAddMoney(double amount, PrintStream out) {
		out.println("Player " + getName() + " has received " + amount + "€");
	}
	
	/**
	 * Asigna el nombre del personaje, siempre y cuando no sea nulo
	 * @param name nombre a establecer, de tipo String
	 * @throws "El nombre no puede ser nulo" si name vale null
	 * @throws "El nombre no puede estar formado por espacios en blanco"
	 * 		   si name no contiene al menos una letra
	 */
	private void setName(String name) {
		if (name != null) {
			assertName(name);
			
			this.name = name;
		} else {
			throw new IllegalArgumentException("Player name can't be null.");
		}
	}
	
	/**
	 * Devuelve el nombre del personaje
	 * @return Nombre del personaje, de tipo cadena de caracteres (String)
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Asigna la casilla en la que se halla el jugador
	 * @param square casilla a asignar, de tipo Square
	 * @throws IllegalArgumentException si square vale null
	 */
	public void setSquare(Square square) {
		assertSquare(square);
		this.square = square;
	}
	
	/**
	 * Devuelve el cuadrado (casilla) en la que se halla el jugador
	 * @return Cuadrado en el que se halla el jugador, de tipo Square
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 * Asigna la cantidad de dinero de la que dispone el jugador
	 * @param money nueva cantidad de dinero, de tipo double
	 * @throws IllegalArgumentException si money es menor que 0
	 */
	public void setMoney(double money) {
		assertMoney(money);
		
		this.money = money;
	}
	
	/**
	 * Devuelve la cantidad de dinero que posee el jugador
	 * @return Cantidad de dinero que posee el jugador, de tipo double
	 */
	public double getMoney() {
		return this.money;
	}
	
	/**
	 * Devuelve la lista de propiedades del jugador
	 * @return Lista de propiedades, ArrayList de PropertySquare
	 */
	public ArrayList<PropertySquare> getProperties() {
		return this.properties;
	}
	
	/**
	 * Establece el interactor del jugador que invoca al método, si no es nulo
	 * @param interactor interactor a asignar, de tipo Interactor
	 * @throws IllegalStateException si el interactor vale null
	 */
	public void setInteractor(PlayerInteractor interactor) {
		if (interactor == null) {
			throw new IllegalStateException("A player's interactor"
					+ " can't be null.");
		}
		
		this.interactor = interactor;
	}
	
	/**
	 * Devuelve el interactor del jugador
	 * @return Interactor del jugador, de tipo Interactor
	 */
	public PlayerInteractor getInteractor() {
		return this.interactor;
	}
	
	/**
	 * Intercambia el interactor del jugador que llama al método
	 * con el del jugador pasado como parámetro
	 * @param player jugador con el que se quiere intercambiar el interactor
	 */
	public void interchangeInteractorWith(Player player) {
		PlayerInteractor tmp = getInteractor();
		
		setInteractor(player.getInteractor());
		player.setInteractor(tmp);
	}
	
	/**
	 * Imprime la cantidad actual del jugador
	 * @param out salida, de tipo PrintStream
	 */
	@SuppressWarnings("unused")
	private void printActualMoney(PrintStream out) {
		out.println("Player " + getName() + "'s actual money: " 
				+ getMoney() + "€");
	}
	
	/**
	 * Devuelve si el jugador está en bancarrota o no
	 * @return True si el jugador está en bancarrota; false en otro caso
	 */
	public boolean isBankrupt() {
		return bankrupt;
	}
	
	/**
	 * El jugador va a bancarrota, cambiando su estado a true
	 * y liquidando sus propiedades
	 * @param out salida, de tipo PrintStream
	 */
	private void goBankrupt(PrintStream out) {
		setBankrupt(true);
	}
	
	/**
	 * Imprime informando de que el jugador que llama al método
	 * ha llegado a bancarrota
	 * @param out salida, de tipo PrintStream
	 */
	@SuppressWarnings("unused")
	private void printBankrupt(PrintStream out) {
		out.println("Player " + getName() + " has gone to bankrupt");
	}

	/**
	 * Establece el estado de bancarrota del jugador
	 * @param bankrupt estado de bancarrota a asignar, de tipo boolean
	 */
	private void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}
	
	/**
	 * Comprueba que el nombre pasado como parámetro no sea vacío
	 * @param name nombre a comprobar, de tipo String
	 * @throws "El nombre no puede estar formado por espacios en blanco"
	 * 		   si name no contiene al menos una letra
	 */
	private void assertName(String name) {
		int c = 0; // contador de caracteres vacíos
		
		// Recorre el nombre en busca de caracteres vacíos
		for (int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);
			if (character == ' ') {
				c++;
			}
		}
		
		// Comprueba si todos los caracteres han sido vacíos
		if (c == name.length()) {
			throw new IllegalArgumentException("Player's name can't be blank.");
		}
	}
	
	/**
	 * Comprueba que el cuadrado/casilla pasado como parámetro no sea nulo
	 * @throws "El cuadrado no puede ser nulo" si square vale null
	 */
	private void assertSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("Player's square can't be null.");
		}
	}
	
	/**
	 * Comprueba que la cantidad de dinero sea correcta
	 * @param money cantidad de dinero a comprobar, de tipo double
	 * @throws "El dinero no puede ser negativo" si money es negativo
	 */
	private void assertMoney(double money) {
		if (money < 0.00) {
			throw new IllegalArgumentException("Player's money "
					+ "can't be negative.");
		}
	}
}