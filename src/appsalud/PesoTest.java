package appsalud;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;
import appsalud.Peso;

public class PesoTest {

    @Test
    public void testConstructorYGetters() {
        // Creamos un objeto Peso con datos de prueba
        LocalDate fecha = LocalDate.of(2025, 7, 6);
        float pesoValor = 70.5f;
        String imc = "Normal";

        Peso peso = new Peso(fecha, pesoValor, imc);

        // Comprobamos que los getters devuelven lo que pusimos en el constructor
        assertEquals(fecha, peso.getFecha());
        assertEquals(pesoValor, peso.getPeso(), 0.001);  // delta para float
        assertEquals(imc, peso.getIMC());
    }
}
