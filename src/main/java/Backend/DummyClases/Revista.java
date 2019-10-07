package Backend.DummyClases;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author helmuthluther
 */
public class Revista {
    
    private int idRevista;
    private String nombreUsuarioAutor;
    private String nombre;
    private String URLPDF;
    private String descripcion;
    private String autor;
    private int volumen;
    private double costoPorDia;
    private double cuotaSuscripcion;
    private boolean bloquearComentarios;
    private boolean bloquearMeGusta;
    private boolean bloquearSuscripciones;
    private Timestamp fechaCreacion;

    public Revista(int idRevista, String nombreUsuarioAutor, String nombre, String URLPDF, String descripcion, String autor, int volumen, double costoPorDia, double cuotaSuscripcion, boolean bloquearComentarios, boolean bloquearMeGusta, boolean bloquearSuscripciones, Timestamp fechaCreacion) {
        this.idRevista = idRevista;
        this.nombreUsuarioAutor = nombreUsuarioAutor;
        this.nombre = nombre;
        this.URLPDF = URLPDF;
        this.descripcion = descripcion;
        this.autor = autor;
        this.volumen = volumen;
        this.costoPorDia = costoPorDia;
        this.cuotaSuscripcion = cuotaSuscripcion;
        this.bloquearComentarios = bloquearComentarios;
        this.bloquearMeGusta = bloquearMeGusta;
        this.bloquearSuscripciones = bloquearSuscripciones;
        this.fechaCreacion = fechaCreacion;
    }

    public Revista(HttpServletRequest request, int tipo){
        this.idRevista = Integer.parseInt(request.getAttribute("idRevista").toString());
        this.nombreUsuarioAutor = request.getSession().getAttribute("usuario").toString();
        this.nombre = request.getParameter("nombre");
        this.URLPDF = "";
        this.descripcion = request.getParameter("descripcion");
        this.autor = request.getParameter("autor");
        this.volumen = Integer.parseInt(request.getParameter("volumen"));
        this.costoPorDia = Double.parseDouble(request.getAttribute("costo").toString());
        if(tipo == 1) this.cuotaSuscripcion = Double.parseDouble(request.getParameter("cuotaSuscripcion"));
        else this.cuotaSuscripcion = Double.parseDouble(request.getAttribute("cuotaSuscripcion").toString());
        if(!request.getParameter("comentarios").equals("true")) this.bloquearComentarios = true;
        else this.bloquearComentarios = false;
        if(!request.getParameter("suscripciones").equals("true")) this.bloquearSuscripciones = true;
        else this.bloquearSuscripciones = false;
        if(!request.getParameter("meGusta").equals("true")) this.bloquearMeGusta = true;
        else this.bloquearMeGusta = false;
        this.fechaCreacion = Timestamp.valueOf(request.getAttribute("fecha").toString());
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombreUsuarioAutor() {
        return nombreUsuarioAutor;
    }

    public void setNombreUsuarioAutor(String nombreUsuarioAutor) {
        this.nombreUsuarioAutor = nombreUsuarioAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getURLPDF() {
        return URLPDF;
    }

    public void setURLPDF(String URLPDF) {
        this.URLPDF = URLPDF;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public double getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(double costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public double getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(double cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    public boolean isBloquearComentarios() {
        return bloquearComentarios;
    }

    public void setBloquearComentarios(boolean bloquearComentarios) {
        this.bloquearComentarios = bloquearComentarios;
    }

    public boolean isBloquearMeGusta() {
        return bloquearMeGusta;
    }

    public void setBloquearMeGusta(boolean bloquearMeGusta) {
        this.bloquearMeGusta = bloquearMeGusta;
    }

    public boolean isBloquearSuscripciones() {
        return bloquearSuscripciones;
    }

    public void setBloquearSuscripciones(boolean bloquearSuscripciones) {
        this.bloquearSuscripciones = bloquearSuscripciones;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    } 
}
