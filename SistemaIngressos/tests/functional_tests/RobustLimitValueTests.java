package functional_tests;


import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.After;
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

public class RobustLimitValueTests {
	private Show show;
	int counter_id = 1;
    private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;
    private LoteDeIngressos lote;
    
    private Ingresso[] criarNIngressos(int nVips, int nMeias, int nNormais) {
    	int total = nVips + nMeias + nNormais;
    	
    	Ingresso[] ingressos = new Ingresso[total];
    	int index = 0;
    	
    	for (int i = 0; i < nVips; i++) {    		
    		ingressos[index++] = new Ingresso(counter_id++, TipoIngresso.VIP);
    	}
    	
    	for (int i = 0; i < nMeias; i++) {    		
    		ingressos[index++] = new Ingresso(counter_id++, TipoIngresso.MEIA_ENTRADA);
    	}
    	
    	for (int i = 0; i < nNormais; i++) {    		
    		ingressos[index++] = new Ingresso(counter_id++, TipoIngresso.NORMAL);
    	}
    	
    	
    	return ingressos;
    }
    
    @Before
    public void setUp() {
    	ingressoVIP = new Ingresso(counter_id++, TipoIngresso.VIP);
        ingressoNormal = new Ingresso(counter_id++, TipoIngresso.NORMAL);
        ingressoMeiaEntrada = new Ingresso(counter_id++, TipoIngresso.MEIA_ENTRADA);

        
        show = new Show("Amanhã", "Artista Famoso", 1000.0, 2000.0, true, 100);
        show.adicionarLote(lote);

        // Marcar todos os ingressos como vendidos
        ingressoVIP.marcarComoVendido();
        ingressoNormal.marcarComoVendido();
        ingressoMeiaEntrada.marcarComoVendido();
    }
    
    @After
    public void breadDown() {
    	show = null;
    }

    @Test
    public void testVIP19Porcento() {
    	Ingresso[] ingressos = criarNIngressos(19, 10, 71);
    	lote = new LoteDeIngressos(1,Arrays.asList(ingressos), 15.0);
        assertEquals("PREJUÍZO", show.getStatusFinanceiro());
    }
    
}