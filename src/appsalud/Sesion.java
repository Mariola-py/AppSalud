package appsalud;

public class Sesion {
    
    private static Usuario usuarioActual;
    
    //Constructor privado para evitar instanciación
    private Sesion(){}
    
    public static void iniciarSesion(Usuario usuario){
        usuarioActual = usuario;
    }

    /**
     * @return the usuarioActual
     */
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
}
