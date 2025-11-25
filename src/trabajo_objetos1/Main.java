package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
       	   	
    	BaseDeDatos bd = new BaseDeDatos();

    	// registro de usuario
    	Usuario u = Usuario.register();
    	
    	if (u != null) {
            // si el usuario no está vacio sigue como deberia
            bd.agregarUsuarioYCuenta(u); 
            u.setBaseDeDatos(bd); 
            u.verMenu();
        } else {
            // si el usuario cancela simplemente termina la ejecución.
            JOptionPane.showMessageDialog(null, "Registro cancelado, saliendo de Umbrella.", "UMBRELLA WALLET", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

