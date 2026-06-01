import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private String estadoGeneral; // MENU, JUGANDO, PAUSA, GAME_OVER
    private List<EntidadVideojuego> entidades;
    private String turnoActual; // "X" o "O"
    private int movimientos;
    private boolean logroDesbloqueado = false;

    public MotorJuego() {
        this.estadoGeneral = "MENU";
        this.entidades = new ArrayList<>();
        this.turnoActual = "X";
        this.movimientos = 0;
    }

    public void iniciarPartida() {
        System.out.println("[Motor] Iniciando nueva partida de Tres en Raya...");
        entidades.clear();
        movimientos = 0;
        turnoActual = "X";
        estadoGeneral = "JUGANDO";
        logroDesbloqueado = false;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estadoGeneral = nuevoEstado;
        System.out.println("[Motor] Estado del juego cambiado a: " + estadoGeneral);
    }

    // Simulación del Game Loop
    public void actualizar() {
        if (!estadoGeneral.equals("JUGANDO")) {
            System.out.println("[Game Loop] Juego en pausa o no iniciado. Estado actual: " + estadoGeneral);
            return;
        }

        System.out.println("\n--- [START GAME LOOP ITERATION] ---");
        // Recorrer y actualizar entidades
        for (EntidadVideojuego entidad : entidades) {
            entidad.actualizar();
        }
        
        // Renderizado simulado por consola del tablero
        dibujarTableroSimulado();
        
        // Verificar Sistema de Logros
        verificarLogros();
        System.out.println("--- [END GAME LOOP ITERATION] ---\n");
    }

    public void ponerFicha(int fila, int col) {
        if (!estadoGeneral.equals("JUGANDO")) {
            System.out.println("[Motor] No se pueden poner fichas. El juego no está en modo JUGANDO.");
            return;
        }

        // Validar si ya existe una ficha en esa coordenada
        for (EntidadVideojuego e : entidades) {
            if (e.getX() == fila && e.getY() == col) {
                System.out.println("[Motor] ¡Movimiento inválido! Casilla (" + fila + "," + col + ") ocupada.");
                return;
            }
        }

        // Gestión de Entidades Básica: Añadir Entidad
        Ficha nuevaFicha = new Ficha(turnoActual, fila, col);
        entidades.add(nuevaFicha);
        movimientos++;
        System.out.println("[Motor] " + turnoActual + " ha colocado una ficha en (" + fila + "," + col + ")");

        // Comprobar si hay victoria
        if (comprobarVictoria(turnoActual)) {
            dibujarTableroSimulado();
            System.out.println("[Motor] ¡¡¡ GAME OVER !!! El jugador " + turnoActual + " ha ganado.");
            cambiarEstado("GAME_OVER");
        } else if (movimientos == 9) {
            dibujarTableroSimulado();
            System.out.println("[Motor] ¡¡¡ GAME OVER !!! Empate técnico.");
            cambiarEstado("GAME_OVER");
        } else {
            // Cambiar turno
            turnoActual = turnoActual.equals("X") ? "O" : "X";
        }
    }

    private boolean comprobarVictoria(String jugador) {
        int[][] tablero = new int[3][3];
        for (EntidadVideojuego e : entidades) {
            if (e.getEstado().equals(jugador)) {
                tablero[e.getX()][e.getY()] = 1;
            }
        }
        // Comprobación rápida de filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == 1 && tablero[i][1] == 1 && tablero[i][2] == 1) return true;
            if (tablero[0][i] == 1 && tablero[1][i] == 1 && tablero[2][i] == 1) return true;
        }
        if (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 1) return true;
        if (tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 1) return true;
        
        return false;
    }

    // Sistema de Logros/Recompensas
    private void verificarLogros() {
        if (!logroDesbloqueado && movimientos >= 1) {
            // Condición: Poner la primera ficha  (Iniciar las hostilidades)
            System.out.println("[LOGRO DESBLOQUEADO] -> 'Primer de muchos': Has colocado la primera pieza en el tablero.");
            logroDesbloqueado = true;
        }
    }

    // Guardado Rápido Simulado (Quick Save) a String Formateado
    public String generarQuickSave() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"estadoGeneral\":\"").append(estadoGeneral).append("\",");
        sb.append("\"turnoActual\":\"").append(turnoActual).append("\",");
        sb.append("\"movimientos\":").append(movimientos).append(",");
        sb.append("\"entidades\":[");
        for (int i = 0; i < entidades.size(); i++) {
            EntidadVideojuego e = entidades.get(i);
            sb.append(String.format("{\"tipo\":\"%s\",\"x\":%d,\"y\":%d}", e.getEstado(), e.getX(), e.getY()));
            if (i < entidades.size() - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }

    private void dibujarTableroSimulado() {
        String[][] panel = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
        for (EntidadVideojuego e : entidades) {
            panel[e.getX()][e.getY()] = e.getEstado();
        }
        System.out.println("  Tablero Actual:");
        System.out.println("    0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + i + " [" + panel[i][0] + "][" + panel[i][1] + "][" + panel[i][2] + "]");
        }
    }
}