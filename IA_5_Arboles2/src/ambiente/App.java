package ambiente;

public class App {
	
	public static void main(String[] args) {
		
		Arbol a = new Arbol();
		a.inicializarArbol(); //Mostrar arbol
		//busqueda
		System.out.println("\nMatriz ideal:");
		a.imprimirMatriz(a.generarMatrizIdeal());
		//Anchura
		new Anchura(a.nodosDelArbol, a.generarMatrizIdeal()).inicializarBusqueda();
		System.out.println();
		//Profundidad
		new Profundidad(a.nodosDelArbol, a.generarMatrizIdeal()).inicializarBusqueda();
		
	}
}
