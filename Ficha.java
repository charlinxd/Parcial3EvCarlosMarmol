public class Ficha extends EntidadVideojuego {
    
    public Ficha(String tipoFicha, int fila, int columna) {
        // w=1, h=1 porque ocupa una casilla. La "vida/energía" se simula con el tipo de estado.
        super("Ficha_" + tipoFicha, fila, columna, 1, 1, tipoFicha, "sprite_" + tipoFicha + ".png");
    }

    @Override
    public void actualizar() {
        // En el Tres en Raya las fichas son estáticas, pero registramos su presencia en el log
        System.out.println("   [Log Ficha] Ficha " + estado + " posicionada firmemente en (" + x + ", " + y + ").");
    }
}