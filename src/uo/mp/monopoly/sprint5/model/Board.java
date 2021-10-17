// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.model;

import java.util.ArrayList;
// Importaci�n de clases
import java.util.List;

import uo.mp.monopoly.sprint5.square.Square;

/**
 * Clase Board para albergar el tablero de juego y sus componentes
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class Board {

	private List<Square> squares; // cuadrados de los que consta el tablero
	
	/**
	 * Constructor por defecto del tablero,
	 * que establece el n�mero de cuadrados (fichas) a 40
	 */
	public Board() {
		setSquares(new ArrayList<Square>());
	}
	
	/**
	 * Recibe un conjunto (lista) de cuadrados y lo a�ade al tablero
	 * @param squares lista de cuadrados a a�adir, List de Square
	 * @return El propio tablero, con sus cuadrados actualizados
	 * @throws IllegalArgumentException si squares vale null o est� vac�a
	 */
	public Board addSquares(List<Square> squares) {
		assertSquareList(squares);
//		int count = 0; // contador
					
		// Se establece en el tablero cada cuadrado de la lista
		for (Square sq : squares) {
			getSquares().add(sq);
//			count++;
		}
					
		return this; // se devuelve el propio tablero actualizado
	}
	
	/**
	 * Devuelve el (n)avo cuadrado del tablero
	 * @param n posici�n del cuadrado que se desea obtener, de tipo int
	 * @return Cuadrado con la posici�n buscada, de tipo Square
	 * @throws "La posici�n a buscar no es v�lida" si n est� fuera de rango
	 * @throws IllegalStateException si la casilla a devolver vale null
	 */
	protected Square getNthSquare(int n) {
		assertPosition(n);
		Square result = null; // variable para almacenar el resultado
		
		// Busca el cuadrado que coincida con la b�squeda
		for (int i = 0; i < getSquares().size(); i++) {
			if (getSquares().get(i).getPosition() == n) {
				result = getSquares().get(i);
				break;
			}
		}
		
		// Se comprueba si la casilla obtenida ha sido null
		if (result == null) {
			throw new IllegalStateException("The returned square can't be null.");
		}

		return result; // se devuelve el cuadrado encontrado (null si no)
	}
	
	/**
	 * Asigna el conjunto de cuadrados (fichas) del tablero
	 * @param array de cuadrados a asignar al tablero, ArrayList de Square
	 * @throws "El conjunto de cuadrados a asignar no puede ser nulo" si
	 *         squares vale null
	 */
	protected void setSquares(List<Square> squares) {
		assertSquareArray(squares);
		
		this.squares = squares;
	}
	
	/**
	 * Devuelve un array con los cuadrados (fichas) del tablero
	 * @return Array de cuadrados del tablero, ArrayList de Square
	 */
	public List<Square> getSquares() {
		return this.squares;
	}
	
	/**
	 * Comprueba que la lista de cuadrados a a�adir no sea nula
	 * @param squares lista a comprobar, de tipo List<Square>
	 * @throws "La lista a a�adir no puede ser nula" si squares vale null o vac�a
	 */
	private void assertSquareList(List<Square> squares) {
		if (squares == null || squares.size() == 0) {
			throw new IllegalArgumentException("Squares list can't be"
					+ " null or empty.");
		}
	}
	
	/**
	 * Comprueba que el array de cuadrados del tablero no sea nulo
	 * @param squares ArrayList de cuadrados a asignar al tablero
	 * @throws "El array de cuadrados del tablero no puede ser nulo" 
	 * 		   si squares vale null
	 */
	private void assertSquareArray(List<Square> squares) {
		if (squares == null) {
			throw new IllegalArgumentException("Squares array can't be null.");
		}
	}
	
	/**
	 * Comprueba que la posici�n sea correcta
	 * @throws "La posici�n a buscar no es v�lida" si position est�
	 * 		   fuera de rango
	 */
	private void assertPosition(int position) {
		if (position < 1 || position > 40) {
			throw new IllegalArgumentException("Searched position isn't valid.");
		}
	}
}