// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.parser;

// Importaci�n de clases
import java.util.ArrayList;
import java.util.List;

import uo.mp.monopoly.sprint5.exception.ParserException;
import uo.mp.monopoly.sprint5.square.GoSquare;
import uo.mp.monopoly.sprint5.square.LotSquare;
import uo.mp.monopoly.sprint5.square.NonFeeSquare;
import uo.mp.monopoly.sprint5.square.PropertySquare;
import uo.mp.monopoly.sprint5.square.RailroadSquare;
import uo.mp.monopoly.sprint5.square.Square;
import uo.mp.monopoly.sprint5.square.TaxSquare;
import uo.mp.monopoly.sprint5.square.UtilitySquare;
import uo.mp.monopoly.sprint5.ui.UserInterface;
import uo.mp.monopoly.sprint5.util.logger.Logger;

/**
 * Clase Parser encargada de procesar la informaci�n
 * procedente de diferentes ficheros
 * @author Samuel Rodr�guez Ares (UO271612)
 */
public class Parser {
	
	private int lineNumber = 1; // contador de l�neas
	
	/**
	 * Procesa una serie de l�neas de texto, generando a cambio
	 * una nueva lista de cuadrados (casillas)
	 * @param lines lista de String con informaci�n de las casillas
	 * @return Nueva lista de casillas con la informaci�n interpretada
	 */
	public List<Square> parse(List<String> lines) {
		List<Square> squares = new ArrayList<Square>(); // lista a devolver
		
		// Se recorren las l�neas de la lista
		for (String l : lines) {
			try {
				if (!l.equals("")) {
					Square sq = parseLine(l); // 1 casilla a a�adir por l�nea
					squares.add(sq); 		  // se a�ade a la lista a devolver
				}
				
			} catch (ParserException pe) {
				// Si se produjo alg�n problema analizando la l�nea, se trata
				handleParserException(pe, lineNumber);
			} catch (IllegalArgumentException iae) {
				// Si la l�nea es vac�a o tiene un tipo inv�lido, se salta
				continue;
			}
			
			lineNumber++; // incrementa el contador
			
		}
		
		return squares; // se devuelve la lista parseada
	}
	
	/**
	 * Maneja una excepci�n de an�lisis de l�nea registrando
	 * el contenido de la excepci�n y la l�nea donde se produjo
	 * @param e excepci�n producida, de tipo ParserException
	 * @param lineNumber l�nea en la que se captur� la excepci�n, de tipo int
	 */
	private void handleParserException(ParserException pe, int lineNumber) {
		System.err.println("An error occurred while parsing the board file. "
				+ "Please try again with a valid one.\n");
		Logger.log("Error en l�nea " + lineNumber + ", " + pe.getMessage());
		
		new UserInterface().show();
	}
	
	/**
	 * Procesa la l�nea de texto pasada como par�metro dividi�ndola
	 * en diferentes partes por cada coma encontrada
	 * y creando casillas de un tipo u otro seg�n la informaci�n procesada
	 * @param line l�nea a procesar, de tipo String
	 * @return Casilla generada a partir de la informaci�n de line
	 * @throws ParserException si el n�mero de campos o el contenido
	 * 		   de alguno de ellos no es el esperado
	 */
	private Square parseLine(String line) throws ParserException {
		String[] parts = line.split(", ");
		String squareType = parts[0];
		
		Square sq = null; // cuadrado a devolver tras parsear la l�nea
		
		// Seg�n el tipo le�do, se genera un tipo de cuadrado u otro
		switch (squareType) {
			case "GoSquare": sq = createGoSquare(parts); break;
			
			case "LotSquare":
			case "RailroadSquare":
			case "UtilitySquare": 
				
				sq = createPropertySquare(squareType, parts); break;
				
			case "NonFeeSquare": sq = createNonFeeSquare(parts); break;
			case "TaxSquare": sq = createTaxSquare(parts); break;
			
			default: throw new IllegalArgumentException("Blank line.");
		}
		
		return sq; // se devuelve el cuadrado generado
	}
	
	/**
	 * Crea una casilla de salida a partir de los trozos de l�nea le�dos
	 * @param parts trozos de l�nea le�dos, de tipo String[]
	 * @return Casilla GoSquare con la informaci�n le�da de parts
	 * @throws ParserException si el n�mero de campos no es 2
	 * 		   o si la informaci�n en alguno de ellos no es correcto
	 */
	private GoSquare createGoSquare(String[] parts) 
			throws ParserException {
		
		checkParts(2, parts);
		
		return new GoSquare(parts[1], lineNumber); // devuelve la casilla
	}
	
	/**
	 * Crea una casilla de propiedad a partir de los trozos de l�nea le�dos
	 * @param parts trozos de l�nea le�dos, de tipo String[]
	 * @return Casilla PropertySquare con la informaci�n le�da de parts
	 * @throws ParserException si el n�mero de campos no es 4
	 * 		   o si la informaci�n en alguno de ellos no es correcto
	 */
	private PropertySquare createPropertySquare(String type, String[] parts) 
			throws ParserException {
		
		checkParts(4, parts);
		
		PropertySquare sq = null; // casilla de propiedad a devolver
		
		String name = parts[1];						// nombre de la casilla
		int buyingPrice = toInteger(parts[2]);		// precio de compra
		int rentingPrice = toInteger(parts[3]);		// precio de renta
		
		switch (type) {
			case "LotSquare":
				sq = new LotSquare(name, lineNumber, buyingPrice, rentingPrice);
			case "RailroadSquare":
				sq = new RailroadSquare(name, lineNumber, buyingPrice, 
						rentingPrice);
			case "UtilitySquare":
				sq = new UtilitySquare(name, lineNumber, buyingPrice, 
						rentingPrice);
		}
		
		return sq; // devuelve la casilla de propiedad generada
	}
	
	/**
	 * Crea una casilla sin tasas a partir de los trozos de l�nea le�dos
	 * @param parts trozos de l�nea le�dos, de tipo String[]
	 * @return Casilla NonFeeSquare con la informaci�n le�da de parts
	 * @throws ParserException si el n�mero de campos no es 2
	 * 		   o si la informaci�n en alguno de ellos no es correcto
	 */
	private NonFeeSquare createNonFeeSquare(String[] parts) 
			throws ParserException {
		
		checkParts(2, parts);
		
		return new NonFeeSquare(parts[1], lineNumber); // devuelve la casilla
	}
	
	/**
	 * Crea una casilla de tasa a partir de los trozos de l�nea le�dos
	 * @param parts trozos de l�nea le�dos, de tipo String[]
	 * @return Casilla TaxSquare con la informaci�n le�da de parts
	 * @throws ParserException si el n�mero de campos no es 2
	 * 		   o si la informaci�n en alguno de ellos no es correcto
	 */
	private TaxSquare createTaxSquare(String[] parts) throws ParserException {
		checkParts(3, parts);
		
		double taxPrice = toInteger(parts[2]); // precio de tasa
		
		return new TaxSquare(parts[1], lineNumber, taxPrice); // devuelve
	}
	
	/**
	 * Convierte la cadena de caracteres le�da en un n�mero entero (si puede)
	 * @param string Cadena de caracteres a convertir, de tipo String
	 * @return N�mero entero obtenido a partir de string, de tipo int
	 * @throws ParserException si el formato de string hace
	 * 		   que no sea posible procesar un formato de n�mero v�lido
	 */
	private int toInteger(String string) throws ParserException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new ParserException("Invalid number format.");
		}
	}
	
	/**
	 * Comprueba que la longitud del array pasado como par�metro coincida
	 * con otro n�mero entero, tambi�n pasado como par�metro
	 * @param length entero de longitud deseada (int)
	 * @param parts array de cadenas de caracteres cuya longitud se comprueba
	 * @throws ParserException si la longitud de parts no coincide con length
	 */
	private void checkParts(int length, String[] parts) 
			throws ParserException {
		
		if (parts.length != length) {
			throw new ParserException("Incorrect fields number.");
		}
	}
}