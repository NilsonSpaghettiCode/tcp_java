package view;

import Model.TCPServer;

public class InterfazApp {
	public static void main(String[] args) {
		
		//Crear servidor
		TCPServer server = new TCPServer(5000);
		server.crearServidorTCP();
		
		while (true) {
			server.esperandoCliente();
			server.leerMensajeCliente();
		}
		
		
		
		
	}

}
