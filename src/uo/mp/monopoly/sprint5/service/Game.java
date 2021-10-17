// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.service;

//Importación de clases
import java.util.ArrayList;
import java.util.List;

import uo.mp.monopoly.sprint5.interactor.PlayerInteractor;
import uo.mp.monopoly.sprint5.model.Board;
import uo.mp.monopoly.sprint5.model.Dice;
import uo.mp.monopoly.sprint5.model.Player;
import uo.mp.monopoly.sprint5.presenter.ColoredConsolePresenter;
import uo.mp.monopoly.sprint5.presenter.GamePresenter;
import uo.mp.monopoly.sprint5.square.PropertySquare;
import uo.mp.monopoly.sprint5.square.Square;

import java.io.PrintStream;

/**
 * Clase Game que gestiona las diferentes rondas y turnos,
 * además del tablero sobre el que se juega y los jugadores participantes
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Game {
	
	private Board board;						// tablero de juego
	private List<Player> players;   			// jugadores participantes
	private GamePresenter presenter; 			// presentador del juego
	private int alivePlayers;					// jugadores restantes
	
	/**
	 * Constructor por defecto, que inicializa sus atributos
	 */
	public Game() {
		board = new Board();
		players = new ArrayList<Player>();
		presenter = new ColoredConsolePresenter();
	}
	
	/**
	 * Comprueba que existe tablero con 12 casillas y 3 jugadores,
	 * coloca a los jugadores en la casilla GO y les asigna 1500€ a cada uno
	 */
	public void setUp() {		
		for (Player p : getPlayers()) {
			alivePlayers++;
			p.setSquare(getBoard().getSquares().get(0));
			p.setMoney(1500.0);
		}
	}
	
	/**
	 * Gestiona el funcionamiento del juego en turnos y rondas
	 * @param out salida, de tipo PrintStream
	 */
	public void play(PrintStream out) {
		
		setUp(); // asigna dinero a jugadores y comprueba que todo esté OK
		
		// Comienza el juego
		getGamePresenter().gameInitialized();
		
		// Distribución por rondas: hasta 10
		rounds(out);
		
		// Selecciona al ganador
		pickWinner(out);

	}
	
	/**
	 * Organiza las diferentes rondas del juego
	 * @param out salida, de tipo PrintStream
	 */
	private void rounds(PrintStream out) {
		for (int i = 0; i < 10; i++) {
			if (alivePlayers != 1) {
				out.println("Round " + (i + 1));
				turns(out);
			} else {
				break;
			}
		}
	}
	
	/**
	 * Organiza los diferentes turnos del juego
	 * @param out salida, de tipo PrintStream
	 */
	private void turns(PrintStream out) {
		for (int i = 0; i < getPlayers().size(); i++) {
			Player nowPlayer = players.get(i);
			PlayerInteractor playerInteractor = nowPlayer.getInteractor();
			
			// Se omite el turno de los jugadores en bancarrota
			if (!getPlayers().get(i).isBankrupt()) {

				// Anuncia la posición actual del jugador que tiene el turno
				getGamePresenter().nextPlayer(nowPlayer.getName(),
						nowPlayer.getSquare().getName());
				
				// Indica el status del jugador
				getGamePresenter().playerStatus(nowPlayer.getName(),
						nowPlayer.getMoney());
				
				// Se anota el número de propiedades antes del movimiento
				int previousProperties = nowPlayer.getProperties().size();

				// Lanzamiento de dados a través del interactor
				playerInteractor.askToRollTheDice();

				// Se genera la puntuación y se asigna a una variable local
				int score = calculateScore();
				getGamePresenter().diceRolled(score);

				// Se almacena la nueva posición en una variable local
				int newPos = nowPlayer.getSquare().getPosition() + score;
				
				Square nextTurnSquare; // casilla a la que irá a parar

				/*
				 * Si la nueva posición es mayor que size, se divide el resultado 
				 * entre size para evitar que "desborde" del tablero
				 */
				if (newPos > getBoard().getSquares().size()) {
					nextTurnSquare = getBoard().getSquares().get((newPos 
					                % getBoard().getSquares().size() - 1));
					
					getGamePresenter().nextSquare(nowPlayer.getName(), 
							nextTurnSquare.toString());
					
					nowPlayer.passesGO(out);
					nowPlayer.goTo(nextTurnSquare, out);

				// Para cualquier otro caso, mueve sin dividir
				} else {
					nextTurnSquare = getBoard().getSquares().get(newPos - 1);
					
					getGamePresenter().nextSquare(nowPlayer.getName(), 
							nextTurnSquare.toString());
					
					nowPlayer.goTo(nextTurnSquare, out);
				}
				
				// Se comprueba si el jugador ha comprado alguna propiedad
				ArrayList<PropertySquare> props = nowPlayer.getProperties();
				
				if (props.size() > previousProperties) {
					getGamePresenter().playerBuys(nowPlayer.getName(), 
							props.get(props.size() - 1).getName());
				}
				
				// Se comprueba si el jugador ha perdido tras jugar el turno
				if (nowPlayer.isBankrupt()) {
					
					getGamePresenter().playerGameOver(nowPlayer.getName());
					nowPlayer.takeMoneyAway(nowPlayer.getMoney(), out);
					alivePlayers--; // se decrementa el contador
					
				} else {
				
				// Anuncia el status del jugador tras el turno (si no perdió)
				getGamePresenter().playerStatus(nowPlayer.getName(),
						nowPlayer.getMoney());
				
				}

				out.println();

			}
		}
	}
	
	/**
	 * Anuncia la posición del jugador pasado como parámetro en out
	 * @param player jugador cuya posición se va a anunciar, tipo Player
	 * @param out salida, de tipo PrintStram
	 */
	@SuppressWarnings("unused")
	private void announcePosition(Player player, PrintStream out) {
		out.println("Turn " + player.getName() + " stands on " + "square "
				+ player.getSquare().getName());
	}
	
	/**
	 * Selecciona al ganador de la partida, buscando el jugador
	 * que haya finalizado el juego con mayor cantidad de dinero
	 * @param out salida, de tipo PrintStream
	 */
	private void pickWinner(PrintStream out) {
		double maxMoney = 0.0;	// contabiliza el máximo dinero encontrado
		String winner = "";		// nombre del ganador
		
		// Busca el jugador más rico en la lista de jugadores
		for (Player p : players) {
			if (p.getMoney() > maxMoney) {
				maxMoney = p.getMoney();
				winner = p.getName();
			}
		}
		
		// Anuncia el ganador
		getGamePresenter().gameFinished(winner);
	}
	
	/**
	 * Asigna el tablero sobre el que se desarrolla el juego
	 * @param board tablero a asignar, de tipo Board
	 * @throws IllegalArgumentException si board vale null
	 */
	public void addBoard(Board board) {
		assertBoard(board);
		
		this.board = board;
	}
	
	/**
	 * Asigna los jugadores participantes en el juego
	 * @param players lista de jugadores participantes, List de Player
	 * @throws IllegalArgumentException "La lista de jugadores a añadir
	 * 		   no puede ser nula" si players vale null
	 */
	public void addPlayers(List<Player> players) {
		if (players != null) {
			this.players = players;
		} else {
			throw new IllegalArgumentException("Players to add list can't"
					+ " be null.");
		}
	}
	
	/**
	 * Añade los interactores pasados como parámetro en el juego
	 * @param interactors lista de interactores, List de Interactor
	 * @throws NullPointerException si no hay suficientes interactores
	 */
	public void addInteractors(List<PlayerInteractor> interactors) {
		checkEnoughInteractors(interactors);
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setInteractor(interactors.get(i));
		}
	}
	
	/**
	 * Se añade y se almacena en el juego el presentador pasado como parámetro
	 * @param presenter presentador a añadir, de tipo GamePresenter
	 * @throws NullPointerException si presenter vale null
	 */
	public void addGamePresenter(GamePresenter presenter) {
		if (presenter == null) {
			throw new NullPointerException("Can't start game without any"
					+ " non-null game presenter.");
		}
		
		this.presenter = presenter;
	}

	/**
	 * Devuelve el tablero actual del juego
	 * @return Tablero actual del juego, de tipo Board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Devuelve la lista actual de jugadores participantes
	 * @return Lista de jugadores participantes, List de Player
	 */
	public List<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Devuelve el presentador que está utilizando el juego
	 * @return Presentador del juego que invoca al método, de tipo GamePresenter
	 */
	public GamePresenter getGamePresenter() {
		return this.presenter;
	}
	
	/**
	 * Calcula la puntuación obtenida a partir de lanzar los dados
	 * @return Puntuación obtenida, de tipo int
	 */
	private int calculateScore() {
		Dice d = new Dice();
		
		int firstNumber = d.roll();
		int secondNumber = d.roll();
		
		return firstNumber + secondNumber;
	}
	
	/**
	 * Comprueba que el tablero no sea nulo
	 * @param board tablero a comprobar, de tipo Board
	 * @throws IllegalArgumentException si board vale null
	 */
	private void assertBoard(Board board) {
		if (board == null) {
			throw new IllegalArgumentException("Board can't be null.");
		}
	}
	
	/**
	 * Comprueba si hay suficientes interactores para asignar
	 * @param interactors lista de interactores, de tipo List<Interactor>
	 * @throws IllegalArgumentException si el tamaño de players es mayor
	 * 		   que el tamaño de interactors
	 */
	private void checkEnoughInteractors(List<PlayerInteractor> interactors) {
		if (interactors.size() < getPlayers().size()) {
			throw new IllegalArgumentException("There can't be less"
					+ " interactors than players.");
		}
	}
}