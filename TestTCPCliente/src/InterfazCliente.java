import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InterfazCliente {
	public static void main(String[] args)
	{
		int port = 5000;
		String address = "";

		System.out.println("Digite la direccion ip del servidor");
		System.out.print(">");
		Scanner input = new Scanner(System.in);
		address = input.next();
			
		Socket cliente = new Socket();

		System.out.println("Cliente socket creado");
		try {
			cliente.connect(new InetSocketAddress(address, port), 2000);
			System.out.println("Conexion en:"+cliente.getInetAddress().getHostAddress()+":"+cliente.getLocalPort());
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			DataInputStream dis = new DataInputStream(cliente.getInputStream());
			
			do 
			{
				byte[] data = new byte[256];
				byte[] data2 = new byte[256];
				Scanner sn = new Scanner(System.in);
				
				System.out.print(">");
				String msgSend = sn.next();
				data = msgSend.getBytes();
				dos.write(data);
				dis.read(data2);
				String mensaje = new String(data2);
				System.out.println("De server:"+mensaje);
				
				mensaje = mensaje.trim();
				if(mensaje.equals("Terminar"))
				{
					cliente.close();

				}
				
			}while(!(cliente.isClosed()));
			
		} catch (ConnectException  e) {
			System.out.println("Error 504: El servidor al que intenta conectarse no esta en linea");
		} catch (SocketTimeoutException e) {
			System.out.println("Error 504: El servidor al que intenta conectarse no esta en linea");
		} catch (IOException e) {
			System.out.println("Error de IO, digite correctamente la entrada de datos");
		}
		
		System.out.println("Proceso Terminado");
		
		
	}
}
