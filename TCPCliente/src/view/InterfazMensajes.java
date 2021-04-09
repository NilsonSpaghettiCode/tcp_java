package view;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfazMensajes
{
	private int _n_mensajes;
	private ArrayList<String> mensajes;
	
	
	public InterfazMensajes() 
	{
		mostrarMenu();
		decidir(abrirEntrada());
		
	}
	
	public void mostrarMenu() 
	{
		System.out.println("Escribe Atras o Salir si así lo desea");
		System.out.println("Atras");
		System.out.println("Salir");
		System.out.println("Porfavor ingrese el numero de mensajes");
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
		case "Atras":
			InterfazMenu interfazMenu = new InterfazMenu(mensajes);
			break;
		case "Salir":
			System.exit(0);
			break;
		default:
			_n_mensajes = Integer.parseInt(entrada);
			InterfazMenu interfazMenu2 = new InterfazMenu(sinNombre());
		break;
		}
		
	}
	
	public void entradaMensajes()
	{
		System.out.println("Ingrese un mensaje");
	}
	
	public ArrayList<String> sinNombre() 
	{
		mensajes = new ArrayList<String>();
		for (int i = 0; i < this.get_n_mensajes(); i++)
		{
			entradaMensajes();
			String mensaje = abrirEntrada();
			mensajes.add(mensaje);
		}
		
		return mensajes;
	}

	public int get_n_mensajes() {
		return _n_mensajes;
	}

	public void set_n_mensajes(int _n_mensajes) {
		this._n_mensajes = _n_mensajes;
	}
	
	

}
