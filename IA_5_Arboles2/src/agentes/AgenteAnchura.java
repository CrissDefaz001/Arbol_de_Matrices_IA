package agentes;

import java.io.IOException;
import ambiente.Anchura;
import ambiente.Arbol;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteAnchura extends Agent {

//	Arbol as;
//	ListaSerial ls = new ListaSerial();

	@Override
	protected void takeDown() {
		super.takeDown();
	}

	@Override
	protected void setup() {

		addBehaviour(new ComportamientoAgenteAnchura());
		super.setup();
	}

	class ComportamientoAgenteAnchura extends Behaviour {

		@Override
		public void action() {
		//	as = (Arbol)  getArguments()[0];
			Object obj = (Object) getArguments()[0];
			int a = 0;
			Anchura an = new Anchura(((Arbol) obj).nodosDelArbol, ((Arbol) obj).generarMatrizIdeal());
			a = an.inicializarBusqueda();
			//ls.setNodosDelArbol(a.inicializarBusqueda());
		
			if (a != 0) {
				/* ======== Mensaje ======= */
				enviarMensaje(a, "AgenteBroker", "A->B");
				/* ======== Mensaje ======= */
				doDelete();
				
			}
			doWait(100);
		}

		@Override
		public boolean done() {
			return false;
		}

		private void enviarMensaje(int lista, String receptor, String idConv) {
			AID id = new AID(); // id de la conversacion
			id.setLocalName(receptor);
			ACLMessage acl = new ACLMessage(ACLMessage.INFORM);
			acl.addReceiver(id);
			acl.setSender(getAID());
		//	acl.setSender(getAgent().getAID()); //si el comportamiento esta en otro lado.
			try {
				acl.setContentObject(lista);
			} catch (IOException e) {
				e.printStackTrace();
			}
			acl.setLanguage("Spanish");
			acl.setConversationId(idConv);
			send(acl);
		}

	}

}
