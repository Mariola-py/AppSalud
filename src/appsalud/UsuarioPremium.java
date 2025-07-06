package appsalud;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Representa un usuario con una suscripción premium.
 * Hereda de la clase Usuario y añade información relacionada con la suscripción.
 */
public class UsuarioPremium extends Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int tipoSuscripcion; //0 mensual, 1 anual, 2 indefinida
    private LocalDate fechaSuscripcion;
    
     /**
     * Crea un nuevo usuario premium a partir de un usuario base.
     * 
     * @param usuario El usuario base del que se heredan los datos.
     * @param tipo El tipo de suscripción (0: mensual, 1: anual, 2: indefinida).
     * @param fechaSuscripcion La fecha de inicio de la suscripción.
     */
    public UsuarioPremium(Usuario usuario, int tipo, LocalDate fechaSuscripcion){
        super(usuario); //Constructor de copia
        this.tipoSuscripcion = tipo;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    /**
     * Verifica si el usuario todavía tiene activa su suscripción premium.
     * 
     * @return true si la suscripción está activa; false en caso contrario.
     */
    public boolean esPremium(){
        if (tipoSuscripcion == 2) return true;
        LocalDate vencimiento = fechaSuscripcion;
        switch (tipoSuscripcion) {
            case 0: // Mensual
                vencimiento = vencimiento.plusMonths(1);
                break;
            case 1: // Anual
                vencimiento = vencimiento.plusYears(1);
                break;
        }
        return LocalDate.now().isBefore(vencimiento);
    }
    
    /**
     * Valida la contraseña de un usuario premium.
     * Debe tener al menos 8 caracteres, incluir una mayúscula, un número y un carácter especial.
     * 
     * @param password La contraseña a validar.
     * @return true si cumple los requisitos; false en caso contrario.
     */
    public static boolean validarPasswordPremium(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (!Character.isLetterOrDigit(c)) {
                tieneEspecial = true;
            }
        }
        return tieneMayuscula && tieneNumero && tieneEspecial;
    }

     /**
     * Renueva la suscripción desde la fecha actual.
     * Se asume que la suscripción está caducada al llamar a este método.
     */
    public void renovarSuscripcion() {

        LocalDate hoy = LocalDate.now();
        fechaSuscripcion = hoy;
        
    }
    
}
