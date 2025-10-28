package trabajo_objetos1;

public class Usuario {
	private String nombre;
	private String apellido;
	private String token;
	private String clave;
	
	public Usuario(String nombre, String apellido, String token, String clave) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.token = token;
		this.clave = clave;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	
}
