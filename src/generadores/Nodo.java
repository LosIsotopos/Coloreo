package generadores;

import java.util.LinkedList;
import java.util.List;

public class Nodo {
	private final int idNodo;
	private int cantAristas = 0;
	private List<Integer> nodosConectados = new LinkedList<Integer>();
	
	public Nodo(int idNodo) {
		this.idNodo = idNodo;
	}
	
	public void aumentarAristas(int idNodo) {
		cantAristas++;
		nodosConectados.add(idNodo);
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public List<Integer> getNodosConectados() {
		return nodosConectados;
	}

	public int getIdNodo() {
		return idNodo;
	}
}
