package trabajo_objetos1;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Cuenta cuenta1 = new Cuenta("a@mail.com", "1234", 2000);


        Boolean login = false;
        do {
        
        }
        while(login = true);
        
        
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
                    "Seleccione una operacion",
                    "Cuenta Bancaria",
                    0,
                    0,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (elegido) {

                case 0: // depositar
                    try {
                        double monto = Double.parseDouble(
                                JOptionPane.showInputDialog("Monto a depositar:")
                        );
                        cuenta1.depositar(monto);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Monto invalido.");
                    }
                    break;

                case 1: // retirar
                    try {
                        double monto = Double.parseDouble(
                                JOptionPane.showInputDialog("Monto a retirar:")
                        );
                        cuenta1.retirar(monto);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Monto invalido.");
                    }
                    break;

                case 2: // esto va a ser transferir But nel lo hice
       

                case 3: // show saldo
                    cuenta1.mostrarSaldo();
                    break;

                case 4: // salir del menu 
                default:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
            }
        }
    }
}
