package trabajo_objetos1;

import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private String mail;
    private int token; // Token de 3 dígitos para transacciones
    private Usuario usuario;
    private double saldo;
    
    // Lista para guardar las transacciones (facturas)
    private List<Transaccion> historialTransacciones = new ArrayList<>();

    // Método privado para generar un nuevo token (de 100 a 999)
    private int generarNuevoToken() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }

    // CONSTRUCTOR
    public Cuenta(String mail, Usuario usuario) {
        this.mail = mail;
        this.usuario = usuario;
        this.saldo = 0.0; 
        this.token = generarNuevoToken(); 
    }

    /**
     * Método auxiliar que solicita el token al usuario y lo compara.
     * @return true si el token es correcto, false si es incorrecto.
     */
    private boolean validarToken() {
        try {
            // Usamos IngresarString para evitar errores con JOptionPane.showInputDialog
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

    /**
     * Pide el token de la cuenta y realiza el depósito si es correcto.
     */
    public void depositar(double monto) {
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido para depósito.");
            return;
        }
        
        if (!validarToken()) {
            return; 
        }

        saldo += monto;
        
        // REGISTRO DE TRANSACCIÓN
        Transaccion t = new Transaccion(TipoTransaccion.DEPOSITO, monto, this.mail);
        historialTransacciones.add(t);
        
        JOptionPane.showMessageDialog(null, t.getDetalle() + "\nNuevo saldo: " + saldo);
    }
    
    /**
     * Realiza un depósito sin pedir ni validar el token. Usado para transferencias entrantes.
     */
    public void depositarSinValidacion(double monto) {
        if (monto > 0) {
            saldo += monto;
            // No se muestra JOptionPane para no interrumpir el flujo de la transferencia.
        }
    }

    /**
     * Pide el token de la cuenta y realiza el retiro si es correcto y hay saldo.
     * @return true si el retiro fue exitoso, false en caso contrario.
     */
    public boolean retirar(double monto) {
        if (monto > saldo || monto <= 0) {
            JOptionPane.showMessageDialog(null, 
                "No es posible retirar ese monto o el monto es inválido.");
            return false;
        }
        
        if (!validarToken()) {
            return false;
        }
        
        saldo -= monto;

        // REGISTRO DE TRANSACCIÓN
        Transaccion t = new Transaccion(TipoTransaccion.RETIRO, monto, this.mail);
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
    
    public String getMail() {
        return mail;
    }

    public List<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }
}
