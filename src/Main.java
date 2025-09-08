/**
 * PRÁCTICA 3 - GESTIÓN DE INVERSIONES
 * Gonzalo Ruiz, Alberto Cano, Pelayo Castro, Juan González, Hugo Fernández de Valderrama e Ignacio Fernández.
 */

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/* ESPECIFICACIONES PRÁCTICA 3
En esta parte de la práctica vamos a tener a información en un archivo de información. Para ello debe:

1. Añadir una opción para escribir la información de una cartera en un archivo de datos.
Para ello, se recomienda serializar la información utilizando ObjectOutputStream de la cartera.
El nombre del archivo debe ser el nombre de la cartera y la extensión “.inv”.

2. Añada una opción para leer la información de una cartera.
La información se puede recuperar de forma consistente a como se ha escrito utilizando un ObjectInputStream
y deserializando la información.
Para leer la información de una cartera debe solicitar al usuario el nombre de la cartera que desea leer.

3. Añada una opción para obtener un informe con las operaciones realizadas entre dos fechas.
Para ello debe solicitar al usuario la fecha de inicio y fin del informe.
Debe generar en un archivo un texto con la información como aparece en el Anexo I.
El nombre del archivo debe ser similar al siguiente: “Informe_operaciones_nombre_2022_12_1-2023_02_28.txt”,
donde nombre debe ser el nombre de la cartera seguido de la fecha de inicio y final del informe.

Añada una opción obtener un informe con el cálculo de rentabilidad de cada uno de los activos entre dos fechas.
Para ello, debe solicitar el usuario la fecha de inicio y fin del informe.
Debe generar en un archivo un texto con la información como aparece en el Anexo II.
El nombre del archivo debe ser como el siguiente: “Informe_rentabilidad_nombre_2022_12_2-2023_03_31.txt”,
donde nombre debe ser el nombre de la cartera seguido de la fecha de inicio y final del informe.

NOTA:
Para escribir de forma adecuada la información en el archivo de texto,
se recomienda hacerlo primero en consola para imprimir como queda y, una vez quede de la forma apropiada, utilizar
un PrintWriter para, utilizando las mismas sentencias de escritura, escribirlo en el archivo de texto.

 */
public class Main {
    public static void main(String[] args) throws Exception {

        Cartera cartera = null;

        int opcion, opciónMenuChiquito;
        boolean carteraCreada = false;

        System.out.println("---------------------------------------");
        System.out.println("            MENÚ PRÁCTICA 2            ");

        do {
            // Escribimos un menú de opciones

            escribirMenu();
            opcion = pedirNumero(0, 6);

            switch (opcion) {

                case 1:
                    cartera = crearCartera();
                    carteraCreada = true;
                    break;
                case 2:
                    do {
                        menuBanco();
                        opciónMenuChiquito = pedirNumero(0, 3);
                        if (opciónMenuChiquito == 0) {
                            System.out.println("VOLVIENDO AL MENÚ PRINCIPAL");
                        } else if (opciónMenuChiquito == 1) {
                            // Ingresar dinero en la cuenta
                            // (Pide el dinero que quiere que metas)
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;
                            } else {
                                ingresarDinero(cartera);
                            }
                        } else if (opciónMenuChiquito == 2) {
                            // Retirar dinero de la cuenta
                            // (Retira si hay fondos sino muestra mensaje de que no hay fondos)
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;
                            } else {
                                retirarDinero(cartera);
                            }
                        } else if (opciónMenuChiquito == 3) {
                            // Obtener Info de cuenta y saldo
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;
                            } else {
                                informeCuenta(cartera);
                            }
                        } else {
                            System.out.println("Opción no válida.");
                            System.out.println("Introduzca una opción valida");
                        }
                    } while (opciónMenuChiquito != 0 && carteraCreada == true);
                    break;

                case 3:
                    do {
                        menuOperaciones();
                        opciónMenuChiquito = pedirNumero(0, 3);
                        if (opciónMenuChiquito == 0) {
                            System.out.println("VOLVIENDO AL MENÚ PRINCIPAL");
                        } else if (opciónMenuChiquito == 1) {
                            // Comprar un activo
                            // (pedir símbolo, nº de acciones/participaciones, precio unitario y comisión)
                            // (Si coincide el símbolo pedir: nombre y Mercado/Categoría)
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;
                            } else {
                                comprarActivo(cartera);
                            }
                        } else if (opciónMenuChiquito == 2) {
                            // Vender Activo
                            // Si no está ese activo en cartera mensaje: "No hay en cartera"
                            // Si está en cartera (pedir: nº de acciones para vender)
                            // Si nº de acciones solicitado > nº acciones en cartera cancelar operación
                            // Si se pueden vender solicitar precio unitario y comisión de la operación
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;

                            } else {
                                venderActivo(cartera);
                            }
                        } else if (opciónMenuChiquito == 3) {
                            // Cobrar dividendos
                            // (pedir símbolo del activo)
                            // Si no existe activo en cartera cancelar operación
                            // Si existe activo solicitar dividendos y comisión
                            if (cartera == null) {
                                System.out.println("Necesita crear una cartera primero");
                                carteraCreada = false;
                            } else {
                                cobrarDividendos(cartera);
                            }
                        } else {
                            System.out.println("Opción no válida.");
                            System.out.println("Introduzca una opción valida");
                        }
                    } while (opciónMenuChiquito != 0 && carteraCreada == true);
                    break;
                case 4:
                    if (cartera.numeroOperaciones == 0) {
                        System.out.println("Primero debes realizar alguna operación para acceder a este menú");
                    }else {
                        do {
                            menuInformeOperaciones();
                            opciónMenuChiquito = pedirNumero(0, 2);

                            if (opciónMenuChiquito == 0) {
                                System.out.println("VOLVIENDO AL MENÚ PRINCIPAL");
                            } else if (opciónMenuChiquito == 1) {
                                //Informe de las operaciones realizadas (lista)
                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    informeOperaciones(cartera);
                                }
                            } else if (opciónMenuChiquito == 2) {
                                // Informe operaciones entre fechas
                                // Pedir fecha inicial y final
                                // Genera informe de operaciones realizadas entre esas fechas (incluidas)
                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    informeOperacionesFecha(cartera);
                                }
                            }
                        } while (opciónMenuChiquito != 0 && carteraCreada == true);
                    }
                    break;
                case 5:
                    if (cartera.numeroOperaciones == 0) {
                        System.out.println("Primero debes realizar alguna operación para acceder a este menú");
                    }else {
                        menuRentabilidad();
                        opciónMenuChiquito = pedirNumero(0, 3);
                        do {
                            if (opciónMenuChiquito == 0) {
                                System.out.println("VOLVIENDO AL MENÚ PRINCIPAL");
                            } else if (opciónMenuChiquito == 1) {
                                // Calcular rentabilidad
                                // Informe de la rentabilidad de todas las operaciones realizadas hasta la fecha
                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    informeRentabilidad(cartera);
                                }
                            } else if (opciónMenuChiquito == 2) {
                                // Calcular rentabilidad hasta una fecha
                                // Pedir fecha y generar informe de la rentabilidad de las operaciones hasta la fecha indicada
                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    informeRentabilidadFechas(cartera);
                                }
                            }
                        } while (opciónMenuChiquito != 0 && carteraCreada == true);
                    }
                    break;
                case 6:
                    String nombreArchivoCartera = cartera.getNombre() + ".inv";
                    do {
                        menuInformes();
                        opciónMenuChiquito = pedirNumero(0, 4);
                            if (opciónMenuChiquito == 0) {
                                System.out.println("VOLVIENDO AL MENÚ PRINCIPAL");
                            } else if (opciónMenuChiquito == 1) {
                                if (cartera == null) {
                                    System.out.println("NecesitaS crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    // Escribir info de la cartera en archivo
                                    // La info se puede recuperar de forma consistente usando (ObjectOutputStream)
                                    // Nombre del archivo = nombre cartera .inv
                                    // cartera.generarArchivoCartera();
                                    generarArchivoCartera(cartera, nombreArchivoCartera);
                                }
                            } else if (opciónMenuChiquito == 2) {

                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    // Leer info de la cartera en archivo
                                    // La info se puede recuperar de forma consistente usando (ObjectInputStream)
                                    // y deserializando la información.
                                    // El programa debe pedir el nombre de la cartera que quieres leer
                                    // leerArchivoCartera(cartera, nombreArchivoCartera);
                                }
                            } else if (opciónMenuChiquito == 3) {
                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    // Obtener Informe de operaciones entre fechas.
                                    // Solicitar dos fechas.
                                    // Genera archivo de texto con info del ANEXO I.
                                    // nombre archivo: “Informe_operaciones_nombre_2022_12_1-2023_02_28.txt”.
                                    // nombre = nombre cartera. Fechas: Las dos solicitadas.
                                    // archivoOperacionesFechas();
                                }
                            } else if (opciónMenuChiquito == 4) {

                                if (cartera == null) {
                                    System.out.println("Necesita crear una cartera primero");
                                    carteraCreada = false;
                                } else {
                                    // Obtener la rentabilidad de las operaciones entre fechas.
                                    // Solicitar dos fechas.
                                    // Genera archivo de texto con info del ANEXO II.
                                    // nombre archivo: “Informe_rentabilidad_nombre_2022_12_1-2023_02_28.txt”.
                                    // nombre = nombre cartera. Fechas: Las dos solicitadas.
                                    // archivoRentabilidadFechas();
                                }
                            }
                        }while (opciónMenuChiquito != 0 && carteraCreada == true);
                    break;
                case 0:
                    System.out.println("Termina el programa.");
                    System.out.println("Hasta la próxima vez!!!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("Introduzca una opción valida");
                    break;
            }
        }while(opcion != 0);
    }

   /* public void leerDeArchivo(String nombreArchivo) {

        try {
            FileInputStream archivo = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(archivo);

            Object objeto = ois.readObject();
            misCoches = (ArrayList<Coche>) objeto;

            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");;
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");;
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer.");;
        }
    }

    private static void leerArchivoCartera(Cartera cartera, String nombreArchivoCartera) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("De que cartera quieres conocer la información: ");
        String nombreCartera = teclado.nextLine();

        if (nombreCartera.equals(nombreArchivoCartera)){
            try {
                FileInputStream archivo = new FileInputStream(nombreArchivoCartera);
                ObjectInputStream ois = new ObjectInputStream(archivo);

                Object objeto = ois.readObject();

                cartera.getNombre() = (String) objeto;
                cartera.getCuentaAsociada() = (CuentaBancaria) objeto;

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
*/
    private static void generarArchivoCartera(Cartera cartera, String nombreArchivoCartera) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivoCartera));

            oos.writeObject(cartera.getNombre());
            oos.writeObject(cartera.getCuentaAsociada());

            oos.close();

        } catch (IOException e) {
            System.out.println("Error al guardar la información de la cartera en el archivo: " + nombreArchivoCartera);
            System.out.println(e);
        }

        if (nombreArchivoCartera != null){
            System.out.println("La información de la cartera ha sido guardada en el archivo: " + nombreArchivoCartera);
        } else {
            System.out.println("Imposible crear el archivo: " + nombreArchivoCartera);

        }

    }

    private static void menuInformes() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------MENÚ DE ARCHIVOS---------------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. ESCRIBIR INFO CARTERA EN ARCHIVO DE DATOS.");
        System.out.println("2. LEER INFO DE UNA CARTERA.");
        System.out.println("3. GENERAR ARCHIVO CON INFORME DE OPERACIONES ENTRE FECHAS.");
        System.out.println("4. GENERAR ARCHIVO CON LA RENTABILIDAD ENTRE FECHAS.");
        System.out.println("0. TERMINAR PROGRAMA.");
        System.out.print("Elija una opción: ");

    }
    private static void escribirMenu() {

        System.out.println("---------------------------------------");
        System.out.println("--------GESTIÓN DE INVERSIONES---------");
        System.out.println("---------------------------------------");
        System.out.println("1. CREAR CARTERA.");
        System.out.println("2. MENÚ CUENTA BANCARIA.");
        System.out.println("3. MENÚ OPERACIONES CON ACTIVOS.");
        System.out.println("4. MENÚ HISTORIAL OPERACIONES.");
        System.out.println("5. MENÚ RENTABILIDAD.");
        System.out.println("6. MENÚ INFORMES EN ARCHIVOS.");
        System.out.println("0. TERMINAR PROGRAMA.");
        System.out.print("Elija una opción: ");
    }

    private static void menuBanco() {
        System.out.println("-----------------------------------");
        System.out.println("-------MENÚ CUENTA BANCARIA--------");
        System.out.println("-----------------------------------");

        System.out.println("1. INGRESAR DINERO.");
        System.out.println("2. RETIRAR DINERO.");
        System.out.println("3. INFORME CUENTA.");
        System.out.println("0. MENÚ PRINCIPAL.");
        System.out.print("Elija una opción: ");
    }
    private static void menuOperaciones() {
        System.out.println("--------------------------------------");
        System.out.println("-------MENÚ CUENTA OPERACIONES--------");
        System.out.println("--------------------------------------");

        System.out.println("1. COMPRAR ACTIVO.");
        System.out.println("2. VENDER ACTIVO.");
        System.out.println("3. COBRAR DIVIDENDO.");
        System.out.println("0. MENÚ PRINCIPAL.");
        System.out.print("Elija una opción: ");
    }
    private static void menuRentabilidad() {
        System.out.println("---------------------------------");
        System.out.println("-------MENÚ RENTABILIDAD --------");
        System.out.println("---------------------------------");

        System.out.println("1. RENTABILIDAD GENERAL.");
        System.out.println("2. RENTABILIDAD ENTRE FECHAS.");
        System.out.println("0. MENÚ PRINCIPAL.");
        System.out.print("Elija una opción: ");
    }
    private static void menuInformeOperaciones() {
        System.out.println("-----------------------------------------");
        System.out.println("-------MENÚ HISTORIAL OPERACIONES--------");
        System.out.println("-----------------------------------------");

        System.out.println("1. HISTORIAL OPERACIONES.");
        System.out.println("2. HISTORIAL OPERACIONES ENTRE FECHAS.");
        System.out.println("0. MENÚ PRINCIPAL.");
        System.out.print("Elija una opción: ");
    }

    private static void informeRentabilidadFechas(Cartera cartera) {

        System.out.println("---------------------------------------------");
        System.out.println("-------INFORME RENTABILIDAD POR FECHAS-------");
        System.out.println("---------------------------------------------");

        LocalDate fecha1, fecha2;

        System.out.println("Entre que fechas quieres ver las operaciones");
        System.out.println("Fecha más antigua: ");
        fecha1 = pedirFecha();
        System.out.println("Fecha más reciente: ");
        fecha2 = pedirFecha();

        System.out.println("Rentabilidad entre el " + fecha1 + " y el " + fecha2);

        double rentabilidadCarteraFechas;

        try {
            rentabilidadCarteraFechas = cartera.rentabilidadCarteraFechas(fecha1,  fecha2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        if (rentabilidadCarteraFechas < 0){
            System.out.println("Llevas una rentabilidad negativa del " + rentabilidadCarteraFechas + "%");
        } else if (rentabilidadCarteraFechas > 0) {
            System.out.println("Llevas una rentabilidad Positiva del " + rentabilidadCarteraFechas + "%");
        }else {
            System.out.println("Rentabilidad nula");
        }

    }
    private static void informeRentabilidad(Cartera cartera) {

        System.out.println("---------------------------------------------");
        System.out.println("------------INFORME RENTABILIDAD-------------");
        System.out.println("---------------------------------------------");

        System.out.println("Rentabilidad histórica de la cartera a día " + LocalDate.now());

        double rentabilidadCartera;

        try {
            rentabilidadCartera = cartera.calcularRentabilidadCartera(LocalDate.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        if (rentabilidadCartera < 0){
            System.out.println("Llevas una rentabilidad negativa del " + rentabilidadCartera + "%");
        } else if (rentabilidadCartera > 0) {
            System.out.println("Llevas una rentabilidad Positiva del " + rentabilidadCartera + "%");
        }else {
            System.out.println("Rentabilidad nula");
        }
    }
    private static void informeOperacionesFecha(Cartera cartera) throws IOException {

        System.out.println("---------------------------------------------");
        System.out.println("-------INFORME OPERACIONES POR FECHAS--------");
        System.out.println("---------------------------------------------");

        LocalDate fecha1, fecha2;

        System.out.println("Entre que fechas quieres ver las operaciones");
        System.out.println("Fecha más antigua: ");
        fecha1 = pedirFecha();
        System.out.println("Fecha más reciente: ");
        fecha2 = pedirFecha();

        System.out.println("Informe de operaciones entre el " + fecha1 + " y el " + fecha2);

        for (Operación op : cartera.operaciones){
            if (op.getFecha().isAfter(fecha1) && op.getFecha().isBefore(fecha2)) {
                System.out.println(op);
            }
        }

        double valorCarteraFechas, rentabilidadCarteraFechas;

        valorCarteraFechas = cartera.valorCarteraFechas(fecha1, fecha2);
        try {
            rentabilidadCarteraFechas = cartera.rentabilidadCarteraFechas(fecha1,  fecha2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Valor entre fechas " + fecha1 + " y " + fecha2 + ": " + valorCarteraFechas);
        System.out.println("Rentabilidad en el periodo: " + rentabilidadCarteraFechas + " %");

    }

    private static void informeOperaciones(Cartera cartera) throws IOException {

        System.out.println("---------------------------------");
        System.out.println("-------INFORME OPERACIONES-------");
        System.out.println("---------------------------------");

        System.out.println("Número de operaciones realizadas " + cartera.numeroOperaciones);

        for (Operación operación : cartera.operaciones){
            System.out.println(operación);
        }

        double valorCartera, rentabilidadCartera;

        valorCartera = cartera.calcularValorActual();
        rentabilidadCartera = cartera.calcularRentabilidadCartera(LocalDate.now());

        System.out.println("Valor a fecha " + LocalDate.now() + ": " + valorCartera);
        System.out.println("Rentabilidad en el periodo: " + rentabilidadCartera + " %");
    }

    private static void cobrarDividendos(Cartera cartera) {
        System.out.println("---------------------------------");
        System.out.println("--------COBRAR DIVIDENDOS--------");
        System.out.println("---------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el símbolo del activo que quiera vender: ");
        String simboloActivo = scanner.next();

        Activo activo = cartera.buscarActivo(simboloActivo);

        if (activo == null || !cartera.puedeVender(activo,1)) {
            System.out.println("El activo que quieres vender no lo tienes");
        } else {
            System.out.println("Introduzca la fecha de la operación: ");
            LocalDate fecha = pedirFecha();
            System.out.println("¿Cuántos dividendos quieres recibir?");
            double numdividendos = scanner.nextDouble();
            System.out.println("Introduzca la comisión: ");
            double comisión = scanner.nextDouble();

            cartera.cobrarDividendos(activo, fecha, numdividendos, comisión);
        }
    }

    private static void venderActivo(Cartera cartera) {
        System.out.println("---------------------------------");
        System.out.println("-----------VENDER ACTIVO---------");
        System.out.println("---------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el símbolo del activo que quiera vender: ");
        String simboloActivo = scanner.next();

        Activo activo = cartera.buscarActivo(simboloActivo);

        double precioActivos, comisión, numAcciones;

        if (activo == null || !cartera.puedeVender(activo,0)) {
            System.out.println("El activo que quieres vender no lo tienes");
        } else {

            System.out.println("¿Cuántas acciones/participaciones quieres vender?");
            numAcciones = scanner.nextDouble();

            System.out.println("Introduzca la fecha de la operación: ");
            LocalDate fecha = pedirFecha();
            System.out.println("¿A qué Precio?");
            precioActivos = scanner.nextDouble();
            System.out.println("Introduzca la comisión: ");
            comisión = scanner.nextDouble();

            boolean venta = cartera.vender(activo,fecha, numAcciones, precioActivos,comisión);
            if (venta == true){
                cartera.venderActivo(activo,fecha, numAcciones, precioActivos, comisión);
            }else{
                System.out.println("Algo salió mal, vuela a intentarlo");
            }

        }
    }

    private static void comprarActivo(Cartera cartera) {
        System.out.println("---------------------------------");
        System.out.println("----------COMPRAR ACTIVO---------");
        System.out.println("---------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el símbolo del activo que quiera comprar: ");
        String simboloActivo = scanner.next();

        Activo activo = cartera.buscarActivo(simboloActivo);

        if (activo == null ) {

            System.out.println("Introduzca el nombre del activo: ");
            String nombreActivo = scanner.next();
            System.out.println("---------------------------------");
            System.out.println("Tipos de activos");
            System.out.println("1. ACCIONES");
            System.out.println("2. ETF");
            System.out.println("3. Fondos de Inversión");
            System.out.println("Introduzca el tipo de activo: ");
            int opcion;
            opcion = pedirNumero(1, 3);
            System.out.println("---------------------------------");

            if (opcion == 1) {
                System.out.println("Realizando compra de Acción");
                System.out.println("Introduzca el Mercado: ");
                String mercadoAccion = scanner.next();
                activo = new Accion(simboloActivo, nombreActivo, true, mercadoAccion);

            } else if (opcion == 2) {
                System.out.println("Realizando compra de ETF");
                System.out.println("Introduzca la Categoría: ");
                String categoriaETF = scanner.next();
                activo = new ETF(simboloActivo, nombreActivo, true, categoriaETF);

            } else if (opcion == 3) {
                System.out.println("Realizando compra de fondo de inversión");
                System.out.println("Introduzca la Categoría: ");
                String categoriaFondo = scanner.next();
                activo = new FondoInversion(simboloActivo, nombreActivo, true, categoriaFondo);
            } else {
                System.out.println("Tipo de Activo no Valido");
            }
        } else {
            System.out.println("Ya compraste anteriormente este activo, por lo que nos saltaremos algunos pasos");
        }
        System.out.println("Introduzca fecha de la operación (DD/MM/YY)");
        LocalDate fecha = pedirFecha();
        System.out.println("¿Cuántas acciones/participaciones quieres comprar?");
        double numActivos = scanner.nextDouble();
        System.out.println("¿A qué Precio?");
        double precioActivos = scanner.nextDouble();
        System.out.println("Introduzca la comisión: ");
        double comisión = scanner.nextDouble();

        boolean compra = cartera.comprar(activo,fecha, numActivos, precioActivos,comisión);
        if (compra == true){
            System.out.println("Compra realizada con éxito");
            cartera.comprarActivo(activo, fecha, numActivos, precioActivos,comisión);
        } else {
            System.out.println("Algo salió mal, vuela a intentarlo");
        }
    }

    private static LocalDate pedirFecha() {
        System.out.println("Dia: ");
        int dia = pedirNumero(1, 31);
        System.out.println("Mes: ");
        int mes = pedirNumero(1, 12);
        System.out.println("Año: ");
        int anno = pedirNumero(1945, LocalDate.now().getYear());

        return LocalDate.of(anno, mes, dia);
    }


    private static void informeCuenta(Cartera cartera) {

        System.out.println("----------------------------");
        System.out.println("--INFORME CUENTA Y CARTERA--");
        System.out.println("----------------------------");

        System.out.println("Nombre Cartera: " + cartera.getNombre());
        System.out.println("Cuenta Bancaria");
        System.out.println("Entidad: " + cartera.getCuentaAsociada().getEntidad());
        System.out.println("IBAN: " + cartera.getCuentaAsociada().getIban());
        System.out.println("Dinero Disponible: " + cartera.getCuentaAsociada().getCantidad());
    }

    private static void retirarDinero(Cartera cartera) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------");
        System.out.println("  RETIRAR DINERO");
        System.out.println("-------------------");

        double retiro;

        do {
            System.out.println("Tienes: " + cartera.getCuentaAsociada().getCantidad());
            System.out.println("¿Cuánto dinero quieres retirar?");
            retiro = Double.parseDouble(scanner.nextLine());
            if (retiro > cartera.getCuentaAsociada().getCantidad()){
                System.out.println("No puedes retirar más dinero del que tienes");
                retiro = 0;
            } else if (retiro <= cartera.getCuentaAsociada().getCantidad()){
                System.out.println("DINERO RETIRADO CORRECTAMENTE.");
            }
            cartera.getCuentaAsociada().retirarDeCuenta(retiro);
        }while(retiro == 0);

        System.out.println(" TIENES: " + cartera.getCuentaAsociada().getCantidad() + "€");

    }


    private static void ingresarDinero(Cartera cartera) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------");
        System.out.println("  INGRESAR DINERO");
        System.out.println("-------------------");
        System.out.println("¿Cuánto dinero quieres ingresar?");
        double ingreso = Double.parseDouble(scanner.nextLine());
        cartera.getCuentaAsociada().ingresarDinero(ingreso);
        System.out.println("DINERO INGRESADO CORRECTAMENTE.");
        System.out.println(" TIENES: " + cartera.getCuentaAsociada().getCantidad() + " €");

    }

    private static int pedirNumero(int a, int b) {
        Scanner teclado = new Scanner(System.in);
        int n;

        do {
            try {
                System.out.print("Introduzca un número entre " + a + " y " + b + ": ");
                n = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir un número");
                teclado.nextLine();
                n = a - 1;
            }
        } while (n < a || n > b);

        return n;
    }

    private static Cartera crearCartera() {
        Scanner teclado = new Scanner(System.in);
        String nombreCartera;
        String numeroCuenta;
        String entidadBancaria;
        double saldoInicial;

        System.out.println("------------------------------------------------------");
        System.out.println("                    CREAR CARTERA                     ");
        System.out.println("------------------------------------------------------");
        System.out.print("Ingrese el nombre de la cartera: ");
        nombreCartera = teclado.nextLine();
        System.out.print("Ingrese la entidad de la cuenta bancaria: ");
        entidadBancaria = teclado.nextLine();
        System.out.print("Ingrese el IBAN de la cuenta bancaria: ");
        numeroCuenta = teclado.nextLine();
        System.out.print("Ingrese el saldo inicial de la cuenta bancaria: ");
        saldoInicial = teclado.nextDouble();

        try {
            CuentaBancaria cuentaBancaria = new CuentaBancaria(entidadBancaria, numeroCuenta, saldoInicial);
            Cartera cartera = new Cartera(nombreCartera,cuentaBancaria);
            return cartera;

        }catch (Exception e){
            System.out.println("No se pudo crear la cartera con los datos introducidos");
            return null;
        }
    }
}