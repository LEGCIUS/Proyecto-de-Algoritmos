/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoalgoritmos;

/**
 *
 * @author usuario
 */
//"C:/Users/metal/OneDrive/Documentos/NetBeansProjects/Tic-Tac-Toe/Main.java"   
//"C:/Users/metal/OneDrive/Documentos/rabito/AdministracionCentroEducativo.java"
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta es la clase encargada del proecto de algoritmos
 */
public class ProyectoAlgo {

    private Nodo cabeza;

    public static void main(String[] args) {
        ProyectoAlgo proyecto = new ProyectoAlgo();
        String[] initialMethods = {"public static", "public void", 
            "private static", "private void"};

        try ( BufferedReader br = new BufferedReader(new FileReader("C:/Users"
                + "/usuario/Downloads/Archivo.java"))) {
            String linea;
            StringBuilder metodoCompleto = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                for (String clave : initialMethods) {
                    if (linea.contains(clave)) {
                        metodoCompleto.setLength(0);
                        metodoCompleto.append(linea).append("\n");

                        while (!verificarEquilibrio(metodoCompleto.toString())) {
                            linea = br.readLine();
                            if (linea == null) {
                                break;
                            }
                            metodoCompleto.append(linea).append("\n");
                        }

                        proyecto.insertarNodo(metodoCompleto.toString());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        proyecto.mostrarNodos();

        Nodo nodoSeleccionado = proyecto.seleccionarNodo();
        if (nodoSeleccionado != null) {
            System.out.println("Nodo seleccionado:\n" + nodoSeleccionado.dato);
            int conteoFor = proyecto.contarBuclesFor(nodoSeleccionado.dato);
            System.out.println("Numero de bucles 'for' en el nodo seleccionado: " + conteoFor);
            proyecto.mostrarComplejidad(conteoFor);
        }
    }

    /**
     * Método para verificar el equilibrio de paréntesis, llaves y corchetes en
     * una expresión
     */
    public static boolean verificarEquilibrio(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (caracter == '(' || caracter == '{' || caracter == '[') {
                pila.push(caracter);
            } else if (caracter == ')' || caracter == '}' || caracter == ']') {
                if (pila.isEmpty()) {
                    return false; 
                }

                char tope = pila.pop();
                if ((caracter == ')' && tope != '(')
                        || (caracter == '}' && tope != '{')
                        || (caracter == ']' && tope != '[')) {
                    return false; 
                }
            }
        }

        return pila.isEmpty(); 
    }

    /**
     * Este metodo insert el no en l list circular
     */
    public void insertarNodo(String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != cabeza) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
            nuevoNodo.siguiente = cabeza;
        }
    }

    /**
     * Este metodo muestra los nodos que se encuentran en la lista circular con
     * un respectivo indice
     */
    public void mostrarNodos() {
        if (cabeza == null) {
            System.out.println("La lista está vacia.");
            return;
        }

        Nodo temp = cabeza;
        int index = 1;
        do {
            System.out.println(index + ". Nodo:\n" + temp.dato);
            temp = temp.siguiente;
            index++;
        } while (temp != cabeza);
    }

    /**
     * Este metodo permite seleccionar al usuario el nodo que desea en base a su
     * indice
     */
    public Nodo seleccionarNodo() {
        if (cabeza == null) {
            System.out.println("La lista esta vacia.");
            return null;
        }
        int Opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese nodito que desea ");
            int numero = scanner.nextInt();

            Nodo temp = cabeza;
            int index = 1;
            do {
                if (index == numero) {
                    return temp;
                }
                temp = temp.siguiente;
                index++;
            } while (temp != cabeza);

            System.out.println("Numero de nodo no válido.");
            return null;
        } while (Opcion == 0);
    }

    /**
     * Este metodo cuenta los for que tiene el nodo selecciondo por el usurio
     */
    public int contarBuclesFor(String texto) {
        int indiceFor = texto.indexOf("for");
        if (indiceFor == -1) {
            return 0;
        } else {
            return 1 + contarBuclesFor(texto.substring(indiceFor + 3));
        }
    }

    /**
     * Este metodo muestra la coplejidad lgoritmic del nodo
     */
    public void mostrarComplejidad(int conteoBucles) {
        String complejidad;
        if (conteoBucles == 0) {
            complejidad = "O(1), es decir constante";
        } else if (conteoBucles == 1) {
            complejidad = "O(n), es decir lineal";
        } else if (conteoBucles == 2) {
            complejidad = "O(n^2) este es elevado a la matrix, a la cuadrada";
        } else {
            complejidad = "O(n^" + conteoBucles + ") - Polinomica de grado " + conteoBucles;
        }
        System.out.println("Complejidad: " + complejidad);
    }
}
