package matrizSimetrica;

import java.io.IOException;

import generadores.Generador;

public class Main {

	public static void main(String[] args) throws IOException {
		int cantNodos = 1000;
		int grado = 500;
		Generador grafo = new Generador(cantNodos);
		grafo.regularPorGrado(grado);
		grafo.mostrarGrafo();
		System.out.println(cantNodos*grado/2);
		System.out.println(grafo.getMatriz().contarTrue());

		grafo.escribir("ColorearCirculo1000-501");

//		grafo.escribir("ColorearCirculo8-2");
	}
}
