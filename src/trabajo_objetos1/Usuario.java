package trabajo_objetos1;

public class Usuario{
	
	private String nombre;
	private String apellido;
	private String clave;
	private Rol rol;
	
	public Usuario(String nombre, String apellido, String clave, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public void verMenu()
	
	
}
