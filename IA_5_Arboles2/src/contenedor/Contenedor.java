package contenedor;

import agentes.AgenteAnchura;
import agentes.AgenteBroker;
import agentes.AgenteHeuristica;
import agentes.AgenteProfundidad;
import ambiente.Arbol;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {

	AgentContainer mainContenedor;
	Arbol as = new Arbol();
	Object[] argumentos = new Object[2];

	// Contendor que recibe una clase como argumento
	public void inicializarContenedor(Arbol as) {
		argumentos[0] = (Arbol) as; // Obtiene el arbol inicializado desde 'App' y lo pasa a un object []
		Runtime rt = Runtime.instance();
		rt = jade.core.Runtime.instance();
		rt.setCloseVM(true);

		Profile profile = new ProfileImpl(null, 1099, null);
		mainContenedor = rt.createMainContainer(profile);
		inicializarAgentes();
	}

	// metodo que crea los agentes
	public void inicializarAgentes() {
		try {
			// cada agente (excepto el broker) recibirá la clase arbol como argumento en sus
			// respectivas clases
			// cuando reciban al arbol podrán ejecutar los metodos del arbol desde su propia
			// clase
			// el broker solo recibirá datos como mensajes desde los otros 3 agentes
			mainContenedor.createNewAgent("AgenteBroker", AgenteBroker.class.getName(), null).start();
			mainContenedor.createNewAgent("AgenteAnchura", AgenteAnchura.class.getName(), argumentos).start();
			mainContenedor.createNewAgent("AgenteProfundidad", AgenteProfundidad.class.getName(), argumentos).start();
			mainContenedor.createNewAgent("AgenteHeuristica", AgenteHeuristica.class.getName(), argumentos).start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

	}

}
