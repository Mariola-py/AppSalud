package appsalud;
import java.io.Serializable;
import java.time.LocalDate;

public class Peso implements Serializable{
    
    private LocalDate fecha;
    private float peso;
    private String IMC;
    
    /**
     * Constructor de la clase Peso.
     * 
     * @param fecha Fecha del registro de peso.
     * @param peso Valor del peso registrado.
     * @param IMC Índice de Masa Corporal asociado al peso.
     */
    public Peso(LocalDate fecha, float peso, String IMC){
        this.fecha = fecha;
        this.peso = peso;
        this.IMC = IMC;
    }

    /**
     * Obtiene la fecha del registro de peso.
     * 
     * @return Fecha del registro.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el valor del peso registrado.
     * 
     * @return Peso en kilogramos.
     */
    public float getPeso() {
        return peso;
    }
    
    /**
     * Obtiene el Índice de Masa Corporal asociado al peso.
     * 
     * @return Índice de Masa Corporal.
     */
    public String getIMC() {
        return IMC;
    }
    
}
