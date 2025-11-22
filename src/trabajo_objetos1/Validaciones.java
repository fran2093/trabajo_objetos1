package trabajo_objetos1;

import javax.swing.JOptionPane;

public abstract class Validaciones {

	public static String ValidarString(String mensaje) {
		String dato;
		do {
			dato = JOptionPane.showInputDialog(mensaje);
			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error");
			}
		} while (dato.isEmpty());
		return dato;
	}

	public static double ValidarDouble(String mensaje) {
		
		String dato;
		boolean flag ;
		do {
			flag = true;
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato == null || dato.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un valor.");
                flag = false;
            } else {
                try {
                    double valor = Double.parseDouble(dato);
                    if (valor <= 0) {
                        JOptionPane.showMessageDialog(null, "El monto debe ser positivo.");
                        flag = false;
                    }
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese un dato numérico válido (ej. 100.50).");
                    flag = false;
                }
            }
        } while (!flag);
       return Double.parseDouble(dato);
    }
	
	public static String ValidarMail(String mensaje) {
		String dato;
		do {
			dato = JOptionPane.showInputDialog(mensaje);
			if (dato.isEmpty() || !dato.endsWith(".com") || !dato.contains("@")) {
				JOptionPane.showMessageDialog(null, "Mail inválido.");
			}
		} while (dato.isEmpty() || !dato.endsWith(".com") || !dato.contains("@"));
		return dato;
	}
	
	public static String ValidarClave(String mensaje) {
		String dato;
		int min = 8;
		do {
			dato = JOptionPane.showInputDialog(mensaje);
			if (dato.isEmpty() || dato.length() < min) {
				JOptionPane.showMessageDialog(null, "Clave inválida.");
			}
		} while (dato.isEmpty() || dato.length() < min);
		return dato;
	}
}