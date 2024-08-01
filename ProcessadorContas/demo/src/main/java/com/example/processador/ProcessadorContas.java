package com.example.processador;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorContas {


    public ProcessadorContas(){

    }

    public void processar(Fatura fatura){
        double somaPagamentos = 0;

        List<Conta> contas = new ArrayList<>();
        contas = fatura.getContas();
        for (Conta conta: contas){
            Pagamento pagamento = new Pagamento(conta.getValorPago(),conta.getData(),conta.getTipoPagamento());

            if(pagamento.getTipoPagamento().equals("BOLETO")){
                if (pagamento.getValorPago() >= 0.01 && pagamento.getValorPago() <= 5000.00) {
                    if (pagamento.getData().isAfter(fatura.getData())) {
                        pagamento.setValorPago(pagamento.getValorPago() * 1.10);
                        somaPagamentos += pagamento.getValorPago();
                    }
                    else{
                        somaPagamentos += pagamento.getValorPago();
                    }
                }
            } else if (pagamento.getTipoPagamento().equals("CARTAO_CREDITO")) {
                if (conta.getData().isBefore(fatura.getData().minusDays(15)) || conta.getData().isEqual(fatura.getData().minusDays(15))) {
                    somaPagamentos += pagamento.getValorPago();
                }
            } else if (pagamento.getTipoPagamento().equals("TRANSFERENCIA_BANCARIA")) {
                if(!conta.getData().isAfter(fatura.getData())){
                    somaPagamentos += pagamento.getValorPago();
                }
            }
        }

        if(somaPagamentos >= fatura.getValorTotal()){
            fatura.setStatus("PAGA");
        }
        else{
            fatura.setStatus("PENDENTE");
        }
        
    }
    
}
