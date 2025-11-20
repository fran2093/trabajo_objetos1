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
	public static int ValidarInt(String mensaje) {
		String dato ="Gamaliel";
		
		boolean flag ;
		do {
			flag = true;
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error");
				flag = false;
			}else {
				for (int i = 0; i < dato.length(); i++) {
					if (!Character.isDigit(dato.charAt(i)) && dato.charAt(0) != '-') {
						JOptionPane.showMessageDialog(null, "No puede ser un dato no numerico");
						flag = false;
						break;
					}
				}
			}
		} while (flag == false);
		return Integer.parseInt(dato) ;
	}
	
	public static double IngresarDouble(String mensaje) {
		String dato;
		double numero = 0.0;
		boolean flag = false;
		
		do {
			dato = ValidarString(mensaje);
			
			try {
				numero = Double.parseDouble(dato);
				flag = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Por favor, ingrese un monto numérico válido (use punto o coma según su configuración local).");
				flag = false;
			}
			
		} while (!flag);
		
		return numero;
	}
	
}