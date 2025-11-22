package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Usuario{
	
	private String nombre;
	private String apellido;
	private String clave;
	private Rol rol;
	private String mail;
	private BaseDeDatos bd;
	private Cuenta cuenta;

	public Usuario(String nombre, String apellido, String mail, String clave, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.clave = clave;
		this.rol = rol;
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
		String apellido = Validaciones.ValidarString("Ingrese su apellido: ");
		String mail = Validaciones.ValidarMail("Ingrese su mail: ");
		String clave = Validaciones.ValidarClave("Ingrese su clave: ");
		
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
	    
	    return new Usuario(nombre, apellido, mail, clave, rolElegido);
		
       }

	public void verMenu() {
		if(rol == Rol.CLIENTE) {
			if (this.cuenta == null) { 
                JOptionPane.showMessageDialog(null, "No se encontró una cuenta asociada.");
                return;
           }
			
	        String[] opciones = {
	                "Depositar",
	                "Retirar",
	                "Transferir",            
	                "Ver saldo",
	                "Salir"
	        };

	        int elegido = -1;

	        while (elegido != 4) {

	            elegido = JOptionPane.showOptionDialog(
	                    null,
	                    "Informacion Personal: " + nombre + " " + apellido + "\n" + "CVU: " + this.cuenta.getCVU() + "\n" + "Token: " + this.cuenta.getToken() ,
	                    "Interfaz Cliente",
	                    0,
	                    0,
	                    null,
	                    opciones,
	                    opciones[0]
	            );

	            switch (elegido) {

	                case 0: // depositar
	                    this.cuenta.depositar("Ingrese el monto a depositar: ");
	                    break;
	                case 1: // retirar                          
	                	this.cuenta.retirar("Ingrese el monto a retirar: ");
	                	break;
	                case 2: // esto va a ser transferir But nel lo hice
	                    try {
	                        String cvuDestinoStr = Validaciones.ValidarString("Ingrese el CVU de la cuenta destino:");
	                        int cvuDestino = Integer.parseInt(cvuDestinoStr);
	                        this.cuenta.transferir(cvuDestino, this.bd);
	                    } catch (NumberFormatException e) {
	                        JOptionPane.showMessageDialog(null, "CVU inválido.");
	                    }
	                    break;
	                case 3: // show saldo     
	                	this.cuenta.mostrarSaldo();
	                	break;
	                case 4: // salir del menu 
	                default:
	                    JOptionPane.showMessageDialog(null, "Saliendo...");
	                    break;
	            }	            	        
	            
	        }
		} else {
			String[] opciones = {
	                "Ver Usuarios",
	                "Retirar",
	                "Transferir",
	                
	                "Ver Saldo",
	                "Salir"
	        };

	        int elegido = -1;

	        while (elegido != 4) {

	            elegido = JOptionPane.showOptionDialog(
	                    null,
	                    "Informacion Personal: " + nombre + " " + apellido + "\n" + "CVU: 67" ,
	                    "Interfaz Administrador",
	                    0,
	                    0,
	                    null,
	                    opciones,
	                    opciones[0]
	            );


	            switch (elegido) {

                case 0: // ver niggas
                	JOptionPane.showMessageDialog(null, bd.verUsuarios(), "Usuarios Registrados", JOptionPane.INFORMATION_MESSAGE);
                	break;
                case 1: // Antes era Retirar, ahora es "Gestionar Cuentas"
                	JOptionPane.showMessageDialog(null, "ADMIN: Funcionalidad de gestión no implementada.");
                	break;
                case 2: // Antes era Transferir
                    JOptionPane.showMessageDialog(null, "ADMIN: Funcionalidad de reportes no implementada.");
                    break;

                case 3: // Antes era Ver Saldo
                	JOptionPane.showMessageDialog(null, "ADMIN: Funcionalidad de logs no implementada.");
                	break;

                case 4: // salir del menu 
                default:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                    }
	            }
	        }
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getRol() {
		return this.rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	//cada usuario tiene su registro en bd, esto es para que lo pueda ver el admin
	public void setBaseDeDatos(BaseDeDatos base) {
	    this.bd = base;
	    if (this.rol == Rol.CLIENTE) {
            this.cuenta = bd.buscarCuentaPorUsuario(this);
            }
	    }
}