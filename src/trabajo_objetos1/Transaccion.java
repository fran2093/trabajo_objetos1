package trabajo_objetos1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {

	//con un enum limitamos los tipos de transacciones ya que ya los tenemos definidos e inamovibles
    private TipoTransaccion tipo; 
    private double monto;
    private LocalDateTime fechaHora;
    private String cuentaOrigen;
    private String cuentaDestino;

    
    // constructor para retiros y/o depositos
    public Transaccion(TipoTransaccion tipo, double monto, String cuentaOrigen) {
        this.tipo = tipo; 
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = null;
    }

    // constructor esta vez para transferencias
    public Transaccion(double monto, String cuentaOrigen, String cuentaDestino) {
        this.tipo = TipoTransaccion.TRANSFERENCIA;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    //metodo para obtener la factura/detalle de cualquier transaccion
    public String getDetalle() {
    	//con este formatter cambiamos como se terminará mostrando la fecha y hora de la transaccion en cuestion, quedando mas prolijo y legible.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String detalle = "--- DETALLE DE TRANSACCIÓN ---\n" +
                         "Tipo: " + this.tipo.name() + "\n" +
                         //con el string format logramos que solo se muestren dos decimales de la transaccion en cuestion, ahorrandonos el problema de mostrar numeros excesivamente largos o sin redondear.
                         "Monto: $" + String.format("%.2f", this.monto) + "\n" +
                         "Fecha/Hora: " + this.fechaHora.format(formatter) + "\n" +
                         "Cuenta Origen: " + this.cuentaOrigen + "\n";
        
        //en el caso de ser una transferencia mostramos tambien el detalle de a qué cuenta se ha transferido, sino este bloque se omite
        if (this.cuentaDestino != null) {
            detalle += "Cuenta Destino: " + this.cuentaDestino + "\n";
        }
        
        return detalle;
    }
}