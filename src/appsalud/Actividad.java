package appsalud;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Actividad implements Serializable{
    private final LocalDateTime fhInicio;
    private final LocalDateTime fhFin;
    private Duration duracionD;
    private final String duracion;
    private final float distancia;
    private double kcal;
    private final int fcMax;
    private int fcMedia;
    private final int fcMin;
    public Usuario usuario = Sesion.getUsuarioActual();
    
    /**
    * Constructor de la clase Actividad.
    * Inicializa una actividad deportiva con la fecha y hora de inicio y fin,
    * la distancia recorrida, y las frecuencias cardíacas máxima y mínima.
    * Calcula automáticamente la duración en formato horas:minutos:segundos,
    * la frecuencia cardíaca media y las calorías quemadas.
    * Además, muestra un mensaje de confirmación, añade la actividad al historial
    * del usuario, serializa los datos de usuarios, y abre el menú principal cerrando la ventana actual.
    *
    * @param fhInicio Fecha y hora de inicio de la actividad.
    * @param fhFin Fecha y hora de fin de la actividad.
    * @param distancia Distancia recorrida durante la actividad (en Km).
    * @param fcMax Frecuencia cardíaca máxima registrada durante la actividad.
    * @param fcMin Frecuencia cardíaca mínima registrada durante la actividad.
    */
    public Actividad(LocalDateTime fhInicio, LocalDateTime fhFin, 
            float distancia,int fcMax, int fcMin){
        this.fhInicio = fhInicio;
        this.fhFin = fhFin;
        this.duracionD = Duration.between(fhInicio, fhFin);
        long horas = duracionD.toHours();
        long minutos = duracionD.toMinutes() % 60;
        long segundos = duracionD.getSeconds() % 60;
        this.duracion =  String.format("%02d:%02d:%02d", horas, minutos, segundos);
        this.distancia = distancia;
        this.fcMax = fcMax;
        this.fcMin = fcMin;
        this.fcMedia = (fcMax+fcMin) / 2;
        this.kcal = calcularKcal(fcMedia, duracionD);
    }
    
    /**
     * Calcula las calorías quemadas durante la actividad física en función de la frecuencia cardíaca media
     * y la duración de la actividad, considerando el sexo, edad y peso del usuario.
     *
     * @param fcMedia la frecuencia cardíaca media durante la actividad.
     * @param duracionD la duración de la actividad como un objeto Duration.
     * @return la cantidad estimada de calorías quemadas durante la actividad.
     */
    protected final double calcularKcal(int fcMedia, Duration duracionD) {
        double minutos = (double) duracionD.getSeconds() / 60.0; // 
        double kcalC;
    
        if (usuario.getSexo() == 0) { // Varón
            kcalC = ((usuario.getEdad() * 2.2 * 0.2017)
                  - (usuario.getPeso() * 2.2 * 0.09036)
                  + (fcMedia * 0.6309)
                  - 55.0969) * minutos / 4.184;
        } else { // Hembra
            kcalC = ((usuario.getEdad() * 0.074)
                  - (usuario.getPeso() * 0.05741)
                  + (fcMedia * 0.4472)
                  - 20.4022) * minutos / 4.184;
        }
    
        return Math.max(0.0, kcalC); // <- fuerza a no devolver negativas
    }
    
    
    /**
     * Comprueba si los datos de una actividad son válidos.
     * Valida que: La frecuencia cardíaca máxima no sea menor que la mínima, la fecha y hora de inicio no sea posterior a la de fin, la distancia sea mayor que cero.
     * En caso de error, muestra un mensaje de error mediante un diálogo y no se crea la actividad.
     * @param fhInicio la fecha y hora de inicio de la actividad.
     * @param fhFin la fecha y hora de fin de la actividad.
     * @param distancia la distancia recorrida (debe ser mayor que 0).
     * @param fcMin la frecuencia cardíaca mínima.
     * @param fcMax la frecuencia cardíaca máxima.
     * @return {@code true} si todos los datos son válidos; {@code false} en caso contrario.
     */
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax){
        if(fcMax < fcMin){
            JOptionPane.showMessageDialog(null, "La frecuencia cardíaca máxima no puede ser inferior a la mínima.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(fhInicio.isAfter(fhFin)){
            JOptionPane.showMessageDialog(null, "El inicio no puede ser posterior al fin de la actividad.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (distancia <= 0){
            JOptionPane.showMessageDialog(null, "La distancia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (fhInicio.isEqual(fhFin)){
            JOptionPane.showMessageDialog(null, "Las fechas y horas de inicio y de fin no pueden ser iguales.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{return true;}
    }

    //Getters y setters para los atributos de la clase Actividad:

    /**
     * Devuelve la fecha y hora de inicio de la actividad.
     *
     * @return la fecha y hora de inicio (LocalDateTime).
     */
    public LocalDateTime getFhInicio() {
        return fhInicio;
    }

    /**
     * Devuelve la fecha y hora de fin de la actividad.
     *
     * @return la fecha y hora de fin (LocalDateTime).
     */
    public LocalDateTime getFhFin() {
        return fhFin;
    }

    /**
     * Obtiene la duración de la actividad en formato HH:mm:ss.
     *
     * @return la duración de la actividad como cadena de texto.
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Obtiene la distancia recorrida durante la actividad.
     * 
     * @return la distancia en kilómetros.
     */
    public float getDistancia() {
        return distancia;
    }

    /**
     * Obtiene las calorías quemadas durante la actividad.
     * 
     * @return la cantidad de calorías quemadas.
     */
    public double getKcal() {
        return kcal;
    }

    /**
     * Obtiene la frecuencia cardíaca máxima registrada durante la actividad.
     * 
     * @return la frecuencia cardíaca máxima.
     */
    public int getFcMax() {
        return fcMax;
    }

    /**
     * Obtiene la frecuencia cardíaca media durante la actividad.
     * 
     * @return la frecuencia cardíaca media.
     */
    public int getFcMedia() {
        return fcMedia;
    }

    /**
     * Obtiene la frecuencia cardíaca mínima registrada durante la actividad.
     * 
     * @return la frecuencia cardíaca mínima.
     */
    public int getFcMin() {
        return fcMin;
    }

    /**
    * Establece la duración de la actividad como un objeto Duration.
    *
    * @param duracionD la duración de la actividad como un objeto Duration.
    */
    public void setDuracionD(Duration duracionD) {
        this.duracionD = duracionD;
    }

    /**
     * Obtiene la duración de la actividad como un objeto Duration.
     * 
     * @return la duración de la actividad como un objeto Duration.
     */
    public Duration getDuracionD() {
        return duracionD;
    }

    /**
     * Establece las calorías quemadas durante la actividad.
     *
     * @param double la cantidad de calorías quemadas.
     */
    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

}
