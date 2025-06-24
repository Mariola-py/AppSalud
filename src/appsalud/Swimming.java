package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;

public class Swimming extends Actividad{
    
    private int numLargos;
    private int tipoNatacion; //0 Piscina, 1 Mar
    
    public Swimming(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, int numLargos, int tipoNatacion){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.numLargos = numLargos;
        this.tipoNatacion = tipoNatacion;
    }
    
    @Override
    protected double calcularKcal(Usuario usuario, int fcMedia, Duration duracion){
        if(usuario.getSexo() == 0){//Varón
            return ((usuario.getEdad()*0.2017) - (usuario.getPeso()* 2.2 * 0.09036) + (fcMedia * 0.6309) - 55.0969) * (duracion.getSeconds() / 60) / 4.184 * 1.1;
        }else{ //Hembra
            return ((usuario.getEdad()*0.074) - (usuario.getPeso()* 2.2 * 0.05741) + (fcMedia * 0.4472) - 20.4022) * (duracion.getSeconds() / 60) / 4.184 * 1.1;
        }
    }
    
}
