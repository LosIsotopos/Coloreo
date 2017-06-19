package generadores;

public class ConexionNodo {
	private int nodoOrigen;
	private int nodoDestino;
	private float porcentaje;
	
	public ConexionNodo(int nodoOrigen, int nodoDestino, float porcentaje) {
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.porcentaje = porcentaje;
	}

	public ConexionNodo(int f, int nodoAux) {
		this.nodoOrigen = f;
		this.nodoDestino = nodoAux;
	}

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public int getNodoDestino() {
		return nodoDestino;
	}

	public float getPorcentaje() {
		return porcentaje;
	}
}
