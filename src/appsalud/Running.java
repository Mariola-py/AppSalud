package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Running extends Actividad{

    private String ritmoMedio;
    private float elevacion;
    private int pasosTotales;
    private double cadencia;

    /**
     * Constructor de Running.
     * Inicializa una actividad de Running con la fecha y hora de inicio y fin,
     * distancia recorrida, frecuencia cardíaca máxima y mínima, elevación acumulada y total de pasos.
     * Calcula la cadencia (pasos por minuto) y el ritmo medio (minutos por kilómetro).
     *
     * @param inicio     Fecha y hora de inicio de la actividad.
     * @param fin        Fecha y hora de fin de la actividad.
     * @param distancia  Distancia recorrida en kilómetros.
     * @param fcMax      Frecuencia cardíaca máxima durante la actividad.
     * @param fcMin      Frecuencia cardíaca mínima durante la actividad.
     * @param elevacion  Elevación acumulada durante la actividad (en metros).
     * @param pasosTotales Total de pasos dados durante la actividad.
     */
    public Running(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, float elevacion, int pasosTotales){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.elevacion = elevacion;
        this.pasosTotales = pasosTotales;
        this.cadencia = pasosTotales / (getDuracionD().getSeconds() / 60);
        this.setKcal(this.getKcal() * 1.3);
        //Obtener ritmo medio
        if (distancia > 0){
            this.ritmoMedio = String.format("%02d:%02d", (long)(getDuracionD().getSeconds()/distancia) / 60, (long)(getDuracionD().getSeconds()/distancia) % 60);
        } else{
            this.ritmoMedio = "00:00";
        }
    }
    
    
    /**
     * Valida los datos de una actividad de running.
     * Comprueba que el número de pasos y la elevación sean mayores que cero,
     * y llama a la validación base de la clase Actividad para otros parámetros.
     * 
     * @param fhInicio  Fecha y hora de inicio de la actividad.
     * @param fhFin     Fecha y hora de fin de la actividad.
     * @param distancia Distancia recorrida en metros o kilómetros.
     * @param fcMin     Frecuencia cardíaca mínima registrada.
     * @param fcMax     Frecuencia cardíaca máxima registrada.
     * @param elevacion Elevación total durante la actividad.
     * @param numPasos  Número total de pasos dados.
     * @return true si la actividad es válida, false en caso contrario.
     */
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax, float elevacion, int numPasos){
        
        if(numPasos <= 0){
            JOptionPane.showMessageDialog(null, "El número de pasos debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(elevacion <= 0){
            JOptionPane.showMessageDialog(null, "La elevación debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return Actividad.actividadValida(fhInicio, fhFin, distancia, fcMin, fcMax);
    }

    /**
     * Obtiene el ritmo medio de la actividad de running en formato mm:ss.
     *
     * @return Ritmo medio como cadena de texto (minutos:segundos).
     */
    public String getRitmoMedio() {
        return ritmoMedio;
    }

    /**
     * Obtiene la elevación total acumulada durante la actividad.
     *
     * @return Elevación en unidades de medida correspondientes (por ejemplo metros).
     */
    public float getElevacion() {
        return elevacion;
    }

    /**
     * Obtiene el número total de pasos dados en la actividad.
     *
     * @return Número total de pasos.
     */
    public int getPasosTotales() {
        return pasosTotales;
    }

    /**
     * Obtiene la cadencia de la actividad, calculada como pasos por minuto.
     *
     * @return Cadencia en pasos por minuto.
     */
    public double getCadencia() {
        return cadencia;
    }  
}
