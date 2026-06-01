public abstract class EntidadVideojuego {
    protected String nombre;
    protected int x, y, w, h;
    protected String estado; // "ACTIVO", "ELIMINADO", "X", "O"
    protected String imagenSimulada; // Ruta para la futura GUI

    public EntidadVideojuego(String nombre, int x, int y, int w, int h, String estado, String imagenSimulada) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.estado = estado;
        this.imagenSimulada = imagenSimulada;
    }

    public abstract void actualizar();

    // Getters y Setters rápidos para la lógica
    public String getNombre() { return nombre; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}