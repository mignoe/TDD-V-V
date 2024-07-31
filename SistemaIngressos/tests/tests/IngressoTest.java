package tests;
import sistema.Ingresso;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class IngressoTest {
	
	private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;

    @Before
    public void setUp() {
        ingressoVIP = new Ingresso(1, TipoIngresso.VIP);
        ingressoNormal = new Ingresso(2, TipoIngresso.NORMAL);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA);
    }
	
    @Test
    public void testCriacaoIngresso() {
        assertEquals(1, ingressoVIP.getId());
        assertEquals(TipoIngresso.VIP, ingressoVIP.getTipo());
        assertFalse(ingressoVIP.isVendido());

        assertEquals(2, ingressoNormal.getId());
        assertEquals(TipoIngresso.NORMAL, ingressoNormal.getTipo());
        assertFalse(ingressoNormal.isVendido());

        assertEquals(3, ingressoMeiaEntrada.getId());
        assertEquals(TipoIngresso.MEIA_ENTRADA, ingressoMeiaEntrada.getTipo());
        assertFalse(ingressoMeiaEntrada.isVendido());
    }
}
