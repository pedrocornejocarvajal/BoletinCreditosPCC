package Utilidades;

import Clientes.Cliente;
import static Utilidades.GestionarFicherosEntrada.*;
import java.io.*;

public abstract class GestionarFicherosSalida {

    //Variables de la clase.
    private static final String BINARIO = "src/Archivos/Clientes.dat";
    private static final String BINARIOCOMPLETO = "src/Archivos/Clientes y direcciones.dat";
    private static final String SALDOCERO = "src/Archivos/SaldoCero";
    private static final String CREDITO = "src/Archivos/Crédito";
    private static final String DEBITO = "src/Archivos/Débito";
    private static final String VIPS = "src/Archivos/Vips";
    private static final String ROBINSONS = "src/Archivos/Robinsons";
    private static ObjectInputStream lectura = null;
    private static ObjectOutputStream escritura = null;
    private static BufferedWriter escrituraTxt = null;
    private static Cliente cliente;

    /**
     * Precondiciones: Debe existir el fichero binario con los clientes en el.
     * Procesa el fichero binario de entrada de datos de clientes para sacar una lista con todos los clientes
     * cuyo saldo en cuenta es 0.
     * Postcondiciones: Crea un fichero, tanto de datos como binario por si es necesario, con los clientes pedidos.
     */
    public static void clienteSaldoCero (){
        procesarPdf();
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(SALDOCERO + ".dat")); //Para sacarlo en un binario
            escrituraTxt = new BufferedWriter(new FileWriter(SALDOCERO + ".txt")); //para escribirlo en un txt
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()==0) {
                    escritura.writeObject(cliente);
                    escrituraTxt.write(cliente.toString());
                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            cerrarFlujoDatos();
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    /**
     * Precondiciones: Debe existir el fichero binario con los clientes en el.
     * Procesa el fichero binario de entrada de datos de clientes para sacar una lista con todos los clientes
     * cuyo saldo es menor que 0, o sea, poseen crédito con la empresa.
     * Postcondiciones: Crea un fichero, tanto de datos como binario por si es necesario, con los clientes pedidos.
     */
    public static void clienteConCredito (){
        procesarPdf();
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(CREDITO + ".dat")); //Para sacarlo en un binario
            escrituraTxt = new BufferedWriter(new FileWriter(CREDITO + ".txt")); //para escribirlo en un txt
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()<0) {
                    escritura.writeObject(cliente);
                    escrituraTxt.write(cliente.toString());
                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            cerrarFlujoDatos();
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    /**
     * Precondiciones: Debe existir el fichero binario con los clientes en el.
     * Procesa el fichero binario de entrada de datos de clientes para sacar una lista con todos los clientes
     * cuyo saldo es mayor que 0, o sea, poseen débito con la empresa.
     * Postcondiciones: Crea un fichero, tanto de datos como binario por si es necesario, con los clientes pedidos.
     */
    public static void clientesConDebito (){
        procesarPdf();
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIO));
            escritura = new ObjectOutputStream(new FileOutputStream(DEBITO + ".dat")); //Para sacarlo en un binario
            escrituraTxt = new BufferedWriter(new FileWriter(DEBITO + ".txt")); //para escribirlo en un txt
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.getSaldo()>0) {
                    escritura.writeObject(cliente);
                    escrituraTxt.write(cliente.toString());
                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            cerrarFlujoDatos();
        }
        System.out.println("Archivo procesado y generado correctamente. Busque en la carpeta Archivos.");
    }

    /**
     * Precondiciones: Debe existir el fichero binario con los clientes en el.
     * Procesa el fichero binario de entrada de datos de clientes para sacar una lista con todos los clientes
     * cuyo saldo es con crédito y cuyos ingresos medios sean mayores a 3000.
     * Postcondiciones: Crea un fichero, tanto de datos como binario por si es necesario, con los clientes pedidos.
     */
    public static void clientesVips (){
        procesarPdfCompleto();
        int contador = 0;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIOCOMPLETO));
            escritura = new ObjectOutputStream(new FileOutputStream(VIPS + ".dat")); //Para sacarlo en un binario
            escrituraTxt = new BufferedWriter(new FileWriter(VIPS + ".txt")); //para escribirlo en un txt
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.calcularVIPS()) {
                    escritura.writeObject(cliente);
                    escrituraTxt.write(cliente.toString());
                    contador++;
                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            cerrarFlujoDatos();
        }
        System.out.printf("Hay un total de %d clientes Vips.%n", contador);
    }

    /**
     * Precondiciones: Debe existir el fichero binario con los clientes en el.
     * Procesa el fichero binario de entrada de datos de clientes para sacar una lista con todos los clientes
     * cuyo saldo es con débito y cuyos gastos medios sean mayores a 3000.
     * Postcondiciones: Crea un fichero, tanto de datos como binario por si es necesario, con los clientes pedidos.
     */
    public static void clientesRobinson (){
        procesarPdfCompleto();
        int contador = 0;
        try {
            lectura = new ObjectInputStream(new FileInputStream(BINARIOCOMPLETO));
            escritura = new ObjectOutputStream(new FileOutputStream(ROBINSONS + ".dat")); //Para sacarlo en un binario
            escrituraTxt = new BufferedWriter(new FileWriter(ROBINSONS + ".txt")); //para escribirlo en un txt
            boolean fin = false;
            cliente = (Cliente)lectura.readObject();
            while (!fin) {
                if (cliente.calcularRobinson()) {
                    escritura.writeObject(cliente);
                    escrituraTxt.write(cliente.toString());
                    contador++;
                }
                try{
                    cliente = (Cliente) lectura.readObject();
                }catch (Exception e){
                    fin = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no se ha encontrado.");
        } finally {
            cerrarFlujoDatos();
        }
        System.out.printf("Hay un total de %d clientes Robinson.%n", contador);
    }

    /**
     * Precondiciones: Debe existir el flujo de datos abierto para poder cerrarlo.
     * Cierra los flujos de datos de las clases "closeable" utulizadas.
     * Postcondiciones: Cierra todos los flujos de datos del programa.
     */
    private static void cerrarFlujoDatos() {
        try {
            if (lectura!=null){
                lectura.close();
            }
            if (escrituraTxt!=null){
                escrituraTxt.close();
            }
            if (escritura!=null){
                escritura.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo de lectura.");
        }
    }
}
