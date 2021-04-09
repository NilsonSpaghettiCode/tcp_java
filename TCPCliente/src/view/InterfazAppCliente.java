package view;

import java.util.ArrayList;

public class InterfazAppCliente {

	public InterfazAppCliente() 
	{
		ArrayList<String> mensajes = new ArrayList<String>();
		InterfazMenu interfazMenu = new InterfazMenu(mensajes);

	}

	public static void main(String[] args)  {
		InterfazAppCliente iac = new InterfazAppCliente();
	
	}


}
