import model.*;
import service.ServicioFacturacion;
import java.time.LocalDate;

/**
 * CLASE PRINCIPAL — Demostración del Sistema de Reservas de Hotel
 *
 * Conceptos de POO demostrados:
 *   Abstracción     → Habitacion (clase abstracta)
 *   Encapsulamiento → atributos privados + getters/setters en todas las clases
 *   Modularidad     → paquetes: model, service, enums
 *   Herencia        → HabitacionSimple, HabitacionDoble, Suite
 *   Composición     → Hotel posee sus Habitaciones
 *   Agregación      → Reserva referencia Cliente independiente
 *   Asociación      → Reserva usa Habitacion existente
 *   Dependencia     → ServicioFacturacion recibe Reserva como parámetro
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE RESERVAS — HOTEL CRISTAL    ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── COMPOSICIÓN: Hotel crea y posee sus Habitaciones ─────────────────
        Hotel hotel = new Hotel("Hotel Cristal", "Av. Central 100, San José", "2200-0000");

        hotel.agregarHabitacion(new HabitacionSimple(101, 80.0,  false));
        hotel.agregarHabitacion(new HabitacionSimple(102, 90.0,  true));
        hotel.agregarHabitacion(new HabitacionDoble(201, 120.0,  1));
        hotel.agregarHabitacion(new HabitacionDoble(202, 130.0,  2));
        hotel.agregarHabitacion(new Suite(301, 200.0, false));
        hotel.agregarHabitacion(new Suite(302, 250.0, true));

        System.out.println("🏨 " + hotel);
        System.out.println("\n── Habitaciones disponibles ──────────────");
        for (Habitacion h : hotel.buscarDisponibles()) {
            System.out.println("   " + h);
        }

        // ── AGREGACIÓN: Clientes existen de forma independiente ───────────────
        System.out.println("\n── Registro de clientes ──────────────────");
        Cliente cliente1 = new Cliente("C001", "Ana Ramirez", "ana@email.com", "8888-1111");
        Cliente cliente2 = new Cliente("C002", "Luis Perez",  "luis@email.com","8888-2222");
        System.out.println("   " + cliente1.getDatosContacto());
        System.out.println("   " + cliente2.getDatosContacto());

        // ── ASOCIACIÓN: Reserva usa Habitacion y Cliente ya existentes ─────────
        System.out.println("\n── Creando reservas ──────────────────────");
        Habitacion hab101  = hotel.buscarHabitacion(101);
        Habitacion suite302 = hotel.buscarHabitacion(302);

        Reserva reserva1 = new Reserva("R001", cliente1, hab101,
                LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 14));
        reserva1.confirmar();

        Reserva reserva2 = new Reserva("R002", cliente2, suite302,
                LocalDate.of(2025, 6, 12), LocalDate.of(2025, 6, 15));
        reserva2.confirmar();

        System.out.println("   " + reserva1);
        System.out.println("   " + reserva2);

        // ── ENCAPSULAMIENTO: la disponibilidad se actualiza internamente ───────
        System.out.println("\n── Disponibilidad tras reservar ──────────");
        System.out.printf("   Hab #101  disponible: %b%n", hab101.isDisponible());
        System.out.printf("   Suite #302 disponible: %b%n", suite302.isDisponible());

        // ── POLIMORFISMO: mismo método, distinto resultado según el tipo ───────
        System.out.println("\n── Polimorfismo: calcularCosto(3 noches) ─");
        Habitacion[] ejemplos = {
            new HabitacionSimple(1, 80.0,  false),
            new HabitacionDoble(2, 120.0,  1),
            new Suite(3,          200.0,  true)
        };
        for (Habitacion h : ejemplos) {
            System.out.printf("   %-22s → $%.2f%n", h.getTipo(), h.calcularCosto(3));
        }

        // ── DEPENDENCIA: ServicioFacturacion recibe Reserva como parámetro ────
        System.out.println("\n── Generando facturas ────────────────────");
        ServicioFacturacion facturacion = new ServicioFacturacion(0.13, "USD");
        facturacion.generarFactura(reserva1);
        System.out.println();
        facturacion.generarFactura(reserva2);

        // ── ENCAPSULAMIENTO: cancelar cambia estado de forma controlada ────────
        System.out.println("\n── Cancelando reserva R001 ───────────────");
        reserva1.cancelar();
        System.out.println("   Estado: " + reserva1.getEstado());
        System.out.printf("   Hab #101 disponible de nuevo: %b%n", hab101.isDisponible());

        System.out.println("\n✅ Demostracion completada.");
    }
}
