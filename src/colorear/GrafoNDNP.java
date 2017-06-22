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
import progProbador.ProgProbador;

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
	private int [] mejorColoreado;

	public GrafoNDNP(String path) throws FileNotFoundException {
		this.matriz = new MatrizSimetrica(path);
		
		this.cantNodos = matriz.getCantNodos();
		this.cantAristas = matriz.getCantAristas();
		this.adyacencia = matriz.getPorcenAdy();
		this.grMax = matriz.getGradoMax();
		this.grMin = matriz.getGradoMin();
		this.grados = matriz.getGrados();
		this.mejorColoreado = new int[cantNodos];
		this.listaColoreada = new int[cantNodos];
		this.frecuencia = new int[cantNodos];
		this.cantColores = 1;

		for (int i = 0; i < cantNodos; i++) {
			listaNodos.add(new Nodo(i));
			listaNodos.get(i).setGrado(grados[i]);
		}	
	}
	
//	public static void main(String[] args) throws IOException {
//		String path = "ColorearCirculo1000-501.in";	
////		String path = "ColorearCirculo8-2.in";
//		GrafoNDNP grafo = new GrafoNDNP(path);
//		grafo.matula(20);
//		grafo.imprimir();
//		System.out.println(new ProgProbador(path, "Coloreado1000-250498.out").check());
//	}
	
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
	}
	
	public void secuencial(int repeticiones) {
		int mejorCantColores = 0;
		for(int i = 0; i < repeticiones; i++) {
			System.out.println("Repeticion: " + i);
			mezclar();
			colorear();
//			System.out.println("Colores de la repeticion: " + cantColores);
			//Guardar mejor resultado	
			if(mejorCantColores == 0 || mejorCantColores > this.cantColores) {
				mejorColoreado = listaColoreada.clone();
				mejorCantColores = cantColores;
				mejorRepeticion = i;
			}
			//guardo resultado en la tabla de frecuencia
			frecuencia[cantColores-1]++;
//			try {
//				imprimir(String.valueOf(i));
//				System.out.print("VERIFICACION: ");
//				if(!new ProgProbador("ColorearGrafo8-3.in", i+".out").check()) {
//					System.err.println("ERROR");
//				} else {
//					System.out.println("BIEN");
//				} 
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		//me quedo con el mejor resultado
		listaColoreada = mejorColoreado.clone();
		cantColores = mejorCantColores;
//		System.out.println("MEJOR CANT DE COLORES: " + cantColores);
//		System.out.println("MEJOR LISTA DE COLORES");
//		for (int i = 0; i < listaColoreada.length; i++) {
//			System.out.println(i + " Color: " + listaColoreada[i]);
//		}
//		System.out.println("FRECUENCIAS");
//		for (int i = 0; i < frecuencia.length; i++) {
//			System.out.println("Color: " + Integer.valueOf(i+1) + " Frecuencia :" + frecuencia[i]);
//		}
		System.out.println("MEJOR REPETICION: " + mejorRepeticion);
//		try {
//			imprimir();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void matula(int repeticiones) {
		int mejorCantColores = 0;
		System.out.println("MATULA");
		for(int i = 0; i < repeticiones; i++) {
			System.out.println("Repeticion: " + i);
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
		int mejorCantColores = 0;
		System.out.println("POWELL");
		for(int i = 0; i < repeticiones; i++) {
			System.out.println("Repeticion: " + i);
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
			System.out.println("REP: " + i);
		}
		//me quedo con el mejor resultado
		listaColoreada = mejorColoreado.clone();
		cantColores = mejorCantColores;
	}

	public void imprimir() throws IOException {
		int adyEntera = (int)Math.ceil(100*adyacencia);
		String path = new String("Coloreado"+cantNodos+ "-" + adyEntera + "Ady.out");
		PrintWriter pr = new PrintWriter(new FileWriter(path));
		pr.println(cantNodos + " " + cantColores + " " + cantAristas + " " + adyacencia + " " + grMax + " " + grMin);
		for (int i = 0; i < cantNodos; i++) {
			pr.println(i + " " + listaColoreada[i]);
		}
		pr.close();
	}
	
	public void imprimir(String pathRep) throws IOException {
		
		String path = new String(pathRep + ".out");
		PrintWriter pr = new PrintWriter(new FileWriter(path));
		pr.println(cantNodos + " " + cantColores + " " + cantAristas + " " + adyacencia + " " + grMax + " " + grMin);
		for (int i = 0; i < cantNodos; i++) {
			pr.println(i + " " + listaColoreada[i]);
		}
		pr.close();
	}
	
	public void imprimirFreq(String algo) {
		int adyEntera = (int)Math.ceil(100*adyacencia);
		String path = new String(algo+cantNodos+ "-" + adyEntera + ".csv");

		try {
			PrintWriter pr = new PrintWriter(new FileWriter(path));
			//pr.println("Primer recorrida de menor cant colores: " + mejorRepeticion);
			for (int i = 0; i < frecuencia.length; i++) {
				pr.println(i+1+","+frecuencia[i]);
			}
			pr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
