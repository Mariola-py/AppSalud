/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appsalud;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author garci
 */
public class UsuarioPremium extends Usuario {
    
    private int tipoSuscripcion; //0 mensual, 1 anual, 2 indefinida
    private Date fechaSuscripcion;
    
    public UsuarioPremium(Usuario usuario, int tipo, Date fechaSuscripcion){
        super(usuario); //Constructor de copia
        this.tipoSuscripcion = tipo;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    @Override
    public boolean esPremium(){
        if (tipoSuscripcion == 2) return true;
        
        Calendar ahora = Calendar.getInstance();
        Calendar vencimiento = Calendar.getInstance();
        vencimiento.setTime(fechaSuscripcion);
        
        switch (tipoSuscripcion){
            case 0: vencimiento.add(Calendar.MONTH, 1); break;
            case 1: vencimiento.add(Calendar.YEAR, 1); break;
        }
        
        return ahora.before(vencimiento);
    }
}
