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
    private StatusVendaShow status_venda_show;


    public Show(String data, String artista, double cache, double despesasInfraestrutura, boolean dataEspecial, double precoIngressoNormal) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.lotes = new ArrayList<>();
        this.dataEspecial = dataEspecial;
        this.precoIngressoNormal = precoIngressoNormal;
        this.status_venda_show = StatusVendaShow.VENDENDO;
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
    
    public LoteDeIngressos getLote(int i)  {
    	return lotes.get(i);
    }
    
    private boolean proporcaoTipoIngressoEstaCorreta() {
    	int meia_entrada = 0;
    	int vips = 0;
    	int normais = 0;
    	
    	for (LoteDeIngressos lote : this.lotes) {
    		System.out.println(lote);
            for (Ingresso ingresso : lote.getIngressos()) {
                switch (ingresso.getTipo()) {
                	case TipoIngresso.MEIA_ENTRADA:
                		meia_entrada += 1;
                		break;
                	case TipoIngresso.VIP: 
                		vips += 1;
                		break;
                	case TipoIngresso.NORMAL:
                		normais += 1;
                		break;
                }
            }
    	}
    	
    	int total = meia_entrada + vips + normais;
    	
    	double proporcaoVips = (double) vips /total;
    	double proporcaoMeia = (double) meia_entrada / total;
    	
    	if (proporcaoVips < 0.2 || proporcaoVips > 0.3 || proporcaoMeia != 0.1) return false;
    	
    	
    	return true;
             
    }
    
    public void fecharVendaDeIngressos() {
        if (this.proporcaoTipoIngressoEstaCorreta()) {
            this.status_venda_show = StatusVendaShow.FECHADO;
        } else {
            throw new RuntimeException("proporcao ingressos incorreta");
        }
    }
    
    public StatusVendaShow getStatusVendaShow() {
    	return this.status_venda_show;
    }
    
    public double getCustoInfra() {
    	if (dataEspecial) return 1.15 * despesasInfraestrutura;
    	
    	return despesasInfraestrutura;
    }
}
