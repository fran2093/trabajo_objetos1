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
	          + "Bienvenido\n"
	          + "Registrate para formar parte de nuestra plataforma.\n\n"
	          + "✔ Descuentos en Productos Umbrella\n"
	          + "✔ Alta seguridad\n"
	          + "✔ Intereses y Beneficios\n"
	          + "Comenzá a operar de manera fácil y segura.",
	          "@UmbrellaCorp", 
	          JOptionPane.INFORMATION_MESSAGE );

		String nombre = Validaciones.ValidarString("Ingrese nombre: ");
		if (nombre == null) { return null; }
		
		String apellido = Validaciones.ValidarString("Ingrese su apellido: ");
		if (apellido == null) { return null; }
		
		String mail = Validaciones.ValidarMail("Ingrese su mail (Gmail): ");
		if (mail == null) { return null; }
		
		String clave = Validaciones.ValidarClave("Ingrese su clave (Minimo 8 caracteres): ");
		if (clave == null) { return null; }
		
	    String[] roles = {"Cliente", "Administrador"};
		
		int opcion = JOptionPane.showOptionDialog(
	            null,
	            "Seleccione su rol:",
	            "Registrar usuario",
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
			
			String[] opcionesCliente = {
				    "Depositar",                
				    "Retirar",                  
				    "Transferir",               
				    "Invertir",                 
				    "Rescatar inversión",      
				    "Rescatar TODO",            
				    "Rendimientos",             
				    "Historial transacciones",  
				    "Simular días",            
				    "Menu Admin",               
				    "Salir"                     
				};



			int elegido = -1;

			while (elegido != 10) {

			    elegido = JOptionPane.showOptionDialog(
			        null,
			        "Informacion personal: " + nombre + " " + apellido + "\n" +
			        "CVU: " + this.cuenta.getCVU() + "\n" +
			        "Token: " + this.cuenta.getToken() + "\n" +
			        "Saldo actual: $" + String.format("%.2f", this.cuenta.getSaldo()) + "\n" +
			        "Saldo invertido: $" + String.format("%.2f", this.cuenta.getSaldoInvertido()),
			        "Interfaz cliente - UMBRELLA CLIENT",
			        0, 0, null,
			        opcionesCliente,
			        opcionesCliente[0]
			    );

			    switch (elegido) {
			        case 0 -> this.cuenta.depositar("Ingrese el monto a depositar: ");
			        case 1 -> this.cuenta.retirar("Ingrese el monto a retirar: ");

			        case 2 -> {
			            try {
			                String cvuDestinoStr = Validaciones.ValidarString("Ingrese el CVU de la cuenta destino:");
			                int cvuDestino = Integer.parseInt(cvuDestinoStr);
			                this.cuenta.transferir(cvuDestino, this.bd);
			            } catch (NumberFormatException e) {
			                JOptionPane.showMessageDialog(null, "Operacion fallida, CVU invalido.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }

			        case 3 -> this.cuenta.invertir("Ingrese el monto a invertir: ");
			        case 4 -> this.cuenta.rescatarInversion("Ingrese el monto a rescatar de la inversión: ");
			        case 5 -> this.cuenta.rescatarTodo();

			        case 6 -> this.cuenta.mostrarRendimiento();
			        case 7 -> this.cuenta.getHistorialTransacciones();
			        case 8 -> this.bd.simularDias();

			        case 9 -> { // Menu Admin
			            this.rol = Rol.ADMINISTRADOR;
			            verMenu();
			            return; // swichea a client calleando a menu de nuevo
			        }

			        case 10 -> JOptionPane.showMessageDialog(null, "Saliendo...");
			        default -> {}
			    }
			}       	 	             
	        
		} else {
			
			String[] opcionesAdmin = {
				    "Ver Usuarios",         
				    "Ver Administradores",  
				    "Menu Cliente",         
				    "Salir"                 
				};


			int elegido = -1;

			while (elegido != 3) {

			    elegido = JOptionPane.showOptionDialog(
			        null,
			        "Informacion Personal: " + nombre + " " + apellido + "\n" +
			        "Correo Umbrella: " + nombre + "admin.umbrella@umbrella.com",
			        "Interfaz Administrador - UMBRELLA ADMIN",
			        0, 0, null,
			        opcionesAdmin,
			        opcionesAdmin[0]
			    );

			    switch (elegido) {
			        case 0 -> JOptionPane.showMessageDialog(null, bd.listarCuentasConCVU(), "Centro de Cuentas - @UMBRELLA", JOptionPane.INFORMATION_MESSAGE);

			        case 1 -> JOptionPane.showMessageDialog(
			            null,
			            "--- Data Base (Administradores Umbrella) ---\n\n" +
			            "Alice Johnson\nMail: alice.j@umbrella.com\n\n" +
			            "James Rodriguez\nMail: j.rodriguez@umbrella.com",
			            "Centro de Cuentas - @UMBRELLA",
			            JOptionPane.INFORMATION_MESSAGE
			        );

			        case 2 -> { // Menu Cliente
			            this.rol = Rol.CLIENTE;
			            verMenu();
			            return; // swichea el mennu ongod volviendolo a callear
			        }

			        case 3 -> JOptionPane.showMessageDialog(null, "Saliendo...");
			        default -> {}
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