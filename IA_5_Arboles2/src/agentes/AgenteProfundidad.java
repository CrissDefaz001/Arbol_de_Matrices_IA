package agentes;

import java.io.IOException;

import ambiente.Arbol;
import ambiente.Profundidad;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteProfundidad extends Agent {

	@Override
	protected void takeDown() {

		super.takeDown();
	}

	@Override
	protected void setup() {

		addBehaviour(new ComportamientoAgenteProf());
		super.setup();
	}

	class ComportamientoAgenteProf extends Behaviour {

		@Override
		public void action() {

			// as = (Arbol) getArguments()[0];
			Object obj = (Object) getArguments()[0];
			int a = 0;
			Profundidad prof = new Profundidad(((Arbol) obj).nodosDelArbol, ((Arbol) obj).generarMatrizIdeal());
			// ls.setNodosDelArbol(a.inicializarBusqueda());

			a = prof.inicializarBusqueda();

			if (a != 0) {
				/* ======== Mensaje ======= */
					enviarMensaje(a, "AgenteBroker", "P->B");
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
	//		acl.setSender(getAgent().getAID()); // si el comportamiento esta en otro lado.
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
