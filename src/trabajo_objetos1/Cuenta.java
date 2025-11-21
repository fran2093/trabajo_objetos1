package trabajo_objetos1;

import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

	private Usuario usuario;
	private int CVU;
    private int token;
    private double saldo;
    
    private List<Transaccion> historialTransacciones = new ArrayList<>();

    private int generarNuevoToken() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }
    
    private int generarCVU() {
        Random random = new Random();
        return random.nextInt(900000000) + 100000000;
    }
    
    // CONSTRUCTOR
    public Cuenta(Usuario usuario) {
          	
        this.usuario = usuario;
        this.CVU = generarCVU();
        this.saldo = 0.0; 
        this.token = generarNuevoToken(); 
    }

    private boolean validarToken() {
        try {

            String tokenIngresadoStr = Validaciones.ValidarString(
                "Ingrese el token de 3 dígitos para confirmar la operación:");
            
            int tokenIngresado = Integer.parseInt(tokenIngresadoStr);
            
            if (tokenIngresado == this.token) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error de validación: Token incorrecto.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El token debe ser un número.");
            return false;
        }
    }

    public void depositar(String mensajeInput) {
    	
    	double monto = Validaciones.ValidarDouble(mensajeInput);
    	
        if (!validarToken()) {
            return; 
        }

        saldo += monto;
        
        Transaccion t = new Transaccion(TipoTransaccion.DEPOSITO, monto, this.CVU);
        historialTransacciones.add(t);
        
        JOptionPane.showMessageDialog(null, t.getDetalle() + "\nNuevo saldo: " + saldo);
    }
    
    public void depositarSinValidacion(String mensajeInput) {
    	
    	double monto = Validaciones.ValidarDouble(mensajeInput);
    	
        saldo += monto;
    }

    public boolean retirar(String mensajeInput) {

    	double monto = Validaciones.ValidarDouble(mensajeInput);
        
        if (!validarToken()) {
            return false;
        }
        
        saldo -= monto ;

        Transaccion t = new Transaccion(TipoTransaccion.RETIRO, monto, this.CVU);
        historialTransacciones.add(t);
        
        JOptionPane.showMessageDialog(null, t.getDetalle() + "\nNuevo saldo: " + saldo);
        return true;
    }
    
    public void mostrarSaldo() {
        JOptionPane.showMessageDialog(null, 
            "Saldo actual: " + String.format("%.2f", saldo));
    }
   
    public void reasignarToken() {
        this.token = generarNuevoToken();
        JOptionPane.showMessageDialog(null, "¡Nuevo token generado!\nEl token es: " + this.token);
    }
    
    // --- Getters ---

    public double getSaldo() {
        return saldo;
    }

    public int getToken() {
        return token;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public int getCVU() {
		return CVU;
	}

	public void setCVU(int cvu) {
		CVU = cvu;
	}

	public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}
