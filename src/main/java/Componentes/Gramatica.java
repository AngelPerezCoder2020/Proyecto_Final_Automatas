package Componentes;

import java.util.ArrayList;

public class Gramatica {
    private final ArrayList<Variable> regla;
    private final String nombre;
    public Gramatica(String nombre, ArrayList<Variable> regla){
        this.nombre=nombre;
        this.regla=regla;
    }
    public String getNombre(){
        return nombre;
    }
    public String GenerarPalabra(){
        String palabra = "";
        for(Variable x:regla){
            palabra+=x.getDerivacion();
        }
        return palabra;
    }
    public String getRegla(){
        String res = "";
        for(Variable x:regla){
            res+=" "+x.getNombre();
        }
        return res;
    }
}