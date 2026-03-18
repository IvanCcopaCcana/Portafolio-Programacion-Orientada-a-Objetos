package model;

import enums.EstadoReserva;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * ASOCIACIÓN: Reserva referencia a Cliente y Habitacion existentes.
 *   - Con Cliente: AGREGACIÓN — el cliente existe de forma independiente.
 *   - Con Habitacion: ASOCIACIÓN — la habitación existe antes y después.
 *
 * ENCAPSULAMIENTO: El estado se maneja internamente; no se puede cambiar
 * directamente desde fuera, solo a través de confirmar(), cancelar(), completar().
 */
public class Reserva {

    private String idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private EstadoReserva estado;

    // AGREGACIÓN: Cliente existe independientemente de esta Reserva
    private Cliente cliente;

    // ASOCIACIÓN: Habitacion existe antes y después de la Reserva
    private Habitacion habitacion;

    public Reserva(String idReserva, Cliente cliente, Habitacion habitacion,
                   LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoReserva.PENDIENTE;
        habitacion.setDisponible(false); // Bloquea la habitación al reservar
    }

    /**
     * POLIMORFISMO: delega en habitacion.calcularCosto() —
     * el tipo real de habitación determina el cálculo.
     */
    public double calcularTotal() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        return habitacion.calcularCosto((int) noches);
    }

    /** ENCAPSULAMIENTO: lógica de cancelación centralizada aquí. */
    public void cancelar() {
        if (this.estado != EstadoReserva.CANCELADA) {
            this.estado = EstadoReserva.CANCELADA;
            habitacion.setDisponible(true);
        }
    }

    public void confirmar() {
        if (this.estado == EstadoReserva.PENDIENTE) {
            this.estado = EstadoReserva.CONFIRMADA;
        }
    }

    public void completar() {
        if (this.estado == EstadoReserva.CONFIRMADA) {
            this.estado = EstadoReserva.COMPLETADA;
            habitacion.setDisponible(true);
        }
    }

    // Getters
    public String getIdReserva()       { return idReserva; }
    public Cliente getCliente()        { return cliente; }
    public Habitacion getHabitacion()  { return habitacion; }
    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public LocalDate getFechaSalida()  { return fechaSalida; }
    public EstadoReserva getEstado()   { return estado; }

    @Override
    public String toString() {
        return String.format(
            "Reserva[%s] | %s | Hab.#%d (%s) | %s -> %s | Total: $%.2f | Estado: %s",
            idReserva, cliente.getNombre(), habitacion.getNumero(),
            habitacion.getTipo(), fechaEntrada, fechaSalida,
            calcularTotal(), estado);
    }
}
