package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
       	   	
    	BaseDeDatos bd = new BaseDeDatos();

    	JOptionPane.showMessageDialog(
                null, 
                bd.listarCuentasConCVU(), 
                "CVUs para Testing", 
                JOptionPane.INFORMATION_MESSAGE
            );
    	// registro de usuario
    	Usuario u = Usuario.register();
    	// guardar user en la bd
    	bd.agregarUsuarioYCuenta(u);
    	u.setBaseDeDatos(bd);
    	u.verMenu();
    }
  
    
}
