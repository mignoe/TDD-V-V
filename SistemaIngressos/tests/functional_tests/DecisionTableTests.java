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
import sistema.StatusVendaShow;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;



import org.junit.jupiter.api.BeforeEach;

public class DecisionTableTests {
	private Show show;
	int counter_id = 1;
    private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;
    private LoteDeIngressos lote;
    
   
    
    @Before
    public void setUp() {     
        double custosInfra = 1000;
        
        show = new Show("Amanhã", "Artista Famoso", 1000.0, custosInfra, true, 100);

    }
    
    @After
    public void breadDown() {
    	show = null;
    }

    @Test
    public void testEhDataEspecial() {
        double custosInfra = 1000;
        boolean ehDataEspecial = true;
        
        show = new Show("Amanhã", "Artista Famoso", 1000.0, custosInfra, ehDataEspecial, 100);
        
        assertEquals(1150, show.getCustoInfra());
    }
    
    @Test
    public void testNaoEhDataEspecial() {
        double custosInfra = 1000;
        boolean ehDataEspecial = false;
        
        show = new Show("Amanhã", "Artista Famoso", 1000.0, custosInfra, ehDataEspecial, 100);
        
        assertEquals(1000, show.getCustoInfra());
    }
}