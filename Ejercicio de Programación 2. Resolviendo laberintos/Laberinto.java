/**
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author pablosky
 *
 */
public class Laberinto {
	
	private int tamanio;
	
	private Casilla [][] matrix;
	
	private Scanner scan;
	
	private ArrayList <Casilla> ruta;
	
	private ArrayList <Casilla> ruta2;
	
	private ArrayList <Casilla> rutaux;
	
	public Laberinto(int tam, Scanner escan) {
		this.matrix = new Casilla [tam][tam];
		this.tamanio = tam;
		this.scan=escan;
	}
	
	public void leerMatriz() {
			
			for (int i = 0; i < this.tamanio; i++) {
				
				String linea = this.scan.nextLine();
				char [] vectoraux  = linea.toCharArray();
				
				for (int j = 0; j < this.tamanio; j++) {
					Casilla c = new Casilla(vectoraux[j],i,j);
					this.matrix[i][j]=c;
				}
				
			}
		}
	
	public void resolverLaberinto() {
		
		if (this.matrix[0][0].getCaracter() == '1' || this.matrix[tamanio-1][tamanio-1].getCaracter() == '1') {
			System.out.println("NO.");
			System.exit(-1);
		} else {
			if (tamanio == 1) {
				if (this.matrix[0][0].getCaracter()=='0') {
					System.out.println("SI, SIN PREMIO.");
					System.out.println("(1,1)");
					System.exit(-1);
				} else {
					System.out.println("SI, CON PREMIO.");
					System.out.println("(1,1)*");
					System.exit(-1);
				}
			}
			int asterisco = 0;
			for (int i = 0; i < tamanio; i++) {
				for (int j = 0; j < tamanio; j++) {
					if (this.matrix[i][j].getCaracter()=='*') {
						asterisco = 1;
					}
				}
			}
			
			if (asterisco == 0) {
				ruta = new ArrayList<>();
				rutaux= new ArrayList<>();				
				rutaux.add(this.matrix[0][0]);
				resolverSinAsterisco(this.matrix[0][0],this.matrix[this.tamanio-1][this.tamanio-1],ruta,rutaux);
				if (ruta.size()==0) {
					System.out.println("NO.");
				}else {
					System.out.println("SI, SIN PREMIO.");
					String errorDisplay = ruta.toString();
					errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
					System.out.println(errorDisplay.replaceAll(",","").replaceAll("/", ","));
				}
				
			} else {
				ruta = new ArrayList<>();
				ruta2 = new ArrayList<>();
				rutaux= new ArrayList<>();
				rutaux.add(this.matrix[0][0]);
				int filasterico=0;
				int columnasterisco=0;
				for (int i = 0; i < tamanio; i++) {
					for (int j = 0; j < tamanio; j++) {
						if (this.matrix[i][j].getCaracter()=='*') {
							this.matrix[i][j].setCaracter('0');
							filasterico=i;
							columnasterisco=j;
						}
					}
				}
				
				if (rodeadoUnos(this.matrix[filasterico][columnasterisco])) {
					resolverSinAsterisco(this.matrix[0][0],this.matrix[this.tamanio-1][this.tamanio-1], ruta, rutaux);
					System.out.println("SI, SIN PREMIO.");
					String errorDisplay = ruta.toString();
					errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
					System.out.println(errorDisplay.replaceAll(",","").replaceAll("/", ","));
				} else {
					
					resolverSinAsterisco(this.matrix[0][0], this.matrix[filasterico][columnasterisco],ruta,rutaux);
					if (ruta.size()==0) {
						System.out.println("NO.");
						return;
					}
					ruta2.addAll(ruta);
					ruta.removeAll(ruta);
					rutaux.removeAll(rutaux);
					clearArroba();
					resolverSinAsterisco(this.matrix[filasterico][columnasterisco],this.matrix[this.tamanio-1][this.tamanio-1],ruta,rutaux);
					ruta2.addAll(ruta);
					if (ruta.size()==0) {
						System.out.println("NO.");
					}else {
						System.out.println("SI, CON PREMIO.");
						this.matrix[filasterico][columnasterisco].setCaracter('*');
						String errorDisplay = ruta2.toString();
						errorDisplay = errorDisplay.substring(1, errorDisplay.length() - 1);
						System.out.println(errorDisplay.replaceAll(",","").replaceAll("/", ","));
						}
					}
				}
			}
		}
	
	private void resolverSinAsterisco(Casilla actual,Casilla fin,ArrayList <Casilla> ruta, ArrayList <Casilla> rutaux) {

		if (actual.equals(fin)) {
			compararCamino(ruta, rutaux);
			
		} else {
			
			int[][] movements = {
				{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}
				};
			
			int filanueva, columnanueva;
			Casilla casillaux;
			
			for (int i = 0; i < movements.length; i++) {
				
				filanueva= actual.getFila()+movements[i][0];
				columnanueva=actual.getColumna()+movements[i][1];
				if (filanueva >=0 && columnanueva>=0 &&  filanueva< this.tamanio && columnanueva < this.tamanio) {
					casillaux = this.matrix[filanueva][columnanueva];
					
					switch (i) {
					case 0:
						if (arribaDisponible(casillaux)) {
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);	
						}
						break;
					case 1:
						if (arribaDerechaDisponible(casillaux)){
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta,rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 2:
						if (derechaDisponible(casillaux)){
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 3:
						if (abajoDerechaDisponible(casillaux)){
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 4:
						if (abajoDisponible(casillaux)){
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin,ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 5:
						if (abajoIzquierdaDisponible(casillaux)) {
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 6:
						if (izquierdaDisponible(casillaux)) {
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					case 7:
						if (arribaIzquierdaDisponible(casillaux)) {
							rutaux.add(casillaux);
							actual.setCaracter('@');
							resolverSinAsterisco(casillaux,fin, ruta, rutaux);
							actual.setCaracter('0');
							rutaux.remove(casillaux);
						}
						break;
					}
				}
			
			}
		}
	}
	

	
	public boolean arribaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}
	
	public boolean arribaDerechaDisponible(Casilla destino) {
			
			if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
				return true;
			}
			return false;
		}

	public boolean derechaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}

	public boolean abajoDerechaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}

	public boolean abajoDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}

	public boolean abajoIzquierdaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}

	public boolean izquierdaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}

	public boolean arribaIzquierdaDisponible(Casilla destino) {
		
		if (destino.getCaracter()!='@' && destino.getCaracter()!= '1') {
			return true;
		}
		return false;
	}
	
	 public void compararCamino(ArrayList<Casilla> camino, ArrayList<Casilla>caminoaux) {
	      if (camino.size()==0 || camino.size() > caminoaux.size()) {
	    	  	camino.removeAll(camino);
				camino.addAll(caminoaux);	
		}
	 }
	 
	 public boolean rodeadoUnos(Casilla cas) {
		 int contador = 0;
		 int[][] movements = {
					{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}
					};
			 int filanueva, columnanueva;
			 Casilla casillaux;
			 
			for (int i = 0; i < movements.length; i++) {
				filanueva= cas.getFila()+movements[i][0];
				columnanueva=cas.getColumna()+movements[i][1];
				if (filanueva < 0 || columnanueva < 0 || filanueva >= this.tamanio || columnanueva >= this.tamanio) {
					contador++;
				} else {
					casillaux = this.matrix[filanueva][columnanueva];
					
					switch (i) {
					case 0:
						if (!arribaDisponible(casillaux)) {
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 1:
						if (!arribaDerechaDisponible(casillaux)){
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 2:
						if (!derechaDisponible(casillaux)){
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 3:
						if (!abajoDerechaDisponible(casillaux)){
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 4:
						if (!abajoDisponible(casillaux)){
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 5:
						if (!abajoIzquierdaDisponible(casillaux)) {
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 6:
						if (!izquierdaDisponible(casillaux)) {
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					case 7:
						if (!arribaIzquierdaDisponible(casillaux)) {
							if(casillaux.getCaracter()!='@') {
								contador++;
							}
						}
						break;
					}	
				}
		}
			if (contador == 8) {
				return true;
			} else {
				return false;
			}
	 }
	 
	 public void clearArroba() {
		 
		 for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (this.matrix[i][j].getCaracter()=='@') {
					this.matrix[i][j].setCaracter('0');
				}
			}
		}
	 }
	 
	 @Override
		public String toString() {
			StringBuffer salida = new StringBuffer();
			String[] myarray = new String[ruta.size()];
			myarray = ruta.toArray(myarray);
			for (String cas : myarray) {
				salida.append(cas.toString());
				salida.append("\n");
			}
			return salida.toString();
		}
	 
	public void printeaMatriz() {
			
			for (int i = 0; i < this.tamanio; i++) {
				for (int j = 0; j < this.tamanio; j++) {
					System.out.print(this.matrix[i][j].getCaracter());
				}
				System.out.println();
			}
		}
}
