package Jarras_De_Agua;

import java.util.Random;

/**
 * Este programa simula un problema de jarras de agua, donde se deben realizar
 * una serie de movimientos para alcanzar cierta cantidad de agua en una de las jarras.
 * Se implementan reglas para llenar, vaciar y transferir agua entre las jarras.
 * @author Rogelio Perez Guevara
 * @version 08/03/2024
 */
public class Jarras_De_Agua {
    // Capacidad de las jarras
    static final int CAPACIDAD_JARRA_4 = 4;
    static final int CAPACIDAD_JARRA_3 = 3;
    // Meta a alcanzar en la jarra 4
    static final int META = 2;
    // Número total de reglas
    static final int NUMERO_REGLAS = 10;
    
    public static void main(String[] args)
    {
        int jarra4 = 0; // Estado inicial de la jarra de 4
        int jarra3 = 0; // Estado inicial de la jarra de 3
        int movimientos = 0; // Contador de movimientos realizados
        Random random = new Random();
        
        // Mostrar estado inicial
        System.out.println("Estado inicial: Jarra 4 = " + jarra4 + ", Jarra 3 = " + jarra3);
        // Mostrar estado meta
        System.out.println("Estado meta: Jarra 4 = " + META);
        
        // Realizar movimientos hasta alcanzar la meta
        while (jarra4 != META)
        {
            int regla;
            do
            {
                regla = random.nextInt(NUMERO_REGLAS) + 1; // Selecciona una regla al azar
            }
            while (!validarRegla(regla, jarra4, jarra3)); // Verificar si la regla es válida
            
            movimientos++; // Incrementar el contador de movimientos
            
            // Ejecutar la regla seleccionada
            switch (regla)
            {
                case 1:
                    jarra4 = llenarJarra4(jarra4);
                    break;
                case 2:
                    jarra3 = llenarJarra3(jarra3);
                    break;
                case 3:
                    jarra4 = tirarAlgoJarra4(jarra4);
                    break;
                case 4:
                    jarra3 = tirarAlgoJarra3(jarra3);
                    break;
                case 5:
                    jarra4 = tirarTodoJarra4(jarra4);
                    break;
                case 6:
                    jarra3 = tirarTodoJarra3(jarra3);
                    break;
                case 7:
                    int cantidad7 = vaciarJarra4AJarra3(jarra4, jarra3);
                    jarra4 -= cantidad7; 
                    jarra3 += cantidad7;
                    break;
                case 8:
                    int cantidad8 = vaciarJarra3AJarra4(jarra4, jarra3);
                    jarra4 += cantidad8;
                    jarra3 -= cantidad8;
                    break;
                case 9:
                    jarra3 = vaciarTodoJarra4AJarra3(jarra4, jarra3);
                    jarra3 += jarra4; // Sumar el contenido de la jarra 4 a la jarra 3
                    jarra4 -= jarra4; // Vaciar la jarra 4
                    break;
                case 10:
                    jarra4 = vaciarTodoJarra3AJarra4(jarra4, jarra3);
                    jarra4 += jarra3; // Sumar el contenido de la jarra 3 a la jarra 4
                    jarra3 -= jarra4; // Vaciar la jarra 3
                    break;
            }
            // Mostrar el estado después del movimiento
            System.out.println("Movimiento " + movimientos + ": Regla " + regla + ", Jarra 4 = " + jarra4 + ", Jarra 3 = " + jarra3);
        }
        // Mostrar mensaje de éxito al alcanzar la meta
        System.out.println("Se logró obtener " + META + " galones en la jarra de 4 en " + movimientos + " movimientos.");
    }
    
    // Método para validar si una regla es aplicable en el estado actual
    static boolean validarRegla(int regla, int jarra4, int jarra3)
    {
        switch (regla)
        {
            case 1:
                return jarra4 < CAPACIDAD_JARRA_4;
            case 2:
                return jarra3 < CAPACIDAD_JARRA_3;
            case 3:
                return jarra4 > 0;
            case 4:
                return jarra3 > 0;
            case 5:
                return jarra4 > 0;
            case 6:
                return jarra3 > 0;
            case 7:
                return (jarra4 + jarra3 >= CAPACIDAD_JARRA_3) && jarra4 > 0 && jarra3 < CAPACIDAD_JARRA_3;
            case 8:
                return (jarra4 + jarra3 >= CAPACIDAD_JARRA_4) && jarra3 > 0 && jarra4 < CAPACIDAD_JARRA_4;
            case 9:
                return (jarra4 + jarra3 <= CAPACIDAD_JARRA_3) && jarra4 > 0 && jarra3 < CAPACIDAD_JARRA_3;
            case 10:
                return (jarra4 + jarra3 <= CAPACIDAD_JARRA_4) && jarra3 > 0 && jarra4 < CAPACIDAD_JARRA_4;
            default:
                return false;
        }
    }
    
    // Métodos para realizar las acciones específicas en las jarras
    
    static int llenarJarra4(int jarra4)
    {
        return CAPACIDAD_JARRA_4;
    }
    
    static int llenarJarra3(int jarra3)
    {
        return CAPACIDAD_JARRA_3;
    }
    
    static int tirarAlgoJarra4(int jarra4)
    {
        Random random = new Random();
        int tirar = random.nextInt(jarra4) + 1;
        return jarra4 - tirar;
    }
    
    static int tirarAlgoJarra3(int jarra3)
    {
        Random random = new Random();
        int tirar = random.nextInt(jarra3) + 1;
        return jarra3 - tirar;
    }
    
    static int tirarTodoJarra4(int jarra4)
    {
        return 0;
    }
    
    static int tirarTodoJarra3(int jarra3)
    {
        return 0;
    }
    
    static int vaciarJarra4AJarra3(int jarra4, int jarra3)
    {
        return Math.min(jarra4, CAPACIDAD_JARRA_3 - jarra3);
    }
    
    static int vaciarJarra3AJarra4(int jarra4, int jarra3)
    {
        return Math.min(jarra3, CAPACIDAD_JARRA_4 - jarra4);
    }
    
    static int vaciarTodoJarra4AJarra3(int jarra4, int jarra3)
    {
        return 0;
    }
    
    static int vaciarTodoJarra3AJarra4(int jarra4, int jarra3)
    {
        return 0;
    }
}