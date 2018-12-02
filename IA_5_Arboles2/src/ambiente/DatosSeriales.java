package ambiente;

import java.io.Serializable;
import java.util.ArrayList;

//Esta clase contendrá las listas de recorridos por anchura, prof y heuristica
//Aqui se serializan los datos para enviarlos entre agentes con getContentObject

public class DatosSeriales implements Serializable {
	private static final long serialVersionUID = 1L;

	ArrayList<Nodo<int[][]>> lista; // lista
	String info; // descripción de lista: anchura, profundidad o heurística.

	public DatosSeriales(ArrayList<Nodo<int[][]>> lista, String info) {
		super();
		this.lista = lista;
		this.info = info;
	}

	public ArrayList<Nodo<int[][]>> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Nodo<int[][]>> lista) {
		this.lista = lista;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
