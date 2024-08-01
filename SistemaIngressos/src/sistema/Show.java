package sistema;

import java.util.ArrayList;

import java.util.List;

public class Show {
	private String data;
    private String artista;
    private double cache;
    private double despesasInfraestrutura;
    private List<LoteDeIngressos> lotes;
    private boolean dataEspecial;
    private double precoIngressoNormal;

    public Show(String data, String artista, double cache, double despesasInfraestrutura, boolean dataEspecial, double precoIngressoNormal) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.lotes = new ArrayList<>();
        this.dataEspecial = dataEspecial;
        this.precoIngressoNormal = precoIngressoNormal;
    }
    

    public void adicionarLote(LoteDeIngressos lote) {
        this.lotes.add(lote);
    }

    public int getTotalIngressosVendidos(TipoIngresso tipo) {
        int total = 0;
        for (LoteDeIngressos lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getTipo() == tipo && ingresso.isVendido()) {
                    total++;
                }
            }
        }
        return total;
    }

    public double calcularReceita() {
        double receita = 0;
        for (LoteDeIngressos lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.isVendido()) {
                    receita += lote.calcularPrecoIngresso(ingresso, this.precoIngressoNormal);
                }
            }
        }
        return receita;
    }

    public double calcularLucro() {
        double receita = calcularReceita();
        double despesas = despesasInfraestrutura + cache;
        if (dataEspecial) {
            despesas += despesasInfraestrutura * 0.15;
        }
        return receita - despesas;
    }

    public String getStatusFinanceiro() {
        double lucro = calcularLucro();
        if (lucro > 0) {
            return "LUCRO";
        } else if (lucro == 0) {
            return "ESTÁVEL";
        } else {
            return "PREJUÍZO";
        }
    }
    
    public String getData() {
    	return this.data;
    }
    
    public String getArtista() {
    	return this.artista;
    }
}
