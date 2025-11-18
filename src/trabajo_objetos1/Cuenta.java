package trabajo_objetos1;

import javax.swing.JOptionPane;

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

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            JOptionPane.showMessageDialog(null, 
                "Deposito exitoso.\nNuevo saldo: " + saldo);
        } else {
            JOptionPane.showMessageDialog(null, 
                "Monto invalido.");
        }
    }
    
    public boolean retirar(double monto) {
        if (monto > saldo || monto <= 0) {
            JOptionPane.showMessageDialog(null, 
                "No es posible retirar ese monto.");
            return false;
        }
        saldo -= monto; //aca le resto el saldo retirado al monto
        JOptionPane.showMessageDialog(null, 
            "Retiro exitoso.\nNuevo saldo: " + saldo);
        return true; //se retiro correctamente jeje
    }
   
    public void mostrarSaldo() {
        JOptionPane.showMessageDialog(null, 
            "Saldo actual: " + saldo);
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
