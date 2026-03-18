package model;

/**
 * HERENCIA: Suite extiende Habitacion con características premium.
 * POLIMORFISMO: Incluye recargo del 20% y tarifa adicional por jacuzzi.
 */
public class Suite extends Habitacion {

    private boolean tieneJacuzzi; // ENCAPSULAMIENTO: privado

    public Suite(int numero, double precioPorNoche, boolean tieneJacuzzi) {
        super(numero, precioPorNoche);
        this.tieneJacuzzi = tieneJacuzzi;
    }

    /**
     * POLIMORFISMO: recargo del 20% por ser suite y $50 extra por jacuzzi.
     */
    @Override
    public double calcularCosto(int noches) {
        double costo = precioPorNoche * noches * 1.20;
        if (tieneJacuzzi) {
            costo += 50.0 * noches;
        }
        return costo;
    }

    @Override
    public String getTipo() {
        return tieneJacuzzi ? "Suite con jacuzzi" : "Suite";
    }

    public boolean isTieneJacuzzi() { return tieneJacuzzi; }
}
