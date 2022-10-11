package Componentes;

import java.util.ArrayList;
import java.util.Random;

public class Variable {
    private final Random generar = new Random();
    private final String nombre;
    private final char tipo;
    private final char letra;
    private final ArrayList<ArrayList<Variable>> derivaciones;
    
    public Variable(String nombre,char letra,char tipo,ArrayList<ArrayList<Variable>> derivaciones){
        this.nombre=nombre;
        this.letra=letra;
        this.tipo=tipo;
        this.derivaciones=derivaciones;
    }
    public String getNombre(){
        return nombre;
    }
    public char getTipo(){
        return tipo;
    }
    public String getDerivacion(){
        String res="";
        if(this.tipo=='T') res+=letra;
        else{
            ArrayList<Variable> u = derivaciones.get(generar.nextInt(derivaciones.size()));
            for(int y=0;y<u.size();y++){
                Variable s = u.get(y);
                res += s.getDerivacion();
            }
        }
        return res;
    }
    public ArrayList<ArrayList<Variable>> getDerivaciones() {
        return derivaciones;
    }
}