import os

def limpiar_pantalla():

    os.system('cls' if os.name == 'nt' else 'clear')

def main():
    jugar_de_nuevo = True
    
    
    while jugar_de_nuevo:
        
        tablero = [
            ["1", "2", "3"],
            ["4", "5", "6"],
            ["7", "8", "9"]
        ]
        
        jugador_actual = "X"
        jugando = True
        movimientos = 0
        estado_ganador = " "
        
        
        while jugando:
            limpiar_pantalla()
            
            print("=======================================")
            print("              TIC TAC TOE              ")
            print("=======================================")
            print("INSTRUCCIONES:")
            print("1. El juego es para dos jugadores: X y O.")
            print("2. Para tirar, ingresa un numero (1-9).")
            print("3. Gana el primero en hacer una linea de 3 (ya sea vertical, horizontal o diagonal).")
            print("=======================================\n")
            
           
            for f in range(3):
                print(f"        {tablero[f][0]} | {tablero[f][1]} | {tablero[f][2]}")
                if f < 2:
                    print("       ---+---+---")
            print()
            
            input_valido = False
            
            
            while not input_valido:
                try:
                    posicion = int(input(f"Turno del jugador [ {jugador_actual} ]. Ingresa el numero de casilla (1-9): "))
                    
                    if 1 <= posicion <= 9:
                        fila = (posicion - 1) // 3
                        col = (posicion - 1) % 3
                        
                        if tablero[fila][col] != "X" and tablero[fila][col] != "O":
                            tablero[fila][col] = jugador_actual
                            input_valido = True
                            movimientos += 1
                        else:
                            print("- ERROR: ¡Casilla ocupada! Escoge otra.")
                    else:
                        print("- ERROR: Numero invalido. Elegir un numero del 1 al 9.")
                except ValueError:
                    print("- ERROR: Por favor, introduce un número válido.")
            
            
            estado_ganador = " "
            for i in range(3):
                if tablero[i][0] == tablero[i][1] == tablero[i][2]:
                    estado_ganador = tablero[i][0]
                if tablero[0][i] == tablero[1][i] == tablero[2][i]:
                    estado_ganador = tablero[0][i]
            
           
            if tablero[0][0] == tablero[1][1] == tablero[2][2]:
                estado_ganador = tablero[0][0]
            if tablero[0][2] == tablero[1][1] == tablero[2][0]:
                estado_ganador = tablero[0][2]
            
           
            if estado_ganador != " ":
                jugando = False
            else:
                if movimientos == 9:
                    estado_ganador = "E"  # Empate
                    jugando = False
                else:
                    jugador_actual = "O" if jugador_actual == "X" else "X"
        
        
        limpiar_pantalla()
        print("=======================================")
        print("            FIN DEL JUEGO              ")
        print("=======================================\n")
        
        for f in range(3):
            print(f"        {tablero[f][0]} | {tablero[f][1]} | {tablero[f][2]}")
            if f < 2:
                print("       ---+---+---")
        print()
        
        if estado_ganador == "E":
            print("       ¡ES UN EMPATE!         ")
        else:
            print(f"   ¡FELICIDADES! EL JUGADOR [ {estado_ganador} ] GANA.")
        print("=======================================\n")
        
       
        respuesta_valida = False
        while not respuesta_valida:
            respuesta = input("¿Quieren jugar otra partida? (Si/No): ").strip().lower()
            
            if respuesta in ("si", "sí"):
                jugar_de_nuevo = True
                respuesta_valida = True
            elif respuesta == "no":
                jugar_de_nuevo = False
                respuesta_valida = True
                print("¡Gracias por jugar!.")
            else:
                print("- ERROR: Respuesta invalida. Escribe Si o No.")

if __name__ == "__main__":
    main()
