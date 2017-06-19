package generadores;

import java.util.ArrayList;
import java.util.Random;

public class RegularPorGrado {
	private int cantNodos;
	private int cantAristas = 0;
	private int aristasTotales;
	private float porcentajeAdy;
	private int gradoMax;
	private int gradoMin;
	private int grado;
	private int aristasPorNodo;
	private ArrayList<ConexionNodo> nodo = new ArrayList<ConexionNodo>();
	
	
	// HAY QUE HACER LA VALIDACION QUE NO SE PUEDE HACER UN GRAFO REGULAR DEPENDIENDO LA CANT DE NODOS
	
	public RegularPorGrado(int cantNodos, int grado) {
		if(cantNodos == grado) {
			System.out.println("Cantidad de Nodos es igual a Grado");
			return;
		} else {
			if (cantNodos%2 == 1) {
				if (grado%2 == 1) {
					System.out.println("No se puede generar un Grafo Regular con estos datos: " +cantNodos+ " y "+grado);
					return;
				}
			}
		}
		this.cantNodos = cantNodos;
		this.grado = grado;
		aristasPorNodo = grado;
		int [] nodoAux = new int [grado];
		for(int f = 0; f < this.cantNodos; f++) {
//			for(int c = 0; c < grado; c++) {
//				nodoAux = new Random().nextInt(cantNodos);
//				// Evito que la ariasta sea un rulo a sí mismo
//				while(nodoAux == f){
//					nodoAux = new Random().nextInt(cantNodos);	
//				}
//				nodo.add(new ConexionNodo(f,nodoAux));
				if (aristasPorNodo != 0) {
					nodoAux = generateRandoms(f, cantNodos);
					for (int i = 0; i < nodoAux.length; i++) {
						nodo.add(new ConexionNodo(f,nodoAux[i]));
						cantAristas++;
					}					
				}
//			}
		}
		porcentajeAdy = 100;
		gradoMax = grado;
		gradoMin = grado;
	}
	
	public int[] generateRandoms (int nodoOrigen, int cantNodos) {
		int [] nodoAux = new int [aristasPorNodo];
		boolean duplicate = true;
		for (int i = 0; i < aristasPorNodo; i++) {
			nodoAux[i] = (new Random().nextInt(cantNodos - nodoOrigen -1)) + nodoOrigen + 1;							
		}
		if (nodoAux.length > 1) {
			while (duplicate) {
				for (int i = 0; i < nodoAux.length; i++) {
					for (int j = i+1; j < nodoAux.length; j++) {
						if (nodoAux[i] == nodoAux[j]) {
							nodoAux[j] = (new Random().nextInt(cantNodos - nodoOrigen - 1)) + nodoOrigen + 1;
							duplicate = true;
						} else {
							duplicate = false;
						}
					}
				}
			}	
		}
		aristasPorNodo--;
		return nodoAux;
	}

	public ArrayList<ConexionNodo> getNodo() {
		return nodo;
	}
	
}
