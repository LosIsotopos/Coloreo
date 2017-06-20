package generadores;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import matrizSimetrica.MatrizSimetrica;

public class RegularPorGrado {
	private int cantNodos;
	private int cantAristas = 0;
	private int aristasTotales;
	private float porcentajeAdy;
	private int gradoMax;
	private int gradoMin;
	private int grado;
	private int aristasPorNodo;
	private MatrizSimetrica matriz;

	
	
	// HAY QUE HACER LA VALIDACION QUE NO SE PUEDE HACER UN GRAFO REGULAR DEPENDIENDO LA CANT DE NODOS
	
	public MatrizSimetrica getMatriz() {
		return matriz;
	}


	public RegularPorGrado(int cantNodos, int grado) {
		if(cantNodos == grado) {
			System.out.println("Cantidad de Nodos es igual a Grado");
			return;
		} else {
			if (cantNodos%2 == 1) {
				if (grado%2 == 1) {
					System.out.println("No se puede generar un Grafo Regular con estos datos: " +cantNodos+ " y "+grado);
					return;
				}
			}
		}
		this.cantNodos = cantNodos;
		this.grado = grado;
		aristasPorNodo = grado;
		matriz = new MatrizSimetrica(cantNodos);
		

		porcentajeAdy = 100;
		gradoMax = grado;
		gradoMin = grado;
		
	}
	

	public void makeGraph() {
		Random rnd = new Random();
		List<Integer> nodosVisitados= new LinkedList<Integer>();
		int idNodoOrigen = rnd.nextInt(cantNodos);;
		int idNodoDestino = buscarElMenorGrado(idNodoOrigen);
		int conexiones = 0;
		int i;
		while (nodosVisitados.size() != cantNodos) {
			idNodoOrigen = rnd.nextInt(cantNodos);
			i = contarConexiones(idNodoOrigen);
			//Obtengo un nodo origen que no haya visitado
			while (nodosVisitados.contains((Object) idNodoOrigen) && nodosVisitados.size() != cantNodos) {
				idNodoOrigen = rnd.nextInt(cantNodos);
			}
			// Le pongo I conexiones al nodoOrigen
			while (i < grado) {
				if(idNodoDestino != idNodoOrigen) {
					// Me fijo cuantas conexiones tiene mi nodo destino, de tener la misma cantidad que el grado
					// No puedo usarlo
					conexiones = contarConexiones(idNodoDestino);
					if(conexiones < grado && !matriz.getValor(idNodoOrigen, idNodoDestino)) {
						ubicar(idNodoOrigen,idNodoDestino);
						i++;
					} else if (conexiones == grado && !nodosVisitados.contains(idNodoDestino)) {
						nodosVisitados.add(idNodoDestino);
					}
				}
				conexiones = 0;
				idNodoDestino = buscarElMenorGrado(idNodoOrigen);
			}
			nodosVisitados.add(idNodoOrigen);
			
		}
	}


	private int buscarElMenorGrado(int idNodoOrigen) {
		int menor = Integer.MAX_VALUE;
		int pos = -1;
		int conexiones;
		for (int i = 0; i < cantNodos; i++) {
			conexiones = contarConexiones(i);
			if((menor > conexiones || contarConexiones(idNodoOrigen) > conexiones) && i != idNodoOrigen) {
				menor = conexiones;
				pos = i;
			}
		}
		return pos;
	}


	private void ubicar(int origen, int destino) {
		if(origen < destino) {
			matriz.setValor(origen, destino);
		} else {
			matriz.setValor(destino, origen);
		}
		
	}


	private int contarConexiones(int idNodo) {
		int conexiones = 0;
		for (int j = 0; j < cantNodos; j++) {
			if(matriz.getValor(idNodo, j)) {
				conexiones++;
			}
		}
		return conexiones;
	}
	
	public void mostrarGrafo() {
		for (int i = 0; i < cantNodos; i++) { 
			for (int j = i; j < cantNodos; j++) {
				if (i != j && matriz.getValor(i, j)) {
					System.out.println(i + " " + j);
				}
			}
			
		}
	}






	
}
