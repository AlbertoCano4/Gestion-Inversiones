import java.time.LocalDate;

public abstract class Activo extends Cartera{

    String simbolo;
    String nombre;
    boolean esNegociable;
    public Activo(String simbolo, String nombre, boolean esNegociable) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.esNegociable = esNegociable;
    }
    public String getSimbolo(){
        return simbolo;
    }
    public String getNombre(){
        return nombre;
    }
    public boolean esNegociable(){
        return esNegociable;
    }
    public void setNegociable(boolean negociable){
        this.esNegociable = negociable;
    }


}
