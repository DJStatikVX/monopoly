// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.presenter;

/**
 * Presentador que imprime todos los mensajes en un formato de texto
 * personalizado, de letra verde brillante sobre fondo negro 
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class ColoredConsolePresenter implements GamePresenter {
	
	/**
	 * Constante para empezar a imprimir texto en color verde brillante
	 */
	public static final String GREEN = "\033[32;1;4m";
	
	/**
	 * Constante para empezar a imprimir texto en color cián brillante
	 */
	public static final String CYAN = "\033[36;1;4m";
	
	/**
	 * Constante para cerrar el formato de texto impreso
	 */
	public static final String CLOSE = "\033[0m";

	/**
	 * Muestra el mensaje "Welcome to Monopoly Game."
	 */
	@Override
	public void gameInitialized() {
		System.out.println(CYAN + "Welcome to Monopoly Game." + CLOSE);
	}

	/**
	 * Muestra el nombre del jugador y la casila en el que se encuentra
	 * @param playerName nombre del jugador, de tipo String
	 * @param square casilla en la que está el jugador, de tipo String
	 */
	@Override
	public void nextPlayer(String playerName, String square) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("Turn ").append(CLOSE);
		sb.append(GREEN).append(playerName).append(CLOSE);
		sb.append(CYAN).append(" stands on ").append(CLOSE);
		sb.append(GREEN).append(square).append(CLOSE);
		
		System.out.println(sb);
	}

	/**
	 * Muestra el valor sumado de las puntuaciones obtenidas por los dados
	 * @param value suma de puntuaciones de los dados [2..12]
	 */
	@Override
	public void diceRolled(int value) {
		System.out.println(GREEN + value + CLOSE);
	}

	/**
	 * Procesa la información de la casilla a la que se va a desplazar
	 * el jugador en cuestión y lo anuncia junto al nombre del mismo
	 * @param playerName nombre del jugador que se desplaza
	 * @param squareInfo cadena de información sin procesar de la próx. casilla
	 */
	@Override
	public void nextSquare(String playerName, String squareInfo) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("Player ").append(CLOSE);
		sb.append(GREEN).append(playerName).append(CLOSE);
		sb.append(CYAN).append(" lands on ").append(CLOSE);
		
		String[] parsed = squareInfo.split(",");
		
		sb.append(GREEN);
		
		for (String str : parsed) {
			sb.append(str);
		}
		
		sb.append(CLOSE);
		
		System.out.println(sb);
	}

	/**
	 * Muetra un mensaje de tipo "Player <playerName> goes bankrupt"
	 * @param playerName nombre del jugador que ha perdido, de tipo String
	 */
	@Override
	public void playerGameOver(String playerName) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("Player ").append(CLOSE);
		sb.append(GREEN).append(playerName).append(CLOSE);
		sb.append(CYAN).append(" goes bankrupt").append(CLOSE);
		
		System.out.println(sb);
	}

	/**
	 * Muestra un mensaje de tipo "Player <playerName> buys <squareName>"
	 * @param playerName nombre del comprador, de tipo String
	 * @param squareName nombre de la casilla, de tipo String
	 */
	@Override
	public void playerBuys(String playerName, String squareName) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("Player ").append(CLOSE);
		sb.append(GREEN).append(playerName).append(CLOSE);
		sb.append(CYAN).append(" buys ").append(CLOSE);
		sb.append(GREEN).append(squareName).append(CLOSE);
		
		System.out.println(sb);
	}

	/**
	 * Muestra un mensaje de tipo:
	 * 	 "End of game. And the winner is <winnerPlayerName>."
	 * @param winnerPlayerName nombre del jugador que ha ganado, de tipo String
	 */
	@Override
	public void gameFinished(String winnerPlayerName) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("End of game. ").append("And the winner is ").append(CLOSE);
		sb.append(GREEN).append(winnerPlayerName).append(CLOSE);
		
		System.out.println(sb);
	}

	/**
	 * Muestra el nombre del jugador y el dinero que tiene, con el formato:
	 * "Player <playerName>: has <wealth>"
	 * @param playerName nombre del jugador, de tipo String
	 * @param wealth dinero del jugador, de tipo double
	 */
	@Override
	public void playerStatus(String playerName, double wealth) {
		StringBuilder sb = new StringBuilder(CYAN);
		
		sb.append("Player ").append(CLOSE);
		sb.append(GREEN).append(playerName).append(CLOSE);
		sb.append(CYAN).append(": ").append("has ").append(CLOSE);
		sb.append(GREEN).append(wealth).append(CLOSE);
		
		System.out.println(sb);
	}
}