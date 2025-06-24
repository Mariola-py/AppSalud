package appsalud;

import java.io.*;
import java.util.ArrayList;

public class UsuariosManager implements Serializable{

    private static final String RUTA_ARCHIVO = "files/usuarios.ser";
    private static final String RUTA_ARCHIVO_PREMIUM = "files/usuariospremium.ser";
    private static Usuario usuario;
    private static UsuarioPremium usuarioPremium;
    private static ArrayList<Usuario> listaUsuarios;
    private static ArrayList<UsuarioPremium> listaUsuariosPremium;
    

    // Guardar lista de usuarios en un archivo
    
    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) {
        File archivo = new File(RUTA_ARCHIVO);

        // Asegurarse de que la carpeta 'files' exista
        archivo.getParentFile().mkdirs();  // Crea la carpeta si no existe

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void guardarUsuariosPremium(ArrayList<UsuarioPremium> listaUsuariosP) {
        File archivo = new File(RUTA_ARCHIVO_PREMIUM);

        // Asegurarse de que la carpeta 'files' exista
        archivo.getParentFile().mkdirs();  // Crea la carpeta si no existe

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaUsuariosP);
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
    
    public static boolean existeUsuario(String username){
        
        listaUsuarios = cargarUsuarios();
        for(Usuario u: listaUsuarios){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean existeUsuarioPremium(Usuario usuario){
        
        listaUsuariosPremium = cargarUsuariosPremium();
        for(UsuarioPremium u: listaUsuariosPremium){
            if(u.getUsername().equalsIgnoreCase(usuario.getUsername())){
                return true;
            }
        }
        return false;
    }
}
