package Backend.Fechas;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author helmuthluther
 */
public class ManejadorFechas {
     
    private Timestamp fecha;
    private DateFormat formatter;
    private Date date;
    private Calendar calendar;
    
    public Timestamp convertirFecha(String fecha){
        try {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(fecha);
            this.fecha = new Timestamp(date.getTime());
        } catch (ParseException ex) {
            System.out.println("Error al dar formato a la fecha");
        }
        return this.fecha;
    }
    
    public Timestamp obtenerFechaFin(Timestamp fechaActual){
        calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, 1);
        fecha = new Timestamp(calendar.getTime().getTime());
        return fecha;
    }
    
    public boolean compararFechas(Timestamp fechaFin){
        fecha = new Timestamp(System.currentTimeMillis());
        if(fechaFin.equals(fecha)){
            return false;
        }
        return fecha.before(fechaFin);
    }
}
