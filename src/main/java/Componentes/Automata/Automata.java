package Componentes.Automata;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automata {
    private String nombre;
    private char[] palabra=null;
    private int contador = 0;
    private Estado estadoFinal;
    private Estado estadoInicial;
    private Estado estadoActual;
    private ArrayList<Estado> estados;
    
    public Automata(String nombre){
        this.nombre=nombre;
        estados = new ArrayList<>();
    }
    public void CrearEstado(){
        Estado nuevo = new Estado(contador);
        estados.add(nuevo);
        contador++;
    }
    public boolean ComprobarPalabra(String palabra){
        this.palabra=palabra.toCharArray();
        Boolean aceptada=true;
        for(int x=0;x<this.palabra.length;x++){
            Boolean si = ComprobarLetra(this.palabra[x]);
            if(si){
                estadoActual = estadoActual.getTransicionUsada().getDestino();
            }else{
                aceptada=false;
                break;
            }
        }
        if(aceptada&&estadoFinal==estadoActual){
            estadoActual=estadoInicial;
            return true;
        }
        estadoActual=estadoInicial;
        return false;
    }
    public boolean ComprobarLetra(char w){
        return estadoActual.comprobarLetra(w);
    }
    public boolean EstadosPrincipales(int y, int x){
        Estado existe = estados.get(x);
        if(y==1){
            estadoInicial = existe;
            estadoActual = estadoInicial;
            return true;
        }else if(y==2){
            estadoFinal = existe;
            return true;
        }
        return false;
    }
    public String getNombre(){
        return nombre;
    }
    public ArrayList<Estado> getEstados(){
        return estados;
    }
    
    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }
}