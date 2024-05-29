/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoalgoritmos;

/**
 *
 * @author usuario
 */

/**
 * Aqui solo se crea la clase nodo la cual crea el nodo y genera un cosntructor
 * de la clsae
 */
public class Nodo {

    String dato;
    Nodo siguiente;

    /**
     * Constructor de la clase
     */
    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = this;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
