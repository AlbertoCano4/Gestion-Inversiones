import java.io.Serializable;

public class CuentaBancaria implements Serializable {
    private String entidad;
    private String iban;
    private static double cantidad;

    public CuentaBancaria(String entidad, String iban, double cantidad) throws Exception {

        if (cantidad < 0 || entidad == null || iban == null){
            throw new Exception();
        }
        this.entidad = entidad;
        this.iban = iban;
        this.cantidad = cantidad;
    }

    public String getEntidad() {

        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getIban() {

        return iban;
    }

    public void setNombre(String nombre) {

        this.iban = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {

        this.cantidad = cantidad;
    }

    static void ingresarDinero(double ingreso) {
        cantidad += ingreso;
    }

    static void retirarDeCuenta(double retiro) {
        cantidad -= retiro;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "entidad='" + entidad + '\'' +
                ", iban='" + iban + '\'' +
                ", cantidad='" + cantidad +
                '}';
    }
}
