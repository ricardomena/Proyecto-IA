/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyia;
import java.util.*;

import java.util.HashSet;

/**
 *
 * @author invitado
 */
public class Nodo  implements Comparator<Nodo>, Comparable<Nodo>{
    public String val;
    public String padre;
    public double peso;
    public HashSet<Arista> adj;
    
    public Nodo(String s) {
        val = s;
        adj = new HashSet<>();
        padre= null;
    }
    Nodo(){
    }
    
    public Nodo(String s, HashSet<Arista> adyacentes) {
        val = s;
        adj = adyacentes;
        padre= null;
    }
          
    public String obtenerNombre() {
        return this.val;
    }
    
    public void agregarAdyacente(Arista x) {
        this.adj.add(x);
    }
    
    public void agregarAdyacentes(HashSet<Arista> xs) {
        this.adj.addAll(xs);
    }
    
    public void imprimirNod(){
        String imp = this.val;
        for(Arista a:adj){
            imp += "\n "+a.nodo.val+" "+Double.toString(a.distancia);
        }
        System.out.println(imp+"\n");
    }
    
    @Override
    public int compare(Nodo o1, Nodo o2) {
          return Double.compare(o1.peso, o2.peso);
    }

    @Override
    public int compareTo(Nodo o) {
        return (this.val).compareTo(o.val);
    }
}
