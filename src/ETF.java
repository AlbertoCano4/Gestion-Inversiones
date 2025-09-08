public class ETF extends Activo{
    String categoria;

    public ETF(String simbolo, String nombre, boolean esNegociable, String categoria) {
        super(simbolo, nombre, esNegociable);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "ETF { " +
                "categoria = " + categoria + '\'' +
                ", negociable = " + esNegociable +
                " }";
    }
}
