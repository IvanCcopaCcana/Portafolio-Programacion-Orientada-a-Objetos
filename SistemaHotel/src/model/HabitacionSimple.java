package model;

/**
 * HERENCIA: HabitacionSimple extiende Habitacion e implementa
 * los métodos abstractos con su lógica específica.
 *
 * POLIMORFISMO: calcularCosto() tiene comportamiento propio de este tipo.
 */
public class HabitacionSimple extends Habitacion {

    private boolean incluyeDesayuno; // ENCAPSULAMIENTO: privado

    public HabitacionSimple(int numero, double precioPorNoche, boolean incluyeDesayuno) {
        super(numero, precioPorNoche);
        this.incluyeDesayuno = incluyeDesayuno;
    }

    /**
     * POLIMORFISMO: Si incluye desayuno, agrega un 10% al precio base.
     */
    @Override
    public double calcularCosto(int noches) {
        double costo = precioPorNoche * noches;
        if (incluyeDesayuno) {
            costo += costo * 0.10;
        }
        return costo;
    }

    @Override
    public String getTipo() {
        return incluyeDesayuno ? "Simple con desayuno" : "Simple";
    }

    public boolean isIncluyeDesayuno() { return incluyeDesayuno; }
}
