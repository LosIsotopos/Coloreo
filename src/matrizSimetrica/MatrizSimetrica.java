package matrizSimetrica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class MatrizSimetrica {
	private boolean [] vectorMatriz;
	private int cantNodos;

	public MatrizSimetrica (int cantNodos) {
		this.cantNodos = cantNodos;
		vectorMatriz = new boolean[calcularAristas()];
	}
	
	public MatrizSimetrica (String pathIn) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(pathIn));
		sc.useLocale(Locale.ENGLISH);
		int cantNodos = sc.nextInt();
		int cantAristas = sc.nextInt();
		int fil;
		int col;
		this.cantNodos = cantNodos;
		vectorMatriz = new boolean[calcularAristas()];
		sc.nextDouble();
		sc.nextInt();
		sc.nextInt();
	
		for (int i = 0 ; i < cantAristas ; i++) {
			fil = sc.nextInt();
			col = sc.nextInt();
			vectorMatriz[(int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2)] = true;
		}
		sc.close();
	}
	
	public int calcularAristas() {
		return (cantNodos * (cantNodos-1) ) / 2;
	}

	public int getCantNodos () {
		return cantNodos;
	}
	
	public boolean[] getVectorMatriz() {
		return vectorMatriz;
	}

	public boolean getValor (int fil, int col) {
		int aux;
		if (fil == col) {
			return false;			
		}
		if (col < fil) {
			aux = fil;
			fil = col;
			col = aux;
		}
		return vectorMatriz[ (int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2) ];
	}
	
	public void setValor (int fil, int col) {
		int index;
		int aux;
		if (fil == col) {
			return;			
		}
		if (fil > col) {
			aux = fil;
			fil = col;
			col = aux;
		}
		index = (int) (fil * cantNodos + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2);
		vectorMatriz[index] = true;
	}
	
	public int contarTrue () {
		int cant = 0;
		for (boolean b : vectorMatriz) {
			if(b == true) {
				cant++;				
			}
		}
		return cant;
	}
}
