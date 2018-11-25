package ambiente;

import java.io.Serializable;
import java.util.ArrayList;

public class Nodo<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Nodo<T> padre;
	T dato = null;
	Object info;
	ArrayList<Nodo<T>> hijos = new ArrayList<>();

	public Nodo (T datos, Object info) {
		this.dato = datos;
		this.info = info;
	}
	
	public Nodo<T> crearHijo(Nodo<T> hijo) {
		hijo.setPadre(this);
		this.hijos.add(hijo);
		return hijo;
	}

	public void crearHijos(ArrayList<Nodo<T>> hijos) {
		hijos.forEach(each -> each.setPadre(this));
		this.hijos.addAll(hijos);
	}

	public ArrayList<Nodo<T>> getHijos() {
		return hijos;
	}

	public T getData() {
		return dato;
	}

	public void setData(T data) {
		this.dato = data;
	}

	private void setPadre(Nodo<T> parent) {
		this.padre = parent;
	}

	public Nodo<T> obtenerPadre() {
		return padre;
	}	

}
