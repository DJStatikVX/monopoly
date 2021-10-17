// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.presenter;

/**
 * Los métodos implementados de esta interfaz imprimen mensajes
 * acerca del transcurso del juego.
 */
public interface GamePresenter {
	
	/**
	 * Muestra el mensaje "Welcome to Monopoly Game."
	 */
	void gameInitialized();
	
	/**
	 * Muestra el nombre del jugador y la casila en el que se encuentra
	 * @param playerName nombre del jugador, de tipo String
	 * @param square casilla en la que está el jugador, de tipo String
	 */
	void nextPlayer(String playerName, String square);
	
	/**
	 * Muestra el valor sumado de las puntuaciones obtenidas por los dados
	 * @param value suma de puntuaciones de los dados [2..12]
	 */
	void diceRolled(int value);
	
	/**
	 * Muestra el nombre de la siguiente casilla en la que parará el jugador
	 * tras lanzar los dados, además del precio de compra y renta
	 * siguiendo el formato:
	 * "playerName lands on squareName, POSITION = squarePosition, 
	 * 	OWNER = ownerName, BUYING PRICE = price, RENTING PRICE = rentingPrice"
 	 *
	 * @param playerName nombre del jugador, de tipo String
	 * @param squareInfo información de la casilla, de tipo String
	 */
	void nextSquare(String playerName, String squareInfo);
	
	/**
	 * Muetra un mensaje de tipo "Player <playerName> goes bankrupt"
	 * @param playerName nombre del jugador que ha perdido, de tipo String
	 */
	void playerGameOver(String playerName);
	
	/**
	 * Muestra un mensaje de tipo "Player <playerName> buys <squareName>"
	 * @param playerName nombre del comprador, de tipo String
	 * @param squareName nombre de la casilla, de tipo String
	 */
	void playerBuys(String playerName, String squareName);
	
	/**
	 * Muestra un mensaje de tipo:
	 * 	 "End of game. And the winner is <winnerPlayerName>."
	 * @param winnerPlayerName nombre del jugador que ha ganado, de tipo String
	 */
	void gameFinished(String winnerPlayerName);
	
	/**
	 * Muestra la información del jugador
	 * "Player <playerName>: His net wealth is <wealth>"
	 * @param playerName nombre del jugador, de tipo String
	 * @param wealth dinero del jugador, de tipo double
	 */
	void playerStatus(String playerName, double wealth);
	
}