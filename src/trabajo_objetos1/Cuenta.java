package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Cuenta {

    private String mail;
    private String clave;
    private Usuario usuario;
    private double saldo;

    public Cuenta(String mail, String clave, double saldoInicial) {
        this.mail = mail;
        this.clave = clave;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            JOptionPane.showMessageDialog(null, 
                "Depósito exitoso.\nNuevo saldo: " + saldo);
        } else {
            JOptionPane.showMessageDialog(null, 
                "Monto inválido.");
        }
    }

    public boolean retirar(double monto) {
        if (monto > saldo || monto <= 0) {
            JOptionPane.showMessageDialog(null, 
                "No es posible retirar ese monto.");
            return false;
        }
        saldo -= monto;
        JOptionPane.showMessageDialog(null, 
            "Retiro exitoso.\nNuevo saldo: " + saldo);
        return true;
    }

    public void mostrarSaldo() {
        JOptionPane.showMessageDialog(null, 
            "Saldo actual: " + saldo);
    }
   
    public double getSaldo() {
        return saldo;
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

	
}
