package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Running extends Actividad{
    private String ritmoMedio;
    private float elevacion;
    private int pasosTotales;
    private double cadencia;
    
    
    public Running(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, float elevacion, int pasosTotales){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.elevacion = elevacion;
        this.pasosTotales = pasosTotales;
        this.cadencia = pasosTotales / (duracion.getSeconds() / 60);
        //Obtener ritmo medio
        if (distancia > 0){
            this.ritmoMedio = String.format("%02d:%02d", (long)(duracion.getSeconds()/distancia) / 60, (long)(duracion.getSeconds()/distancia) % 60);
        } else{
            this.ritmoMedio = "00:00";
        }
    }
    
    @Override
    protected double calcularKcal(Usuario usuario, int fcMedia, Duration duracion){
        if(usuario.getSexo() == 0){//Varón
            return ((usuario.getEdad()*0.2017) - (usuario.getPeso()* 2.2 * 0.09036) + (fcMedia * 0.6309) - 55.0969) * (duracion.getSeconds() / 60) / 4.184 * 1.3;
        }else{ //Hembra
            return ((usuario.getEdad()*0.074) - (usuario.getPeso()* 2.2 * 0.05741) + (fcMedia * 0.4472) - 20.4022) * (duracion.getSeconds() / 60) / 4.184 * 1.3;
        }
    }
}
