package es.studium.Practica2;

public class Hotel 
{
	public static void main(String[] args) 
	{
		int id = ClientePersistencia.createCliente("Mar�a Jos�", "Mart�nez", "mjmartinez@grupostudium.com", "12345678Z",
				"Studium2020");
		
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
 		ClientePersistencia.updateCliente(id, "apellidos", "Mart�nez Navas");
 		
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
 		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));ClientePersistencia.deleteCliente(id);
 	}
}
