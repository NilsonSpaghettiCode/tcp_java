import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;


public class InterfazServer {
	public static void main(String[] args) {
		String msg = "Recibido";
		try {
			ServerSocket server = new ServerSocket(5000);
			
			System.out.println("Servidor iniciado:"+server.getInetAddress().getHostAddress()+":"+server.getLocalPort());
			Socket socketCliente;
			System.out.println("Esperando Cliente");
			socketCliente = server.accept();
			System.out.println("Cliente conectado: "+socketCliente.getInetAddress().getHostAddress()+":"+socketCliente.getPort());
			DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
			DataOutputStream dos = new DataOutputStream(socketCliente.getOutputStream());
			do 
			{
				byte[]datos = new byte[256];
				dis.read(datos);
				String mensaje = new String(datos);
				
				System.out.println("De cliente:"+mensaje);
				mensaje = mensaje.trim();
				if(mensaje.equalsIgnoreCase("Terminar")) 
				{
					System.out.println("Se recibio una solicitud para terminar la conexion");
					dos.write("Terminar".getBytes());
					dis.close();
					dos.close();
					socketCliente.close();
					System.out.println("Conexión terminada");
				}else 
				{
					dos.write(msg.getBytes());
					//System.out.println("Estado conexion:"+socketCliente.isClosed());
				}
				
			}while(!(socketCliente.isClosed()));
			
			server.close();
			System.out.println("Server Close");
			
		} catch ( BindException  e) {
			System.out.println("Ya hay un servicio enlazado a ese dirección. "+e);
		} catch (IOException e) {
			System.out.println("Error IO: se produjo en error en la entrada o salida de datos. "+ e);
		}
	}
}
