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
	
//	public void regularPorGrado (int gradoPorNodo) {
//		for (int i = 0; i < cantNodos; i++) {
//			for (int j = i + 1; j < cantNodos; j++) {
//				if(gradoPorNodo != grado[i] && !matriz.getValor(i, j)) {
//					matriz.setValor(i, j);
//					grado[i]++;
//					grado[j]++;
//					cantAristas++;
//				}
//			}
//		}
//	}
	public void regularPorGrado (int gradoPorNodo) {
		cantAristas = 0;
		int grados = gradoPorNodo;
		int aristas = (cantNodos * grados) / 2 ;
		
		if (cantNodos < grados) {
			System.out.println("El Grado no puede ser mayor a los Nodos");
		} else {
			if (cantNodos%2 == 1 && grados%2 == 1 && cantNodos != grados) {
				System.out.println("No se puede Nodos Impares con Grado Impar");
			} else {
				if (cantNodos == grados) {
					System.out.println("No se puede si Nodos = Grado");
				} else {
					// SI CANTIDAD DE NODOS -1 ES GRADOS, OSEA SI NODOS = 7 Y GRADO = 6
					// UNO TODOS CON TODOS
					if (cantNodos-1 == grados) {
						aristas = completarTodo(aristas);
					}
					if (cantNodos-1 == grados+1) {
						aristas = completarTodo(aristas);
						aristas = sacarGradoUno(aristas);
					}
					if (grados == 3) {
						aristas = gradoUno(aristas);
						aristas = gradoDos(aristas);
						grados-=3;
					}
					if (grados == 2) {
						// SI ES GRADO 3 QUE HAGA GRADO 2 Y GRADO 1
						// HACE GRADO 2, HACE EL CIRCULO
						aristas = gradoDos(aristas);
						grados-=2;	
					}
					// PARA GRADO 1, UNE POR EJ: 0-3, 1-4 , 2-5
					if (grados == 1) {
						aristas = gradoUno(aristas);
						grados--;
						// FIN GRADO 1							
					}
					
					aristas = gradoDos(aristas);
					int offset = 2;
					while (aristas > 0) {
						// SI LOS NODOS SON IMPARES
						aristas = hacerPares(aristas, offset);
						if (cantNodos%2 == 0) {
							aristas = hacerImpares(aristas, offset);
						}
						offset++;
						
						
						
//						System.out.println("ANTES DE HACER ALGO ARISTAS = " + aristas);
//						aristas = gradoUno(aristas);
//						System.out.println("DESPUES DE GRADO 1 ARISTAS = " + aristas);
//						aristas = gradoDos(aristas);
//						System.out.println("DESPUES DE GRADO 2 ARISTAS = " + aristas);
//						if (cantNodos%2 == 1) {
//							matriz.setValor(cantNodos/2, cantNodos-1);
//							grado[cantNodos/2]++;
//							grado[cantNodos-1]++;
//							aristas--;
//							System.out.println(Integer.valueOf(cantNodos/2) + " " + Integer.valueOf(cantNodos-1));
//							System.out.println("PEPE");
//						}
//						matriz.setValor(0, cantNodos-2);
//						aristas--;
//						grado[0]++;
//						grado[cantNodos-2]++;
//						System.out.println("0 "+ Integer.valueOf(cantNodos-2));
//						System.out.println("A");
//						System.out.println("ARISTAS = " + aristas);
//						int i = 1;
//						while (i < cantNodos && aristas > 0) {
//							for (int j = i+1; j < cantNodos; j++) {
//								if (!matriz.getValor(i, j) && grado[i] < grados && grado[j] < grados) {
//									matriz.setValor(i, j);
//									System.out.println(i + " " + j);
//									i++;
//									aristas--;
//								}
//							}
//						}
					}
				}
			}
		}
		cantAristas =  (cantNodos * gradoPorNodo) / 2 ;
	}
	
	private int hacerPares (int aristas, int offset) {
		int i = 0;
		int j = i + offset;
		while (i != cantNodos-2) {
			matriz.setValor(i, j);
			aristas--;
			if (i+offset < cantNodos) {
				i += offset;
			} else if (i+1 == cantNodos) {
				i = 1;
			}
			if (j+offset < cantNodos) {
				j += offset;								
			} else if (j+offset-1 == cantNodos) {
				j = 1;
			}
		}
		matriz.setValor(i, 0);
		aristas--;
		return aristas;
	}
	
	private int hacerImpares (int aristas, int offset) {
		int i = 1;
		int j = i + offset;
		while (i != cantNodos-1) {
			matriz.setValor(i, j);
			aristas--;
			if (i+offset < cantNodos) {
				i += offset;
			} else if (i+1 == cantNodos) {
				i = 1;
			}
			if (j+offset < cantNodos) {
				j += offset;								
			} else if (j+offset-1 == cantNodos) {
				j = 1;
			}
		}
		matriz.setValor(i, 1);
		aristas--;
		return aristas;
	}
	// ESTE GRADO 1 HACE 0-3, 1-4, 2-5
	private int gradoUno(int aristas) {
		int i = 0;
		while(i + (cantNodos/2) != cantNodos) {
			matriz.setValor(i, i+(cantNodos/2));
			grado[i]++;
			grado[i+(cantNodos/2)]++;
			i++;
			cantAristas++;
			aristas--;
//			System.out.println(i + " " + Integer.valueOf(i+(cantNodos/2)));
		}
		return aristas;
	}
	
	private int sacarGradoUno(int aristas) {
		int i = 0;
		while(i + (cantNodos/2) != cantNodos) {
			matriz.outValor(i, i+(cantNodos/2));
			grado[i]--;
			grado[i+(cantNodos/2)]--;
			i++;
			cantAristas--;
			aristas++;
//			System.out.println(i + " " + Integer.valueOf(i+(cantNodos/2)));
		}
		return aristas;
	}
	//ESTE GRADO 1 HACE 0-2, 1-4, 3-5
//	private int gradoUno(int aristas) {
//		int i = 0;
//		int count = cantNodos/2;
//		while (i < cantNodos && count != 0) {
//			if (grado[i] == 0) {
//				if (count%2 == 1){
//					if (i+2 < cantNodos && !matriz.getValor(i, i+2)) {
//						matriz.setValor(i, i+2);
//						grado[i]++;
//						grado[i+2]++;
//						count--;
//						aristas--;
//						System.out.println(i + " " + Integer.valueOf(i+2));
//					}					
//				} else {
//					if (i+3 < cantNodos  && !matriz.getValor(i, i+3)) {
//						matriz.setValor(i, i+3);
//						grado[i]++;
//						grado[i+3]++;
//						count--;
//						aristas--;
//						System.out.println(i + " " + Integer.valueOf(i+3));
//					}
//				}		
//			}
//			i++;
//		}
//		return aristas;
//	}
	
	private int gradoDos(int aristas) {
		for (int j = 0; j < cantNodos; j++) {
			if ( j + 1 == cantNodos) {
				matriz.setValor(0, j);
				grado[0]++;
				grado[j]++;
//				System.out.println("0 "+ j);
			} else {
				matriz.setValor(j, j+1);
				grado[j]++;
				grado[j+1]++;
//				System.out.println(j + " " + Integer.valueOf(j+1));
			}
			aristas--;
			cantAristas++;
		}
		return aristas;
	}

	private int completarTodo(int aristas) {
		for (int j = 0; j < cantNodos; j++) {
			for (int j2 = j+1; j2 < cantNodos; j2++) {
				matriz.setValor(j, j2);
				aristas--;
				cantAristas++;
			}
		}
		return aristas;
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
		fichero.println(cantNodos + " " + matriz.calcularAristas() + " " + getPorcentajeAdy() + " " + getGradoMin() + " " + getGradoMax());
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
}
