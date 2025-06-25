package appsalud;

import java.io.*;
import java.util.ArrayList;

public class UsuariosManager implements Serializable{

    private static UsuariosManager instance;
    private static final String RUTA_ARCHIVO = "files/usuarios.ser";
    private static final String RUTA_ARCHIVO_PREMIUM = "files/usuariospremium.ser";
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<UsuarioPremium> listaUsuariosPremium;
    

    private UsuariosManager(){
        listaUsuarios = cargarUsuarios();
    }
    
    public static UsuariosManager getInstance(){
        if (instance == null){
            instance = new UsuariosManager();
        }
        return instance;
    }
    
    // Guardar lista de usuarios en un archivo
    
    public void guardarUsuarios() {
        File archivo = new File(RUTA_ARCHIVO);

        // Asegurarse de que la carpeta 'files' exista
        archivo.getParentFile().mkdirs();  // Crea la carpeta si no existe

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
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


    // Cargar lista de usuarios desde archivo
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
    
    public boolean existeUsuario(String username){
        
        for(Usuario u: listaUsuarios){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    
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
    
    public void addUsuario(Usuario u){
        listaUsuarios.add(u);
        guardarUsuarios();
    }
    
    public void addUsuarioPremium(UsuarioPremium u){
        listaUsuariosPremium.add(u);
        guardarUsuariosPremium();
    }
    
    
    public static boolean existeUsuarioPremium(Usuario usuario){

        ArrayList<UsuarioPremium> listaUsuariosPremiumA = cargarUsuariosPremium();
        for(UsuarioPremium u: listaUsuariosPremiumA){
            if(u.getUsername().equalsIgnoreCase(usuario.getUsername())){
                return true;
            }
        }
        return false;
    }

    /**
     * @return the listaUsuarios
     */
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @return the listaUsuariosPremium
     */
    public ArrayList<UsuarioPremium> getListaUsuariosPremium() {
        return listaUsuariosPremium;
    }
    
    
}
