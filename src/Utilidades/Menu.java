package Utilidades;

import java.util.Scanner;
public abstract class Menu {

    /**
     * Precondiciones: No tiene.
     * Método que pinta el menú por pantalla para el gestor.
     * @return Devuelve un entero.
     * Postcondiciones: El entero representa la opción elegida.
     */
    public static int mostrarMenu() {
        int opcion;
        System.out.println("""
                ----------DATOS CLIENTES----------
                [1] Clientes Saldo 0.
                [2] Clientes Crédito.
                [3] Clientes Débito.
                [4] Clientes VIP'S.
                [5] Clientes Robinson.
                [0] Salir.
                ----------------------------------
                """);
        opcion = leerNumero();
        return opcion;
    }

    /**
     * Precondiciones: Debemos haber introducido una opción válida para que funcione.
     * Gestor del menú. Según la selección del cliente llama a un método u otro.
     * Postcondiciones: Al acabar una de las opciones, vuelve a mostrar el menú, y si no eliges una en el rango, pide otra.
     */
    public static void elegirMenu() {
        boolean salir = false;
        while (!salir) {
            switch (mostrarMenu()) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    GestionarFicherosSalida.clienteSaldoCero();
                    break;
                case 2:
                    GestionarFicherosSalida.clienteConCredito();
                    break;
                case 3:
                    GestionarFicherosSalida.clientesConDebito();
                    break;
                case 4:
                    GestionarFicherosSalida.clientesVips();
                    break;
                case 5:
                    GestionarFicherosSalida.clientesRobinson();
                    break;
                default:
                    System.out.println("No es una opción válida de menú. Pruebe otra por favor.");
            }
        }
    }

    /**
     * Precondiciones: No tiene.
     * Método de comprobación para la entrada de datos al programa.
     * @return Devuelve un entero que nos sirve para el menú y además comprobamos su validez.
     * Postcondiciones: Siempre sale un entero.
     */
    public static int leerNumero() {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        boolean salir = false;
        do {
            try {
                numero = sc.nextInt();
                salir = true;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Esto no es un número entero.");
            }
        } while (!salir);
        return numero;
    }
}
