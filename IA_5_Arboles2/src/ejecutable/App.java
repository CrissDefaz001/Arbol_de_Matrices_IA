package ejecutable;

import ambiente.Arbol;
import contenedor.Contenedor;

public class App {
	
	public static void main(String[] args) {
		
		//Desde consola:
//		Arbol a = new Arbol();
//		a.inicializarArbol(); //Mostrar arbol
//		//busqueda
//		System.out.println("\nMatriz ideal:");
//		a.imprimirMatriz(a.generarMatrizIdeal());
//		//Anchura
//		new Anchura(a.nodosDelArbol, a.generarMatrizIdeal()).inicializarBusqueda();
//		System.out.println();
//		//Profundidad
//		new Profundidad(a.nodosDelArbol, a.generarMatrizIdeal()).inicializarBusqueda();
		
		//Utilizando agentes inteligentes:
		System.out.println("|====================== Arbol ======================|");
		Arbol a = new Arbol();
		a.inicializarArbol();
		System.out.println("Total de nodos (matrices) generadas: "+ a.nodosDelArbol.size());
		Contenedor con = new Contenedor();
		System.out.println("\nMatriz ideal:");
		a.imprimirMatriz(a.generarMatrizIdeal());
		System.out.println("|====================== Agentes ======================|");
		con.inicializarContenedor(a);
		
	}
}
