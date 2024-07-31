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
}
