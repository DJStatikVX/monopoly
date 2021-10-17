// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importación de clases
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import uo.mp.monopoly.sprint5.interactor.AutomaticPlayerInteractor;
import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.square.GoSquare;
import uo.mp.monopoly.sprint5.square.LotSquare;
import uo.mp.monopoly.sprint5.square.NonFeeSquare;
import uo.mp.monopoly.sprint5.square.RailroadSquare;
import uo.mp.monopoly.sprint5.square.Square;
import uo.mp.monopoly.sprint5.square.TaxSquare;
import uo.mp.monopoly.sprint5.square.UtilitySquare;

/**
 * Test de la clase Player (jugadores)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class PlayerTest {
	
	private Player p;
	private Square[] squares;
	
	@Before
	public void setUp() {
		
		p = new Player("Default");
		p.setInteractor(new AutomaticPlayerInteractor());
		
		squares = new Square[] {
				new GoSquare("GO", 1),
				new LotSquare("Mediterranean Ave", 2, 60, 2),
				new NonFeeSquare("Golden Gate Park", 3),
				new LotSquare("Baltic Ave", 4, 60, 4),
				new TaxSquare("Income tax", 5, 200),
				new RailroadSquare("Reading Railroad", 6, 200, 20),
				new LotSquare("Oriental Ave", 7, 100, 6),
				new NonFeeSquare("Central Park", 8),
				new LotSquare("Vermont Ave", 9, 100, 6),
				new LotSquare("Connecticut Ave", 10, 120, 8),
				new NonFeeSquare("Tivoli Gardens", 11),
				new LotSquare("St. Charles Place", 12, 140, 10),
				new UtilitySquare("Electric Company", 13, 150, 15),
				new LotSquare("States Ave", 14, 140, 10),
				new LotSquare("Virginia Ave", 15, 160, 12),
				new RailroadSquare("Pennsylvania Railroad", 16, 200, 20),
				new LotSquare("St. James Place", 17, 180, 14),
				new NonFeeSquare("Hyde Park", 18),
				new LotSquare("Tennessee Ave", 19, 180, 14),
				new LotSquare("New York Ave", 20, 200, 16),
				new NonFeeSquare("FreeParking", 21),
				new LotSquare("Kentucky Ave", 22, 220, 18),
				new NonFeeSquare("Park Güell", 23),
				new LotSquare("Indiana Ave", 24, 220, 18),
				new LotSquare("Illinois Ave", 25, 240, 20),
				new RailroadSquare("O Railroad", 26, 200, 20),
				new LotSquare("Atlantic Ave", 27, 260, 22),
				new LotSquare("Ventnor Ave", 28, 260, 22),
				new UtilitySquare("Water Works", 29, 150, 15),
				new LotSquare("Marvin Gardens Ave", 30, 280, 24),
				new NonFeeSquare("Griffith Park", 31),
				new LotSquare("Pacific Ave", 32, 300, 26),
				new LotSquare("North Carolina Ave", 33, 300, 26),
				new NonFeeSquare("Luxembourg Garden", 34),
				new LotSquare("Pennsylvania Ave", 35, 320, 28),
				new RailroadSquare("ShortLine", 36, 200, 20),
				new NonFeeSquare("Balboa Park", 37),
				new LotSquare("Park Place", 38, 350, 35),
				new TaxSquare("Luxury tax", 39, 100),
				new LotSquare("Boardwalk", 40, 400, 50)
		};
	}

	/**
	 * Test del constructor de la clase con parámetro correcto
	 */
	@Test
	public void testPlayer() {
		
		// Caso 1: Parámetro con cadena no vacía
		p = new Player("Samuel");
		assertEquals("Samuel", p.getName());
		
	}
	
	/**
	 * Test del constructor con parámetro nulo
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullParamPlayer() {
		p = new Player(null);
	}
	
	/**
	 * Test del constructor con parámetro en blanco
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBlankParamPlayer() {
		p = new Player("    ");
	}

	/**
	 * Test del método goTo() con parámetros válidos
	 */
	@Test
	public void testGoTo() {
		
		// Comprobamos usando los 40 cuadrados que se utilizan en el juego
		for (int i = 0; i < 40; i++) {
			p.goTo(squares[i], System.out);
			assertEquals(squares[i], p.getSquare());
		}
	}
	
	/**
	 * Test del método goTo() con un cuadrado nulo
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullSquareGoTo() {
		p.goTo(null, System.out);
	}
}