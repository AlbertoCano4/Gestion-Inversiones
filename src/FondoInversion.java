public class FondoInversion extends Activo{
    String categoria;

    public FondoInversion(String simbolo, String nombre, boolean esNegociable, String categoria) {
        super(simbolo, nombre, esNegociable);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "FondoInversion{" +
                "categoria='" + categoria + '\'' +
                ", negociable=" + esNegociable +
                '}';
    }

}
