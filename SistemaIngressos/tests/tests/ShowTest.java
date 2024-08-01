import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import sistema.Ingresso;
import sistema.TipoIngresso;
import sistema.LoteDeIngressos;
import sistema.Show;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class ShowTest {
    private Show show;

    @Before
    public void setUp() {
        show = new Show("31/07/2024", "Artista Famoso", 1000.0, 2000.0, true);
    }

    @Test
    public void testCriacaoShow() {
        assertNotNull(show);
        assertNotNull(show.getData());
        assertEquals("31/07/2024", show.getData());
        assertNotNull(show.getArtista());
        assertEquals("Artista Famoso", show.getArtista());
    }
}