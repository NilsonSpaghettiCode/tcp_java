package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TCPClient {
	// Propiedades de un Socket cliente
	private int puerto_destino_server;
	private String ip_destino_server;
	private Socket cliente;

	private boolean instace = false;

	public TCPClient() {

		ip_destino_server = "0.0.0.0";
		puerto_destino_server = 5000;
		instace = true;
	}

	public void crearConexionServidor() {

		try {
			cliente = new Socket(ip_destino_server, puerto_destino_server);
			System.out.println("Conexion creada para la trasmision de datos al servidor: " + this.getIp_destino_server()
					+ ":" + this.getPuerto_destino_server());
		} catch (UnknownHostException e) {
			System.out.println("Host desconocido");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la entrada o salida de datos al crear la conexion con el sevidor!");
		}
	}

	public void crearSocket() {
		cliente = new Socket();
		System.out.println("Cliente socket creado sin asociación");

		try {
			cliente.setSoTimeout(10000);
			System.out.println("Tiempo de espera establecido para obtener una respuesta del servidor (10segundos)");
		} catch (SocketException e) {
			System.out.println("Error al establecer el tiempo de espera");
		}

	}

	public boolean conectarseSever() {
		try {
			InetAddress inetAddres = InetAddress.getByName("localhost");
			SocketAddress sa = new InetSocketAddress(inetAddres, 0);
			// cliente.bind(sa);
			cliente.connect(
					new InetSocketAddress(InetAddress.getByName(this.getIp_destino_server()), puerto_destino_server),
					2000);
			System.out.println("Cliente conectado a el servidor " + this.getIp_destino_server() + ":"
					+ this.getPuerto_destino_server());

		} catch (SocketTimeoutException e) {
			System.out.println("Error 504: El sevidor al cual desea enviar el mensaje no se encuentra en linea");

		} catch (IOException e) {
			System.out.println("Error: entradas y salidas " + e);

		}

		return cliente.isConnected();
	}

	public void enviarMensaje(String mensaje) {
		try {
			cliente.getOutputStream().write(mensaje.getBytes());
			System.out.println("Mensaje enviado:" + mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error el enviar el mensaje");
		}
	}

	public void enviarMensajes(ArrayList<String> mensajes) {

		try {
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			if (!(mensajes.isEmpty())) {
				dos.writeUTF(String.valueOf(mensajes.size()));
				System.out.println("Mensaje enviado al servidor:" + mensajes.size());
				for (int i = 0; i < mensajes.size(); i++) {

					String mensaje = mensajes.get(i);
					dos.writeUTF(mensaje);
					System.out.println("Mensaje enviado al servidor:" + mensaje);
				}
			}

		} catch (IOException e) {
			System.out.println("Error el enviar el mensajes" + e);
			System.err.println(e);
		}
	}

	public void respuestaServer() {
		byte[] datos = new byte[256];
		try {
			cliente.getInputStream().read(datos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Mensaje servidor:" + new String(datos));
	}

	public void finalizarConversacion() {

	}

	public int getPuerto_destino_server() {
		return puerto_destino_server;
	}

	public void setPuerto_destino_server(int puerto_destino_server) {
		this.puerto_destino_server = puerto_destino_server;
	}

	public String getIp_destino_server() {
		return ip_destino_server;
	}

	public void setIp_destino_server(String ip_destino_server) {
		this.ip_destino_server = ip_destino_server;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public boolean isInstace() {
		return instace;
	}

	public void setInstace(boolean instace) {
		this.instace = instace;
	}

}
