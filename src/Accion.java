public class Accion extends Activo{
   private String mercado;

    public Accion(String simbolo, String nombre, boolean esNegociable, String mercado) {
        super(simbolo, nombre, esNegociable);
        this.mercado = mercado;
    }

    public String getMercado() {
        return mercado;
    }

    @Override
    public String toString() {
        return "Accion{" +
                "mercado='" + mercado + '\'' +
                ", negociable=" + esNegociable +
                '}';
    }
}
