package functionalTests;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.processador.Conta;
import com.example.processador.Fatura;
import com.example.processador.ProcessadorContas;

public class AnaliseValoresLimiteTest {

    private ProcessadorContas processador;
    private Fatura fatura;

    @BeforeEach
    void setup() {
        processador = new ProcessadorContas();
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20), 1500, "Fábio Maciel");
    }

    //Testa o caso onde o valor do boleto é 0,00, o que deve resultar em uma fatura pendente.
    @Test
    void testProcessarContasValorMinimoBoletoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 0.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }

    //Testa o caso onde o valor do boleto é o mínimo válido (0,01), o que deve resultar em uma fatura paga.
    @Test
    void testProcessarContasValorMinimoValidoBoletoPendente() {
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20), 0.01, "Fábio Maciel");
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 0.01, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    //Testa o caso onde o valor do boleto é o máximo permitido (5.000,00), o que deve resultar em uma fatura paga.
    @Test
    void testProcessarContasValorMaximoBoletoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 5000.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso onde o valor do boleto é acima do máximo permitido (5.000,01), o que deve resultar em uma fatura pendente.
    @Test
    void testProcessarContasValorAcimaMaximoBoletoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 5000.01, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }

    //Testa o caso onde o pagamento por cartão de crédito foi realizado dentro do prazo de 15 dias antes da data da fatura, o que deve resultar em uma fatura paga.
    @Test
    void testProcessarContasCartaoCreditoDentroDoPrazoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 5), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    // Testa o caso onde o pagamento por cartão de crédito foi realizado fora do prazo de 15 dias antes da data da fatura, o que deve resultar em uma fatura pendente.
    @Test
    void testProcessarContasCartaoCreditoForaDoPrazoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 6), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }
}