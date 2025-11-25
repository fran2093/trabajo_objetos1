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
    
    private double saldoInvertido;
    private List<Double> historialInversion = new ArrayList<>();
    private int diasInversion;
    
    private double generarTasaDiaria() {
        return (Math.random() * 0.10) - 0.05;
    }

    private int generarNuevoToken() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }
    
    private int generarCVU() {
        Random random = new Random();
        return random.nextInt(900000000) + 100000000;
    }
    
    //constructor (cada cuenta se crea a partir unicamente de un usuario mediante el metodo de BaseDeDatos)
    public Cuenta(Usuario usuario) {
          	
        this.usuario = usuario;
        this.CVU = generarCVU();
        this.saldo = 0.0; 
        this.token = generarNuevoToken();
        this.saldoInvertido = 0.0; 
        this.historialInversion.add(0.0);
    }
    
    public void depositar(String mensajeInput) {
    	
    	double monto = Validaciones.ValidarDouble(mensajeInput);
        saldo += monto;
        
        Transaccion t = new Transaccion(TipoTransaccion.DEPOSITO, monto, this.CVU);
        historialTransacciones.add(t);
        
        JOptionPane.showMessageDialog(null, t.getDetalle() + "\nNuevo saldo: " + saldo);
        return; 
    }
    
    public void depositarSinValidacion(double monto) {
        
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
    
    public boolean transferir(int cvuDestino, BaseDeDatos bd) {
    	double monto = Validaciones.ValidarDouble("Ingrese el monto a transferir");
    	
    	if (!validarToken()) {
			return false;
		}
    	
    	if (monto > this.saldo) {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
			return false;
		}
    	
    	Cuenta cuentaDestino = bd.buscarCuentaPorCVU(cvuDestino);
    	
    	if (cuentaDestino == null) {
			JOptionPane.showMessageDialog(null, "No se encontró la cuenta destino.");
			return false;
		}
    	
    	if (cuentaDestino.getCVU() == this.CVU) {
            JOptionPane.showMessageDialog(null, "No puedes transferirte a ti mismo.");
            return false;
        }
    	
    	this.saldo -= monto;
    	cuentaDestino.depositarSinValidacion(monto);
        
    	Transaccion t = new Transaccion(monto, this.CVU, cuentaDestino.getCVU());
        historialTransacciones.add(t);
        
        JOptionPane.showMessageDialog(null, 
                t.getDetalle() + 
                "\nTransferencia exitosa a CVU: " + cvuDestino + 
                "\nNuevo saldo: $" + String.format("%.2f", this.saldo));
                
        return true;
    }
   
    private boolean validarToken() {
        try {

            String tokenIngresadoStr = Validaciones.ValidarString(
                "Ingrese el token de 3 dígitos para confirmar la operación:");
            
            int tokenIngresado = Integer.parseInt(tokenIngresadoStr);
            
            if (tokenIngresado == this.token) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error de validación: token incorrecto.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: el token debe ser un número.");
            return false;
        }
    }
    
    public void reasignarToken() {
        this.token = generarNuevoToken();
        JOptionPane.showMessageDialog(null, "Nuevo token generado\nSu nuevo token es: " + this.token);
    }
    
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

	public double getSaldoInvertido() {
		return saldoInvertido;
	}

	public void setSaldoInvertido(double saldoInvertido) {
		this.saldoInvertido = saldoInvertido;
	}

	public List<Transaccion> getHistorialTransacciones() {
	    String historialTexto = "TRANSACCIONES UMBRELLA\n";
	    
	    // recorre la lista transacción por transacción concatenando los detalles de estas
	    for (Transaccion t : historialTransacciones) {
	        historialTexto += t.getDetalle() + "\n=============================\n"; }
	    
	    // en el caso de que la lista esté vacía:
	    if (historialTransacciones.isEmpty()) {
	        historialTexto += "No hay transacciones registradas."; }
	    JOptionPane.showMessageDialog(null, historialTexto, "Historial de Cuenta", JOptionPane.INFORMATION_MESSAGE);
	    
	    return historialTransacciones;
	}
	
	public boolean invertir(String mensajeInput) {
        double monto = Validaciones.ValidarDouble(mensajeInput);
        
        if (!validarToken()) {
            
            return false;
        }

        if (monto > this.saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente en cuenta principal.");
            return false;
        }
        
        this.saldo -= monto;
        this.saldoInvertido += monto;

        JOptionPane.showMessageDialog(null, 
            "Inversión realizada con éxito.\n" + 
            "Monto invertido: $" + String.format("%.2f", monto) + 
            "\nNuevo saldo principal: $" + String.format("%.2f", this.saldo),
            "Inversión UMBRELLA", JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

    public void calcularRendimientoDiario() {
    	if (this.saldoInvertido <= 0) {
            return; 
        }

        double tasa = generarTasaDiaria();
        double rendimiento = this.saldoInvertido * tasa;
        this.saldoInvertido += rendimiento;
        this.diasInversion++;

        this.historialInversion.add(this.saldoInvertido);

        JOptionPane.showMessageDialog(null, 
            "Rendimiento del día " + diasInversion + " (Tasa: " + String.format("%.2f", tasa * 100) + "%)\n" + 
            "Rendimiento generado: $" + String.format("%.2f", rendimiento) + 
            "\nNuevo Saldo de Inversión: $" + String.format("%.2f", this.saldoInvertido),
            "Simulación diaria", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // reporte general de todos los rendimientos hasta el momento
    public void mostrarRendimiento() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVERSIONES UMBRELLA\n");
        sb.append("Conteo días de inversión: ").append(this.diasInversion).append("\n");
        sb.append("Saldo actual: $").append(String.format("%.2f", this.saldoInvertido)).append("\n\n");
        
        sb.append("Fluctuación día a día: \n");
        for (int i = 1; i < historialInversion.size(); i++) {
            double saldoInicial = historialInversion.get(i-1);
            double saldoFinal = historialInversion.get(i);
            
            double rendimiento = saldoFinal - saldoInicial;
            double tasa = (saldoInicial != 0) ? rendimiento / saldoInicial : 0.0;
            
            sb.append("Día ").append(i).append(":\n");
            sb.append("  Tasa aplicada: ").append(String.format("%.2f", tasa * 100)).append("%\n");
            sb.append("  Rendimiento: $").append(String.format("%.2f", rendimiento)).append("\n");
            sb.append("  Saldo final: $").append(String.format("%.2f", saldoFinal)).append("\n");
            sb.append("----------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Rendimiento de Inversión", JOptionPane.INFORMATION_MESSAGE);
    }
}
