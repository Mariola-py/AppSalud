package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Cycling extends Actividad{

    /**
     * @return the cadencia
     */
    public float getCadencia() {
        return cadencia;
    }
    
    private float cadencia;
    
    public Cycling(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, float cadencia){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.cadencia = cadencia;
    }
    
    @Override
    protected double calcularKcal(Usuario usuario, int fcMedia, Duration duracion){
        if(usuario.getSexo() == 0){//Varón
            return ((usuario.getEdad()*0.2017) - (usuario.getPeso()* 2.2 * 0.09036) + (fcMedia * 0.6309) - 55.0969) * (duracion.getSeconds() / 60) / 4.184 * 1.2;
        }else{ //Hembra
            return ((usuario.getEdad()*0.074) - (usuario.getPeso()* 2.2 * 0.05741) + (fcMedia * 0.4472) - 20.4022) * (duracion.getSeconds() / 60) / 4.184 * 1.2;
        }
    }
    
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax,  float cadencia){
        if(fcMax < fcMin){
            JOptionPane.showMessageDialog(null, "La frecuencia cardíaca máxima no puede ser inferior a la mínima.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(fhInicio.isAfter(fhFin)){
            JOptionPane.showMessageDialog(null, "El inicio no puede ser posterior al fin de la actividad.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (distancia <= 0){
            JOptionPane.showMessageDialog(null, "La distancia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(cadencia <= 0){
            JOptionPane.showMessageDialog(null, "La cadencia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else{return true;}
    }
    
}
