package model;

/**
 * HERENCIA: HabitacionDoble hereda de Habitacion y agrega atributos propios.
 * POLIMORFISMO: calcularCosto() agrega tarifa por cama extra.
 */
public class HabitacionDoble extends Habitacion {

    private int camasExtra; // ENCAPSULAMIENTO: privado

    public HabitacionDoble(int numero, double precioPorNoche, int camasExtra) {
        super(numero, precioPorNoche);
        this.camasExtra = camasExtra;
    }

    /**
     * POLIMORFISMO: $15 adicionales por noche por cada cama extra.
     */
    @Override
    public double calcularCosto(int noches) {
        double costoBase = precioPorNoche * noches;
        double costoCamas = camasExtra * 15.0 * noches;
        return costoBase + costoCamas;
    }

    @Override
    public String getTipo() {
        return "Doble" + (camasExtra > 0 ? " (+" + camasExtra + " cama extra)" : "");
    }

    public int getCamasExtra() { return camasExtra; }
}
