package model;

import java.util.ArrayList;
import java.util.List;

/**
 * COMPOSICIÓN: Hotel contiene una lista de Habitaciones.
 * Si el Hotel se destruye, sus Habitaciones también desaparecen —
 * Hotel es el único dueño de esa colección.
 *
 * ENCAPSULAMIENTO: la lista de habitaciones es privada y solo se
 * manipula mediante métodos públicos controlados.
 */
public class Hotel {

    private String nombre;
    private String direccion;
    private String telefono;

    // COMPOSICIÓN: la lista nace y muere con el Hotel
    private List<Habitacion> habitaciones;

    public Hotel(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.habitaciones = new ArrayList<>();
    }

    /** COMPOSICIÓN: agrega una habitación al inventario del hotel. */
    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    /** ABSTRACCIÓN: el cliente no necesita conocer la estructura interna. */
    public List<Habitacion> buscarDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.isDisponible()) {
                disponibles.add(h);
            }
        }
        return disponibles;
    }

    public Habitacion buscarHabitacion(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) return h;
        }
        return null;
    }

    // Getters
    public String getNombre()    { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono()  { return telefono; }

    public List<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones); // copia defensiva
    }

    @Override
    public String toString() {
        return String.format("Hotel: %s | Dir: %s | Habitaciones: %d",
                nombre, direccion, habitaciones.size());
    }
}
