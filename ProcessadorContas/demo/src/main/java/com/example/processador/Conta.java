package com.example.processador;

import java.time.LocalDate;

public class Conta {

    private long codigo;
    private LocalDate data;
    private double valorPago;
    private String tipoPagamento;
    
    public Conta(long codigo, LocalDate data, double valorPago, String tipoPagamento) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
        this.tipoPagamento = tipoPagamento;
    }
    
    public Conta() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    
}
