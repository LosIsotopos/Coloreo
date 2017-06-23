package matrizSimetrica;

import java.io.IOException;

import colorear.GrafoNDNP;
import progProbador.ProgProbador;

public class Main {

	public static void main(String[] args) throws IOException {
		int cantNodos = 1000;
		int grado = 500;

		String path = "Grafo600-60Ady.in";	

		GrafoNDNP grafo = new GrafoNDNP(path);
//		grafo.secuencial(10000);
//		grafo.matula(10000);
		grafo.wellshPowell(10000);
		grafo.imprimir();	

		grafo.imprimirFreq("WellshPowell");
		
		if(new ProgProbador(path, "Coloreado1000-75Ady.out").check()) {
			System.out.println("OKAI");

		} else {
			System.err.println("Algo fallo");
		}

//		double porcenAdy = 0.75;
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
