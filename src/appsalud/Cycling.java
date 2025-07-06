package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * Representa una actividad de ciclismo realizada por el usuario.
 * <p>
 * Esta clase hereda de {@link Actividad} e incluye atributos específicos del ciclismo como la
 * cadencia media en pedaladas por minuto. Al instanciarse, ajusta el cálculo de calorías
 * multiplicándolo por 1.2 respecto al valor base calculado en {@link Actividad}.
 * </p>
 * 
 * @author Mariola
 */
public class Cycling extends Actividad{

    /** Cadencia media en pedaladas por minuto (rpm) durante la actividad. */
    private float cadencia;
    
    /**
     * Constructor de la clase Cycling.
     *
     * @param inicio  Fecha y hora de inicio de la actividad.
     * @param fin     Fecha y hora de fin de la actividad.
     * @param distancia Distancia recorrida en la actividad.
     * @param fcMax   Frecuencia cardíaca máxima registrada.
     * @param fcMin   Frecuencia cardíaca mínima registrada.
     * @param cadencia Cadencia media durante la actividad (rpm).
     */
    public Cycling(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, float cadencia){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.setKcal(this.getKcal() * 1.2);
        this.cadencia = cadencia;
    }
    
    /**
     * Valida los parámetros de la actividad de ciclismo.
     *
     * @param fhInicio Fecha y hora de inicio.
     * @param fhFin    Fecha y hora de fin.
     * @param distancia Distancia recorrida.
     * @param fcMin    Frecuencia cardíaca mínima.
     * @param fcMax    Frecuencia cardíaca máxima.
     * @param cadencia Cadencia media durante la actividad.
     * @return true si los parámetros son válidos, false en caso contrario.
     */
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax,  float cadencia){
        if(cadencia <= 0){
            JOptionPane.showMessageDialog(null, "La cadencia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return Actividad.actividadValida(fhInicio, fhFin, distancia, fcMin, fcMax);
    }

    /**
     * Obtiene la cadencia media de la actividad.
     *
     * @return cadencia media en pedaladas por minuto.
     */
    public float getCadencia() {
        return cadencia;
    }
    
}
