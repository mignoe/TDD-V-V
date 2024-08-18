package functionalTests;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.processador.Conta;
import com.example.processador.Fatura;
import com.example.processador.ProcessadorContas;

public class TabelaDecisaoTest {

    private ProcessadorContas processador;
    private Fatura fatura;

    @BeforeEach
    void setup() {
        processador = new ProcessadorContas();
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20), 1500, "Fábio Maciel");
    }

    // Testa o caso em que um boleto é pago no dia da fatura e seu valor é suficiente para quitá-la. A fatura deve ser marcada como "PAGA".
    @Test
    void testBoletoEmDiaValorSuficientePaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 1500.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso em que um boleto é pago com atraso, mas a soma do valor do boleto com a multa de 10% é suficiente para pagar a fatura. A fatura deve ser marcada como "PAGA".
    @Test
    void testBoletoAtrasadoValorSuficienteComMultaPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 21), 1400.00, "BOLETO"));
        
        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso em que um pagamento com cartão de crédito foi realizado dentro do prazo de 15 dias antes da fatura, e o valor é suficiente para pagá-la. A fatura deve ser marcada como "PAGA".
    @Test
    void testCartaoCreditoDentroDoPrazoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 5), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso em que um pagamento com cartão de crédito foi realizado fora do prazo de 15 dias antes da fatura. Mesmo que o valor seja suficiente, a fatura deve ser marcada como "PENDENTE".
    @Test
    void testCartaoCreditoForaDoPrazoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 6), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }

    // Testa o caso em que uma transferência bancária foi realizada antes ou no dia da fatura e o valor é suficiente para quitá-la. A fatura deve ser marcada como "PAGA".
    @Test
    void testTransferenciaValorSuficientePaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 19), 1500.00, "TRANSFERENCIA_BANCARIA"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso em que uma transferência bancária foi realizada após a data da fatura. Mesmo que o valor seja suficiente, a fatura deve ser marcada como "PENDENTE".
    @Test
    void testTransferenciaDataAposFaturaPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 21), 1500.00, "TRANSFERENCIA_BANCARIA"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }
}

