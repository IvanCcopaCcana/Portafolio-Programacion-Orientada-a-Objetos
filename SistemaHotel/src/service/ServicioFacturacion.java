package service;

import model.Reserva;
import model.Cliente;

/**
 * DEPENDENCIA: ServicioFacturacion depende de Reserva pero NO la posee.
 * La recibe como parámetro — relación de uso temporal, no de propiedad.
 *
 * MODULARIDAD: Vive en el paquete 'service', con una única responsabilidad:
 * generar y enviar facturas.
 *
 * ENCAPSULAMIENTO: Los detalles del cálculo de impuesto y formato
 * están ocultos en métodos privados.
 */
public class ServicioFacturacion {

    private double tarifaImpuesto;
    private String moneda;

    public ServicioFacturacion(double tarifaImpuesto, String moneda) {
        this.tarifaImpuesto = tarifaImpuesto;
        this.moneda = moneda;
    }

    /**
     * DEPENDENCIA: recibe una Reserva como parámetro; no la almacena.
     * Solo la usa durante la ejecución de este método.
     */
    public void generarFactura(Reserva reserva) {
        double subtotal = reserva.calcularTotal();
        double impuesto = calcularImpuesto(subtotal);
        double total    = subtotal + impuesto;

        System.out.println("=".repeat(50));
        System.out.println("           FACTURA DE RESERVA");
        System.out.println("=".repeat(50));
        System.out.printf("Reserva ID   : %s%n", reserva.getIdReserva());
        System.out.printf("Cliente      : %s%n", reserva.getCliente().getNombre());
        System.out.printf("Habitacion   : #%d - %s%n",
                reserva.getHabitacion().getNumero(),
                reserva.getHabitacion().getTipo());
        System.out.printf("Check-in     : %s%n", reserva.getFechaEntrada());
        System.out.printf("Check-out    : %s%n", reserva.getFechaSalida());
        System.out.printf("Estado       : %s%n", reserva.getEstado());
        System.out.println("-".repeat(50));
        System.out.printf("Subtotal     : %s %.2f%n", moneda, subtotal);
        System.out.printf("Impuesto     : %s %.2f (%.0f%%)%n", moneda, impuesto, tarifaImpuesto * 100);
        System.out.printf("TOTAL        : %s %.2f%n", moneda, total);
        System.out.println("=".repeat(50));

        enviarFactura(reserva.getCliente(), total);
    }

    // ENCAPSULAMIENTO: método privado, lógica interna no expuesta
    private double calcularImpuesto(double subtotal) {
        return subtotal * tarifaImpuesto;
    }

    // DEPENDENCIA: usa Cliente solo localmente, sin almacenarlo
    private void enviarFactura(Cliente cliente, double total) {
        System.out.printf("Factura enviada a: %s (Total: %s %.2f)%n",
                cliente.getEmail(), moneda, total);
    }

    public double getTarifaImpuesto() { return tarifaImpuesto; }
    public String getMoneda()         { return moneda; }
}
