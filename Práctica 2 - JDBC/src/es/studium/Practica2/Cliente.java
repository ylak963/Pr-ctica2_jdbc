package es.studium.Practica2;

public class Cliente 
{
	private int idCliente;
	private String nombre;
	private String apellidos;
	private String email;
	private String dni;
	private String clave;
	
	public Cliente()
	{
		idCliente=0;
		nombre="";
		apellidos="";
		email="";
		dni="";
		clave="";
	}
	public Cliente(int idCl, String n, String apel, String mail, String d, String c)
	{
		idCliente=idCl;
		nombre=n;
		apellidos=apel;
		email=mail;
		dni=d;
		clave=c;
	}
	public int getIdCliente() 
	{
		return idCliente;
	}
	public void setIdCliente(int idCliente) 
	{
		this.idCliente = idCliente;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getApellidos() 
	{
		return apellidos;
	}
	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getDni() 
	{
		return dni;
	}
	public void setDni(String dni) 
	{
		this.dni = dni;
	}
	public String getClave() 
	{
		return clave;
	}
	public void setClave(String clave) 
	{
		this.clave = clave;
	}
}
