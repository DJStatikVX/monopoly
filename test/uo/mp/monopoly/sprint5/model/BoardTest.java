// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importación de clases
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.mp.monopoly.sprint5.model.Board;
import uo.mp.monopoly.sprint5.square.GoSquare;
import uo.mp.monopoly.sprint5.square.LotSquare;
import uo.mp.monopoly.sprint5.square.NonFeeSquare;
import uo.mp.monopoly.sprint5.square.RailroadSquare;
import uo.mp.monopoly.sprint5.square.Square;
import uo.mp.monopoly.sprint5.square.TaxSquare;
import uo.mp.monopoly.sprint5.square.UtilitySquare;

/**
 * Test de la clase Board
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class BoardTest {
	
	private Board board;
	private List<Square> squares;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		squares = Arrays.asList(new Square[] { 
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
			});
	}

	/**
	 * Test del método addSquares() con parámetro válido
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testAddSquares() {
		
		// Caso 1: Se añade una lista vacía de cuadrados
		List<Square> list1 = new ArrayList<Square>();
		board.addSquares(list1);

		for (int i = 0; i < board.getSquares().size(); i++) {
			assertNull(board.getSquares().get(i));
		}
		
		// Caso 2: Se añade una lista no vacía de cuadrados
		List<Square> list2 = new ArrayList<Square>();
		board.setSquares(new ArrayList<Square>());
		list2.add(new Square("TWO", 2));
		board.addSquares(list2);
		
		assertEquals(2, board.getSquares().get(0).getPosition());
		
		// Caso 3: Se añade la lista por defecto de cuadrados
		board.addSquares(squares);
		
		for (int i = 0; i < board.getSquares().size(); i++) {
			assertNotNull(board.getSquares().get(i));
		}
		
	}
	
	/**
	 * Test del método addSquares() con parámetro nulo
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testNullParamAddSquares() {
		board.addSquares(null);
	}

	/**
	 * Test del método getNthSquare con parámetro válido
	 */
	@Test
	public void testGetNthSquare() {
		board.addSquares(squares);
		
		// Probamos con todos los cuadrados con los que se juega en el sprint 2
		for (int i = 1; i <= board.getSquares().size(); i++) {
			assertEquals(board.getSquares().get(i - 1), board.getNthSquare(i));
		}
	}
	
	/**
	 * Test del método getNthSquare con parámetro incorrecto
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidParamGetNthSquare() {
		
		// Caso 1: Parámetro negativo
		board.getNthSquare(-2);
		
		// Caso 2: Parámetro mayor que la longitud de la lista de cuadrados
		board.getNthSquare(47);
		
	}
}