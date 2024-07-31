package sistema;

public class Ingresso {
	private int id;
    private TipoIngresso tipo;
    private boolean vendido;

    public Ingresso(int id, TipoIngresso tipo) {
        this.id = id;
        this.tipo = tipo;
        this.vendido = false;
    }
    
    public int getId() {
        return id;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public boolean isVendido() {
        return vendido;
    }
}
