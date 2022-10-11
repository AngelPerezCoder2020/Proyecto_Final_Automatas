package Componentes.GramaticaL;

import java.util.ArrayList;
import java.util.Random;

public class NoTerminal extends Variable{
    private final Random generar = new Random();
    private final ArrayList<ArrayList<Variable>> derivaciones;
    
    public NoTerminal(String nombre, ArrayList<ArrayList<Variable>> derivaciones){
        super(nombre);
        this.derivaciones=derivaciones;
    }
    @Override
    public String getDerivacion() {
        String res="";
        ArrayList<Variable> u = derivaciones.get(generar.nextInt(derivaciones.size()));
        for(int y=0;y<u.size();y++){
            Variable s = u.get(y);
            res += s.getDerivacion();
        }
        return res;
    }
    public ArrayList<ArrayList<Variable>> getDerivaciones() {
        return derivaciones;
    }
}