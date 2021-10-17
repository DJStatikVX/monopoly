// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.parser;

// Importación de clases
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
 * Clase Parser encargada de procesar la información
 * procedente de diferentes ficheros
 * @author Samuel Rodríguez Ares (UO271612)
 */
public class Parser {
	
	private int lineNumber = 1; // contador de líneas
	
	/**
	 * Procesa una serie de líneas de texto, generando a cambio
	 * una nueva lista de cuadrados (casillas)
	 * @param lines lista de String con información de las casillas
	 * @return Nueva lista de casillas con la información interpretada
	 */
	public List<Square> parse(List<String> lines) {
		List<Square> squares = new ArrayList<Square>(); // lista a devolver
		
		// Se recorren las líneas de la lista
		for (String l : lines) {
			try {
				if (!l.equals("")) {
					Square sq = parseLine(l); // 1 casilla a añadir por línea
					squares.add(sq); 		  // se añade a la lista a devolver
				}
				
			} catch (ParserException pe) {
				// Si se produjo algún problema analizando la línea, se trata
				handleParserException(pe, lineNumber);
			} catch (IllegalArgumentException iae) {
				// Si la línea es vacía o tiene un tipo inválido, se salta
				continue;
			}
			
			lineNumber++; // incrementa el contador
			
		}
		
		return squares; // se devuelve la lista parseada
	}
	
	/**
	 * Maneja una excepción de análisis de línea registrando
	 * el contenido de la excepción y la línea donde se produjo
	 * @param e excepción producida, de tipo ParserException
	 * @param lineNumber línea en la que se capturó la excepción, de tipo int
	 */
	private void handleParserException(ParserException pe, int lineNumber) {
		System.err.println("An error occurred while parsing the board file. "
				+ "Please try again with a valid one.\n");
		Logger.log("Error en línea " + lineNumber + ", " + pe.getMessage());
		
		new UserInterface().show();
	}
	
	/**
	 * Procesa la línea de texto pasada como parámetro dividiéndola
	 * en diferentes partes por cada coma encontrada
	 * y creando casillas de un tipo u otro según la información procesada
	 * @param line línea a procesar, de tipo String
	 * @return Casilla generada a partir de la información de line
	 * @throws ParserException si el número de campos o el contenido
	 * 		   de alguno de ellos no es el esperado
	 */
	private Square parseLine(String line) throws ParserException {
		String[] parts = line.split(", ");
		String squareType = parts[0];
		
		Square sq = null; // cuadrado a devolver tras parsear la línea
		
		// Según el tipo leído, se genera un tipo de cuadrado u otro
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
	 * Crea una casilla de salida a partir de los trozos de línea leídos
	 * @param parts trozos de línea leídos, de tipo String[]
	 * @return Casilla GoSquare con la información leída de parts
	 * @throws ParserException si el número de campos no es 2
	 * 		   o si la información en alguno de ellos no es correcto
	 */
	private GoSquare createGoSquare(String[] parts) 
			throws ParserException {
		
		checkParts(2, parts);
		
		return new GoSquare(parts[1], lineNumber); // devuelve la casilla
	}
	
	/**
	 * Crea una casilla de propiedad a partir de los trozos de línea leídos
	 * @param parts trozos de línea leídos, de tipo String[]
	 * @return Casilla PropertySquare con la información leída de parts
	 * @throws ParserException si el número de campos no es 4
	 * 		   o si la información en alguno de ellos no es correcto
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
	 * Crea una casilla sin tasas a partir de los trozos de línea leídos
	 * @param parts trozos de línea leídos, de tipo String[]
	 * @return Casilla NonFeeSquare con la información leída de parts
	 * @throws ParserException si el número de campos no es 2
	 * 		   o si la información en alguno de ellos no es correcto
	 */
	private NonFeeSquare createNonFeeSquare(String[] parts) 
			throws ParserException {
		
		checkParts(2, parts);
		
		return new NonFeeSquare(parts[1], lineNumber); // devuelve la casilla
	}
	
	/**
	 * Crea una casilla de tasa a partir de los trozos de línea leídos
	 * @param parts trozos de línea leídos, de tipo String[]
	 * @return Casilla TaxSquare con la información leída de parts
	 * @throws ParserException si el número de campos no es 2
	 * 		   o si la información en alguno de ellos no es correcto
	 */
	private TaxSquare createTaxSquare(String[] parts) throws ParserException {
		checkParts(3, parts);
		
		double taxPrice = toInteger(parts[2]); // precio de tasa
		
		return new TaxSquare(parts[1], lineNumber, taxPrice); // devuelve
	}
	
	/**
	 * Convierte la cadena de caracteres leída en un número entero (si puede)
	 * @param string Cadena de caracteres a convertir, de tipo String
	 * @return Número entero obtenido a partir de string, de tipo int
	 * @throws ParserException si el formato de string hace
	 * 		   que no sea posible procesar un formato de número válido
	 */
	private int toInteger(String string) throws ParserException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new ParserException("Invalid number format.");
		}
	}
	
	/**
	 * Comprueba que la longitud del array pasado como parámetro coincida
	 * con otro número entero, también pasado como parámetro
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