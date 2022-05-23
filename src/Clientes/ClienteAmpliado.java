package Clientes;

public class ClienteAmpliado extends Cliente {

    //Variables de propias de la subclase.
    private String direccion;
    private int codPostal;

    //Constructores.
    public ClienteAmpliado() {}

    public ClienteAmpliado(int numCliente, String nombre, String apellido1, String apellido2, double saldo, double ingresosMedios, double gastosMedios, String direccion, int codPostal) {
        super(numCliente, nombre, apellido1, apellido2, saldo, ingresosMedios, gastosMedios);
        this.direccion = direccion;
        this.codPostal = codPostal;
    }

    //Getter y Setter.
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Sobreescribo el método según mis especificaciones.
     * @return Un String que representa al objeto cliente ampliado.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Dirección: %s, Código Postal: %d.%n", direccion, codPostal);
    }
}
