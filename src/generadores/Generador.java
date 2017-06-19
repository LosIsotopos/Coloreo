package generadores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import matrizSimetrica.MatrizSimetrica;

public class Generador {
	private MatrizSimetrica matriz;
	private int[] grado;
	private int cantAristas;
	private int cantNodos;
	
	public Generador(int cantNodos) {
		this.cantNodos = cantNodos;
		grado = new int[cantNodos];
		matriz = new MatrizSimetrica(cantNodos);
		cantAristas = 0;
	}
	
	public void aleatorioProbArista (double probArista) {		
		for(int f = 0; f < cantNodos; f++) {
			for(int c = f + 1; c < cantNodos; c++) {
				if(Math.random() <= probArista) {
					matriz.setValor(f, c);
					cantAristas++;
					grado[f]++;
					grado[c]++;
				}
			}
		}
	}
	
	public void aleatorioProbAdy (double probAdy) {		
		int nodo1;
		int nodo2;
		int auxAristas;
		cantAristas = (int) (calcularAristas() * probAdy);
		auxAristas = cantAristas;
		while (auxAristas > 0) {
			nodo1 = new Random().nextInt(cantNodos);
			nodo2 = new Random().nextInt(cantNodos);
			if (nodo1 != nodo2 && !matriz.getValor(nodo1, nodo2)) {
				matriz.setValor(nodo1, nodo2);
				grado[nodo1]++;
				grado[nodo2]++;
				auxAristas--;
			}
		}
	}
	
	public int getGradoMin() {
		int gradoMin = 0;
		for (int i = 0; i < cantNodos; i++) {
			if (grado[i] < grado[gradoMin])
				gradoMin = i;
		}
		return gradoMin;
	}

	public int getGradoMax() {
		int gradoMax = 0;
		for (int i = 0; i < cantNodos; i++) {
			if (grado[i] > grado[gradoMax])
				gradoMax = i;
		}
		return gradoMax;
	}

	public double getPorcentajeAdy() {
		return (double)cantAristas / calcularAristas();
	}

	public int calcularAristas() {
		return (cantNodos * (cantNodos-1) ) / 2;
	}
	
	public void mostrarGrafo () {
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i; j < cantNodos; j++) {
				if (i != j && matriz.getValor(i, j) == true) {
					System.out.println(i + " " + j);					
				}
			}
		}
	}
	
	public void escribir(String path) throws IOException {
		FileWriter archivo = new FileWriter(path + ".in");
		PrintWriter fichero = new PrintWriter(archivo);
		fichero.println(cantNodos + " " + cantAristas + " " + getPorcentajeAdy() + " " + getGradoMin() + " " + getGradoMax());
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i; j < cantNodos; j++) {
				if (i != j && matriz.getValor(i, j) == true) {
					fichero.println(i + " " + j);					
				}
			}
		}
		archivo.close();
	}
}
