/**
 * 
 */
import java.util.Scanner;
/**
 * @author pablosky
 *	Este es el proyecto de laberinto por backtracking
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		
		Scanner escaner = new Scanner(System.in);
		int tamanio = escaner.nextInt();
		escaner.nextLine();

		Laberinto lab = new Laberinto(tamanio,escaner);
		lab.leerMatriz();
		//lab.printeaMatriz();
		lab.resolverLaberinto();
		
		escaner.close();
	}
	
	
	
	

}
