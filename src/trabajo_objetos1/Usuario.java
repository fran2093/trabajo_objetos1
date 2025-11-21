package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Usuario{
	
	private String nombre;
	private String apellido;
	private String clave;
	private Rol rol;
	private String mail;
	
	public Usuario(String nombre, String apellido, String clave, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.rol = rol;
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public static Usuario register() {
		JOptionPane.showMessageDialog(
			    null,
			    "💠 UMBRELLA WALLET 💠\n\n"
			  + "¡Bienvenido!\n"
			  + "Registrate para formar parte de nuestra plataforma.\n\n"
			  + "✔ Cuentas Corrientes\n"
			  + "✔ Cajas de Ahorro\n"
			  + "✔ Intereses y Beneficios\n"
			  + "Comenzá a operar de manera fácil y segura."
			);

		
		String nombre = Validaciones.ValidarString("Ingrese Nombre: ");
		String apellido = JOptionPane.showInputDialog("Ingrese su apellido: ");
		String mail = JOptionPane.showInputDialog("Ingrese su mail: ");
		String clave = JOptionPane.showInputDialog("Ingrese su clave: ");

		
		
	    String[] roles = {"Cliente", "Administrador"};
		
		int opcion = JOptionPane.showOptionDialog(
	            null,
	            "Seleccione su rol:",
	            "Registrar Usuario",
	            0,
	            0,
	            null,
	            roles,
	            roles[0]
	    );

	    Rol rolElegido;

	    if(opcion == 0) {
	        rolElegido = Rol.CLIENTE;
	    } else {
	        rolElegido = Rol.ADMINISTRADOR;
	    }
	    
	    return new Usuario(nombre, apellido, clave, rolElegido);
		
	    
	    
       }
	

	
	
	public void verMenu() {
		if(rol == Rol.CLIENTE) {
	        String[] opciones = {
	                "Depositar",
	                "Retirar",
	                "Transferir",            
	                "Mostrar saldo",
	                "Salir"
	        };

	        int elegido = -1;

	        while (elegido != 4) {

	            elegido = JOptionPane.showOptionDialog(
	                    null,
	                    "Informacion Personal: " + nombre + " " + apellido + "\n" + "CVU: " + this.cuenta.getCVU() ,
	                    "Interfaz Cliente",
	                    0,
	                    0,
	                    null,
	                    opciones,
	                    opciones[0]
	            );

	            switch (elegido) {

	                case 0: // depositar
	                    
	                case 1: // retirar                          

	                case 2: // esto va a ser transferir But nel lo hice

	                case 3: // show saldo     

	                case 4: // salir del menu 
	                default:
	                    JOptionPane.showMessageDialog(null, "Testing...");
	                    break;
	            }	            	        
	            
	        }
		} else {
			String[] opciones = {
	                "Test",
	                "Test",
	                "Test",
	                
	                "Test",
	                "Salir"
	        };

	        int elegido = -1;

	        while (elegido != 4) {

	            elegido = JOptionPane.showOptionDialog(
	                    null,
	                    "Seleccione una operacion",
	                    "Interfaz Administrador",
	                    0,
	                    0,
	                    null,
	                    opciones,
	                    opciones[0]
	            );

	            switch (elegido) {

	                case 0: // depositar
	                    
	                case 1: // retirar                          

	                case 2: // esto va a ser transferir But nel lo hice

	                case 3: // show saldo     

	                case 4: // salir del menu 
	                default:
	                    JOptionPane.showMessageDialog(null, "Testing...");
	                    break;
	            }	            	        
	            
	        }
			
		}
		
	}
	
	
}
