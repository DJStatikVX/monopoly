// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

// Importación de clases
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.monopoly.sprint5.model.Dice;

/**
 * Test de la clase Dice (dado)
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class DiceTest {
	
	private Dice d;

	@Before
	public void setUp() throws Exception {
		d = new Dice();
	}

	/**
	 * Test del método roll()
	 */
	@Test
	public void testRoll() {
		
		// Se lanza el dado y se almacena el resultado en una variable
		int result = d.roll();
		
		// Comprobamos que el número obtenido esté entre 1 y 6 (inclusive)
		assertTrue(result >= 1);
		assertTrue(result <= 6);
		
	}
}