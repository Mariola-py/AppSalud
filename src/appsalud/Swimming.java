package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

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
    
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax, int numLargos){
        if(fcMax < fcMin){
            JOptionPane.showMessageDialog(null, "La frecuencia cardíaca máxima no puede ser inferior a la mínima.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(fhInicio.isAfter(fhFin)){
            JOptionPane.showMessageDialog(null, "El inicio no puede ser posterior al fin de la actividad.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (distancia <= 0){
            JOptionPane.showMessageDialog(null, "La distancia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(numLargos <= 0){
            JOptionPane.showMessageDialog(null, "El número de largos debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else{return true;}
    }

    /**
     * @return the numLargos
     */
    public int getNumLargos() {
        return numLargos;
    }

    /**
     * @return the tipoNatacion
     */
    public int getTipoNatacion() {
        return tipoNatacion;
    }
}
