package functionalTests;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.processador.Conta;
import com.example.processador.Fatura;
import com.example.processador.ProcessadorContas;

public class ParticoesEquivalenciaTest {

    private ProcessadorContas processador;
    private Fatura fatura;

    @BeforeEach
    void setup() {
        processador = new ProcessadorContas();
        fatura = new Fatura(LocalDate.of(2023, Month.FEBRUARY, 20), 1500, "Fábio Maciel");
    }

    // Testa o caso de um boleto dentro do prazo, mas cujo valor não é suficiente para pagar a fatura. O resultado esperado é que a fatura continue pendente.
    @Test
    void testProcessarContasBoletoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 500.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }

    //Testa o caso de um boleto dentro do prazo, cujo valor é suficiente para pagar a fatura. O resultado esperado é que a fatura seja marcada como paga.
    @Test
    void testProcessarContasBoletoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 20), 1500.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    //Testa o caso de um boleto pago com atraso e cujo valor ainda não é suficiente para pagar a fatura. O resultado esperado é que a fatura continue pendente.
    @Test
    void testProcessarContasBoletoAtrasadoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 21), 500.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }

    //Testa o caso de um boleto pago com atraso, com valor suficiente após o acréscimo da multa para pagar a fatura. O resultado esperado é que a fatura seja marcada como paga.
    @Test
    void testProcessarContasBoletoAtrasadoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 21), 500.00, "BOLETO"));
        fatura.adicionarConta(new Conta(002, LocalDate.of(2023, Month.FEBRUARY, 21), 1000.00, "BOLETO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    //Testa o caso de um pagamento por cartão de crédito dentro do prazo de 15 dias antes da fatura, onde o valor é suficiente para pagar a fatura. O resultado esperado é que a fatura seja marcada como paga.
    @Test
    void testProcessarContasCartaoCreditoPaga() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 1), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PAGA");
    }

    //Testa o caso de um pagamento por cartão de crédito fora do prazo de 15 dias antes da fatura, mesmo que o valor seja suficiente. O resultado esperado é que a fatura continue pendente.
    @Test
    void testProcessarContasCartaoCreditoPendente() {
        fatura.adicionarConta(new Conta(001, LocalDate.of(2023, Month.FEBRUARY, 10), 1500.00, "CARTAO_CREDITO"));

        processador.processar(fatura);
        assertEquals(fatura.getStatus(), "PENDENTE");
    }
}

