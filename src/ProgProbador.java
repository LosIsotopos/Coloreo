import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import matrizSimetrica.MatrizSimetrica;

public class ProgProbador {
	private ArrayList<Integer> coloreo = new ArrayList<Integer>();
	private MatrizSimetrica matriz;
	public ProgProbador(String grafo, String coloreo) throws FileNotFoundException {
		matriz = new MatrizSimetrica(grafo);
		Scanner sc = new Scanner(new File(coloreo));
		int cantNodos = sc.nextInt();
		// Perdida de datos
		// Cantidad de colores
		sc.nextInt();
		// CCol
		sc.nextInt();
		// cantAristas
		sc.nextInt();
		// %ady
		sc.nextDouble();
		// grmax
		sc.nextInt();
		// grmin
		sc.nextInt();
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
					return false;
				}
			}
		}
		return true;
	}
}
