
import java.util.*;
import java.time.LocalDateTime;

public class Farmacia {

    static Scanner sc = new Scanner(System.in);

    // CONTADORES DE ID
    static int idTrabajador = 1;
    static int idProveedor = 1;
    static int idMedicamento = 1;
    static int idMovimiento = 1;

    // LISTAS DEL SISTEMA
    static ArrayList<Trabajador> trabajadores = new ArrayList<>();
    static ArrayList<Proveedor> proveedores = new ArrayList<>();
    static ArrayList<Medicamento> medicamentos = new ArrayList<>();
    static ArrayList<Movimiento> movimientos = new ArrayList<>();

    // =================================
    // CLASE TRABAJADOR
    // =================================

    static class Trabajador {
        String id, nombre, puesto, telefono;

        Trabajador(String nombre, String puesto, String telefono) {
            this.id = String.format("TRB-%04d", idTrabajador++);
            this.nombre = nombre;
            this.puesto = puesto;
            this.telefono = telefono;
        }

        void mostrar() {
            System.out.println(
                id + " | " + nombre + " | " +
                puesto + " | " + telefono
            );
        }
    }

    // =================================
    // CLASE PROVEEDOR
    // =================================

    static class Proveedor {
        String id, nombre, telefono, direccion;

        Proveedor(String nombre, String telefono, String direccion) {
            this.id = String.format("PRO-%04d", idProveedor++);
            this.nombre = nombre;
            this.telefono = telefono;
            this.direccion = direccion;
        }

        void mostrar() {
            System.out.println(
                id + " | " + nombre + " | " +
                telefono + " | " + direccion
            );
        }
    }

    // =================================
    // CLASE MEDICAMENTO
    // =================================

    static class Medicamento {
        String id, nombre, lote, caducidad;
        double precio;
        int cantidad;
        String proveedorId;

        Medicamento(String nombre, double precio, int cantidad,
                    String lote, String caducidad, String proveedorId) {

            this.id = String.format("MED-%04d", idMedicamento++);
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
            this.lote = lote;
            this.caducidad = caducidad;
            this.proveedorId = proveedorId;
        }

        void mostrar() {
            System.out.println(
                id + " | " + nombre +
                " | Precio: $" + precio +
                " | Existencias: " + cantidad +
                " | Lote: " + lote +
                " | Caducidad: " + caducidad +
                " | Proveedor: " + proveedorId
            );
        }
    }

    // =================================
    // CLASE MOVIMIENTO
    // =================================

    static class Movimiento {
        String id, tipo, medicamentoId, trabajadorId;
        int cantidad;
        LocalDateTime fecha;

        Movimiento(String tipo, String medicamentoId,
                   String trabajadorId, int cantidad) {

            this.id = String.format("MOV-%04d", idMovimiento++);
            this.tipo = tipo;
            this.medicamentoId = medicamentoId;
            this.trabajadorId = trabajadorId;
            this.cantidad = cantidad;
            this.fecha = LocalDateTime.now();
        }

        void mostrar() {
            System.out.println(
                id + " | " + tipo +
                " | Medicamento: " + medicamentoId +
                " | Trabajador: " + trabajadorId +
                " | Cantidad: " + cantidad +
                " | Fecha: " + fecha
            );
        }
    }

    // =================================
    // BUSCAR REGISTROS
    // =================================

    static Trabajador buscarTrabajador(String id) {
        for (Trabajador t : trabajadores) {
            if (t.id.equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    static Proveedor buscarProveedor(String id) {
        for (Proveedor p : proveedores) {
            if (p.id.equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    static Medicamento buscarMedicamento(String id) {
        for (Medicamento m : medicamentos) {
            if (m.id.equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    // =================================
    // REGISTRAR TRABAJADORES
    // =================================

    static void registrarTrabajador() {
        sc.nextLine();

        System.out.println("\n--- REGISTRAR TRABAJADOR ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Puesto: ");
        String puesto = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        Trabajador t = new Trabajador(nombre, puesto, telefono);
        trabajadores.add(t);

        System.out.println("Trabajador registrado correctamente.");
        System.out.println("ID asignado: " + t.id);
    }

    // =================================
    // REGISTRAR PROVEEDORES
    // =================================

    static void registrarProveedor() {
        sc.nextLine();

        System.out.println("\n--- REGISTRAR PROVEEDOR ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        System.out.print("Direccion: ");
        String direccion = sc.nextLine();

        Proveedor p = new Proveedor(nombre, telefono, direccion);
        proveedores.add(p);

        System.out.println("Proveedor registrado correctamente.");
        System.out.println("ID asignado: " + p.id);
    }

    // =================================
    // REGISTRAR MEDICAMENTOS
    // =================================

    static void registrarMedicamento() {
        sc.nextLine();

        System.out.println("\n--- REGISTRAR MEDICAMENTO ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Cantidad inicial: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        if (precio < 0 || cantidad < 0) {
            System.out.println("Precio o cantidad invalidos.");
            return;
        }

        System.out.print("Numero de lote: ");
        String lote = sc.nextLine();

        System.out.print("Fecha de caducidad: ");
        String caducidad = sc.nextLine();

        System.out.print("ID del proveedor: ");
        String proveedorId = sc.nextLine();

        if (buscarProveedor(proveedorId) == null) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        Medicamento m = new Medicamento(
            nombre, precio, cantidad, lote, caducidad, proveedorId
        );

        medicamentos.add(m);

        System.out.println("Medicamento registrado correctamente.");
        System.out.println("ID asignado: " + m.id);
    }

    // =================================
    // ENTRADA DE MEDICAMENTOS
    // =================================

    static void entradaMedicamento() {
        sc.nextLine();

        System.out.println("\n--- ENTRADA DE MEDICAMENTOS ---");

        System.out.print("ID del medicamento: ");
        String id = sc.nextLine();

        Medicamento m = buscarMedicamento(id);

        if (m == null) {
            System.out.println("Medicamento no encontrado.");
            return;
        }

        System.out.print("ID del trabajador: ");
        String trabajadorId = sc.nextLine();

        if (buscarTrabajador(trabajadorId) == null) {
            System.out.println("Trabajador no encontrado.");
            return;
        }

        System.out.print("Cantidad que ingresa: ");
        int cantidad = sc.nextInt();

        if (cantidad <= 0) {
            System.out.println("Cantidad invalida.");
            return;
        }

        m.cantidad += cantidad;

        Movimiento mov = new Movimiento(
            "ENTRADA", m.id, trabajadorId, cantidad
        );

        movimientos.add(mov);

        System.out.println("Entrada registrada correctamente.");
        System.out.println("ID del movimiento: " + mov.id);
        System.out.println("Existencias actuales: " + m.cantidad);
    }

    // =================================
    // SALIDA DE MEDICAMENTOS
    // =================================

    static void salidaMedicamento() {
        sc.nextLine();

        System.out.println("\n--- SALIDA DE MEDICAMENTOS ---");

        System.out.print("ID del medicamento: ");
        String id = sc.nextLine();

        Medicamento m = buscarMedicamento(id);

        if (m == null) {
            System.out.println("Medicamento no encontrado.");
            return;
        }

        System.out.print("ID del trabajador: ");
        String trabajadorId = sc.nextLine();

        if (buscarTrabajador(trabajadorId) == null) {
            System.out.println("Trabajador no encontrado.");
            return;
        }

        System.out.print("Cantidad que sale: ");
        int cantidad = sc.nextInt();

        if (cantidad <= 0 || cantidad > m.cantidad) {
            System.out.println("Cantidad invalida o inventario insuficiente.");
            return;
        }

        m.cantidad -= cantidad;

        Movimiento mov = new Movimiento(
            "SALIDA", m.id, trabajadorId, cantidad
        );

        movimientos.add(mov);

        System.out.println("Salida registrada correctamente.");
        System.out.println("ID del movimiento: " + mov.id);
        System.out.println("Existencias actuales: " + m.cantidad);
    }

    // =================================
    // MOSTRAR TRABAJADORES
    // =================================

    static void mostrarTrabajadores() {
        System.out.println("\n--- LISTA DE TRABAJADORES ---");

        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }

        for (Trabajador t : trabajadores) t.mostrar();
    }

    // =================================
    // MOSTRAR PROVEEDORES
    // =================================

    static void mostrarProveedores() {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        for (Proveedor p : proveedores) p.mostrar();
    }

    // =================================
    // MOSTRAR MEDICAMENTOS
    // =================================

    static void mostrarMedicamentos() {
        System.out.println("\n--- INVENTARIO DE MEDICAMENTOS ---");

        if (medicamentos.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
            return;
        }

        for (Medicamento m : medicamentos) m.mostrar();
    }

    // =================================
    // MOSTRAR HISTORIAL
    // =================================

    static void mostrarMovimientos() {
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");

        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }

        for (Movimiento m : movimientos) m.mostrar();
    }

    // =================================
    // MENU PRINCIPAL
    // =================================

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n================================");
            System.out.println("       FARMACIA - SISTEMA");
            System.out.println("================================");
            System.out.println("1. Registrar trabajador");
            System.out.println("2. Registrar proveedor");
            System.out.println("3. Registrar medicamento");
            System.out.println("4. Entrada de medicamentos");
            System.out.println("5. Salida de medicamentos");
            System.out.println("6. Mostrar trabajadores");
            System.out.println("7. Mostrar proveedores");
            System.out.println("8. Mostrar medicamentos");
            System.out.println("9. Mostrar historial");
            System.out.println("10. Consultar inventario");
            System.out.println("0. Salir");
            System.out.println("================================");

            System.out.print("Selecciona una opcion: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opcion valida.");
                sc.nextLine();
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarTrabajador();
                    break;

                case 2:
                    registrarProveedor();
                    break;

                case 3:
                    registrarMedicamento();
                    break;

                case 4:
                    entradaMedicamento();
                    break;

                case 5:
                    salidaMedicamento();
                    break;

                case 6:
                    mostrarTrabajadores();
                    break;

                case 7:
                    mostrarProveedores();
                    break;

                case 8:
                    mostrarMedicamentos();
                    break;

                case 9:
                    mostrarMovimientos();
                    break;

                case 10:
                    mostrarMedicamentos();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
