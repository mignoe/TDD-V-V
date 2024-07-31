package tests;

import sistema.Ingresso;
import sistema.TipoIngresso;
import sistema.LoteDeIngressos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoteDeIngressosTest {
	
	private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;
    private LoteDeIngressos lote;
	
	@BeforeEach
	void setUp() throws Exception {
		ingressoVIP = new Ingresso(1, TipoIngresso.VIP);
        ingressoNormal = new Ingresso(2, TipoIngresso.NORMAL);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA);
        List<Ingresso> ingressos = Arrays.asList(ingressoVIP, ingressoNormal, ingressoMeiaEntrada);
        lote = new LoteDeIngressos(1, ingressos, 15.0); // 15% de desconto
	}

	 @Test
    public void testCriacaoLote() {
        assertEquals(1, lote.getId());
        assertNotNull(lote.getIngressos());
        assertEquals(3, lote.getIngressos().size());
        assertEquals(15.0, lote.getDesconto());
    }
	 
	 @Test
	 public void testGetIngressos() {
	     assertEquals(3, lote.getIngressos().size());
	     assertEquals(TipoIngresso.NORMAL, lote.getIngressos().get(0).getTipo());
	     assertEquals(TipoIngresso.VIP, lote.getIngressos().get(1).getTipo());
	     assertEquals(TipoIngresso.MEIA_ENTRADA, lote.getIngressos().get(2).getTipo());
	 }
	 
	 @Test
	 public void testGetDesconto() {
	     assertEquals(15.0, lote.getDesconto());
	 }
}
