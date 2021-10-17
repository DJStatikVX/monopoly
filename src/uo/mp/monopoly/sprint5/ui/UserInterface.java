// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.ui;

import java.util.List;

import uo.mp.monopoly.sprint5.exception.GameException;
import uo.mp.monopoly.sprint5.exception.UserInteractionException;
import uo.mp.monopoly.sprint5.interactor.PlayerInteractor;
import uo.mp.monopoly.sprint5.model.Board;
import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.parser.Parser;
import uo.mp.monopoly.sprint5.service.Game;
import uo.mp.monopoly.sprint5.square.GoSquare;
import uo.mp.monopoly.sprint5.square.Square;
import uo.mp.monopoly.sprint5.util.console.Console;
import uo.mp.monopoly.sprint5.util.file.FileUtil;
import uo.mp.monopoly.sprint5.util.logger.Logger;

/**
 * Resuelve la interacción del usuario con el programa
 * 	- Muestra el menú
 * 	- Procesa la operación elegida por el usuario
 * 		- pide al usuario la información necesaria para completar la opción 
 * 		- muestra el resultado de la ejecución de la operación, o
 * 		  un mensaje de error si no se pudo realizar
 */
public class UserInterface {
	
	private Menu menu = new Menu();		// menú del que toma las opciones
	private Game game = new Game();		// juego sobre el que interactúa
	
	/**
	 * Muestra las opciones del menú por pantalla
	 * e intenta procesar la opción elegida por el usuario
	 */
	public void show() {
		char option;
		
		while (true) {
			Console.println("Choose an option\n");
			
			menu.show(); // se muestra el menú
			Console.println("");

			option = menu.readOption(); // lee la opción escogida
			Console.println("");

			try {
				processOption(option); // intenta procesar la opción
			} catch (GameException ge) {
				handleGameException(ge.getMessage());
			} catch (UserInteractionException uie) {
				handleUserInteractionException(uie.getMessage());
			}
		}

	}
	
	/**
	 * Procesa la opción elegida por el usuario
	 * @param option opción del menú escogida, de tipo char
	 * @throws UserInteractionException si se introduce una opción incorrecta
	 * @throws GameException si se produce algún error de usuario/aplicación
	 */
	private void processOption(char option) throws GameException, 
			UserInteractionException {
		
		switch (option) {
			
			case 'y':
			case 'Y': newPlayer(); break;	// añadir un nuevo jugador
			
			case 'd':
			case 'D': delPlayer(); break;	// eliminar un jugador
			
			case 'b':
			case 'B': newBoard(); break;	// añadir un nuevo tablero
			
			case 'p':
			case 'P': letsPlay(); break;	// comenzar a jugar
			
			default: throw new UserInteractionException("Input is not valid. "
					+ "Please, try again.");
			
		}
	}
	
	/**
	 * Añade un nuevo jugador. Para ello, se pide al usuario que introduzca
	 * un apodo para el mismo y un interactor automático o por consola
	 * @throws GameException si el nombre del nuevo jugador está repetido
	 */
	private void newPlayer() throws GameException {
		String name = Console.readString("New player's name?");
		checkNotRepeatedName(name);
		
		Player p = null;	// jugador a añadir
		
		try {
			p = new Player(name);
		} catch (IllegalArgumentException iae) {
			System.err.println("Player name can't be empty. Please try again.\n");
			show();
		}
		
		PlayerInteractor interactor = new PublicForm().askForInteractor();
		Console.println("");
	
		try {
			p.setInteractor(interactor);
		} catch (IllegalStateException ise) {
			handleIllegalStateException(ise.getMessage());
		}
		
		try {
			checkNullPlayer(p);
		} catch (IllegalArgumentException iae) {
			System.err.println(iae.getMessage() + "\n");
			show();
		}
		
		game.getPlayers().add(p);
		
		Console.println("Player " + p.getName() + " has been properly added.\n");
	}

	/**
	 * Elimina un jugador ya existente. Para ello, se pide al usuario
	 * que introduzca el nombre del jugador a eliminar
	 * @throws GameException si el nombre del jugador no existe
	 */
	private void delPlayer() throws GameException {
		String name = Console.readString("Player to delete's name?");
		
		try {
			checkNotEmptyName(name);
		} catch (IllegalArgumentException iae) {
			System.err.println(iae.getMessage() + "\n");
			show();
		}
		
		checkExistentName(name);
		
		// Se busca al jugador que tenga por nombre el especificado
		for (int i = 0; i < game.getPlayers().size(); i++) {
			Player p = game.getPlayers().get(i);
			
			// Si lo encuentra, lo borra y termina de recorrer
			if (p.getName().equals(name)) {
				game.getPlayers().remove(p);
				
				Console.println("Player " + p.getName() + " was removed.");
				break;
			}
		}
	}
	
	/**
	 * Añade un nuevo tablero. Esta operación 
	 * no está implementada en esta versión
	 * @throws GameException si hay algún problema de número o tipo de casillas
	 */
	private void newBoard() throws GameException {
		String fileName = Console.readString("File name?");
		Board b = new Board();
		Parser p = new Parser();
		
		List<Square> squares = p.parse(FileUtil.readLines(fileName));
		checkParsedSquares(squares);
		
		b.addSquares(squares);
		
		game.addBoard(b);
		
		Console.println("\nBoard was successfuly created.\n");
	}
	
	/**
	 * Comienza el juego como opción elegida por el usuario
	 */
	private void letsPlay() throws GameException {
		try {
			checkBoardSquares(game.getBoard());		// comprobación de tablero
		} catch (IllegalStateException ise) {
			System.err.println(ise.getMessage());
			show();
		}
		
		checkValidNumberOfPlayers();				// comprobación de jugadores
		Console.println("Starting the game...\n");
		game.play(System.out);
	}
	
	/**
	 * Manejador para errores de usuario/aplicación/lógicos
	 * intentar realizar alguna de las opciones
	 */
	private void handleGameException(String msg) {
		Logger.log(msg);
		Console.println("An application error has occurred. Please try again.");
		System.err.println(msg);
		Console.println("");
		show();
	}
	
	/**
	 * Manejador para errores de usuario/aplicación/lógicos
	 * intentar realizar alguna de las opciones
	 */
	private void handleUserInteractionException(String msg) {
		Console.println("The selected option is not correct. "
				+ "Please try again.");	
		show();
	}
	
	/**
	 * Manejador para errores de programación/sistema producidos al
	 * intentar realizar alguna de las opciones
	 */
	private void handleIllegalStateException(String msg) {
		Logger.log(msg);
		Console.println("An internal error has occurred. Please contact "
				+ "the developer for more details.");
	}
	
	/**
	 * Comprueba que el nombre pasado como parámetro no sea nulo
	 * @param name nombre a comprobar, de tipo String
	 * @throws GameException si name está repetido
	 */
	private void checkNotRepeatedName(String name) throws GameException {
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(name)) {
				throw new GameException("Couldn't add new player "
						+ name + " because the name is repeated");
			}
		}
	}
	
	/**
	 * Comprueba que el nombre pasado como parámetro no esté vacío
	 * @param name nombre a comprobar, de tipo String
	 * @throws IllegalArgumentException si name es vacío
	 */
	private void checkNotEmptyName(String name) throws IllegalArgumentException {
		int c = 0; // contador de caracteres vacíos
		
		for (int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);
			if (character == ' ') {
				c++;
			}
		}
		
		if (c == name.length()) {
			throw new IllegalArgumentException("Player name can't be blank.");
		}
	}
	
	/**
	 * Comprueba que el nombre pasado como parámetro exista
	 * @param name nombre a comprobar, de tipo String
	 * @throws GameException si name no existe
	 */
	private void checkExistentName(String name) throws GameException {
		boolean found = false;
		
		// Se recorre la lista de jugadores
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(name)) {
				found = true;
			}
		}
		
		// Si no se encontró, lanza la excepción
		if (!found) {
			throw new GameException("Couldn't delete player " + name
					+ " because the name doesn't exist");
		}
	}
	
	/**
	 * Comprueba que el jugador a añadir no sea null
	 * @param player jugador a comprobar, de tipo Player
	 * @throws IllegalArgumentException si player vale null
	 */
	private void checkNullPlayer(Player p) throws IllegalArgumentException {
		if (p == null) {
			throw new IllegalArgumentException("Can't add a null player.");
		}
	}
	
	/**
	 * Realiza una serie de controles a la lista de casillas parseada
	 * @param squares lista de casilla a comprobar, List de Square
	 * @throws GameException si alguno de los controles lanza una GameException
	 */
	private void checkParsedSquares(List<Square> squares) throws GameException {
		checkGoSquare(squares);
		checkValidSize(squares);
	}
	
	/**
	 * Comprueba que la primera casilla de las que se quieren añadir
	 * al tablero corresponde a una casilla de tipo GoSquare
	 * @param squares lista de casillas a comprobar, de tipo List<Square>
	 * @throws GameException si la primera casilla de squares no es una GoSquare
	 */
	private void checkGoSquare(List<Square> squares) throws GameException {
		if (squares.get(0).getClass() != GoSquare.class) {
			throw new GameException("A game error has ocurred. The first "
					+ "square of the new board isn't a Go square.");
		}
	}
	
	/**
	 * Comprueba que el número de casillas a añadir esté entre 12 y 40
	 * @param squares lista de casillas a comprobar, de tipo List<Square>
	 * @throws GameException si el tamaño de squares no está entre 12 y 40
	 */
	private void checkValidSize(List<Square> squares) throws GameException {
		if (squares.size() < 12 || squares.size() > 40) {
			throw new GameException("A game error has occured. The board size "
					+ squares.size() + " isn't valid.");
		}
	}
	
	/**
	 * Comprueba que el tablero no esté vacío al inicializar el juego
	 * @param board tablero a comprobar, de tipo Board
	 * @throws IllegalStateException si el tamaño de squares de board es 0
	 */
	private void checkBoardSquares(Board board) throws IllegalStateException {
		if (board.getSquares().get(0) == null) {
			throw new IllegalStateException("Can't start game with"
					+ " an empty board.\n");
		}
	}
	
	/**
	 * Comprueba que el número de jugadores esté entre 2 y 8
	 * @throws GameException si el número de jugadores no es válido
	 */
	private void checkValidNumberOfPlayers() throws GameException {
		if (game.getPlayers().size() < 2 || game.getPlayers().size() > 8) {
			throw new GameException("Couldn't start the game. Number of "
					+ "players must stay between 2 and 8.\n");
		}
	}
}