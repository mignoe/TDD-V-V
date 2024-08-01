package tests;


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
    private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;
    private LoteDeIngressos lote;

    @Before
    public void setUp() {
    	ingressoVIP = new Ingresso(1, TipoIngresso.VIP);
        ingressoNormal = new Ingresso(2, TipoIngresso.NORMAL);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA);

        lote = new LoteDeIngressos(1, Arrays.asList(ingressoVIP, ingressoNormal, ingressoMeiaEntrada), 15.0);
        show = new Show("Amanhã", "Artista Famoso", 1000.0, 2000.0, true, 100);
        show.adicionarLote(lote);

        // Marcar todos os ingressos como vendidos
        ingressoVIP.marcarComoVendido();
        ingressoNormal.marcarComoVendido();
        ingressoMeiaEntrada.marcarComoVendido();
    }

    @Test
    public void testCriacaoShow() {
        assertNotNull(show);
        assertNotNull(show.getData());
        assertEquals("Amanhã", show.getData());
        assertNotNull(show.getArtista());
        assertEquals("Artista Famoso", show.getArtista());
    }
    
    @Test
    public void testGetTotalIngressosVendidos() {
        assertEquals(1, show.getTotalIngressosVendidos(TipoIngresso.VIP));
        assertEquals(1, show.getTotalIngressosVendidos(TipoIngresso.NORMAL));
        assertEquals(1, show.getTotalIngressosVendidos(TipoIngresso.MEIA_ENTRADA));
    }

    @Test
    public void testCalcularReceita() {
        assertEquals(305.0, show.calcularReceita(), 0.001);
    }

    @Test
    public void testCalcularLucro() {
        assertEquals(-2995.0, show.calcularLucro(), 0.001);
    }

    @Test
    public void testGetStatusFinanceiro() {
        assertEquals("PREJUÍZO", show.getStatusFinanceiro());
    }
}