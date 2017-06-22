package matrizSimetrica;

import java.io.IOException;

import colorear.GrafoNDNP;
import generadores.Generador;
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
		
		String path = "ColorearGrafo8-3.in";	
//		String path = "ColorearCirculo8-2.in";
		GrafoNDNP grafo = new GrafoNDNP(path);
		grafo.matula(20);
		grafo.imprimir();
		System.out.println(new ProgProbador(path, "Coloreado8-12.out").check());
	}
}
