package es.studium.Practica2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia 
{
	public static Connection conectar()
	{
		String driver ="com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";
		String user = "root";
		String password ="1234";
		Connection conexion = null;

		try
		{
			//Nos aseguramos que arranca el driver
			Class.forName(driver);
			//Establecemos la conexión con la base de datos hotel
			conexion=DriverManager.getConnection(url,user,password);
			if(conexion !=null)
			{
				System.out.println("Conectado a la base de datos");
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Error en la conexión");
			ex.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Error"+ cnfe.getMessage());
		}
		return conexion;
	}

	public static void desconexion(Connection conexion)
	{
		try
		{
			conexion.close();
			System.out.println("Desconexión de la base datos");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	/* Devuelve el id del nuevo cliente */
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) 
	{
		//Conectamos a la base de datos
		Connection conex = conectar();
		int respuesta =0;
		try
		{
			//Nos permitirá ejecutar las sentencias sql en la base de datos
			Statement st = conex.createStatement();
			//String cadenaSQL = "INSERT INTO " + articulos +" VALUES (null, '" + nombreArticulo + "', "+ tamanioArticulo + ", "+ "'"+descripcionArticulo + "'"+ ", "+ precioArticulo + ","	+ choidProvFK +");";
			String sentenciaCreate = "INSERT INTO clientes"+" values(null,'" + nombre + "','"+ apellidos +"','"+ email +"','"+ dni +"','"+ clave +"');";
			
			
			System.out.println(sentenciaCreate);
			st.executeUpdate(sentenciaCreate);
			
			st=conex.createStatement();
			ResultSet rsConsulta = st.executeQuery("select idCliente from clientes");
			// Saca el id del nuevo cliente
			while (rsConsulta.next()) 
			{
				System.out.println(rsConsulta.getInt("idCliente"));
				respuesta=rsConsulta.getInt("idCliente"); //Revisar si da problemas
			}
			rsConsulta.close();
			st.close();
		}
		catch(SQLException ex)
		{
			System.out.println("Error al hacer un Insert");
			ex.printStackTrace();
			respuesta=1;
		}
		desconexion(conex);
		return respuesta;
	}

	/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
	public static String readCliente(int idCliente, String campo) 
	{
		String contenido="";
		Connection conexion = conectar();
		try
		{
			//Nos permitirá ejecutar las sentencias sql en la base de datos
			Statement st = conexion.createStatement();

			ResultSet rsConsulta = st.executeQuery("select" +campo+" from clientes where idCliente="+ idCliente);
			// Recorremos con el resulSet la tabla cliente
			while (rsConsulta.next()) 
			{
				contenido=rsConsulta.getString(campo);
			}
			rsConsulta.close();
			st.close();
		}
		catch(SQLException ex)
		{
			System.out.println("Error al realizar la consulta");
			ex.printStackTrace();
		}
		return contenido;
	}

	// Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. 
	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) 
	{
		boolean estado = false;
		Connection conex = conectar();
		try
		{
			Statement st = conex.createStatement();
			//"UPDATE disponen SET idReunionFK ="+choReunion.getSelectedItem()+	", idArticuloFK="+txtArticuloFK.getText()+" WHERE idReunion = "+idReunionFK;
			st.executeUpdate("UPDATE clientes set"+ campo+ "='"+nuevoValor+"' WHERE idCliente ="+idCliente);
			st.close();
			estado=true;
		}
		catch(SQLException ex)
		{
			System.out.println("Error al actualizar");
			ex.printStackTrace();
		}
		desconexion(conex);
		return estado;
		
	}
	// Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. 
	public static boolean deleteCliente(int idCliente) 
	{
		boolean estado = false;
		Connection conex = conectar();
		try
		{
			Statement st = conex.createStatement();
			//"DELETE FROM articulos WHERE idArticulo = " + idArticulo;
			st.executeUpdate("DELETE FROM clientes where idCliente="+idCliente);
			st.close();
			estado=true;
		}
		catch(SQLException ex)
		{
			System.out.println("Error en el borrado");
			ex.printStackTrace();
			estado=false;
		}
		desconexion(conex);
		return estado;
	}
}
