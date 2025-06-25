package appsalud;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

public class UsuarioPremium extends Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int tipoSuscripcion; //0 mensual, 1 anual, 2 indefinida
    private LocalDate fechaSuscripcion;
    
    public UsuarioPremium(Usuario usuario, int tipo, LocalDate fechaSuscripcion){
        super(usuario); //Constructor de copia
        this.tipoSuscripcion = tipo;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
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
    
}
