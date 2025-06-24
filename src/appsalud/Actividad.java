package appsalud;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Actividad implements Serializable{
    protected LocalDateTime fhInicio;
    protected LocalDateTime fhFin;
    protected Duration duracion;
    protected float distancia;
    protected double kcal;
    protected int fcMax;
    protected int fcMedia;
    protected int fcMin;
    protected Usuario usuario;
    
    
    public Actividad(LocalDateTime fhInicio, LocalDateTime fhFin, 
            float distancia,int fcMax, int fcMin){
        this.fhInicio = fhInicio;
        this.fhFin = fhFin;
        this.duracion = Duration.between(fhInicio, fhFin);
        this.distancia = distancia;
        this.kcal = calcularKcal(usuario, fcMedia, duracion);
        this.fcMax = fcMax;
        this.fcMedia = (fcMax+fcMin) / 2;
        this.fcMin = fcMin;
    }
    
    protected double calcularKcal(Usuario usuario, int fcMedia, Duration duracion){
        if(usuario.getSexo() == 0){//Varón
            return ((usuario.getEdad()*0.2017) - (usuario.getPeso()* 2.2 * 0.09036) + (fcMedia * 0.6309) - 55.0969) * (duracion.getSeconds() / 60) / 4.184;
        }else{ //Hembra
            return ((usuario.getEdad()*0.074) - (usuario.getPeso()* 2.2 * 0.05741) + (fcMedia * 0.4472) - 20.4022) * (duracion.getSeconds() / 60) / 4.184;
        }
    }
   
 
}
