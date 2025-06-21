package appsalud;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;


public class Usuario {
    
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private int edad;
    private boolean sexo; /*0 Varón, 1 Hembra*/
    private float peso;
    private float altura;
    private ArrayList<Peso> historialPesos;
    private ArrayList<Actividad> historialActividades;
    private int factorActividad; /*0 Sedentario (1.2), 1 Ligera (1.375), 2 Moderada (1.55), 3 Intensa (1.725), 4 Atletas (1.9)*/
    
    
    public Usuario(String username, String password, String nombre, String apellidos, LocalDate fechaNacimiento, float altura, boolean sexo, float peso, int factorActividad){
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.factorActividad = factorActividad;
        this.historialActividades = new ArrayList<Actividad>();
        this.historialPesos = new ArrayList<Peso>();
        LocalDate hoy = LocalDate.now();
        this.edad = Period.between(fechaNacimiento, hoy).getYears();

    }
    
    //Constructor de copia
    
    public Usuario(Usuario otro){
        this.username = otro.username;
        this.password = otro.password;
        this.nombre = otro.nombre;
        this.apellidos = otro.apellidos;
        this.fechaNacimiento = otro.fechaNacimiento;
        this.edad = otro.edad;
        this.sexo = otro.sexo;
        this.peso = otro.peso;
        this.altura = otro.altura;
        this.factorActividad = otro.factorActividad;
        this.historialActividades = new ArrayList<Actividad>(otro.historialActividades);
        this.historialPesos = new ArrayList<Peso>(otro.historialPesos);
        
    }

    
    public boolean esPremium(){
        return false;
    }
    
    private String getIMC(){
        float imc;
        imc = (peso / (altura*altura));
        if (imc < 18.5){
            return ("IMC: "+imc+". Por debajo del peso recomendado.");
        }
        else if (imc < 25){
            return ("IMC: "+imc+". Estás en tu línea ;)");
        }
        else if(imc < 30){
            return ("IMC: "+imc+". Un poco por encima del peso recomendado.");
        }
        else {
            return ("IMC: "+imc+". Muy por encima del peso recomendado.");
        }
                
    }
}
