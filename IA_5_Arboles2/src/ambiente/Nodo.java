package ambiente;

import java.io.Serializable;
import java.util.ArrayList;

public class Nodo<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Nodo<T> implica que el nodo puede contener cualquier estructura de datos
	//matrices, objetos, listas, arboles, pilas, etc.
	
	Nodo<T> padre; //nodo padre
	T dato = null; //dato, en este caso, contiene una matriz
	Object info;// nombre o descriptor del nodo
	ArrayList<Nodo<T>> hijos = new ArrayList<>(); //lista para almacenar nodos hijos

	//constructor de nodo: matriz y nombre de matriz
	public Nodo (T datos, Object info) {
		this.dato = datos;
		this.info = info;
	}
	
	//crea un solo hijo
	public Nodo<T> crearHijo(Nodo<T> hijo) { 
		hijo.setPadre(this);
		this.hijos.add(hijo);
		return hijo;
	}

	//crea varios hijos a partir de un arraylist de hijos
	public void crearHijos(ArrayList<Nodo<T>> hijos) { 
		hijos.forEach(each -> each.setPadre(this));
		this.hijos.addAll(hijos);
	}

	//devuelve una lista de hijos de un nodo padre específico
	public ArrayList<Nodo<T>> getHijos() {
		return hijos;
	}

	// retorna la matriz de un nodo
	public T getData() {
		return dato;
	}

	public void setData(T data) {
		this.dato = data;
	}

	private void setPadre(Nodo<T> parent) {
		this.padre = parent;
	}

	public Nodo<T> getPadre() {
		return padre;
	}	

}
