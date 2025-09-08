import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Cartera implements Serializable {
    private String nombre;
    ArrayList<Operación> operaciones;
    private CuentaBancaria cuentaAsociada;

    public Cartera(String nombre, CuentaBancaria cuentaAsociada) {
        this.nombre = nombre;
        this.cuentaAsociada = cuentaAsociada;
        operaciones = new ArrayList<>();
    }

    public CuentaBancaria getCuentaAsociada() {
        return cuentaAsociada;
    }

    public Cartera() {
    }

    public String getNombre() {
        return nombre;
    }

    public Activo buscarActivo(String simboloActivo) {
        try{
            for (Operación op : operaciones){
                if (op.getActivo().getSimbolo().equals(simboloActivo)){
                    return op.getActivo();
                }
            }
        } catch (Exception e){
            System.out.println("No ha comprado este activo anteriormente.");
        }
        return null;
    }
    public boolean vender(Activo activo, LocalDate fecha, double numActivos, double precioActivos, double comisión) {

        if(activo != null && fecha.isBefore(LocalDate.now()) && numActivos > 0 && precioActivos <= cuentaAsociada.getCantidad() && comisión >= 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean puedeVender(Activo activo, int cantidad) {
        int cantidadTotal = 0;
        for (Operación op : operaciones) {
            if (op.getActivo().equals(activo) && op.getTipoOperacion() == TipoOperacion.COMPRA) {
                cantidadTotal += op.getCantidad();
            } else if (op.getActivo().equals(activo) && op.getTipoOperacion() == TipoOperacion.VENTA) {
                cantidadTotal -= op.getCantidad();
            }
        }

        return cantidadTotal >= cantidad;
    }
    public boolean comprar(Activo activo, LocalDate fecha, double numActivos, double precioActivos, double comisión) {

        if (activo != null && fecha.isBefore(LocalDate.now()) && numActivos > 0 && precioActivos <= cuentaAsociada.getCantidad() && comisión >= 0) {
            return true;
        } else {
            return false;
        }

    }
    static int numeroOperaciones = 0;
    public void comprarActivo(Activo activo, LocalDate fecha, double numActivos, double precioActivos, double comisión) {

        double dineroGastado = (numActivos * precioActivos) + comisión;

        if (cuentaAsociada.getCantidad() < precioActivos){
            System.out.println("No tienes suficiente dinero para realizar esta operación");
        }else if(cuentaAsociada.getCantidad() >= precioActivos){
            operaciones.add(new Operación(activo, fecha, precioActivos, comisión, TipoOperacion.COMPRA, numActivos));
            cuentaAsociada.setCantidad(cuentaAsociada.getCantidad() - dineroGastado);
            System.out.println("Compra realizada");
            System.out.println("Dinero restante: " + cuentaAsociada.getCantidad() + " €");
            numeroOperaciones++;
        }
    }
    public void venderActivo(Activo activo,LocalDate fecha, double numActivos, double precioActivos, double comisión) {

        double dineroIngresado = (numActivos*precioActivos) + comisión;
        operaciones.add(new Operación(activo, fecha, precioActivos, comisión, TipoOperacion.VENTA, numActivos));
        cuentaAsociada.setCantidad(cuentaAsociada.getCantidad() + dineroIngresado);
        System.out.println("Venta realizada");
        numeroOperaciones++;
        if (cuentaAsociada.getCantidad() == 0){
            System.out.println("Has vendido todos tus activos de " + activo.getNombre());
        }else{
            System.out.println("Te quedan: " + (numActivos- cuentaAsociada.getCantidad()) + " activos de " + activo.getNombre());
        }

    }
    void cobrarDividendos(Activo activo, LocalDate fecha, double numDividendos, double comisión){
        double dineroDeDividendos = numDividendos - comisión;
        operaciones.add(new Operación(activo, fecha, dineroDeDividendos, comisión, TipoOperacion.DIVIDENDOS, 1));
        cuentaAsociada.setCantidad(cuentaAsociada.getCantidad() + dineroDeDividendos);
        System.out.println("Se han cobrado los dividendos");
        numeroOperaciones++;
    }

    public double calcularValorActual() throws IOException{

        double valorCartera = 0;

        for (Operación op : operaciones){

            Activo activo = op.getActivo();
            double precio = Precio.buscaPrecio(activo.getSimbolo(), LocalDate.now());

            if (Objects.requireNonNull(op.getTipoOperacion() == TipoOperacion.COMPRA)){
                valorCartera += op.getCantidad() * op.getPrecio();
            }
        }
        return valorCartera;
    }

    public double calcularRentabilidadCartera(LocalDate fecha) throws IOException, NumberFormatException{

        double rentabilidad;
        double valorActual = calcularValorActual();
        double valorVentas = 0;
        double valorCompras = 0;
        double valorDividendos = 0;


        for (Operación op: operaciones) {
            Activo activo = op.getActivo();
            // double precio = Precio.buscaPrecio(activo.getSimbolo(), fecha);

            if (op.getTipoOperacion() == TipoOperacion.COMPRA) {
                valorCompras += op.getCantidad() * op.getPrecio() + op.getComision();
            } else if (op.getTipoOperacion() == TipoOperacion.VENTA) {
                valorVentas += op.getCantidad() * op.getPrecio() - op.getComision();
            } else if (op.getTipoOperacion() == TipoOperacion.DIVIDENDOS) {
                valorDividendos += op.getPrecio() - op.getComision();
            }
        }
        rentabilidad = (((valorActual + valorVentas + valorDividendos)/valorCompras)-1) * 100;

        return rentabilidad;
    }

    public double valorCarteraFechas(LocalDate fecha1, LocalDate fecha2) throws IOException {
        double valorCartera = 0;

        for (Operación op : operaciones){
            Activo activo = op.getActivo();
            double precio = Precio.buscaPrecio(activo.getSimbolo(), LocalDate.now());
            if (Objects.requireNonNull(op.getTipoOperacion() == TipoOperacion.COMPRA)){
                if (op.getFecha().isAfter(fecha1) && op.getFecha().isBefore(fecha2)) {
                    valorCartera += op.getCantidad() * op.getPrecio();
                }
            }
        }
        return valorCartera;
    }

    public double rentabilidadCarteraFechas(LocalDate fecha1, LocalDate fecha2) throws IOException {
        double rentabilidad;
        double valorActual = valorCarteraFechas(fecha1, fecha2);
        double valorVentas = 0;
        double valorCompras = 0;
        double valorDividendos = 0;


        for (Operación op: operaciones) {
            Activo activo = op.getActivo();
            // double precio = Precio.buscaPrecio(activo.getSimbolo(), fecha);
            if (op.getFecha().isAfter(fecha1) && op.getFecha().isBefore(fecha2)) {
                if (op.getTipoOperacion() == TipoOperacion.COMPRA) {
                    valorCompras += op.getCantidad() * op.getPrecio() + op.getComision();
                } else if (op.getTipoOperacion() == TipoOperacion.VENTA) {
                    valorVentas += op.getCantidad() * op.getPrecio() - op.getComision();
                } else if (op.getTipoOperacion() == TipoOperacion.DIVIDENDOS) {
                    valorDividendos += op.getPrecio() - op.getComision();
                }
            }
        }
        rentabilidad = (((valorActual + valorVentas + valorDividendos)/valorCompras)-1) * 100;

        return rentabilidad;


    }

    public void generarArchivoCartera() {

        String nombreArchivo = nombre + ".inv";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));

            oos.writeObject(nombre);
            oos.writeObject(cuentaAsociada);

            oos.close();

        } catch (IOException e) {
            System.out.println("Error al guardar la información de la cartera en el archivo: " + nombreArchivo);
            System.out.println(e);
        }

        if (nombreArchivo != null) {
            System.out.println("La información de la cartera ha sido guardada en el archivo: " + nombreArchivo);
        } else {
            System.out.println("Imposible crear el archivo: " + nombreArchivo);

        }
    }

    private static void leerArchivoCartera(String nombreArchivoCartera) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("De que cartera quieres conocer la información: ");
        String nombreCartera = teclado.nextLine();

        if (nombreCartera.equals(nombreArchivoCartera)){
            try {
                FileInputStream archivo = new FileInputStream(nombreArchivoCartera);
                ObjectInputStream ois = new ObjectInputStream(archivo);

                Object objeto = ois.readObject();



                ois.close();
                System.out.println("Información de la cartera " + nombreCartera + " leída del archivo.");

            } catch (FileNotFoundException e) {
                System.out.println("No se ha encontrado el archivo.");;
            } catch (IOException e) {
                System.out.println("Error al leer del archivo.");;
            } catch (ClassNotFoundException e) {
                System.out.println("Error al leer.");;
            }
        }
        else{
            System.out.println("No hay ninguna cartera creada con ese nombre");
        }
    }
}
