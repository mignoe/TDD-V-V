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

    public Show(String data, String artista, double cache, double despesasInfraestrutura, boolean dataEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.lotes = new ArrayList<>();
        this.dataEspecial = dataEspecial;
    }
    
    public String getData() {
    	return this.data;
    }
    
    public String getArtista() {
    	return this.artista;
    }
}
