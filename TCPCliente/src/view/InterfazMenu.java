package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.TCPClient;

public class InterfazMenu
{
	private ArrayList<String> mensajes;
	private TCPClient socketCliente;
	
	public InterfazMenu(ArrayList<String> mensajes) {
		this.mensajes = mensajes;
		mostrarMenu();
		decidir(abrirEntrada());
	}
	
	public InterfazMenu(ArrayList<String> mensajes, TCPClient socketCliente) {
		this.mensajes = mensajes;
		mostrarMenu();
		decidir(abrirEntrada());
		this.socketCliente = socketCliente;
	}
	public void mostrarMenu() 
	{
		System.out.println("Porfavor seleccione una opcion");
		System.out.println("1.Ingresar mensajes");
		System.out.println("2.Ver mensajes");
		System.out.println("3.Enviar mensajes al servidor");
		System.out.println("4.Terminar comunicacion con el servidor");
		System.out.println("5.Salir");
	}
	
	public String abrirEntrada() 
	{
		Scanner input = new Scanner(System.in);
		System.out.print(">");
		String entrada = input.next();
		
		return entrada;
	}
	
	public void decidir(String entrada) 
	{
		switch (entrada) {
		case "1":
			InterfazMensajes interfazMensajes = new InterfazMensajes();
			break;
		case "2":
			mostrarMensajes();
			InterfazMenu interfazMenu2 = new InterfazMenu(mensajes);
			break;
		case "3":
			establecerConexionTCPServer();
			break;
		case "4":
			
			break;
		case "5":
			System.exit(0);
			break;
		default:
			System.out.println("Esta opcion no existe");
			InterfazMenu interfazMenu = new InterfazMenu(mensajes);
			break;
		}
	}
	
	public void mostrarMensajes() 
	{
		if (mensajes.isEmpty()) {
			System.out.println("No hay mensajes!");
		} else {
			for (int i = 0; i < mensajes.size(); i++) {
				System.out.println(mensajes.get(i));
			
			}
		}
		
	}
	
	public void establecerConexionTCPServer()
	{
		socketCliente = new TCPClient();
		socketCliente.crearSocket();
		if(socketCliente.conectarseSever()) 
		{
			socketCliente.enviarMensajes(mensajes);
		}
		InterfazMenu interfazMensajes = new InterfazMenu(mensajes, socketCliente);
		
	}

}
