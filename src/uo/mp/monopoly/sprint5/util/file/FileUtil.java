// Paquete al que pertenece la clase
package uo.mp.monopoly.sprint5.util.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// Importación de clases
import java.util.LinkedList;
import java.util.List;

import uo.mp.monopoly.sprint5.exception.NotYetImplementedException;
import uo.mp.monopoly.sprint5.ui.UserInterface;
import uo.mp.monopoly.sprint5.util.logger.Logger;

/**
 * Una clase de utilidad para leer / escribir líneas de texto 
 * desde / hacia un fichero de texto.
 */
public class FileUtil {
 
	/**
	 * Lee un archivo de texto y devuelve sus líneas de caracteres
	 * @param filename nombre del archivo a leer, de tipo String
	 * @return Lista de casillas en texto para jugar, LinkedList de String
	 */
	public static List<String> readLines(String filename) {
		List<String> result = new LinkedList<>();
		BufferedReader in;
		
		try {

			in = new BufferedReader(new FileReader(filename));

			try {

				while (in.ready()) {
					result.add(in.readLine());
				}

			} finally {
				in.close();
			}
			
		} catch (FileNotFoundException fnfe) {
			System.err.println("File " + filename + " was not found."
					+ " Please, try again.\n");
			Logger.log(fnfe.getMessage());
			
			new UserInterface().show();

		} catch (IOException ioe) {
			System.err.println("An input/output error has occurred.");
			Logger.log(ioe.getMessage());

			new UserInterface().show();
		}
		
//		result.add("GoSquare, Go");
//		result.add("LotSquare, Mediterranean Ave, 60, 2");
//		result.add("NonFeeSquare, Golden Gate Park");
//		result.add("LotSquare, Baltic Ave, 60, 4");
//		result.add("TaxSquare, Income tax, 200");
//		result.add("RailroadSquare, Reading Railroad, 200, 20");
//		result.add("LotSquare, Oriental Ave, 100, 6");
//		result.add("NonFeeSquare, Central Park");
//		result.add("LotSquare, Vermont Ave, 100, 6");
//		result.add("LotSquare, Connecticut Ave, 120, 8");
//		result.add("NonFeeSquare, Tivoli Gardens");		
//		result.add("LotSquare, St. Charles Place, 140, 10");
//		result.add("UtilitySquare, Electric Company, 150, 15");
//		result.add("LotSquare, States Ave, 140, 10");
//		result.add("LotSquare, Virginia Ave, 160, 12");
//		result.add("RailroadSquare, Pennsylvania Railroad, 200, 20");
//		result.add("LotSquare, St. James Place, 180, 14");
//		result.add("NonFeeSquare, Hyde Park");
//		result.add("LotSquare, Tennessee Ave, 180, 14");
//		result.add("LotSquare, New York Ave, 200, 16");
//		result.add("NonFeeSquare, FreeParking");
//		result.add("LotSquare, Kentucky Ave, 220, 18");
//		result.add("NonFeeSquare, Park Güell");
//		result.add("LotSquare, Indiana Ave, 220, 18");
//		result.add("LotSquare, Illinois Ave, 240, 20");
//		result.add("RailroadSquare, O Railroad, 200, 20");
//		result.add("LotSquare, Atlantic Ave, 260, 22"); 
//		result.add("LotSquare, Ventnor Ave, 260, 22");
//		result.add("UtilitySquare, Water Works, 150, 15");
//		result.add("LotSquare, Marvin Gardens Ave, 280, 24");
//		result.add("NonFeeSquare, Griffith Park");
//		result.add("LotSquare, Pacific Ave, 300, 26");
//		result.add("LotSquare, North Carolina Ave, 300, 26");
//		result.add("NonFeeSquare, Luxembourg Garden");
//		result.add("LotSquare, Pennsylvania Ave, 320, 28");
//		result.add("RailroadSquare, ShortLine, 200, 20");
//		result.add("NonFeeSquare, Balboa Park");
//		result.add("LotSquare, Park Place, 350, 35");
//		result.add("TaxSquare, Luxury tax, 100");
//		result.add("LotSquare, Boardwalk, 400, 50");

		return result;
	}

	/**
	 * Guarda una lista de líneas en un fichero de texto
	 * @param outFileName nombre del fichero de salida, de tipo String
	 * @param lines lista de líneas de texto, de tipo List de String
	 * @throws NotYetImplementedException porque aún no está implementado
	 */
	public void saveToFile(String outFileName, List<String> lines) {	
		throw new NotYetImplementedException();
	}

}