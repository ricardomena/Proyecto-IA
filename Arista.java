/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyia;

/**
 *
 * @author Ricardo
 */
public class Arista {
    public final Nodo nodo;
    public final double distancia;
    
    Arista(Nodo n, double d) {
        this.nodo = n;
        this.distancia = d;
    }
    
    Arista(Nodo n, double d, double tra, double esc) {
        this.nodo = n;
        this.distancia = d;
    }
}
