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
    public char getLetra(){
        return letra;
    }
    public String getDerivacion(){
        String res="";
        ArrayList<Variable> u = derivaciones.get(generar.nextInt(derivaciones.size()-1));
        for(int y=0;y<u.size();y++){
            Variable s = u.get(y);
            if(s.getTipo()=='T'){ 
               res += String.valueOf(s.getLetra()); 
            }else res += s.getDerivacion();
        }
        return res;
    }
    public ArrayList<ArrayList<Variable>> getDerivaciones() {
        return derivaciones;
    }
}