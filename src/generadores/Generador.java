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
	
	public void regularPorGrado(int grados) {
		int aux = grados;
		int salto = 1;
		int k = 0;
		// si cantidad de nodos es par => conecto con el opuesto
		if (aux != 0 && (cantNodos % 2) == 0) {
			while (k + (cantNodos / 2) != cantNodos) {
				// mientras no llegue al
				// ultimo nodo, conecto
				// los opuestos
				matriz.setValor(k, k + (cantNodos / 2));
				grado[k]++;
				grado[k + (cantNodos / 2)]++;
				k++;
				cantAristas++;
			}
			aux -= 1;
		}
		// mientras no se termino con el grado pedido
		while (aux != 0) {
			for (int i = 0; i < cantNodos; i++) {
				if (i + salto <= cantNodos - 1) {
					if (matriz.getValor(i, i + salto) == false) {
						matriz.setValor(i, i + salto);
						cantAristas++;
						grado[i]++;
						grado[i + salto]++;
					}
				} else {
					if (matriz.getValor((i + salto) - (cantNodos), i) == false) {
						matriz.setValor((i + salto) - (cantNodos), i);
						cantAristas++;
						grado[(i + salto) - (cantNodos)]++;
						grado[i]++;
					}
				}
			}
			aux -= 2;
			salto++;
		}
		cantAristas--;
	}
	
	public void regularPorAdy(double porcAdy) {
		// calculamos el grado en base al porcentaje de adyacencia recibido.
		int grado = (int) (porcAdy * (cantNodos - 1));
		regularPorGrado(grado);
	}
			
	public void nPartitos (int nParticion) {
		int [] ubicacionNodoEnParticion = new int [cantNodos];
		// Genero un vector de los nodos, a los cuales los voy a asignar random a uno de los
		// conjuntos de las N - PARTICIONES
		for (int i = 0; i < cantNodos; i++) {
			ubicacionNodoEnParticion[i] = new Random().nextInt(nParticion);
		}
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				if(ubicacionNodoEnParticion[i] != ubicacionNodoEnParticion[j] && !matriz.getValor(i, j)) {
					matriz.setValor(i, j);
					grado[i]++;
					grado[j]++;
					cantAristas++;
				}
			}
		}
	}
	
	
	public void escribir(String path) throws IOException {
		FileWriter archivo = new FileWriter(path + ".in");
		PrintWriter fichero = new PrintWriter(archivo);
		fichero.println(cantNodos + " " + matriz.calcularAristas() + " " + getPorcentajeAdy() + " " + getGradoMax() + " " + getGradoMin());
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i; j < cantNodos; j++) {
				if (i != j && matriz.getValor(i, j) == true) {
					fichero.println(i + " " + j);					
				}
			}
		}
		archivo.close();
	}
	
	public MatrizSimetrica getMatriz() {
		return matriz;
	}
	
	public int getGradoMin() {
		int gradoMin = Integer.MAX_VALUE;
		for (int i = 0; i < cantNodos; i++) {
			if (grado[i] < gradoMin)
				gradoMin = grado[i];
		}
		return gradoMin;
	}

	public int getGradoMax() {
		int gradoMax = 0;
		for (int i = 0; i < cantNodos; i++) {
			if (grado[i] > gradoMax)
				gradoMax = grado[i];
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
}
