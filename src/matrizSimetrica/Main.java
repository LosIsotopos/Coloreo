package matrizSimetrica;

import java.io.IOException;

import colorear.GrafoNDNP;
import progProbador.ProgProbador;

public class Main {

	public static void main(String[] args) throws IOException {
		int cantNodos = 1000;

		int grado = 500;
//		Generador grafo = new Generador(cantNodos);
//		grafo.regularPorGrado(grado);
//		grafo.mostrarGrafo();
//		System.out.println(cantNodos*grado/2);
//		System.out.println(grafo.getMatriz().contarTrue());
//
//		grafo.escribir("ColorearCirculo1000-501");

//		grafo.escribir("ColorearCirculo8-2");
		
		String path = "Grafo1000-75Ady.in";	
//		String path = "ColorearCirculo8-2.in";
		GrafoNDNP grafo = new GrafoNDNP(path);
		grafo.matula(10000);
		grafo.imprimir();
		System.out.println("Done");
		if(new ProgProbador(path, "Coloreado1000-75Ady.out").check()) {
			grafo.imprimirFreq("Matula");
		} else {
			System.err.println("Algo fallo");
		}

		double porcenAdy = 0.75;
//		Generador grafo = new Generador(cantNodos);
		
//		grafo.aleatorioProbAdy(porcenAdy);
//		grafo.regularPorAdy(porcenAdy);
		
//		grafo.escribir("Grafo600-40Ady");
//		grafo.escribir("Grafo600-60Ady");
//		grafo.escribir("Grafo600-90Ady");
		
//		grafo.escribir("Grafo1000-50Ady");
//		grafo.escribir("Grafo1000-75Ady");
		

	}
}
