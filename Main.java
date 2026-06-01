public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIMULADOR DE TRES EN RAYA (MODO CONSOLA) ===");
        
        // 1. Inicialización de Sistemas
        MotorJuego motor = new MotorJuego();
        GestorEntradas inputManager = new GestorEntradas(motor);

        // 2. Control de Estado: Iniciar Partida
        motor.iniciarPartida();
        motor.actualizar();

        // 3. Simulación de Inputs y Bucle de juego iterativo
        inputManager.procesarComando("PONER_FICHA,1,1"); // X al centro
        motor.actualizar();

        inputManager.procesarComando("PONER_FICHA,0,0"); // O a la esquina superior izquierda
        motor.actualizar();

        // 4. Probar control de estado: Pausa y Reanudación
        inputManager.procesarComando("PAUSA");
        motor.actualizar(); // Intentará actualizar en pausa
        
        inputManager.procesarComando("REANUDAR");
        
        // Siguen los turnos
        inputManager.procesarComando("PONER_FICHA,0,1"); // X
        motor.actualizar();
        
        inputManager.procesarComando("PONER_FICHA,2,2"); // O
        motor.actualizar();

        // 5. Demostración de Guardado Rápido (Quick Save) antes del desenlace
        System.out.println("\n=== REALIZANDO QUICK SAVE SIMULADO ===");
        String partidaGuardada = motor.generarQuickSave();
        System.out.println(partidaGuardada);
        System.out.println("=======================================\n");

        // 6. Jugada final para provocar el 'Game Over' por victoria de X
        inputManager.procesarComando("PONER_FICHA,2,1"); // X completa la columna central
        motor.actualizar(); 
        
        // Comprobación de que el motor bloquea tras el Game Over
        inputManager.procesarComando("PONER_FICHA,0,2");
    }
}