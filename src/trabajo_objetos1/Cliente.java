package trabajo_objetos1;

public class Cliente extends Cuenta {
	private String nombre;
	private String apellido;
	private String numero;
	private String direccion;
	
	

	public Cliente(String mail, String clave, double saldo, String nombre, String apellido, String numero,
			String direccion) {
		super(mail, clave, saldo);
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero = numero;
		this.direccion = direccion;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}
