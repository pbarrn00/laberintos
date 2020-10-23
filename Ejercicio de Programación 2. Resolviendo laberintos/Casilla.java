

/**
 * 
 */
/**
 * @author pablosky
 *
 */
public class Casilla {

	private char caracter;

	private int fila;
	
	private int columna;	
	
	public Casilla(char car, int f, int c) {
		this.caracter = car;
		this.fila=f;
		this.columna=c;
	}
	
	public boolean casillaValida(Casilla cas, int tamanio) {
		
		if(cas.getFila() < 0 || cas.getColumna() < 0 || cas.getFila() >= tamanio || cas.getColumna() >= tamanio) {
			return false;
		}
		return true;
	}
	
	public char getCaracter() {
		return caracter;
	}


	public void setCaracter(char caracter) {
		this.caracter = caracter;
	}


	public int getFila() {
		return fila;
	}


	public void setFila(int fila) {
		this.fila = fila;
	}


	public int getColumna() {
		return columna;
	}


	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casilla other = (Casilla) obj;
		if (caracter != other.caracter)
			return false;
		if (columna != other.columna)
			return false;
		if (fila != other.fila)
			return false;
		return true;
	}

	@Override 
	public String toString(){
		
		StringBuffer salida = new StringBuffer();
		
			if (getCaracter()=='*') {
				salida.append("("+(getFila()+1)+"/"+(getColumna()+1)+")*");
			} else {
				salida.append("("+(getFila()+1)+"/"+(getColumna()+1)+")");
			}
		
		return salida.toString();
	 
	}
	
	
	
	
	
	
	
	
	
 }
