package Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServer {
	// Variables locales primitivas
	private String ip_server;
	private int puerto_local_server;

	// Variables objetos importados
	private ServerSocket server;

	// Cliente
	private Socket cliente;
	private String mensaje;
	private byte[] dataBytes;

	public TCPServer(int puerto_local_server) {
		this.puerto_local_server = puerto_local_server;
	}

	public void crearServidorTCP() {
		try {
			System.out.println("Creando servidor");
			server = new ServerSocket(this.getPuerto_local_server());
			this.setIp_server(server.getInetAddress().getHostAddress());

			System.out.println("Servidor creado en " + this.getIp_server() + ":" + this.puerto_local_server);
			
			server.setSoTimeout(120000);
			System.out.println("Tiempo de espera en caso de inactividad 60 segundos establecido!");
		} catch (IOException e) {
			System.out.println("Error al crear el servidor");
		}
	}

	public void esperandoCliente() {
		try {
			System.out.println("Esperando cliente en el puerto "+this.getPuerto_local_server()+".....");
			cliente = server.accept();
			System.out.println("Cliente conectado, Puerto Origen: " + cliente.getPort() + " IP origen: "+ cliente.getInetAddress().getHostAddress());
			
			
		} catch (SocketTimeoutException e ) {
			// TODO Auto-generated catch block
			System.out.println("El servidor estuvo mucho tiempo inactivo, por seguridad se cerrara");
			this.cerrarServidor();
		} catch (IOException e) {
			
			System.out.println("Error al intentar conectar un cliente");
		}

	}

	public void leerMensajeCliente() {
		dataBytes = new byte[256];
		try {
			DataInputStream taDataInputStream = new DataInputStream(cliente.getInputStream());
			
			String numeroDeMensajes = taDataInputStream.readUTF();
			for (int i = 0; i < Integer.parseInt(numeroDeMensajes); i++) {
				System.out.println(taDataInputStream.readUTF());
			}

			//System.out.println("Numero de bytes recibidos:"+nbytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al leer mensajes");
		}

	}
	
	public void cerrarServidor() {

		try {
			System.out.println("Cerrando server....");
			server.close();
			System.out.println("Servidor cerrado");
			
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error al cerrar el servidor");
			System.exit(0);
		}

	}

	public String getIp_server() {
		return ip_server;
	}

	public void setIp_server(String ip_server) {
		this.ip_server = ip_server;
	}

	public int getPuerto_local_server() {
		return puerto_local_server;
	}

	public void setPuerto_local_server(int puerto_local_server) {
		this.puerto_local_server = puerto_local_server;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

}
