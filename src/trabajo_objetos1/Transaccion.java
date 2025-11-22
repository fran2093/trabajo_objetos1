package trabajo_objetos1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {

	//con un enum limitamos los tipos de transacciones ya que ya los tenemos definidos e inamovibles
    private TipoTransaccion tipo; 
    private double monto;
    private LocalDateTime fechaHora;
    private int CVUorigen;
    private int CVUdestino;

    
    // constructor para retiros y/o depositos
    public Transaccion(TipoTransaccion tipo, double monto, int CVUorigen) {
        this.tipo = tipo; 
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.CVUorigen = CVUorigen;
        this.CVUdestino = 0;
    }

    // constructor esta vez para transferencias
    public Transaccion(double monto, int CVUorigen, int CVUdestino) {
        this.tipo = TipoTransaccion.TRANSFERENCIA;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.CVUorigen = CVUorigen;
        this.CVUdestino = CVUdestino;
    }

    //metodo para obtener la factura/detalle de cualquier transaccion
    public String getDetalle() {
    	//con este formatter cambiamos como se terminará mostrando la fecha y hora de la transaccion en cuestion, quedando mas prolijo y legible.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String detalle = "--- DETALLE DE UMBRELLA TRANSACCIÓN ---\n" +
                         "Tipo: " + this.tipo.name() + "\n" +
                         //con el string format logramos que solo se muestren dos decimales de la transaccion en cuestion, ahorrandonos el problema de mostrar numeros excesivamente largos o sin redondear.
                         "Monto: $" + String.format("%.2f", this.monto) + "\n" +
                         "Fecha y hora: " + this.fechaHora.format(formatter) + "\n" +
                         "CVU Origen: " + this.CVUorigen + "\n";
        
        //en el caso de ser una transferencia mostramos tambien el detalle de a qué cuenta se ha transferido, sino este bloque se omite
        if (this.CVUdestino != 0) {
            detalle += "CVU Destino: " + this.CVUdestino + "\n";
        }
        
        return detalle;
    }
}