package colorear;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import generadores.Nodo;
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
	private ArrayList<Nodo> listaNodos = new ArrayList<Nodo>();
	private int[] frecuencia;
	private int mejorRepeticion;

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
			listaNodos.add(new Nodo(i));
			listaNodos.get(i).setGrado(grados[i]);
		}	
	}
	
	public static void main(String[] args) throws IOException {
		String path = "ColorearGrafo8-3.in";	
//		String path = "ColorearCirculo8-2.in";
		GrafoNDNP grafo = new GrafoNDNP(path);
		grafo.secuencial(3);
	}
	
	public void mezclar() {
		Collections.shuffle(listaNodos);
	}
	
	private void ordenarPorGrado(int i) {
		if (i == 1) {
			Collections.sort(listaNodos, new Comparator<Nodo>(){
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n1.getGrado() - n2.getGrado();
				}
			});		
		} else {
			Collections.sort(listaNodos, new Comparator<Nodo>(){
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n2.getGrado() - n1.getGrado();
				}
			});	
		}
	}	
	
	
	public void colorear() {
		this.cantColores = 1;
		int colorBase = 1;
		int color = colorBase;
		int noColoreado = -1;
		int j;
		for (int i = 0; i < listaColoreada.length; i++) {
			listaColoreada[i] = noColoreado;
		}
		// El primero lo pinto por defecto
		listaColoreada[listaNodos.get(0).getIdNodo()] = color;
		// Ahora empiezo por el que sigue y voy preguntando
		for (int i = 1; i < cantNodos; i++) {
			listaColoreada[listaNodos.get(i).getIdNodo()] = color;
			j = 0;
			while (j < cantNodos) {
				if (matriz.getValor(listaNodos.get(i).getIdNodo(), j) && listaColoreada[listaNodos.get(i).getIdNodo()] == listaColoreada[j]) {
					color++;
					if (color > cantColores) {
						cantColores = color;						
					}
					listaColoreada[listaNodos.get(i).getIdNodo()] = color;
					j = -1;
				}
				j++;
			}
			color = colorBase;
		}
		System.out.println("Me va tirando por repeticion, esto esta en colorear()");
		for (int i = 0; i < cantNodos; i++) {
			System.out.println(i + " Color: " + listaColoreada[i]);
		}
	}
	
	public void secuencial(int repeticiones) {
		int [] mejorColoreado = new int[cantNodos];
		int mejorCantColores = 0;
		
		for(int i = 0; i < repeticiones; i++) {
			System.out.println("Repeticion: " + i);
			mezclar();
			colorear();
			System.out.println("Colores de la repeticion: " + cantColores);
			//Guardar mejor resultado	
			if(mejorCantColores == 0 || mejorCantColores > this.cantColores) {
				mejorColoreado = listaColoreada.clone();
				mejorCantColores = cantColores;
				mejorRepeticion = i;
			}
			//guardo resultado en la tabla de frecuencia
			frecuencia[cantColores-1]++;
		}
		//me quedo con el mejor resultado
		listaColoreada = mejorColoreado.clone();
		cantColores = mejorCantColores;
		System.out.println("MEJOR CANT DE COLORES: " + cantColores);
		System.out.println("MEJOR LISTA DE COLORES");
		for (int i = 0; i < listaColoreada.length; i++) {
			System.out.println(i + " Color: " + listaColoreada[i]);
		}
		System.out.println("FRECUENCIAS");
		for (int i = 0; i < frecuencia.length; i++) {
			System.out.println("Color: " + Integer.valueOf(i+1) + " Frecuencia :" + frecuencia[i]);
		}
		System.out.println("MEJOR REPETICION: " + mejorRepeticion);
	}

	public void matula(int repeticiones) {
		int [] mejorColoreado = new int[cantNodos];
		int mejorCantColores = 0;
		
		for(int i = 0; i < repeticiones; i++) {
			
			mezclar();
			ordenarPorGrado(1); //le paso un 1 porque es creciente
			colorear();
			
			//Guardar el mejor resultado	
			if(mejorCantColores == 0 || mejorCantColores > this.cantColores) {
				mejorColoreado = listaColoreada.clone();
				mejorCantColores = cantColores;
				mejorRepeticion = i;
			}
			//guardo resultado en la tabla de frecuencia
			frecuencia[cantColores-1]++;
		}
		//me quedo con el mejor resultado
		listaColoreada = mejorColoreado.clone();
		cantColores = mejorCantColores;
	}

	public void wellshPowell(int repeticiones) {
		int [] mejorColoreado = new int[cantNodos];
		int mejorCantColores = 0;
		
		for(int i = 0; i < repeticiones; i++) {
			
			mezclar();
			ordenarPorGrado(0); //le paso un 0 porque es decreciente
			colorear();
			
			//Guardar el mejor resultado	
			if(mejorCantColores == 0 || mejorCantColores > this.cantColores) {
				mejorColoreado = listaColoreada.clone();
				mejorCantColores = cantColores;
				mejorRepeticion = i;
			}
			//guardo resultado en la tabla de frecuencia
			frecuencia[cantColores-1]++;
		}
		//me quedo con el mejor resultado
		listaColoreada = mejorColoreado.clone();
		cantColores = mejorCantColores;
	}


	
	// COLOREO PABLO
//	public void colorear() {
//		// se fija si lo puede el color que tiene
//		// sino agrega uno nuevo y lo pinta de ese
//		int colorBase = 1;
//		int color = colorBase;
//		int noColoreado = -1;
//		for (int i = 0; i < listaColoreada.length; i++) {
//			listaColoreada[i] = noColoreado;
//		}
//		int nodoAnt = listaNodos.get(0);
//		// listaColoreada[listaNodos.get(0)] = color;
//		for (int nodo44 : listaNodos) {
//			System.out.println(nodo44);
//		}
//		for (int nodoActual : listaNodos) {
//			if (listaColoreada[nodoActual] == noColoreado) {
//				listaColoreada[nodoActual] = color;
//			}
//			for (int i = 0; i < cantNodos; i++) {
//				if (matriz.getValor(nodoActual, i)
//						&& (listaColoreada[i] == noColoreado || listaColoreada[i] == listaColoreada[nodoActual])) {
//					if (nodoAnt != i && listaColoreada[nodoAnt] != listaColoreada[nodoActual]
//							&& !matriz.getValor(nodoAnt, i)) {
//						if (check(listaColoreada[nodoAnt], i)) {
//							color = listaColoreada[nodoAnt];
//						} else {
//							while (listaColoreada[nodoActual] >= color) {
//								color++;
//							}
//						}
//					} else {
//						while (listaColoreada[nodoActual] >= color) {
//							color++;
//						}
//					}
//					listaColoreada[i] = color;
//				}
//				color = colorBase;
//			}
//			nodoAnt = nodoActual;
//		}
//		for (int i = 0; i < cantNodos; i++) {
//			System.out.println(i + " Color: " + listaColoreada[i]);
//		}
//	}
//	private boolean check(int color, int nodo) {
//		for (int i = 0; i < cantNodos; i++) {
//			if (matriz.getValor(nodo, i) && listaColoreada[i] == color) {
//				return false;
//			}
//
//		}
//		return true;
//	}
	
	// FIN COLOREO PABLO
	
	
//	public void colorear() {
//		// se fija si lo puede el color que tiene
//		// sino agrega uno nuevo y lo pinta de ese
//		int colorBase = 1;
//		int color = colorBase;
//		int noColoreado = -1;
//		int nodoAnt = listaNodos.get(0);
//		for (int i = 0; i < listaColoreada.length; i++) {
//			listaColoreada[i] = noColoreado;
//		}
////		listaColoreada[listaNodos.get(0)] = color;
//		for (int nodo44 : listaNodos) {
//			System.out.println(nodo44);
//		}
//		for (int nodo1 : listaNodos) {
//			if (listaColoreada[nodo1] == noColoreado) {
//				listaColoreada[nodo1] = color;				
//			}
//			for (int i = 0; i < cantNodos; i++) {
//				if (matriz.getValor(nodo1, i) && (listaColoreada[i] == noColoreado || listaColoreada[i] == listaColoreada[nodo1])) {
//					if (nodoAnt != i && listaColoreada[nodoAnt] != listaColoreada[nodo1] && !matriz.getValor(nodoAnt, i)) {
//						color = listaColoreada[nodoAnt];
//					} else {
//						while (listaColoreada[nodo1] >= color) {
//						color++;
//						}
//					}
//					listaColoreada[i] = color;
//				}
//				color = colorBase;
//			}
//			nodoAnt = nodo1;
//		}
//	}

	
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

	public void imprimir() throws IOException {
		PrintWriter pr = new PrintWriter(new FileWriter("Coloreado: "+cantNodos+ "-" + cantAristas));
		for (int i = 0; i < cantNodos; i++) {
			pr.println(i + " " + listaColoreada[i]);
		}
		pr.close();
	}

}
