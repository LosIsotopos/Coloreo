package matrizSimetrica;

import generadores.Generador;
import generadores.RegularPorGrado;

public class Main {

	public static void main(String[] args) {		
//		Generador grafo = new Generador(5);
//		grafo.aleatorioProbArista(0.5);
//		System.out.println(grafo.getPorcentajeAdy());
//		grafo.mostrarGrafo();
		
//		Generador grafo = new Generador(5);
//		grafo.aleatorioProbAdy(0.5);
//		grafo.mostrarGrafo();
		
		RegularPorGrado r = new RegularPorGrado(4, 2);
		r.makeGraph();
		r.mostrarGrafo();
	}
}
