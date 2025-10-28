package trabajo_objetos1;

public class Cuenta {
	private String mail;
	private String clave;
	private Usuario usuario;
	
	public Cuenta(String mail, String clave, Usuario usuario) {
		super();
		this.mail = mail;
		this.clave = clave;
		this.usuario = usuario;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
