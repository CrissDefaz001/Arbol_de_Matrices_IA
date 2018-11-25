package ambiente;

import java.util.ArrayList;

import jade.util.leap.Serializable;

//Clase que raiza todos los calculos y envia los resultados al agente
public class ListaSerial implements Serializable {
	private static final long serialVersionUID  = 1L;
	
	
	public ArrayList<Nodo<int[][]>> nodosDelArbol;

	public ListaSerial() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ListaSerial(ArrayList<Nodo<int[][]>> nodosDelArbol) {
		super();
		this.nodosDelArbol = nodosDelArbol;
	}


	public ArrayList<Nodo<int[][]>> getNodosDelArbol() {
		return nodosDelArbol;
	}


	public void setNodosDelArbol(ArrayList<Nodo<int[][]>> nodosDelArbol) {
		this.nodosDelArbol = nodosDelArbol;
	}
	
	
	
	


}
