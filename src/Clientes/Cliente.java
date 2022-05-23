/*
Número de Cliente, Nombre, Apellido1, Apellido2, Saldo, Ingresos Medios, Gastos Medios,
Dirección, Código Postal.
 */

package Clientes;

import java.io.Serializable;

public class Cliente implements Serializable, Tipos {

    //Declaro las variables de la clase.
    private int numCliente;
    private double saldo, ingresosMedios, gastosMedios;
    private String nombre, apellido1, apellido2;

    //Constructores.
    public Cliente(){}

    public Cliente(int numCliente, String nombre, String apellido1, String apellido2, double saldo, double ingresosMedios, double gastosMedios) {
        this.numCliente = numCliente;
        this.saldo = saldo;
        this.ingresosMedios = ingresosMedios;
        this.gastosMedios = gastosMedios;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    //Getter y Setter.
    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getIngresosMedios() {
        return ingresosMedios;
    }

    public void setIngresosMedios(double ingresosMedios) {
        this.ingresosMedios = ingresosMedios;
    }

    public double getGastosMedios() {
        return gastosMedios;
    }

    public void setGastosMedios(double gastosMedios) {
        this.gastosMedios = gastosMedios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Precondiciones: Se le debe pasar un cliente de la Clase cliente no nulo.
     * Método que recibe un cliente de la lista, y calcula según las especificaciones si es un cliente Vip o no.
     * @return Devuelve un booleano que indica si es Vip o no.
     * Postcondiciones: No tiene.
     */
    @Override
    public boolean calcularVIPS() {
        boolean clienteEsVip = false;
        if (this.saldo < 0 && this.ingresosMedios > 3000){
            clienteEsVip = true;
        }
        return clienteEsVip;
    }

    /**
     * Precondiciones: Se le debe pasar un cliente de la Clase cliente no nulo.
     * Método que recibe un cliente de la lista, y calcula según las especificaciones si es un cliente Robinson o no.
     * @return Devuelve un booleano que indica si es Vip o no.
     * Postcondiciones: No tiene.
     */
    @Override //Probando a simplificar el if como me dice el IntelliJ
    public boolean calcularRobinson() {
        return this.saldo > 0 && this.gastosMedios > 3000;
    }

    /**
     * Sobreescribo el método según mis especificaciones.
     * @return Un String que representa al objeto cliente.
     */
    @Override
    public String toString() {
        return String.format("Número de cliente: %d, Nombre: %s, Apellido 1: %s, " +
                "Apellido 2: %s, Saldo: %.2f, Ingresos Medios: %.2f, Gastos Medios: %.2f%n",
                numCliente, nombre, apellido1, apellido2, saldo, ingresosMedios, gastosMedios);
    }
}
