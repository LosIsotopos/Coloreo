package progProbador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import matrizSimetrica.MatrizSimetrica;

public class ProgProbador {
	private ArrayList<Integer> coloreo = new ArrayList<Integer>();
	private MatrizSimetrica matriz;
	public ProgProbador(String grafo, String coloreo) throws FileNotFoundException {
		matriz = new MatrizSimetrica(grafo);
		Scanner sc = new Scanner(new File(coloreo));
		sc.useLocale(Locale.ENGLISH);
		int cantNodos = sc.nextInt();
		// Perdida de datos
		// Cantidad de colores
		int a = sc.nextInt();
		// CCol
		int b = sc.nextInt();
		// cantAristas
		double c= sc.nextDouble();
		// %ady
		int d =sc.nextInt();
		// grmax
		int e =sc.nextInt();
		// grmin
		for (int i = 0; i < cantNodos; i++) {
			// INDICE + COLOR
			// Tecnicamente no hace falta ya que el está ordenado el archivo
			this.coloreo.add(sc.nextInt(), sc.nextInt());
		}
	}
	
	public boolean check() {
		for (int i = 0; i < matriz.getCantNodos(); i++) {
			for (int j = 1; j < matriz.getCantNodos(); j++) {
				if (matriz.getValor(i, j) && coloreo.get(i) == coloreo.get(j)) {
					System.out.println("EL NODO: "+ i + " Y EL NODO: " + j);
					return false;
				}
			}
		}
		return true;
	}
}
