package Componentes;

import java.util.ArrayList;

public class Estado {
    private int numero;
    private int trs;
    private ArrayList<Transicion> transiciones;
    
    public Estado(int numero){
        transiciones = new ArrayList<>();
        this.numero=numero;
    }   
    public void addTransicion(char l,Estado d){
        Transicion nuevo = new Transicion(l,this,d);
        transiciones.add(nuevo);
    }
    public Transicion getTransicionUsada(){
        return transiciones.get(trs);
    }
    public boolean comprobarLetra(char w){
        for(int x=0;x<transiciones.size();x++){
            if(transiciones.get(x).getLetra()==w){
                trs = x;
                return true;
            }
        }
        return false;
    }
    public int getNumero(){
        return numero;
    }
    public ArrayList<Transicion> getTransaccinoes(){
        return transiciones;
    }
}