package generadores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AleatorioProbAdy {
	private int cantNodos;
	private int cantAristas = 0;
	private int aristasTotales;
	private float porcentajeAdy;
	private int gradoMax;
	private int gradoMin;
	private ArrayList<ConexionNodo> nodo = new ArrayList<ConexionNodo>();
	
	public AleatorioProbAdy (int cantNodos, float porcAdy) {
		this.cantNodos = cantNodos;
		aristasTotales = (cantNodos * (cantNodos-1)) / 2;
		
		for(int f = 0; f < this.cantNodos; f++) {
			for(int c = f; c < this.cantNodos; c++) {
				nodo.add(new ConexionNodo(f,c,(float) Math.random()));
			}
		}
		descartarPorAdy(porcAdy);
		gradoMax = calcularGradoMaximo();
		gradoMin = calcularGradoMinimo();
		porcentajeAdy = cantAristas / aristasTotales;
	}
	
	private void descartarPorAdy(float porcAdy) {
		int total;
		int size;
		int cantBorrados;
		Collections.sort(nodo, new Comparator<ConexionNodo>() {
			@Override
			public int compare(ConexionNodo arg0, ConexionNodo arg1) {
				return  Float.compare(arg0.getPorcentaje(),arg1.getPorcentaje());
			}
	    });
		total = (int) (nodo.size()*porcAdy);
		size = nodo.size()-1;
		cantBorrados = 0;
		while (cantBorrados != total) {
			nodo.remove(size);
			size--;
			cantBorrados++;
		}
		cantAristas = nodo.size();
		Collections.sort(nodo, new Comparator<ConexionNodo>() {
			@Override
			public int compare(ConexionNodo arg0, ConexionNodo arg1) {
				return  arg0.getNodoOrigen() - arg1.getNodoOrigen();
			}
	    });
	}

	public int calcularGradoMinimo () {
		int gradoMin = Integer.MAX_VALUE;
		int nodoAnt = nodo.get(0).getNodoOrigen();
		int gradoEsp = 1;
		int i = 1;
		while (i < nodo.size()) {
			while (nodo.get(i).getNodoOrigen() == nodoAnt) {
				gradoEsp++;
				i++;
			}
			if (gradoEsp < gradoMin) {
				gradoMin = gradoEsp;
			}
			nodoAnt = nodo.get(i).getNodoOrigen();
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
		int nodoAnt = nodo.get(0).getNodoOrigen();
		int gradoEsp = 1;
		int i = 1;
		while (i < nodo.size()) {
			while (nodo.get(i).getNodoOrigen() == nodoAnt) {
				gradoEsp++;
				i++;
			}
			if (gradoEsp > gradoMax) {
				gradoMax = gradoEsp;
			}
			nodoAnt = nodo.get(i).getNodoOrigen();
			gradoEsp = 1;
			i++;
		}
		return gradoMax;
	}
	
	public void imprimir (String path) throws IOException {
		FileWriter archivo = new FileWriter(path + ".in");
		PrintWriter fichero = new PrintWriter(archivo);
		fichero.println(cantNodos + " " + cantAristas + " " + porcentajeAdy + " " + gradoMax + " " + gradoMin);
		for (int i = 0; i < nodo.size(); i++) {
			fichero.println(nodo.get(i).getNodoOrigen() + " " + nodo.get(i).getNodoDestino());
		}
		fichero.close();
	}
}
