package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * Clase que representa una actividad de natación.
 * <p>
 * Esta clase hereda de {@link Actividad} y añade atributos específicos
 * relacionados con la natación como el número de largos realizados
 * y el tipo de natación (piscina o mar).
 * </p>
 *
 * @author Mariola
 */
public class Swimming extends Actividad{
    
    private int numLargos;
    private int tipoNatacion; //0 Piscina, 1 Mar
    
    /**
     * Constructor de la clase Swimming.
     *
     * @param inicio     Fecha y hora de inicio de la actividad.
     * @param fin        Fecha y hora de fin de la actividad.
     * @param distancia  Distancia recorrida en metros.
     * @param fcMax      Frecuencia cardíaca máxima durante la actividad.
     * @param fcMin      Frecuencia cardíaca mínima durante la actividad.
     * @param numLargos  Número de largos nadados.
     * @param tipoNatacion Tipo de natación realizada.
     */
    public Swimming(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, int numLargos, int tipoNatacion){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.numLargos = numLargos;
        this.tipoNatacion = tipoNatacion;
        this.setKcal(this.getKcal() * 1.1);

    }
    
    
    /**
     * Valida los datos de una actividad de natación.
     *
     * Comprueba que el número de largos sea mayor que cero y delega
     * la validación del resto de parámetros a la clase base Actividad.
     *
     * @param fhInicio Fecha y hora de inicio de la actividad.
     * @param fhFin Fecha y hora de fin de la actividad.
     * @param distancia Distancia recorrida en metros.
     * @param fcMin Frecuencia cardíaca mínima registrada.
     * @param fcMax Frecuencia cardíaca máxima registrada.
     * @param numLargos Número de largos realizados.
     * @return true si los datos son válidos, false en caso contrario.
     */
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax, int numLargos){
        if(numLargos <= 0){
            JOptionPane.showMessageDialog(null, "El número de largos debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return Actividad.actividadValida(fhInicio, fhFin, distancia, fcMin, fcMax);
    }

    /**
     * Obtiene el número de largos realizados en la actividad de natación.
     *
     * @return el número de largos.
     */
    public int getNumLargos() {
        return numLargos;
    }

    /**
     * Obtiene el tipo de natación de la actividad.
     *
     * @return el tipo de natación como un entero (por ejemplo, 1 para estilo libre, 2 para espalda, etc.).
     */
    public int getTipoNatacion() {
        return tipoNatacion;
    }
}
