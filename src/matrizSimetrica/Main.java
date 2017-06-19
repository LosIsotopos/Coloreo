package matrizSimetrica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import generadores.ConexionNodo;
import generadores.RegularPorGrado;

public class Main {
	private static int[] vectorOrigen = {0,0,0,1,1,1,1,2};

	public static void main(String[] args) {
//		System.out.println(calcularGradoMinimo());
//		System.out.println(calcularGradoMaximo());
//		ArrayList<ConexionNodo> nodo = new ArrayList<ConexionNodo>();
//		nodo.add(new ConexionNodo(0, 1, (float)0.21));
//		nodo.add(new ConexionNodo(1, 6, (float)0.51));
//		nodo.add(new ConexionNodo(9, 3, (float)0.41));
//		nodo.add(new ConexionNodo(11, 2, (float)0.11));
//		nodo.add(new ConexionNodo(3, 4, (float)0.91));
//		nodo.add(new ConexionNodo(6, 5, (float)0.951));
//
//
//		Collections.sort(nodo, new Comparator<ConexionNodo>() {
//			@Override
//			public int compare(ConexionNodo arg0, ConexionNodo arg1) {
//	            return  Float.compare(arg0.getPorcentaje(),arg1.getPorcentaje());
//
//			}
//	    });
//		int total = (int) (nodo.size()*0.5);
//		int size = nodo.size()-1;
//		int cantBorrados = 0;
//		while (cantBorrados != total) {
//			nodo.remove(size);
//			size--;
//			cantBorrados++;
//		}
//		int cantAristas = nodo.size();
//		Collections.sort(nodo, new Comparator<ConexionNodo>() {
//			@Override
//			public int compare(ConexionNodo arg0, ConexionNodo arg1) {
//				// TODO Auto-generated method stub
//				return  arg0.getNodoOrigen() - arg1.getNodoOrigen();
//			}
//	    });
//		while (nodo.size() != 0) {
//			System.out.println(nodo.get(0).getNodoOrigen());
//			nodo.remove(0);
//		}
//		int [] a;
//		a = generateRandoms(0, 10, 3);
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(a[i]);
//		}
		
		RegularPorGrado rxg = new RegularPorGrado(6,3);
		ArrayList<ConexionNodo> nodo = rxg.getNodo();
		while (nodo.size() != 0) {
			System.out.println(nodo.get(0).getNodoOrigen() + " " + nodo.get(0).getNodoDestino());
			nodo.remove(0);
		}

	}
	
	public static int[] generateRandoms (int nodoOrigen, int cantNodos, int grado) {
		int [] nodoAux = new int [grado];
		boolean duplicate = true;
		for (int i = 0; i < grado; i++) {
			nodoAux[i] = new Random().nextInt(cantNodos);			
		}
		while (duplicate) {
			for (int i = 0; i < nodoAux.length; i++) {
				for (int j = i+1; j < nodoAux.length; j++) {
					if (nodoAux[i] == nodoAux[j]) {
						nodoAux[j] = new Random().nextInt(cantNodos);
						duplicate = true;
					} else {
						duplicate = false;
					}
				}
			}
		}
		return nodoAux;
	}
	
	public static int calcularGradoMinimo () {
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
	
	public static int calcularGradoMaximo () {
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
}
