import java.util.Scanner;

public class TicTacToe {

    static final String LINEA = "=======================================";

    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        char[][] tablero = new char[3][3];
        int movimientos;
        char jugadorActual;
        char estadoGanador;
        boolean jugarDeNuevo = true;
        
        while (jugarDeNuevo) {

            for (int f = 0; f < 3; f++) {
                for (int c = 0; c < 3; c++) {
                    tablero[f][c] = (char) ('1' + f * 3 + c);
                }
            }

            jugadorActual = 'X';
            boolean jugando = true;
            movimientos = 0;
            estadoGanador = ' ';

            while (jugando) {
                limpiarPantalla();

                System.out.println(LINEA);
                System.out.println("              TIC TAC TOE              ");
                System.out.println(LINEA);
                System.out.println("INSTRUCCIONES:");
                System.out.println("1. El juego es para dos jugadores: X y O.");
                System.out.println("2. Para tirar, ingresa un número (1-9).");
                System.out.println("3. Gana el primero en hacer una línea de 3 (ya sea vertical, horizontal o diagonal).");
                System.out.println(LINEA);
                System.out.println();

                mostrarTablero(tablero);
                System.out.println();

                boolean inputValido = false;
                while (!inputValido) {
                    System.out.println("Turno del jugador [ " + jugadorActual + " ].");
                    System.out.print("Ingresa el número de casilla (1-9): ");

                    int posicion;
                    try {
                        posicion = Integer.parseInt(entrada.nextLine().trim());
                    } catch (NumberFormatException e) {
                        posicion = 0; 
                    }

                    if (posicion >= 1 && posicion <= 9) {
                        int fila = (posicion - 1) / 3;
                        int col = (posicion - 1) % 3;

                        if (tablero[fila][col] != 'X' && tablero[fila][col] != 'O') {
                            tablero[fila][col] = jugadorActual;
                            inputValido = true;
                            movimientos++;
                        } else {
                            System.out.println("- ERROR: ¡Casilla ocupada! Escoge otra.");
                        }
                    } else {
                        System.out.println("- ERROR: Número inválido. Elegir un número del 1 al 9.");
                    }
                }

                estadoGanador = ' ';

                for (int i = 0; i < 3; i++) {
                    if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                        estadoGanador = tablero[i][0];
                    }
                    if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                        estadoGanador = tablero[0][i];
                    }
                }

                if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
                    estadoGanador = tablero[0][0];
                }
                if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
                    estadoGanador = tablero[0][2];
                }

                if (estadoGanador != ' ') {
                    jugando = false;
                } else if (movimientos == 9) {
                    estadoGanador = 'E'; 
                    jugando = false;
                } else {
                    jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
                }
            }

            limpiarPantalla();
            System.out.println(LINEA);
            System.out.println("            FIN DEL JUEGO              ");
            System.out.println(LINEA);
            System.out.println();
            mostrarTablero(tablero);
            System.out.println();

            if (estadoGanador == 'E') {
                System.out.println("       ¡ES UN EMPATE!         ");
            } else {
                System.out.println("   ¡FELICIDADES! EL JUGADOR [ " + estadoGanador + " ] GANA.");
            }
            System.out.println(LINEA);

            System.out.println();
            System.out.print("¿Quieren jugar otra partida? (Si/No): ");
            String respuesta = entrada.nextLine().trim();

            if (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("sí")) {
                jugarDeNuevo = true;
            } else {
                jugarDeNuevo = false;
                System.out.println("¡Gracias por jugar!");
            }
        }

        entrada.close();
    }

    static void mostrarTablero(char[][] tablero) {
        for (int f = 0; f < 3; f++) {
            System.out.println("        " + tablero[f][0] + " | " + tablero[f][1] + " | " + tablero[f][2]);
            if (f < 2) {
                System.out.println("       ---+---+---");
            }
        }
    }

    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
