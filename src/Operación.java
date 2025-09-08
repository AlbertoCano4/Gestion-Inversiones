import java.time.LocalDate;

public class Operación {
    private Activo activo;
    private LocalDate fecha;
    private double precio;
    private double comision;
    private TipoOperacion tipoOperacion;
    private double cantidad;


    public Operación(Activo activo, LocalDate fecha, double precio, double comision, TipoOperacion tipoOperacion, double cantidad) {
        this.activo = activo;
        this.fecha = fecha;
        this.precio = precio;
        this.comision = comision;
        this.tipoOperacion = tipoOperacion;
        this.cantidad = cantidad;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }


    public String toString() {
        return "Operación de " + tipoOperacion + ": El " + getFecha() + " " + cantidad + " de " + activo.simbolo +
                " a " + precio + "€. TOTAL: " + (cantidad * precio) + " €";
    }
}

