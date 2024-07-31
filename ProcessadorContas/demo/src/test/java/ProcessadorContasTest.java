
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.processador.Fatura;
import com.example.processador.ProcessadorContas;

public class ProcessadorContasTest {

    private ProcessadorContas processador;
    private Fatura fatura;

    @BeforeEach
    void setup(){
        processador = new ProcessadorContas();
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20),1500,"Fábio Maciel");
    }

    @Test
    void testProcessarContasFaturaPagaBoleto(){

    }

    @Test
    void testProcessarContasFaturaPagaCartaoCreditoETransferenciaBancaria(){

    }

    @Test
    void testProcessarContasFaturaPendente(){
        
    }

}
