package generadores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AleatorioProbArista {
	private int cantNodos;
	private int cantAristas = 0;
	private int aristasTotales;
	private float porcentajeAdy;
	private int gradoMax;
	private int gradoMin;
	private int[] vectorOrigen;
	private int[] vectorDestino;
	
	public AleatorioProbArista (int cantNodos, float probArista) {
		int index = 0;
		this.cantNodos = cantNodos;
		aristasTotales = (cantNodos * (cantNodos-1)) / 2;
		
		for(int f = 0; f < this.cantNodos; f++) {
			for(int c = f; c < this.cantNodos; c++) {
				if(Math.random() > probArista) {
					vectorOrigen[index] = f;
					vectorDestino[index] = c;
					index++;
					cantAristas++;
				}
			}
		}
		porcentajeAdy = cantAristas / aristasTotales;
		gradoMax = calcularGradoMaximo();
		gradoMin = calcularGradoMinimo();
	}
	
	//I = F * N + C – ( F^2 + 3 * F + 2) / 2	
//	index = (int) (f * cantNodos + c - (Math.pow(f, 2) + 3 * f + 2) / 2);
//	m1.setAt(index, 1);
	public int calcularGradoMinimo () {
		int gradoMin = Integer.MAX_VALUE;
		int nodoAnt = vectorOrigen[0];
		int gradoEsp = 1;
		int i = 1;
		while (i < vectorOrigen.length) {
			while (vectorOrigen[i] == nodoAnt) {
				gradoEsp++;
				i++;
			}
			if (gradoEsp < gradoMin) {
				gradoMin = gradoEsp;
			}
			nodoAnt = vectorOrigen[i];
			gradoEsp = 1;
			i++;
		}
		if (gradoEsp < gradoMin) {
			gradoMin = gradoEsp;
		}
		return gradoMin;
	}
	
	public int calcularGradoMaximo () {
		int gradoMax = 0;
		int nodoAnt = vectorOrigen[0];
		int gradoEsp = 1;
		int i = 1;
		while (i < vectorOrigen.length) {
			while (vectorOrigen[i] == nodoAnt) {
				gradoEsp++;
				i++;
			}
			if (gradoEsp > gradoMax) {
				gradoMax = gradoEsp;
			}
			nodoAnt = vectorOrigen[i];
			gradoEsp = 1;
			i++;
		}
		return gradoMax;
	}
	
	public void imprimir (String path) throws IOException {
		FileWriter archivo = new FileWriter(path + ".in");
		PrintWriter fichero = new PrintWriter(archivo);
		fichero.println(cantNodos + " " + cantAristas + " " + porcentajeAdy + " " + gradoMax + " " + gradoMin);
		for (int i = 0; i < vectorOrigen.length; i++) {
			fichero.println(vectorOrigen[i] + " " + vectorDestino[i]);
		}
		fichero.close();
	}
}
