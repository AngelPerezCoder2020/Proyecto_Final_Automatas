package Componentes;

public class Transicion {
    private char letra;
    private Estado destino;
    public Transicion(char letra,Estado destino){
        this.letra=letra;
        this.destino=destino;
    }
    public char getLetra(){
        return letra;
    }
    public Estado getDestino(){
        return destino;
    }
}