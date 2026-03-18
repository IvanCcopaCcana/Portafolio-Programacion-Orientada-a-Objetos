package model;

/**
 * ENCAPSULAMIENTO: Todos los atributos son privados, accesibles solo
 * mediante getters y setters con validación.
 *
 * MODULARIDAD: Cliente es una entidad independiente — puede existir sin
 * ninguna Reserva (lo que justifica la AGREGACIÓN con Reserva).
 */
public class Cliente {

    private String id;
    private String nombre;
    private String email;
    private String telefono;

    public Cliente(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * ENCAPSULAMIENTO: expone un resumen de contacto sin revelar atributos internos.
     */
    public String getDatosContacto() {
        return String.format("Nombre: %s | Email: %s | Tel: %s", nombre, email, telefono);
    }

    // Getters
    public String getId()       { return id; }
    public String getNombre()   { return nombre; }
    public String getEmail()    { return email; }
    public String getTelefono() { return telefono; }

    // Setters con validación
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return String.format("Cliente[%s] %s", id, nombre);
    }
}
