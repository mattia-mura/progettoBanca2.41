
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import classi.Portafoglio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    public class portafoglioTest {

        private Portafoglio portafoglio;

        @BeforeEach
        void setUp() {
            portafoglio = new Portafoglio(100.0); // Inizializza con 100.0 euro
        }

        @Test
        void testGetSchei() {
            assertEquals(100.0, portafoglio.getSchei(), 0.001);
        }

        @Test
        void testAumentaSchei() {
            portafoglio.aumentaSchei(50.0);
            assertEquals(150.0, portafoglio.getSchei(), 0.001);
        }

        @Test
        void testDecrementaSchei() {
            portafoglio.decrementaSchei(30.0);
            assertEquals(70.0, portafoglio.getSchei(), 0.001);
        }

        @Test
        void testDecrementaScheiSottoZero() {
            portafoglio.decrementaSchei(150.0);
            assertEquals(-50.0, portafoglio.getSchei(),0.001);
        }
    }

