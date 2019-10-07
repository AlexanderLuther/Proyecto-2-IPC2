package Backend.DummyClases;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author helmuthluther
 */
public class Pago {
    
    private int idPago;
    private int idPublicacion;
    private double monto;
    private Timestamp fechaPago;

    public Pago(int idPago, int idPublicacion, double monto, Timestamp fechaPago) {
        this.idPago = idPago;
        this.idPublicacion = idPublicacion;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }
    
    public Pago(HttpServletRequest request) {
        this.idPago = Integer.parseInt(request.getAttribute("idPago").toString());
        this.idPublicacion = Integer.parseInt(request.getAttribute("idPublicacion").toString());;
        this.monto = Double.parseDouble(request.getAttribute("monto").toString());;
        this.fechaPago = Timestamp.valueOf(request.getAttribute("fechaPago").toString());
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    
    
}
