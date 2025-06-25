package appsalud;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Actividad implements Serializable{
    private LocalDateTime fhInicio;
    private LocalDateTime fhFin;
    protected Duration duracionD;
    private String duracion;
    private float distancia;
    private double kcal;
    private int fcMax;
    private int fcMedia;
    private int fcMin;
    protected Usuario usuario = Sesion.getUsuarioActual();
    
    
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
        this.kcal = calcularKcal(usuario, fcMedia, duracionD);
        this.fcMax = fcMax;
        this.fcMedia = (fcMax+fcMin) / 2;
        this.fcMin = fcMin;
    }
    
    protected double calcularKcal(Usuario usuario, int fcMedia, Duration duracionD){
        if(usuario.getSexo() == 0){//Varón
            return ((usuario.getEdad()*0.2017) - (usuario.getPeso()* 2.2 * 0.09036) + (fcMedia * 0.6309) - 55.0969) * (duracionD.getSeconds() / 60) / 4.184;
        }else{ //Hembra
            return ((usuario.getEdad()*0.074) - (usuario.getPeso()* 2.2 * 0.05741) + (fcMedia * 0.4472) - 20.4022) * (duracionD.getSeconds() / 60) / 4.184;
        }
    }
    
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
        }else{return true;}
    }

    /**
     * @return the fhInicio
     */
    public LocalDateTime getFhInicio() {
        return fhInicio;
    }

    /**
     * @return the fhFin
     */
    public LocalDateTime getFhFin() {
        return fhFin;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @return the distancia
     */
    public float getDistancia() {
        return distancia;
    }

    /**
     * @return the kcal
     */
    public double getKcal() {
        return kcal;
    }

    /**
     * @return the fcMax
     */
    public int getFcMax() {
        return fcMax;
    }

    /**
     * @return the fcMedia
     */
    public int getFcMedia() {
        return fcMedia;
    }

    /**
     * @return the fcMin
     */
    public int getFcMin() {
        return fcMin;
    }
   
}
