package appsalud;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un registro de peso de un usuario en una fecha determinada.
 * Esta clase es serializable para permitir su almacenamiento y recuperación.
 * Contiene la fecha del registro, el valor del peso y el índice de masa corporal (IMC)
 * asociado a ese peso.
 * 
 * @author Mariola
 */
public class Peso implements Serializable{
    
	/**
     * Fecha en la que se registró el peso.
     */
    private LocalDate fecha;
    
    /**
     * Valor del peso registrado en kilogramos.
     */
    private float peso;
    
    /**
     * Índice de Masa Corporal (IMC) asociado al peso registrado, representado como cadena.
     */
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
