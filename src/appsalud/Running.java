package appsalud;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Running extends Actividad{

    /**
     * @return the ritmoMedio
     */
    public String getRitmoMedio() {
        return ritmoMedio;
    }

    /**
     * @return the elevacion
     */
    public float getElevacion() {
        return elevacion;
    }

    /**
     * @return the pasosTotales
     */
    public int getPasosTotales() {
        return pasosTotales;
    }

    /**
     * @return the cadencia
     */
    public double getCadencia() {
        return cadencia;
    }
    private String ritmoMedio;
    private float elevacion;
    private int pasosTotales;
    private double cadencia;
    
    
    public Running(LocalDateTime inicio, LocalDateTime fin, float distancia, int fcMax, int fcMin, float elevacion, int pasosTotales){
        super(inicio, fin, distancia, fcMax, fcMin);
        this.elevacion = elevacion;
        this.pasosTotales = pasosTotales;
        this.cadencia = pasosTotales / (duracionD.getSeconds() / 60);
        //Obtener ritmo medio
        if (distancia > 0){
            this.ritmoMedio = String.format("%02d:%02d", (long)(duracionD.getSeconds()/distancia) / 60, (long)(duracionD.getSeconds()/distancia) % 60);
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
    
    public static boolean actividadValida(LocalDateTime fhInicio, LocalDateTime fhFin, float distancia, int fcMin, int fcMax, float elevacion, int numPasos){
        if(fcMax < fcMin){
            JOptionPane.showMessageDialog(null, "La frecuencia cardíaca máxima no puede ser inferior a la mínima.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(fhInicio.isAfter(fhFin)){
            JOptionPane.showMessageDialog(null, "El inicio no puede ser posterior al fin de la actividad.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (distancia <= 0){
            JOptionPane.showMessageDialog(null, "La distancia debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(numPasos <= 0){
            JOptionPane.showMessageDialog(null, "El número de pasos debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if(elevacion <= 0){
            JOptionPane.showMessageDialog(null, "La elevación debe ser mayor que cero.", "Error registrando actividad.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{return true;}
    }
}
