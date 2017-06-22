package matrizSimetrica;

import java.io.IOException;

import generadores.Generador;

public class Main {

	public static void main(String[] args) throws IOException {
		int cantNodos = 1000;
		int grado = 501;
		Generador grafo = new Generador(cantNodos);
		grafo.generarRegular(grado);
		grafo.mostrarGrafo();
		System.out.println(grafo.getGradoMax());
		System.out.println(grafo.getGradoMin());
		System.out.println(cantNodos*grado/2);
		System.out.println(grafo.getMatriz().contarTrue());

		grafo.escribir("ColorearCirculo1000-501");

//		grafo.escribir("ColorearCirculo8-2");

		
//		Generador grafo = new Generador(cantNodos);
//		grafo.aleatorioProbArista(0.5);
//		System.out.println(grafo.getPorcentajeAdy());
//		grafo.mostrarGrafo();
		
//		Generador grafo = new Generador(5);
//		grafo.aleatorioProbAdy(0.5);
//		grafo.mostrarGrafo();
		
//		Generador grafo = new Generador(6);
//		grafo.nPartitos(2);
//		grafo.mostrarGrafo();		
		
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
//				aristas--
//				System.out.println(nodo1 + " " + nodo2);
//			}
//		}
		
		// ASDDDD
//		int cantNodos = 7;
//		int grados = 3;
//		MatrizSimetrica matriz = new MatrizSimetrica(cantNodos);
//		int []grado = new int [cantNodos];
//		int cantAristas = 0;
//		int nodo1;
//		int nodo2;
//		int aristas = (cantNodos * grados) / 2 ;
//		System.out.println("COUNT TIENE QUE SER: " + aristas);
//		int cantAux = 0;
//		int aux;
//		int auxGrados = 0;
//		int i = 0;
//		boolean primerIn = true;
//		
//		int count = cantNodos/2;
//		while (i < cantNodos && count != 0) {
//			if (grado[i] == 0) {
//				if (count%2 == 1){
//					if (i+2 < cantNodos && !matriz.getValor(i, i+2)) {
//						matriz.setValor(i, i+2);
//						grado[i]++;
//						grado[i+2]++;
//						count--;
//					}					
//				} else {
//					if (i+3 < cantNodos  && !matriz.getValor(i, i+3)) {
//						matriz.setValor(i, i+3);
//						grado[i]++;
//						grado[i+3]++;
//						count--;
//					}
//				}		
//			}
//			i++;
//		}
		
		//fin asdd
		
		// EL MIOOO
//		if (cantNodos < grados) {
//			System.out.println("El Grado no puede ser mayor a los Nodos");
//		} else {
//			if (cantNodos%2 == 1 && grados%2 == 1 && cantNodos != grados) {
//				System.out.println("No se puede Nodos Impares con Grado Impar");
//			} else {
//				if (cantNodos == grados) {
//					System.out.println("No se puede si Nodos = Grado");
//				} else {
//					while (aristas > 0) {
//						// SI CANTIDAD DE NODOS -1 ES GRADOS, OSEA SI NODOS = 7 Y GRADO = 6
//						// UNO TODOS CON TODOS
//						if (cantNodos-1 == grados) {
//							for (int j = 0; j < cantNodos; j++) {
//								for (int j2 = j+1; j2 < cantNodos; j2++) {
//									matriz.setValor(j, j2);
//									aristas--;
//									cantAristas++;
//								}
//							}
//						}
//						// FIN CANT NODOS -1 = GRADOS
//						if (grados == 2 || grados == 3) {
//							// SI ES GRADO 3 QUE HAGA GRADO 2 Y GRADO 1
//							// HACE GRADO 2, HACE EL CIRCULO
//							for (int j = 0; j < cantNodos; j++) {
//								if ( j + 1 == cantNodos) {
//									matriz.setValor(0, j);
//								} else {
//									matriz.setValor(j, j+1);
//								}
//								aristas--;
//								cantAristas++;
//							}
//							grados-=2;
//							//FIN HACE GRADO 2	
//						}
//						// PARA GRADO 1, UNE POR EJ: 0-3, 1-4 , 2-5
//						if (grados == 1) {
//							while(i + (cantNodos/2) != cantNodos) {
//								matriz.setValor(i, i+(cantNodos/2));
//								grado[i]++;
//								grado[i+(cantNodos/2)]++;
//								i++;
//								cantAristas++;
//								aristas--;
//							}
//							grados--;
//							// FIN GRADO 1							
//						}
						
//						while (cantAux != cantNodos/2) {
//							nodo1 = new Random().nextInt(cantNodos);
//							nodo2 = new Random().nextInt(cantNodos);
//							if(nodo1 != nodo2 && grado[nodo1] == auxGrados && grado[nodo2] == auxGrados) {
//								if ( nodo1 > nodo2 ){
//									aux = nodo1;
//									nodo1 = nodo2;
//									nodo2 = aux;
//								}
//								if (!matriz.getValor(nodo1, nodo2)) {
//									// HACER FUNCION INTERCAMBIO
//									matriz.setValor(nodo1, nodo2);
//									grado[nodo1]++;
//									grado[nodo2]++;
//									cantAristas++;
//									cantAux++;
//									aristas--;
//									System.out.println(nodo1 + " " + nodo2);						
//								}
//							}
//						}
//						auxGrados++;
//						cantAux = 0;
		
//					}	
//				}
//			}
//			
//		}
		
		//FIN MIOO
		
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
		
		// INICIO COPIADO
//		aux = grados;
//		int salto = 1;
//		int k = 0;
//		//si cantidad de nodos es par => conecto con el opuesto
//		if(aux != 0 && (cantNodos % 2) == 0) {	
//			while(k + (cantNodos/2) != cantNodos) {
//				matriz.setValor(k, k+(cantNodos/2));
//				grado[k]++;
//				grado[k+(cantNodos/2)]++;
//				k++;
//				cantAristas++;
//			}
//			aux -= 1;
//		}
//		while(aux != 0) //mientras no se termino con el grado pedido
//		{
//			for(i = 0; i < cantNodos; i++)
//			{
//				if( i+salto <= cantNodos-1)
//				{
//					if(!matriz.getValor(i, i+salto))
//					{
//						matriz.setValor(i, i+salto);
//						cantAristas++;
//						grado[i]++;
//						grado[i+salto]++;
//					}
//				}
//				else
//				{
//					if(!matriz.getValor((i+salto)-(cantNodos), i))
//					{
//						matriz.setValor((i+salto)-(cantNodos), i);
//						cantAristas++;
//						grado[(i+salto)-(cantNodos)]++;
//						grado[i]++;
//					}
//				}
//			}
//			aux -= 2;
//			salto ++;
//		}
//		cantAristas--;
		
		// FIN COPIADO
		
//		System.out.println(cantAristas);
//		System.out.println("OSEA HELLOU");
//		count= 0;
//		for (int j = 0; j < cantNodos; j++) {
//			for (int j2 = j+1 ; j2 < cantNodos; j2++) {
//				if (matriz.getValor(j, j2)) {
//					System.out.println(j + "-" + j2);	
//					count++;
//				}					
//			}
//		}
//		System.out.println("COUNT: " + count);
	}
}
