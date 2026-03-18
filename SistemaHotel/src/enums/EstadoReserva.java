package enums;

/**
 * MODULARIDAD: Este enum encapsula los posibles estados de una reserva
 * en un módulo separado, evitando el uso de "magic strings" en el código.
 */
public enum EstadoReserva {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    COMPLETADA
}
