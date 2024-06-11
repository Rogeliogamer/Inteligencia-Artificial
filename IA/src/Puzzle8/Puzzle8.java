package Puzzle8;

import java.util.*;

/**
 * Desarrolla un programa que simule el proceso inteligente 
 * para solucionar un problema de mundo de bloques o 
 * el acertijo de 8 posiciones utilizando búsqueda por el mejor nodo 
 * ambos con calificación del 100% correspondiente o 
 * bien el problema del agente viajero por ascensión de colinas 
 * con una calificación de 80% correspondiente.
 * @author Rogelio Perez Guevara
 * @version 06/05/2024
 */
class Nodo {
    int[][] estado;
    Nodo padre;
    List<Nodo> hijos;

    public Nodo(int[][] estado) {
        this.estado = estado;
        this.padre = null;
        this.hijos = new ArrayList<>();
    }

    public Nodo(int[][] estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
        this.hijos = new ArrayList<>();
    }
}

public class Puzzle8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el estado inicial del 8 puzzle (separado por espacios, use 0 para el espacio vacío):");
        int[][] estadoInicial = leerEstado(scanner);

        System.out.println("Ingrese el estado meta del 8 puzzle (separado por espacios, use 0 para el espacio vacío):");
        int[][] estadoMeta = leerEstado(scanner);

        Nodo raiz = new Nodo(estadoInicial);

        System.out.println("Solución");
        resolverPuzzle(raiz, estadoMeta);
        
        System.out.println("\nImprimiendo todo el arbol:");
        imprimirArbol(raiz, 0);
    }

    public static int[][] leerEstado(Scanner scanner) {
        int[][] estado = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                estado[i][j] = scanner.nextInt();
            }
        }
        return estado;
    }

    public static void resolverPuzzle(Nodo raiz, int[][] estadoMeta) {
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        Set<String> visitados = new HashSet<>();
        List<Nodo> soluciones = new ArrayList<>();

        while (!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();
            visitados.add(Arrays.deepToString(nodoActual.estado));

            if (Arrays.deepEquals(nodoActual.estado, estadoMeta)) {
                soluciones.add(nodoActual);
                continue; // No se generan más hijos para este nodo
            }

            if (!soluciones.isEmpty()) {
                // Si ya se encontró una solución, se evita generar más hijos
                continue;
            }

            generarHijos(nodoActual, cola, visitados);
        }

        if (soluciones.isEmpty()) {
            System.out.println("No se encontró solución.");
        } else {
            System.out.println("Soluciones encontradas:");
            for (Nodo solucion : soluciones) {
                mostrarSolucion(solucion);
                System.out.println();
            }
        }
    }

    public static void generarHijos(Nodo nodo, Queue<Nodo> cola, Set<String> visitados) {
        int[][] estadoActual = nodo.estado;
        int filaVacia = -1;
        int columnaVacia = -1;

        // Encontrar la posición del espacio vacío (0)
        outerloop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estadoActual[i][j] == 0) {
                    filaVacia = i;
                    columnaVacia = j;
                    break outerloop;
                }
            }
        }

        // Generar movimientos válidos intercambiando el espacio vacío con sus vecinos
        int[][] movimientos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Arriba, Abajo, Izquierda, Derecha

        for (int[] movimiento : movimientos) {
            int nuevaFila = filaVacia + movimiento[0];
            int nuevaColumna = columnaVacia + movimiento[1];

            if (nuevaFila >= 0 && nuevaFila < 3 && nuevaColumna >= 0 && nuevaColumna < 3) {
                int[][] nuevoEstado = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    nuevoEstado[i] = estadoActual[i].clone();
                }

                // Intercambiar el espacio vacío con el vecino
                nuevoEstado[filaVacia][columnaVacia] = estadoActual[nuevaFila][nuevaColumna];
                nuevoEstado[nuevaFila][nuevaColumna] = 0;

                // Verificar si el nuevo estado ya fue visitado
                if (!visitados.contains(Arrays.deepToString(nuevoEstado))) {
                    Nodo nuevoNodo = new Nodo(nuevoEstado, nodo);
                    nodo.hijos.add(nuevoNodo);
                    cola.add(nuevoNodo);
                }
            }
        }
    }

    public static void mostrarSolucion(Nodo nodo) {
        // Implementar la lógica para mostrar la solución encontrada a partir del nodo final
        // Esto implica seguir el camino desde el nodo final hasta la raíz (estado inicial)
        List<int[][]> solucion = new ArrayList<>();
        while (nodo != null) {
            solucion.add(0, nodo.estado);
            nodo = nodo.padre;
        }

        for (int[][] estado : solucion) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(estado[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static void imprimirArbol(Nodo nodo, int nivel) {
        if (nodo == null) return;

        // Imprimir el nodo actual con su nivel
        for (int i = 0; i < nivel; i++) {
            System.out.print("  "); // Espacio para el nivel
        }
        System.out.println("- Nivel " + nivel + " - Estado:");
        imprimirEstado(nodo.estado);

        // Imprimir los hijos recursivamente
        for (Nodo hijo : nodo.hijos) {
            imprimirArbol(hijo, nivel + 1);
        }
    }
    
    public static void imprimirEstado(int[][] estado) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(estado[i][j] + " ");
            }
            System.out.println();
        }
    }
}