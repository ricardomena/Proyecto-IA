package proyia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author invitado
 */
public class Grafo2 {
    
    public HashSet<Nodo> nodos;
    
    public HashSet<Nodo> obtenerNodos() {
        return nodos;
    }
    
    public void agregarNodo(Nodo n) {
        nodos.add(n);
    }
    
    public Nodo buscarNodo(String nombre) {
        for (Nodo x:nodos) {
            if (x.obtenerNombre().equals(nombre)) {
                return x;
            }
        }
        return null;
    }
    
    public void cargar(String ruta) throws FileNotFoundException {
        Scanner input = new Scanner ( new File (ruta));
        Nodo n;
        Nodo n2;
        double d;
        Arista a;
        Arista a2;
        int lineas;
        while (input.hasNext()) {
            switch (input.next()) {
                case "v":
                    {
                        n = new Nodo(input.next());
                        lineas = Integer.parseInt(input.next());
                        
                        while(lineas > 10){
                            n.agregarLinea(lineas % 10);
                            lineas = lineas/10;
                        }
                        n.agregarLinea(lineas);
                        this.agregarNodo(n);
                        break;
                    }
                case "e":
                    {
                        n = this.buscarNodo(input.next());
                        n2 = this.buscarNodo(input.next());
                        d = Double.parseDouble(input.next());
                        a = new Arista(n,d);
                        n2.agregarAdyacente(a);
                        a2 = new Arista(n2,d);
                        n.agregarAdyacente(a2);
                        break;
                    }
            }
        }
    }
    
    public ArrayList<String> aEstrella (String inicio, String fin){
        Nodo n = this.buscarNodo(inicio);
        Nodo f = this.buscarNodo(fin);
        Nodo aux;
        int costoCambio12 = 10;
        int costoCambio13 = 10;
        int costoCambio23 = 10;
        ArrayList<Integer> lineas = null;
        int linea = -1;
        ArrayList<String> finals = new ArrayList<String>();
        ArrayList<Nodo> abiertos = new ArrayList<Nodo>();
        List<String> cerrados = new ArrayList<String>();
        n.peso= 0;
        n.padre = null;
        
        if(n.estaciones.size()==1){
            linea = n.estaciones.get(0);
        }
        else{
            lineas = n.estaciones;
        }
        
        if(f.equals(null)){
            System.out.println("Nodo final no encontrado");
            return null;
        }
        if(n.equals(null)){
            System.out.println("Nodo inicio no encontrado");
            return null;
        }
        
        while(! f.val.equals(n.val)){
            for(Arista x:n.adj){
                aux = x.nodo;
                if (! cerrados.contains(aux.val)){
                    if (lineas == null){
                        if(aux.estaciones.contains(linea)){
                            aux.peso = n.peso + x.distancia;
                        }
                        else{
                            if(linea == 1 && aux.estaciones.get(0)==2){
                                aux.peso = n.peso + x.distancia + costoCambio12;
                            }
                            else if(linea == 2 && aux.estaciones.get(0)==1){
                                aux.peso = n.peso + x.distancia + costoCambio12;
                            }
                            else if(linea == 1 && aux.estaciones.get(0)==3){
                                aux.peso = n.peso + x.distancia + costoCambio13;
                            }
                            else if(linea == 3 && aux.estaciones.get(0)==1){
                                aux.peso = n.peso + x.distancia + costoCambio13;
                            }
                            else if(linea == 2 && aux.estaciones.get(0)==3){
                                aux.peso = n.peso + x.distancia + costoCambio23;
                            }
                            else if(linea == 3 && aux.estaciones.get(0)==2){
                                aux.peso = n.peso + x.distancia + costoCambio23;
                            }
                            
                        }
                        aux.padre= n.val;
                        abiertos.add(aux);
                    }
                    else{
                        aux.peso = n.peso + x.distancia;
                        aux.padre= n.val;
                        abiertos.add(aux);
                        lineas = null;
                        linea = aux.estaciones.get(0);
                    }
                }
            }
            Collections.sort(abiertos, new Nodo());
            cerrados.add(n.val);
            n = abiertos.get(0);
            abiertos.remove(0);
        }
        while(! n.val.equals(inicio)){
            finals.add(n.val);
            n=this.buscarNodo(n.padre);
        }
        finals.add(n.val);
        //List<ArrayList<String>> Resp = new ArrayList<ArrayList<String>>;
        return finals;
    }
    
    public Grafo2() {
        nodos = new HashSet<>();
    }
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
}
