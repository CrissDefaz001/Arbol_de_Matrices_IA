package contenedor;

import agentes.AgenteAnchura;
import agentes.AgenteBroker;
import agentes.AgenteProfundidad;
import ambiente.Arbol;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {

	AgentContainer mainContenedor;
	Object[] argumentos = new Object[2]; 
	//arreglo de argumentos a pasar a los agentes
	
	public void inicializarContenedor() {
		
		Runtime rt = Runtime.instance();
		rt.setCloseVM(true);
		Profile pf = new ProfileImpl(null, 1099, null);
		mainContenedor = rt.createMainContainer(pf);
		inicializarAgentes();
	}
	
	public void inicializarContenedor(Arbol as) {
		
		argumentos[0] = (Object)  as;
		Runtime rt = Runtime.instance();
		rt = jade.core.Runtime.instance();
        rt.setCloseVM(true);

        Profile profile = new ProfileImpl(null, 1099, null);
        mainContenedor = rt.createMainContainer(profile);
        inicializarAgentes();
	}

	public void inicializarAgentes() {
		
		try {
			mainContenedor.createNewAgent("AgenteBroker", AgenteBroker.class.getName(), argumentos).start();
			mainContenedor.createNewAgent("AgenteAnchura", AgenteAnchura.class.getName(), argumentos).start();
	        mainContenedor.createNewAgent("AgenteProfundidad", AgenteProfundidad.class.getName(), argumentos).start();
			
		} catch (StaleProxyException e) {

			e.printStackTrace();
		
		}
		
	}
	
}
