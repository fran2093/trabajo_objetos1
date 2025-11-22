package trabajo_objetos1;


public class Main {

    public static void main(String[] args) {
       	   	
    	BaseDeDatos bd = new BaseDeDatos();

    	// registro de usuario
    	Usuario u = Usuario.register();
    	u.verMenu();
    	// guardar user en la bd
    	bd.agregarUsuario(u);
       
    }
  
    
}
