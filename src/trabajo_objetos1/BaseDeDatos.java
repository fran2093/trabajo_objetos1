package trabajo_objetos1;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BaseDeDatos {

	private  List<Usuario> listaUsuarios = new ArrayList<>();
	private List<Cuenta> listaCuentas = new ArrayList<>();
	
	 public BaseDeDatos(){
		 Usuario bob = new Usuario("Bob", "Marley", "Bob@gmail.com", "MedicationMakesMeHigh", Rol.CLIENTE);
		 Usuario mario = new Usuario("Mario", "Rod", "Rod@gmail.com", "QuieroHamburgesas", Rol.CLIENTE);
		 
		 listaUsuarios.add(bob);
         listaUsuarios.add(mario);
         listaCuentas.add(new Cuenta(bob));
         listaCuentas.add(new Cuenta(mario));
	 }

	 public void agregarUsuarioYCuenta(Usuario u) {
	        listaUsuarios.add(u);
	        // crea automáticamente una cuenta  para un CLIENTE
	        if (u.getRol() == Rol.CLIENTE) {
	            listaCuentas.add(new Cuenta(u));
	        }
	    }
    
	 public Cuenta buscarCuentaPorUsuario(Usuario usuario) {
		    
		    String mailBuscado = usuario.getMail(); // usa el mail para filtrar, este nos sirve como pk.
		    
		    for (Cuenta cuenta : listaCuentas) {
		        
		        // compara el mail del usuario con el de la cuenta.
		        if (cuenta.getUsuario().getMail().equals(mailBuscado)) { 
		            return cuenta;
		        }
		    }

		    return null; 
		}
    
    public Cuenta buscarCuentaPorCVU(int cvu) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getCVU() == cvu) {
                return cuenta;
            }
        }
        return null;
    }
    
    public String listarCuentasConCVU() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Data Base (Clientes Umbrella) ---\n\n");

        for (Cuenta c : listaCuentas) {
            String nombreUsuario = c.getUsuario().getNombre();
            sb.append("Usuario: ").append(nombreUsuario).append("\n");
            sb.append("CVU: ").append(c.getCVU()).append("\n");
            sb.append("---------------------------\n");
        }

        return sb.toString();
    }
    
    public String verUsuarios() {
        StringBuilder sb = new StringBuilder(); //clase para construir textos complejos pero mas pro
        
        sb.append("USUARIOS REGISTRADOS:\n\n");

        for (Usuario u : listaUsuarios) {
            sb.append("Nombre: ").append(u.getNombre()).append("\n");
            sb.append("Apellido: ").append(u.getApellido()).append("\n");
            sb.append("Mail: ").append(u.getMail()).append("\n");
            sb.append("Rol: ").append(u.getRol()).append("\n");
            sb.append("---------------------------\n");
        }
        return sb.toString();
    }
    
    public void simularDias() {
    	int cuentasInversionActualizadas = 0;
     
    	for (Cuenta cuenta : listaCuentas) {
         cuenta.calcularRendimientoDiario();
         cuentasInversionActualizadas++;
    	}
     JOptionPane.showMessageDialog(null, "se ha simulado un día.\n", "Umbrella - simulador de días", JOptionPane.INFORMATION_MESSAGE);
 }
}