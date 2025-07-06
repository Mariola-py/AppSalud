package appsalud;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

/**
 * Representa un usuario del sistema con sus datos personales, historial de pesos,
 * historial de actividades físicas y otros atributos relacionados.
 * Implementa Serializable para permitir la serialización del objeto.
 */
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
    private int factorActividad; //*0 Sedentario (1.2), 1 Ligera (1.375), 2 Moderada (1.55), 3 Intensa (1.725), 4 Atletas (1.9)
    
    /**
     * Constructor principal para crear un usuario con todos sus datos iniciales.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param nombre Nombre real del usuario.
     * @param apellidos Apellidos del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param altura Altura del usuario en centímetros.
     * @param sexo Sexo del usuario (0 para varón, 1 para hembra).
     * @param peso Peso inicial del usuario.
     * @param factorActividad Nivel de actividad física (0 a 4).
     */
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
    
     /**
     * Constructor de copia que crea un nuevo usuario copiando los datos de otro.
     * 
     * @param otro Usuario del cual copiar los datos.
     */
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

    /**
     * Calcula y devuelve un mensaje con el índice de masa corporal (IMC)
     * basado en el peso proporcionado.
     * 
     * @param peso Peso para calcular el IMC.
     * @return String con el valor del IMC y una interpretación del estado corporal.
     */
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
     * Valida si la contraseña proporcionada cumple con los requisitos mínimos:
     * al menos 6 caracteres, contiene letras y números.
     * 
     * @param password Contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
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

    /**
     * Registra un nuevo peso en el historial del usuario.
     * Ordena el historial cronológicamente y actualiza el atributo de peso actual.
     * 
     * @param nuevoPeso Objeto de tipo Peso a registrar.
     */
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
     * Obtiene el nombre de usuario.
     * 
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el peso del usuario.
     * 
     * @param peso Nuevo peso a establecer.
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Nueva contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la edad del usuario.
     * 
     * @return Edad.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el sexo del usuario.
     * 0 para varón, 1 para hembra.
     * 
     * @return Sexo.
     */
    public int getSexo() {
        return sexo;
    }

    /**
     * Obtiene el peso actual del usuario.
     * 
     * @return Peso.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Obtiene el historial de pesos del usuario.
     * 
     * @return Lista de objetos Peso.
     */
    public ArrayList<Peso> getHistorialPesos() {
        return historialPesos;
    }

    /**
     * Obtiene el historial de actividades del usuario.
     * 
     * @return Lista de actividades realizadas.
     */
    public ArrayList<Actividad> getHistorialActividades() {
        return historialActividades;
    }

    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return Apellidos.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     * 
     * @return Fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene la altura del usuario en centímetros.
     * 
     * @return Altura.
     */
    public float getAltura() {
        return altura;
    }

    /**
     * Obtiene el nivel de actividad física del usuario.
     * 
     * @return Factor de actividad.
     */
    public int getFactorActividad() {
        return factorActividad;
    }
}

