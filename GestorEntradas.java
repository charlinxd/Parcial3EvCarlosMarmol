public class GestorEntradas {
    private MotorJuego motor;

    public GestorEntradas(MotorJuego motor) {
        this.motor = motor;
    }

    public void procesarComando(String comando) {
        System.out.println("\n>>> INPUT RECIBIDO: " + comando);
        
        if (comando.startsWith("PONER_FICHA")) {
            // Formato esperado: "PONER_FICHA,fila,columna" -> Ejemplo: "PONER_FICHA,1,2"
            String[] partes = comando.split(",");
            int fila = Integer.parseInt(partes[1]);
            int col = Integer.parseInt(partes[2]);
            motor.ponerFicha(fila, col);
        } else if (comando.equals("PAUSA")) {
            motor.cambiarEstado("PAUSA");
        } else if (comando.equals("REANUDAR")) {
            motor.cambiarEstado("JUGANDO");
        } else if (comando.equals("REINICIAR")) {
            motor.cambiarEstado("MENU");
        }
    }
}