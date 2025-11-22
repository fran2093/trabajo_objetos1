package trabajo_objetos1;


import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {

	private  List<Usuario> listaUsuarios = new ArrayList<>();
	
	 public BaseDeDatos(){
		 listaUsuarios.add(new Usuario("Bob", "Marley", "Bob@gmail.com", "MedicationMakesMeHigh", Rol.CLIENTE));
		 listaUsuarios.add(new Usuario("Mario", "Rod", "Rod@gmail.com", "QuieroHamburgesas", Rol.CLIENTE));
	 }
	

	
    private List<Cuenta> listaCuentas = new ArrayList<>();
    
	
    public void agregarUsuario(Usuario u) {
    	listaUsuarios.add(u);
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
    
}
