package Backend.DummyClases;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author helmuthluther
 */
public class Comentario {
    
    private int idRevista;
    private int idComentario;
    private String nombreUsuario;
    private String comentario;
    private Timestamp fecha;

    public Comentario(int idRevista, int idComentario, String nombreUsuario, String comentario, Timestamp fecha) {
        this.idRevista = idRevista;
        this.idComentario = idComentario;
        this.nombreUsuario = nombreUsuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    
    public Comentario(HttpServletRequest request) {
        this.idRevista = Integer.parseInt(request.getAttribute("idRevista").toString());
        this.idComentario = Integer.parseInt(request.getAttribute("idComentario").toString());
        this.nombreUsuario = request.getSession().getAttribute("usuario").toString();
        this.comentario = request.getParameter("comentario");
        this.fecha = Timestamp.valueOf(request.getAttribute("fechaComentario").toString());
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
