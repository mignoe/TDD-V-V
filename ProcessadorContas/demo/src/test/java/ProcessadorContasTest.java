
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.processador.Conta;
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
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 500, "BOLETO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 20), 400, "BOLETO"));
        fatura.adicionarConta(new Conta(003, LocalDate.of(2023, Month.FEBRUARY, 20), 600, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PAGA");
    }

    @Test
    void testProcessarContasBoletoValorAlto(){
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20),6000,"Fábio Maciel");

        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 5500, "BOLETO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 20), 500, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PENDENTE");
    }

    @Test
    void testProcessarContasFaturaPagaCartaoCreditoETransferenciaBancaria(){
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 5), 700, "CARTAO_CREDITO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 17), 800, "TRANSFERENCIA_BANCARIA"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PAGA");
    }

    @Test
    void testProcessarContasFaturaPendentePorDataCartaoCredito(){
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 6), 700, "CARTAO_CREDITO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 17), 800, "TRANSFERENCIA_BANCARIA"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PENDENTE");
    }

    @Test
    void testProcessarContasFaturaPendentePorTransferenciaBancaria(){
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 5), 700, "CARTAO_CREDITO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 21), 800, "TRANSFERENCIA_BANCARIA"));
        
        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PENDENTE");
    
    }

    @Test
    void testProcessarContasFaturaPendentePorValor(){
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 400, "BOLETO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 20), 400, "BOLETO"));
        fatura.adicionarConta(new Conta(003, LocalDate.of(2023, Month.FEBRUARY, 20), 600, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(),"PENDENTE");
    }

}
