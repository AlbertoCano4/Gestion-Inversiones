# 📈 Gestión de Inversiones

Este proyecto es una aplicación de consola en Java para la **gestión de carteras de inversión**. Permite a los usuarios crear y gestionar una cartera, realizar operaciones con diferentes tipos de activos (acciones, ETFs y fondos de inversión), gestionar una cuenta bancaria asociada y generar informes detallados. El proyecto fue desarrollado como parte de un curso de programación, centrándose en la manipulación de objetos, la gestión de la entrada/salida de datos y la interacción con APIs externas para obtener precios en tiempo real.

---

## 🛠️ Tecnologías utilizadas

* **Java**: Lenguaje de programación principal del proyecto.
* **Programación Orientada a Objetos (POO)**: La estructura del proyecto se basa en clases y objetos, con herencia (`Activo`, `Accion`, `ETF`, `FondoInversion`) y polimorfismo, lo que facilita la gestión de diferentes tipos de activos.
* **APIs y Web Scraping**: El proyecto utiliza la librería `java.net.URL` y `java.util.Scanner` para conectarse a Yahoo Finance y obtener los precios históricos de las acciones, lo que permite el cálculo de la rentabilidad.
* **Manejo de archivos (`java.io`)**: Incluye la capacidad de serializar y deserializar objetos de la clase `Cartera` en un archivo binario (`.inv`) utilizando `ObjectOutputStream` y `ObjectInputStream`, lo que permite guardar y cargar el estado de una cartera.
* **Generación de informes (`java.io`)**: El programa puede crear archivos de texto (`.txt`) con informes detallados sobre las operaciones y la rentabilidad de la cartera.

---

## ✨ Características

* **Gestión de carteras**: Permite crear una cartera de inversión con un nombre y una cuenta bancaria asociada.
* **Operaciones con activos**:
    * **Compra**: Permite comprar acciones, ETFs y fondos de inversión, registrando el precio, la cantidad y la comisión.
    * **Venta**: Permite vender activos de la cartera.
    * **Dividendos**: Gestiona el cobro de dividendos.
* **Gestión de cuenta bancaria**: Permite ingresar y retirar dinero de la cuenta asociada a la cartera.
* **Informes**:
    * **Informe de operaciones**: Muestra un listado de todas las operaciones realizadas.
    * **Informe de rentabilidad**: Calcula y muestra la rentabilidad actual de la cartera.
    * **Informes personalizados**: Permite generar informes de operaciones y rentabilidad entre dos fechas específicas, guardando los resultados en archivos de texto.
* **Persistencia de datos**: Los datos de la cartera se pueden guardar y cargar desde archivos, permitiendo a los usuarios continuar su sesión en cualquier momento.

---

## 🚀 Guía de inicio

Para usar esta aplicación, sigue estos pasos:

### Prerrequisitos

* Java Development Kit (JDK) 8 o superior.
* Un IDE como IntelliJ IDEA o Eclipse, o un editor de texto con un compilador de Java.

### Instalación

1.  Clona el repositorio en tu máquina local:
    ```bash
    git clone [https://github.com/AlbertoCano4/Gestion-Inversiones.git](https://github.com/AlbertoCano4/Gestion-Inversiones.git)
    ```
2.  Navega hasta el directorio del proyecto.
3.  Abre el proyecto en tu IDE y compila el código.

### Cómo usar

1.  Ejecuta la clase principal `Main.java`.
2.  El programa te guiará a través de un menú interactivo.
3.  Primero, **crea una cartera** para empezar a gestionar tus inversiones.
4.  Utiliza las opciones del menú para realizar operaciones, gestionar tu saldo y generar informes.

---

## 👨‍💻 Autores

* **Gonzalo Ruiz**
* **Alberto Cano**
* **Pelayo Castro**
* **Juan González**
* **Hugo Fernández de Valderrama**
* **Ignacio Fernández**
