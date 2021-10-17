// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.presenter;

/**
 * Presentador que imprime todos los mensajes en un formato de texto
 * normal, de letra negra sobre fondo blanco 
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class ConsolePresenter implements GamePresenter {

	/**
	 * Muestra el mensaje "Welcome to Monopoly Game."
	 */
	@Override
	public void gameInitialized() {
		System.out.println("Welcome to Monopoly Game.");
	}

	/**
	 * Muestra el nombre del jugador y la casila en el que se encuentra
	 * @param playerName nombre del jugador, de tipo String
	 * @param square casilla en la que está el jugador, de tipo String
	 */
	@Override
	public void nextPlayer(String playerName, String square) {
		StringBuilder sb = new StringBuilder("Turn ");
		
		sb.append(playerName).append(" stands on ").append(square);
		
		System.out.println(sb);
	}

	/**
	 * Muestra el valor sumado de las puntuaciones obtenidas por los dados
	 * @param value suma de puntuaciones de los dados [2..12]
	 */
	@Override
	public void diceRolled(int value) {
		System.out.println(value);
	}

	/**
	 * Procesa la información de la casilla a la que se va a desplazar
	 * el jugador en cuestión y lo anuncia junto al nombre del mismo
	 * @param playerName nombre del jugador que se desplaza
	 * @param squareInfo cadena de información sin procesar de la próx. casilla
	 */
	@Override
	public void nextSquare(String playerName, String squareInfo) {
		StringBuilder sb = new StringBuilder("Player ");
		
		sb.append(playerName).append("lands on ");
		
		String[] parsed = squareInfo.split(",");
		
		for (String str : parsed) {
			sb.append(str);
		}
		
		System.out.println(sb);

	}

	/**
	 * Muetra un mensaje de tipo "Player <playerName> goes bankrupt"
	 * @param playerName nombre del jugador que ha perdido, de tipo String
	 */
	@Override
	public void playerGameOver(String playerName) {
		StringBuilder sb = new StringBuilder("Player ");
		
		sb.append(playerName).append(" goes bankrupt");
		
		System.out.println(sb);
	}

	/**
	 * Muestra un mensaje de tipo "Player <playerName> buys <squareName>"
	 * @param playerName nombre del comprador, de tipo String
	 * @param squareName nombre de la casilla, de tipo String
	 */
	@Override
	public void playerBuys(String playerName, String squareName) {
		StringBuilder sb = new StringBuilder("Player ");
		
		sb.append(playerName).append(" buys ").append(squareName);
		
		System.out.println(sb);
	}

	/**
	 * Muestra un mensaje de tipo:
	 * 	 "End of game. And the winner is <winnerPlayerName>."
	 * @param winnerPlayerName nombre del jugador que ha ganado, de tipo String
	 */
	@Override
	public void gameFinished(String winnerPlayerName) {
		StringBuilder sb = new StringBuilder("End of game. ");
		
		sb.append("And the winner is ").append(winnerPlayerName);
		
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
		StringBuilder sb = new StringBuilder("Player ");
		
		sb.append(playerName + ": ").append("has ").append(wealth);
		
		System.out.println(sb);
	}
}