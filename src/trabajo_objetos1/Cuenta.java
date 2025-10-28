package trabajo_objetos1;

public class Cuenta {
	private String mail;
	private String clave;
	private double saldo;
	
	public Cuenta(String mail, String clave, double saldo) {
		super();
		this.mail = mail;
		this.clave = clave;
		this.saldo = saldo;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	
	
}
