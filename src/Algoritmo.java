import java.util.Scanner;

public class Algoritmo {

    static Scanner entrada = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static void registrarConsumo(double[] consumos) {

        int apartamento;
        double consumo;

        System.out.print("Ingrese el numero del apartamento (1 a 6): ");
        apartamento = entrada.nextInt();

        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Numero invalido. Ingrese un apartamento entre 1 y 6: ");
            apartamento = entrada.nextInt();
        }

        System.out.print("Ingrese el consumo en m3: ");
        consumo = entrada.nextDouble();

        while (consumo <= 0) {
            System.out.print("El consumo debe ser mayor que 0. Ingrese nuevamente: ");
            consumo = entrada.nextDouble();
        }

        consumos[apartamento - 1] = consumo;

        System.out.println("Consumo registrado correctamente.");
    }

    public static void consultarFactura(double[] consumos) {

        int apartamento;

        System.out.print("Ingrese el numero del apartamento (1 a 6): ");
        apartamento = entrada.nextInt();

        while (apartamento < 1 || apartamento > 6) {
            System.out.print("Numero invalido. Ingrese un apartamento entre 1 y 6: ");
            apartamento = entrada.nextInt();
        }

        double consumo = consumos[apartamento - 1];

        if (consumo == 0) {
            System.out.println(
                    "El apartamento " + apartamento + " no tiene consumo registrado"
            );
        } else {
            imprimirFactura(apartamento, consumo);
        }
    }

    public static void imprimirFactura(int apartamento, double consumo) {

        double tarifa;

        if (consumo <= 10) {
            tarifa = 3000;
        } else if (consumo <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }

        double valorConsumo = consumo * tarifa;
        double cargoFijo = 12000;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = 0;

        if (consumo > 25) {
            recargo = subtotal * 0.10;
            System.out.println("ALERTA: consumo excesivo");
        }

        double total = subtotal + recargo;

        System.out.println("\n===== FACTURA =====");
        System.out.println("Apartamento: " + apartamento);
        System.out.println("Consumo: " + consumo + " m3");
        System.out.println("Tarifa aplicada: $" + tarifa + " por m3");
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);

        if (recargo > 0) {
            System.out.println("Recargo 10%: $" + recargo);
        } else {
            System.out.println("Recargo: $0");
        }

        System.out.println("Total a pagar: $" + total);
    }

    public static void generarReporte(double[] consumos) {

        double totalConsumo = 0;
        double mayorConsumo = 0;
        int apartamentoMayor = 0;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;

        for (int i = 0; i < consumos.length; i++) {

            if (consumos[i] > 0) {

                registrados++;
                totalConsumo += consumos[i];

                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    apartamentoMayor = i + 1;
                }

                if (consumos[i] > 25) {
                    excesivos++;
                }

            } else {
                sinRegistro++;
            }
        }

        if (registrados == 0) {
            System.out.println("Aun no hay consumos registrados");
            return;
        }

        double promedio = totalConsumo / registrados;

        System.out.println("\n===== REPORTE GENERAL =====");
        System.out.println("Consumo total: " + totalConsumo + " m3");
        System.out.println("Consumo promedio: " + promedio + " m3");
        System.out.println(
                "Apartamento con mayor consumo: " + apartamentoMayor
        );
        System.out.println(
                "Mayor consumo: " + mayorConsumo + " m3"
        );
        System.out.println(
                "Apartamentos sin registro: " + sinRegistro
        );
        System.out.println(
                "Apartamentos con consumo excesivo: " + excesivos
        );
    }

    public static void main(String[] args) {

        double[] consumos = new double[6];

        int opcion;

        do {

            mostrarMenu();

            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    registrarConsumo(consumos);
                    break;

                case 2:
                    consultarFactura(consumos);
                    break;

                case 3:
                    generarReporte(consumos);
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);

    }
}