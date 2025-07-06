package appsalud;

/**
 * Clase singleton que gestiona la sesión del usuario actual en la aplicación.
 * Permite iniciar sesión y obtener el usuario actualmente logueado.
 */
public class Sesion {
    
    /**
     * Usuario que está actualmente en sesión.
     */
    private static Usuario usuarioActual;
    
    //Constructor privado para evitar instanciación
    private Sesion(){}
    
    /**
    * Inicia la sesión estableciendo el usuario actual.
    * 
    * @param usuario Usuario que inicia sesión.
    */
    public static void iniciarSesion(Usuario usuario){
        usuarioActual = usuario;
    }

    /**
    * Obtiene el usuario que está actualmente en sesión.
    * 
    * @return Usuario actualmente logueado, o null si no hay sesión iniciada.
    */
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
}
