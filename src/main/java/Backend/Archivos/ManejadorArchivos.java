package Backend.Archivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author helmuthluther
 */
public class ManejadorArchivos {
    
    private final String PATHFOTOPERFIL = "/home/helmuthluther/Documentos/Programas Desarrollados/NetBeansProjects/Java Web/SistemaWebDeRevistas/src/main/webapp/fotos-perfil";
    private final String PATHREVISTA = "/home/helmuthluther/Documentos/Programas Desarrollados/NetBeansProjects/Java Web/SistemaWebDeRevistas/src/main/webapp/revistas";
    private final String PATHFOTOPERFILRECUPERAR = "/SistemaWebDeRevistas/fotos-perfil/";
    private final String PATHREVISTARECUPERAR = "/SistemaWebDeRevistas/revistas/";
    private final String PATHPORDEFECTOFOTOPERFILRECUPERAR = "/SistemaWebDeRevistas/images/fotoPerfil";
    private Part archivo;
    private InputStream flujoDeEntrada;
    private String nombreArchivo;
    private String pathArchivoGuardar;
    private String pathArchivoRecuperar;
    private String pathDirectorio;
    private FileOutputStream flujoDeSalida;
    private File directorioAlmacenamiento;
    private int datosArchivo;
    
    public String guardarArchivo(int tipo, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        if(tipo == 1){
            pathDirectorio = PATHFOTOPERFIL;
            nombreArchivo = request.getParameter("nombreUsuario"); 
            archivo = request.getPart("fotoPerfil");
            pathArchivoRecuperar = PATHFOTOPERFILRECUPERAR + nombreArchivo;
        }
        else{
            pathDirectorio = PATHREVISTA;
            nombreArchivo = request.getAttribute("idRevista")+".pdf"; 
            archivo = request.getPart("revista");
            pathArchivoRecuperar = PATHREVISTARECUPERAR + nombreArchivo;
        }
        
        //Verificar si existe el directorio de almacenamiento, de lo contrario se crea. 
        directorioAlmacenamiento =  new File(pathDirectorio);
        if (!directorioAlmacenamiento.exists()) {
            directorioAlmacenamiento.mkdir();
        } 
        // Acceder al archivo cargado por el cliente. Si no se cargo ningun archivo se enviara el path de la imagen de perfil por defecto
        if(archivo.getSize() == 0 && tipo == 1){
            return PATHPORDEFECTOFOTOPERFILRECUPERAR;
        }
        
        
        flujoDeEntrada = archivo.getInputStream();
        
        //Crear un nuevo archivo y establecer su respectivo path
        pathArchivoGuardar = pathDirectorio + File.separator + nombreArchivo;
        flujoDeSalida = new FileOutputStream (pathArchivoGuardar);
     
        //Escribir los bytes de la imagen subida al archivo indicado
        datosArchivo = flujoDeEntrada.read();
        while (datosArchivo != -1) {
            flujoDeSalida.write(datosArchivo);
            datosArchivo = flujoDeEntrada.read();
        }
        flujoDeSalida.close();
        return pathArchivoRecuperar; 
    }
}
