package colorear;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import matrizSimetrica.MatrizSimetrica;

public class GrafoNDNP {
	private MatrizSimetrica matriz;
	private int[] grados;
	private int grMax;
	private int grMin;
	private int cantNodos;
	private int cantColores;
	private int cantAristas;
	private double adyacencia;
	private int[] listaColoreada;
	private ArrayList<Integer> listaNodos = new ArrayList<Integer>();
	private int[] frecuencia;
	private int mejorRep;

	public GrafoNDNP(String path) throws FileNotFoundException {
		this.matriz = new MatrizSimetrica(path);
		
		this.cantNodos = matriz.getCantNodos();
		this.cantAristas = matriz.getCantAristas();
		this.adyacencia = matriz.getPorcenAdy();
		this.grMax = matriz.getGradoMax();
		this.grMin = matriz.getGradoMin();
		this.grados = matriz.getGrados();
		
		this.listaColoreada = new int[cantNodos];
		this.frecuencia = new int[cantNodos];
		this.cantColores = 1;

		for (int i = 0; i < cantNodos; i++) {
			listaNodos.add(i);
		}	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
			
		GrafoNDNP grafo = new GrafoNDNP("ColorearCirculo8-2.in");
//		grafo.mezclar();
		grafo.colorear();
	}
	public void mezclar() {
		Collections.shuffle(listaNodos);
	}
	
	public void colorear() {
		// se fija si lo puede el color que tiene
		// sino agrega uno nuevo y lo pinta de ese
		int colorBase = 1;
		int color = colorBase;
		int noColoreado = -1;
		for (int i = 0; i < listaColoreada.length; i++) {
			listaColoreada[i] = noColoreado;
		}
		listaNodos.removeAll(listaNodos);
		listaNodos.add(6);
		listaNodos.add(4);
		listaNodos.add(5);
		listaNodos.add(1);
		listaNodos.add(2);
		listaNodos.add(0);
		listaNodos.add(3);
		listaNodos.add(7);
		int nodoAnt = listaNodos.get(0);
//		listaColoreada[listaNodos.get(0)] = color;
		for (int nodo44 : listaNodos) {
			System.out.println(nodo44);
		}
		for (int nodo1 : listaNodos) {
			if (listaColoreada[nodo1] == noColoreado) {
				listaColoreada[nodo1] = color;				
			}
			for (int i = 0; i < cantNodos; i++) {
				if (matriz.getValor(nodo1, i) && (listaColoreada[i] == noColoreado || listaColoreada[i] == listaColoreada[nodo1])) {
					if (nodoAnt != i && listaColoreada[nodoAnt] != listaColoreada[nodo1] && !matriz.getValor(nodoAnt, i)) {
						color = listaColoreada[nodoAnt];
					} else {
						while (listaColoreada[nodo1] >= color) {
						color++;
						}
					}
					listaColoreada[i] = color;
				}
				color = colorBase;
			}
			nodoAnt = nodo1;
		}
		for (int i = 0; i < cantNodos; i++) {
			System.out.println(i + " Color: " + listaColoreada[i]);
		}
	}

	public void secuencial() {
		// PINTAR EL PRIMERO // 0
//		colorear(0);
//		for (int i = 0; i < matriz.getCantNodos(); i++) {
//			for (int j = i + 1; j < matriz.getCantNodos(); j++) {
//				// SI ESTAN UNIDOS AL OTRO LO PINTO DE OTRO COLOR
//				if (matriz.getValor(i, j)) {
//					colorear(j);
//				}
//			}
//		}
	}

	public void matula() {

	}

	public void wellshPowell() {

	}

	public void imprimir() {

	}
}
