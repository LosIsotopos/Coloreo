package generadores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import matrizSimetrica.MatrizSimetrica;

public class Generador {
	private MatrizSimetrica matriz;
	private int[] grados;
	private int cantAristas;
	private int cantNodos;
	
	public Generador(int cantNodos) {
		this.cantNodos = cantNodos;
		grados = new int[cantNodos];
		matriz = new MatrizSimetrica(cantNodos);
		cantAristas = 0;
	}
	
	public int getGradoMin() {
		int gradoMin = 0;
		for (int i = 0; i < cantNodos; i++) {
			if (grados[i] < grados[gradoMin])
				gradoMin = i;
		}
		return gradoMin;
	}

	public int getGradoMax() {
		int gradoMax = 0;
		for (int i = 0; i < cantNodos; i++) {
			if (grados[i] > grados[gradoMax])
				gradoMax = i;
		}
		return gradoMax;
	}

	double getPorcentajeAdy() {
		return cantAristas / calcularAristas();
	}

	public int calcularAristas() {
		return (cantNodos * (cantNodos-1) ) / 2;
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
