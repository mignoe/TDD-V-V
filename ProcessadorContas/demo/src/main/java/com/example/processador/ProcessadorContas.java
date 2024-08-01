package com.example.processador;

import java.util.List;
import java.time.LocalDate;

public class ProcessadorContas {

    public ProcessadorContas() {
    }

    public void processar(Fatura fatura) {
        double somaPagamentos = 0;

        List<Conta> contas = fatura.getContas();
        for (Conta conta : contas) {
            Pagamento pagamento = new Pagamento(conta.getValorPago(), conta.getData(), conta.getTipoPagamento());

            switch (pagamento.getTipoPagamento()) {
                case "BOLETO":
                    somaPagamentos += processarBoleto(pagamento, fatura.getData());
                    break;
                case "CARTAO_CREDITO":
                    somaPagamentos += processarCartaoCredito(pagamento, fatura.getData());
                    break;
                case "TRANSFERENCIA_BANCARIA":
                    somaPagamentos += processarTransferenciaBancaria(pagamento, fatura.getData());
                    break;
                default:
                    break;
            }
        }

        if (somaPagamentos >= fatura.getValorTotal()) {
            fatura.setStatus("PAGA");
        } else {
            fatura.setStatus("PENDENTE");
        }
    }

    private double processarBoleto(Pagamento pagamento, LocalDate dataFatura) {
        double valorPago = pagamento.getValorPago();
        if (valorPago >= 0.01 && valorPago <= 5000.00) {
            if (pagamento.getData().isAfter(dataFatura)) {
                valorPago *= 1.10;
            }
            return valorPago;
        }
        return 0;
    }

    private double processarCartaoCredito(Pagamento pagamento, LocalDate dataFatura) {
        if (pagamento.getData().isBefore(dataFatura.minusDays(15)) || pagamento.getData().isEqual(dataFatura.minusDays(15))) {
            return pagamento.getValorPago();
        }
        return 0;
    }

    private double processarTransferenciaBancaria(Pagamento pagamento, LocalDate dataFatura) {
        if (!pagamento.getData().isAfter(dataFatura)) {
            return pagamento.getValorPago();
        }
        return 0;
    }
}
