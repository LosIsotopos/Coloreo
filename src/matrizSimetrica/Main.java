package matrizSimetrica;

import java.util.Random;

import generadores.Generador;

public class Main {

	public static void main(String[] args) {		
//		Generador grafo = new Generador(5);
//		grafo.aleatorioProbArista(0.5);
//		System.out.println(grafo.getPorcentajeAdy());
//		grafo.mostrarGrafo();
		
//		Generador grafo = new Generador(5);
//		grafo.aleatorioProbAdy(0.5);
//		grafo.mostrarGrafo();
		
//		Generador grafo = new Generador(6);
//		grafo.nPartitos(2);
//		grafo.mostrarGrafo();
		
		
//		int [] vec = {1,1,1,2,0,0};
//		System.out.println(findLower(vec));
		
		
		int cantNodos = 6;
		int grados = 3;
		MatrizSimetrica matriz = new MatrizSimetrica(cantNodos);
		int []grado = new int [cantNodos];
		int cantAristas = 0;
		int nodo1;
		int nodo2;
		int aristas = (cantNodos * grados) / 2 ;
		
		int cantAux = 0;
		int aux;
		int auxGrados = 0;
		int i = 0;
		boolean primerIn = true;
//		while (cantAux != cantNodos/2) {
//			nodo1 = new Random().nextInt(cantNodos);
//			nodo2 = new Random().nextInt(cantNodos);
//			if(nodo1 != nodo2 && grado[nodo1] == auxGrados && grado[nodo2] == auxGrados) {
//				// HACER FUNCION INTERCAMBIO
//				if ( nodo1 > nodo2){
//					aux = nodo1;
//					nodo1 = nodo2;
//					nodo2 = aux;
//				}
//				matriz.setValor(nodo1, nodo2);
//				grado[nodo1]++;
//				grado[nodo2]++;
//				cantAristas++;
//				cantAux++;
//				aristas--;
//				System.out.println(nodo1 + " " + nodo2);
//			}
//		}
		while (aristas > 0) {
			while (cantAux != cantNodos/2) {
				nodo1 = new Random().nextInt(cantNodos);
				nodo2 = new Random().nextInt(cantNodos);
				if(nodo1 != nodo2 && grado[nodo1] == auxGrados && grado[nodo2] == auxGrados) {
					if ( nodo1 > nodo2 ){
						aux = nodo1;
						nodo1 = nodo2;
						nodo2 = aux;
					}
					if (!matriz.getValor(nodo1, nodo2)) {
						// HACER FUNCION INTERCAMBIO
						matriz.setValor(nodo1, nodo2);
						grado[nodo1]++;
						grado[nodo2]++;
						cantAristas++;
						cantAux++;
						aristas--;
						System.out.println(nodo1 + " " + nodo2);						
					}
				}
			}
			auxGrados++;
			cantAux = 0;
		}
		
//		while (aristas > 0) {
//			primerIn = true;
//			auxGrados++;
//			i = 0;
//			while (mismoGrado(grado) || primerIn) {
//				nodo1 = new Random().nextInt(cantNodos - i) + i;
//				while (i == nodo1 || grado[i] != auxGrados || auxGrados != grado[nodo1] || matriz.getValor(i, nodo1)) {
//					nodo1 = new Random().nextInt(cantNodos - i) + i;					
//				}
//				matriz.setValor(i, nodo1);
//				grado[nodo1]++;
//				grado[i]++;
//				aristas--;
//				System.out.println(i + "-" + nodo1);
//				i++;
//				primerIn = false;
//			}
//		}
		
		System.out.println(cantAristas);
		System.out.println("a");
		System.out.println("b");
		System.out.println("c");
		System.out.println("d");
		System.out.println("e");
		System.out.println("f");
		int count= 0;
		for (int j = 0; j < cantNodos; j++) {
			for (int j2 = 0; j2 < cantNodos; j2++) {
				if (matriz.getValor(j, j2)) {
					System.out.println(j + "-" + j2);	
					count++;
				}					
			}
		}
		System.out.println("COUNT: " + count);
	}
}
