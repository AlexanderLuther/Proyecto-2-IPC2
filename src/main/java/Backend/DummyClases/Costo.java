package Backend.DummyClases;

import java.sql.Timestamp;

/**
 *
 * @author helmuthluther
 */
public class Costo {
    
    private int idCosto;
    private int idRevista;
    private double costo;
    private Timestamp fecha;

    public Costo(int idCosto, int idRevista, double costo, Timestamp fecha) {
        this.idCosto = idCosto;
        this.idRevista = idRevista;
        this.costo = costo;
        this.fecha = fecha;
    }

    public int getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
