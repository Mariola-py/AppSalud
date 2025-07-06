package appsalud;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase singleton que gestiona la lista de usuarios y usuarios premium.
 * Permite añadir, buscar, guardar y cargar usuarios desde archivos.
 */
public class UsuariosManager implements Serializable{

    private static final long serialVersionUID = 1L;

    /** Instancia única del singleton */
    private static UsuariosManager instance;
    /** Rutas de los archivos donde se guardan los usuarios */
    private static final String RUTA_ARCHIVO = "files/usuarios.ser";
    private static final String RUTA_ARCHIVO_PREMIUM = "files/usuariospremium.ser";
    /** Listas de usuarios y usuarios premium */
    private static ArrayList<Usuario> listaUsuarios;
    private static ArrayList<UsuarioPremium> listaUsuariosPremium;
    
    /**
     * Constructor privado para el patrón singleton.
     * Carga los usuarios normales desde el archivo correspondiente.
     */
    private UsuariosManager(){
        listaUsuarios = cargarUsuarios();
        listaUsuariosPremium = cargarUsuariosPremium();
    }
    
    /**
     * Método para obtener la instancia única del singleton.
     * Si no existe, la crea.
     * 
     * @return Instancia de UsuariosManager
     */
    public static UsuariosManager getInstance(){
        if (instance == null){
            instance = new UsuariosManager();
        }
        return instance;
    }
    
    
    /**
     * Guarda la lista de usuarios en un archivo.
     * Asegura que la carpeta 'files' exista antes de guardar.
     * Utiliza ObjectOutputStream para serializar la lista de usuarios.
     * 
     */
    public void guardarUsuarios() {
        File archivo = new File(RUTA_ARCHIVO);

        // Asegurarse de que la carpeta 'files' exista
        archivo.getParentFile().mkdirs();  // Crea la carpeta si no existe

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Siempre guarda también los premium (por si acaso)
        guardarUsuariosPremium();
    }
    
    /**
     * Guarda la lista de usuarios premium en un archivo.
     * Asegura que la carpeta 'files' exista antes de guardar.
     * Utiliza ObjectOutputStream para serializar la lista de usuarios premium.
     */
    public void guardarUsuariosPremium() {
        File archivo = new File(RUTA_ARCHIVO_PREMIUM);

        // Asegurarse de que la carpeta 'files' exista
        archivo.getParentFile().mkdirs();  // Crea la carpeta si no existe

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaUsuariosPremium);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carga la lista de usuarios desde un archivo.
     * Si el archivo no existe, devuelve una lista vacía.
     * Utiliza ObjectInputStream para deserializar la lista de usuarios.
     * 
     * @return Lista de usuarios cargada desde el archivo
     */
    public static ArrayList<Usuario> cargarUsuarios() {
        File file = new File(RUTA_ARCHIVO);
        if (!file.exists()) {
            return new ArrayList<>();  // Si no existe el archivo, devuelve lista vacía
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Carga la lista de usuarios premium desde un archivo.
     * Si el archivo no existe, devuelve una lista vacía.
     * Utiliza ObjectInputStream para deserializar la lista de usuarios premium.
     * 
     * @return Lista de usuarios premium cargada desde el archivo
     */
    public static ArrayList<UsuarioPremium> cargarUsuariosPremium() {
        File file = new File(RUTA_ARCHIVO_PREMIUM);
        if (!file.exists()) {
            return new ArrayList<>();  // Si no existe el archivo, devuelve lista vacía
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<UsuarioPremium>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Comprueba si un usuario premium ya existe en la lista de usuarios.
     * @param username Nombre de usuario a buscar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existeUsuario(String username){
        
        for(Usuario u: listaUsuarios){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Devuelve un usuario normal dado su nombre de usuario.
     *
     * @param username Nombre del usuario.
     * @return El usuario si se encuentra; null si no existe.
     */
    public Usuario dameUsuario(String username){
        
        if(existeUsuario(username)){
            for(Usuario u: listaUsuarios){
                if(u.getUsername().equalsIgnoreCase(username)){ 
                    return u;
                }
            }
        }
        return null;
    }

    /**
     * Devuelve un usuario premium dado su nombre de usuario.
     *
     * @param username Nombre del usuario.
     * @return El usuario premium si se encuentra; null si no existe.
     */
    public static UsuarioPremium dameUsuarioPremium(String username){
        
        if(existeUsuarioPremium(username)){
            for(UsuarioPremium u: listaUsuariosPremium){
                if(u.getUsername().equalsIgnoreCase(username)){ 
                    return u;
                }
            }
        }
        return null;
    }
    
    /**
     * Añade un nuevo usuario normal a la lista y guarda en archivo.
     *
     * @param u Usuario a añadir.
     */
    public void addUsuario(Usuario u){
        listaUsuarios.add(u);
        guardarUsuarios();
    }
    
    /**
     * Añade un nuevo usuario premium a la lista y guarda en archivo.
     *
     * @param u Usuario premium a añadir.
     */
    public void addUsuarioPremium(UsuarioPremium u){
        listaUsuariosPremium.add(u);
        guardarUsuariosPremium();
    }
    
    /**
     * Comprueba si un usuario ya existe en la lista de usuarios premium.
     * @param username Nombre de usuario a buscar.
     * @return true si el usuario premium existe, false en caso contrario.
     */
    public static boolean existeUsuarioPremium(String username){

        ArrayList<UsuarioPremium> listaUsuariosPremiumA = cargarUsuariosPremium();
        for(UsuarioPremium u: listaUsuariosPremiumA){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la lista de usuarios normales.
     *
     * @return Lista de usuarios.
     */
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Devuelve la lista de usuarios premium.
     *
     * @return Lista de usuarios premium.
     */
    public ArrayList<UsuarioPremium> getListaUsuariosPremium() {
        return listaUsuariosPremium;
    }
    
    
}
