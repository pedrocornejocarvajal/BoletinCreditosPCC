package Utilidades;

import Clientes.Cliente;
import Clientes.ClienteAmpliado;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class GestionarFicherosEntrada {

    //Variables de la clase.
    private static final String ORIGEN = "src/Archivos/Clientes.txt";
    private static final String BINARIO = "src/Archivos/Clientes";
    private static final String BINARIOCOMPLETO = "src/Archivos/Clientes y direcciones";
    private static BufferedReader lectura = null;
    private static ObjectOutputStream salida = null;
    private static String linea;

    /**
     * Precondiciones: Debe existir un fichero de entrada de texto con los datos (TXT).
     * Lee el fichero de texto línea a línea (lo que representa a un cliente con todos sus datos) y a través
     * de otra función las transforma en un objeto de la clase Cliente y los escribe en un fichero binario.
     * Registra a los clientes sin su dirección ni código postal.
     * Postcondiciones: Deja un fichero binario preparado con todos los clientes para su posterior uso.
     */
    public static void procesarPdf (){
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            salida = new ObjectOutputStream(new FileOutputStream(BINARIO + ".dat"));
            linea = lectura.readLine();
            while (linea!=null){
                salida.writeObject(sacarCliente(linea));
                linea = lectura.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (salida!=null){
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
    }

    /**
     * Precondiciones: Debe existir un fichero de entrada de texto con los datos (TXT).
     * Lee el fichero de texto línea a línea (lo que representa a un cliente con todos sus datos) y a través
     * de otra función las transforma en un objeto de la clase Cliente y los escribe en un fichero binario.
     * Procesa a los clientes con todos sus datos.
     * Postcondiciones: Deja un fichero binario preparado con todos los clientes para su posterior uso.
     */
    public static void procesarPdfCompleto (){
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            salida = new ObjectOutputStream(new FileOutputStream(BINARIOCOMPLETO + ".dat"));
            linea = lectura.readLine();
            while (linea!=null){
                salida.writeObject(sacarClienteAmpliado(linea));
                linea = lectura.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
                if (salida!=null){
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
    }

    /**
     * Precondiciones: Debe recibir una línea completa del fichero de texto original.
     * Procesa la línea que recibe y la transforma en un objeto cliente, sacando cada uno de los datos por sus
     * tokens separadores, y creando, dato a dato, el objeto cliente necesario. Procesa sin dirección ni código postal.
     * @param entrada Un String que representa a un cliente con sus datos.
     * @return Devuelve un objeto Cliente con sus datos.
     * Postcondiciones: No tiene.
     */
    public static Cliente sacarCliente (String entrada){
        String nombre;
        Scanner scanner = new Scanner(entrada);
        Cliente cliente = new Cliente();
        cliente.setNumCliente(scanner.nextInt());
        nombre = scanner.next();
        if (scanner.hasNext("[a-z]+")){
            nombre += " " + scanner.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(scanner.next());
        cliente.setApellido2(scanner.next());
        cliente.setSaldo(scanner.nextDouble());
        cliente.setIngresosMedios(scanner.nextDouble());
        cliente.setGastosMedios(scanner.nextDouble());
        return cliente;
    }

    /**
     * Precondiciones: Debe recibir una línea completa del fichero de texto original.
     * Procesa la línea que recibe y la transforma en un objeto cliente, sacando cada uno de los datos por sus
     * tokens separadores, y creando, dato a dato, el objeto cliente necesario. Procesa el cliente completo.
     * @param entrada Un String que representa a un cliente con sus datos.
     * @return Devuelve un objeto Cliente con sus datos.
     * Postcondiciones: No tiene.
     */
    public static Cliente sacarClienteAmpliado(String entrada){
        String nombre, direccion;
        Scanner scanner = new Scanner(entrada);
        ClienteAmpliado cliente = new ClienteAmpliado();
        cliente.setNumCliente(scanner.nextInt());
        nombre = scanner.next();
        if (scanner.hasNext("[a-z]+")){
            nombre += " " + scanner.next();
        }
        cliente.setNombre(nombre);
        cliente.setApellido1(scanner.next());
        cliente.setApellido2(scanner.next());
        cliente.setSaldo(scanner.nextDouble());
        cliente.setIngresosMedios(scanner.nextDouble());
        cliente.setGastosMedios(scanner.nextDouble());
        direccion = scanner.next();
        while (scanner.hasNext(Pattern.compile("[^0-9]+"))){
            direccion += " " + scanner.next();
        }
        cliente.setDireccion(direccion);
        cliente.setCodPostal(scanner.nextInt());
        return cliente;
    }

        /* Método para sacarlo a través de un ArrayList. Útil también tenerlo en cuenta.
    public static ArrayList<Cliente> leerPdfList (){
        ArrayList<Cliente> lista = new ArrayList<>();
        BufferedReader lectura = null;
        String linea;
        try {
            lectura = new BufferedReader(new FileReader(ORIGEN));
            linea = lectura.readLine();
            while (linea!=null){
                lista.add(sacarClienteAmpliado(linea));
                linea = lectura.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de lectura no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer del archivo.");
        } finally {
            try {
                if (lectura!=null){
                    lectura.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de lectura.");
            }
        }
        return lista;
    }*/
}
