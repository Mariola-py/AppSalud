package appsalud;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class ActividadTest {

    @Before
    public void setUp() {
        // Crear un usuario de prueba
        Usuario usuarioPrueba = new Usuario("prueba", "prueba1234", "prueba", "prueba", LocalDate.of(2025, 7, 6), 175, 1, (float)60.5, 1);
        
        // Establecer el usuario actual en la sesión
        Sesion.iniciarSesion(usuarioPrueba);
    }

    @Test
    public void testConstructorYGetters() {
        LocalDateTime inicio = LocalDateTime.of(2025, 7, 6, 10, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 7, 6, 11, 0);

        Actividad act = new Actividad(inicio, fin, (float)10.5, 180, 70);

        // Comprobamos que los getters devuelven las fechas correctas
        assertEquals(inicio, act.getFhInicio());
        assertEquals(fin, act.getFhFin());

        // Comprobamos que la duración es correcta (en segundos)
        long duracionSegs = act.getDuracionD().getSeconds();
        assertEquals(3600, duracionSegs);  // 1 hora = 3600 segundos
    }

    @Test
    public void testCalculoKcal() {
        LocalDateTime inicio = LocalDateTime.of(2025, 7, 6, 10, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 7, 6, 11, 0);
        Duration duracionD = Duration.between(inicio, fin);
        
        Actividad act = new Actividad(inicio, fin, (float)10.5, 180, 70);
        // Llamamos a calcularKcal, que usa el usuario actual de la sesión
        double kcal = act.calcularKcal(act.getFcMedia(), duracionD);

        // Comprobamos que kcal no es 0 ni negativa (ajusta según tu lógica)
        assertTrue(kcal > 0);
    }
}
