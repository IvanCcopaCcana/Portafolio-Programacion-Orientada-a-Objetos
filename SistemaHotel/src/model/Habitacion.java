package model;

/**
 * ABSTRACCIÓN: Habitacion es una clase abstracta que define la estructura
 * general de cualquier tipo de habitación del hotel. No se puede instanciar
 * directamente — obliga a crear tipos concretos (Simple, Doble, Suite).
 *
 * ENCAPSULAMIENTO: Los atributos están declarados como 'protected' para que
 * las subclases puedan accederlos, pero no clases externas. Se exponen
 * únicamente a través de getters/setters controlados.
 *
 * MODULARIDAD: Esta clase vive en el paquete 'model', separada de la lógica
 * de negocio y de los servicios.
 */
public abstract class Habitacion {

    // ENCAPSULAMIENTO: atributos protegidos, no accesibles directamente desde fuera
    protected int numero;
    protected double precioPorNoche;
    protected boolean disponible;

    public Habitacion(int numero, double precioPorNoche) {
        this.numero = numero;
        this.precioPorNoche = precioPorNoche;
        this.disponible = true;
    }

    // ABSTRACCIÓN: métodos abstractos que cada subclase debe implementar
    public abstract double calcularCosto(int noches);
    public abstract String getTipo();

    // ENCAPSULAMIENTO: getters y setters
    public int getNumero() { return numero; }

    public double getPrecioPorNoche() { return precioPorNoche; }

    public void setPrecioPorNoche(double precioPorNoche) {
        if (precioPorNoche > 0) {
            this.precioPorNoche = precioPorNoche;
        }
    }

    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return String.format("Habitacion #%d [%s] - $%.2f/noche - %s",
                numero, getTipo(), precioPorNoche,
                disponible ? "Disponible" : "Ocupada");
    }
}
