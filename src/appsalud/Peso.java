package appsalud;
import java.io.Serializable;
import java.time.LocalDate;

public class Peso implements Serializable{
    
    private LocalDate fecha;
    private float peso;
    private String IMC;
    
    public Peso(LocalDate fecha, float peso, String IMC){
        this.fecha = fecha;
        this.peso = peso;
        this.IMC = IMC;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }
    
    
}
