package sistema;

import java.util.List;

public class LoteDeIngressos {
	 private int id;
	    private List<Ingresso> ingressos;
	    private double desconto; // Representado como percentual (ex: 15.0 para 15%)

	    public LoteDeIngressos(int id, List<Ingresso> ingressos, double desconto) {
	        this.id = id;
	        this.ingressos = ingressos;
	        if (desconto > 25.0) {
	            throw new IllegalArgumentException("Desconto máximo permitido é 25%");
	        }
	        this.desconto = desconto;
	    }
	    
	    public int getId() {
	        return id;
	    }

	    public List<Ingresso> getIngressos() {
	        return ingressos;
	    }

	    public double getDesconto() {
	        return desconto;
	    }
	    
	    public double calcularPrecoFinal(double precoNormal) {
	    	double total = 0;
	    	
	    	for (Ingresso ingresso : ingressos) {
	        	double precoBase = 0.0;
	        	
	        	switch (ingresso.getTipo()) {
	        	case VIP:
	        		precoBase = precoNormal * 2;
	        		break;
	        	case MEIA_ENTRADA:
	        		precoBase = precoNormal / 2;
	        		break;
	        	case NORMAL:
	        		precoBase = precoNormal;
	        		break;
	        	}
	        	
	        	if (ingresso.getTipo() != TipoIngresso.MEIA_ENTRADA) {
	        		precoBase -= precoBase * (desconto / 100);
	        	}
	        	
	        	total += precoBase;
	        }


	        return total;
	    }
}
