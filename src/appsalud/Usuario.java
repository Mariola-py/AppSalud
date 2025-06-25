package appsalud;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;


public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private int edad;
    private int sexo; /*0 Varón, 1 Hembra*/
    private float peso;
    private float altura;
    private ArrayList<Peso> historialPesos;
    private ArrayList<Actividad> historialActividades;
    private int factorActividad; /*0 Sedentario (1.2), 1 Ligera (1.375), 2 Moderada (1.55), 3 Intensa (1.725), 4 Atletas (1.9)*/
    
    
    public Usuario(String username, String password, String nombre, String apellidos, LocalDate fechaNacimiento, float altura, int sexo, float peso, int factorActividad){
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
        this.historialActividades = otro.historialActividades;
        this.historialPesos = otro.historialPesos;
        
    }

    public String getIMC(float peso){
        float imc;
        imc = (peso / ((getAltura()/100)*(getAltura()/100)));
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
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validarPassword(String password){
        
        if (password.length() < 6) {
            return false;
        }
        boolean tieneLetra = false;
        boolean tieneNumero = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            }
            if (tieneLetra && tieneNumero) {
                return true;
            }
        }
        return false;
    }
    
    public void registrarPeso(Peso nuevoPeso){
        //Añadir peso al historial
        getHistorialPesos().add(nuevoPeso);
        //Ordenar el historial por orden cronológico
        getHistorialPesos().sort(Comparator.comparing(Peso::getFecha));
        //Actualizar el atributo peso con el valor más reciente
        Peso pesoUltimo = getHistorialPesos().get(getHistorialPesos().size()-1);
        this.peso = pesoUltimo.getPeso();
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @return the sexo
     */
    public int getSexo() {
        return sexo;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @return the historialPesos
     */
    public ArrayList<Peso> getHistorialPesos() {
        return historialPesos;
    }

    /**
     * @return the historialActividades
     */
    public ArrayList<Actividad> getHistorialActividades() {
        return historialActividades;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return the altura
     */
    public float getAltura() {
        return altura;
    }

    /**
     * @return the factorActividad
     */
    public int getFactorActividad() {
        return factorActividad;
    }
}

