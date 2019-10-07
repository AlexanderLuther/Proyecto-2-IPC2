package Backend.DummyClases;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author helmuthluther
 */
public class Marcador {
    
    private int idRevista;
    private String contenido;
    private int tipo;

    public Marcador(HttpServletRequest request, int tipo) {
        this.idRevista = Integer.parseInt(request.getAttribute("idRevista").toString());
        this.tipo = tipo;
        if(this.tipo == 1) this.contenido = request.getParameter("categorias");
        else this.contenido = request.getParameter("etiquetas");
    }

    public Marcador(int idRevista, String contenido, int tipo) {
        this.idRevista = idRevista;
        this.contenido = contenido;
        this.tipo = tipo;
    }
    
    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
